package day08

import utils.Coordinate
import utils.generateCombinations
import utils.printGrid
import utils.readGrid
import utils.readInputLines
import java.math.BigDecimal
import java.math.RoundingMode

class ResonantColinearity(private val grid: Map<Coordinate, String>) {

    fun part1(): Int {
        val frequencies = grid.values.toSet().filterNot{ it == "."}
        return frequencies.flatMap { findAntinodes(it) }.toSet().size
    }

    /**
     * for each node of the same type on the map,
     * find the location an antinode *could* be by calculating the distance
     */
    fun findAntinodes(frequency: String): Set<Coordinate> {
        val nodeLocations = generateCombinations(
            grid.filterValues { it == frequency }
                .keys
                .toList()
        )
        val antinodes = buildSet<Coordinate> {
            nodeLocations.forEach { (n1, n2) ->
                val delta = n1 - n2
                val possibleLocations = setOf(n1 + delta, n1 - delta, n2 + delta, n2 - delta)
                    .filter { grid.containsKey(it) && grid[it] != frequency }
                addAll(possibleLocations)
            }
        }
        return antinodes
    }

    fun part2(): Int {
        val frequencies = grid.values.toSet().filterNot{ it == "."}
//        return frequencies.flatMap { findAntinodesWithUpdatedModel(it) }.toSet().size
        val antinodeLocations = frequencies.flatMap { findAntinodesWithUpdatedModel(it) }.toSet()
        val updatedGrid = grid.toMutableMap()
        antinodeLocations.forEach { antinode -> if (grid[antinode] == ".") updatedGrid[antinode] = "#" }
        printGrid(updatedGrid)
        return antinodeLocations.size
    }

    /**
     * for each node of the same type on the map,
     * find the location an antinode *could* be by calculating the distance
     */
    fun findAntinodesWithUpdatedModel(frequency: String): Set<Coordinate> {
        // find location of all antennas with the given frequency
        val antennaLocations = grid.filterValues{ it == frequency}
        val combinations = generateCombinations(antennaLocations.keys.toList())

        // check each grid position (maybe)
        val antinodes = grid.keys.filter { coordinate ->
            // for each combination of antenna locations, calculate the gradient and check if the coordinate is on that line
            combinations.any { (n1, n2) ->
                // slope intercept
                when {
                    n1.x == n2.x && coordinate.x == n1.x -> true
                    n1.y == n2.y && coordinate.y == n1.y -> true
                    else -> {
                        val a = (n2.y - n1.y).toDouble() / (n2.x - n1.x)
                        val b = n1.y - a * n1.x
                        // y = ax +b
                        coordinate.y.toBigDecimal().setScale(5) == ((a * coordinate.x).roundTo(5) + b.roundTo(5))
                    }
                }
            }
        }

        return antinodes.toSet()
    }
}

private fun Double.roundTo(scale: Int): BigDecimal = BigDecimal(this).setScale(scale, RoundingMode.HALF_UP)

fun main() {
    val resonantColinearity = ResonantColinearity(readGrid(readInputLines(8)))
    println(resonantColinearity.part1())
    println(resonantColinearity.part2())
}
