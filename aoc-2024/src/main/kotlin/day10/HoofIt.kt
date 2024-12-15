package day10

import utils.Coordinate
import utils.readGrid
import utils.readInputLines

class HoofIt(input: Map<Coordinate, String>) {
    val map = input.mapValues { it.value.toInt() }

    fun part1(): Int {
        // find all the trailheads
        val trailHeads = map.filterValues { it == 0 }
        return trailHeads.keys.sumOf {
            calculateScore(it).size
        }
    }

    fun calculateScore(currentLocation: Coordinate): Set<Coordinate> {
        if (map.getValue(currentLocation) == 9) {
            return setOf(currentLocation)
        }
        val trailsToExplore = currentLocation.getAdjacentCoordinates()
            .filter { map.containsKey(it) }
            .filter { map[it] == map.getValue(currentLocation) + 1 }

        return trailsToExplore.flatMap { calculateScore(it) }.toSet()
    }

    fun part2(): Long {
        // find all the trailheads
        val trailHeads = map.filterValues { it == 0 }
        return trailHeads.keys.sumOf {
            calculateScoreForDistinctRoutes(it)
        }
    }

    fun calculateScoreForDistinctRoutes(currentLocation: Coordinate): Long {
        if (map.getValue(currentLocation) == 9) {
            return 1
        }
        val trailsToExplore = currentLocation.getAdjacentCoordinates()
            .filter { map.containsKey(it) }
            .filter { map[it] == map.getValue(currentLocation) + 1 }

        return trailsToExplore.sumOf { calculateScoreForDistinctRoutes(it) }
    }
}

fun main() {
    val hoofIt = HoofIt(readGrid(readInputLines(10)))
    println(hoofIt.part1())
    println(hoofIt.part2())
}
