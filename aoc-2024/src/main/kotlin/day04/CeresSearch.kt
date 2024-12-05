package day04

import utils.Coordinate
import utils.readGrid
import utils.readInputLines

class CeresSearch(private val input: Map<Coordinate, String>) {

    fun part1(): Int {
        return input.filter { it.value == "X" }
            .map { isXmas(it) }
            .sum()
    }

    fun part2(): Int {
        return input.filter { it.value == "A" }
            .filter { isXMas(it) }
            .size
    }

    fun isXmas(cell: Map.Entry<Coordinate, String>): Int {
        return Direction.entries.filter { direction ->
            val adjacentLetters = (1..3).joinToString("") {
                input.getOrDefault(cell.key + (direction.coordinateDelta * it), "")
            }
            adjacentLetters == "MAS"
        }.size
    }

    fun isXMas(cell: Map.Entry<Coordinate, String>): Boolean {
        val (nw, se, ne, sw) = listOf(Direction.NW, Direction.SE, Direction.NE, Direction.SW)
            .map { input.getOrDefault(cell.key + it.coordinateDelta, "") }
        val slash1 = "$nw$se"
        val slash2 = "$ne$sw"
        return (slash1 == "MS" || slash1 == "SM") && (slash2 == "MS" || slash2 == "SM")
    }

    enum class Direction(val direction: String, val coordinateDelta: Coordinate) {
        E("e", Coordinate(1, 0)),
        SE("se", Coordinate(1, 1)),
        SW("sw", Coordinate(-1, 1)),
        W("w", Coordinate(-1, 0)),
        NW("nw", Coordinate(-1, -1)),
        NE("ne", Coordinate(1, -1)),
        S("s", Coordinate(0, 1)),
        N("n", Coordinate(0, -1));
    }
}

fun main() {
    val ceresSearch = CeresSearch(readGrid(readInputLines(4)))
    println(ceresSearch.part1())
    println(ceresSearch.part2())
}
