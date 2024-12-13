package day06

import utils.Coordinate
import utils.readInputLines
import utils.splitToString

class GuardGallivant(private val input: List<String>) {

    fun parseMap() = input.flatMapIndexed { rowIdx, line ->            line.splitToString()
                .withIndex()
                .associate { (colIdx, value) ->
                    Pair(Coordinate(colIdx, rowIdx), value)
                }.entries
        }.associate { it.key to it.value }

    private fun runSimulation(map: Map<Coordinate, String>, startingPosition: Position): Set<Coordinate> {
        var currentPosition = startingPosition
        val placesVisited = mutableSetOf<Coordinate>()
        while (map.containsKey(currentPosition.currentCell)) {
            placesVisited.add(currentPosition.currentCell)
            currentPosition = currentPosition.move(map)
        }

        return placesVisited
    }

    fun containsLoop(map: Map<Coordinate, String>, startingPosition: Position): Boolean {
        var currentPosition = startingPosition
        val placesVisited = mutableSetOf<Position>()
        while (map.containsKey(currentPosition.currentCell)) {
            println("${currentPosition.currentCell.x} ${currentPosition.currentCell.y}")
            if (!placesVisited.add(currentPosition)) {
                return true
            }
            currentPosition = currentPosition.move(map)
        }

        return false
    }

    fun part1(): Int {
        val map = parseMap()
        val startingPosition = Position(map.filterValues { it == "^" }.keys.first(), Direction.N)
        return runSimulation(map, startingPosition).size
    }

    fun part2(): Long {
        // run initial simulation to find potential spots to place obstruction
        val originalMap = parseMap()
        val startingPosition = Position(originalMap.filterValues { it == "^" }.keys.first(), Direction.N)
        val possiblePositions = runSimulation(originalMap, startingPosition) - startingPosition.currentCell

        return possiblePositions.parallelStream().map { position ->
            val map = originalMap.toMutableMap()
            map[position] = "#"
            containsLoop(map, startingPosition).also { if (it) println("${position.x} ${position.y}" ) }
        }.filter { it }.count()
    }

    data class Position(val currentCell: Coordinate, private val currentDirection: Direction) {
        fun move(map: Map<Coordinate, String>): Position {
            return if (map[currentCell + currentDirection.coordinateDelta] == "#") {
                val newDirection = when(currentDirection) {
                    Direction.N -> Direction.E
                    Direction.E -> Direction.S
                    Direction.S -> Direction.W
                    Direction.W -> Direction.N
                }
                val newPosition = Position(currentCell + newDirection.coordinateDelta, newDirection)
                val newMap = map.toMutableMap()
                newMap[newPosition.currentCell] = newDirection.direction
                newPosition
            } else {
                Position(currentCell + currentDirection.coordinateDelta, currentDirection)
            }
        }
    }

    enum class Direction(val direction: String, val coordinateDelta: Coordinate) {
        N("^", Coordinate(0, -1)),
        E(">", Coordinate(1, 0)),
        S("v", Coordinate(0, 1)),
        W("<", Coordinate(-1, 0));
    }
}

fun main() {
    val guardGallivant = GuardGallivant(readInputLines(6))
    println(guardGallivant.part1())
    println(guardGallivant.part2())
}
