package day16

import utils.*
import utils.Direction.*

const val WALL = "#"
const val EMPTY = "."
const val START = "S"

class ReindeerMaze(private val map: Map<Coordinate, String>) {
    fun part1(): Int {
        val startingPoint = map.entries.first { it.value == "S" }.key
        val startingLocation = Location(startingPoint, E)
        val edges = buildEdges(setOf(startingLocation), emptySet(), mutableListOf())
        val graph = Graph(edges, true, false)
        graph.dijkstra(startingLocation.toString())
        val endPoint = map.entries.first { it.value == "E" }.key
//        graph.printPath(Location(endPoint, N).toString())
        return Direction.entries.mapNotNull {
            graph.getWeightToPathOrNull(Location(endPoint, it).toString())
        }.min()
    }

    private tailrec fun buildEdges(cellsToExplore: Set<Location>, cellsExplored: Set<Location>, edges: MutableList<Edge<String>>): List<Edge<String>> {
        if (cellsToExplore.isEmpty()) {
            return edges
        }

        val cellToExplore = cellsToExplore.first()
        if (map[cellToExplore.position] == "E") {
            return buildEdges(cellsToExplore - cellToExplore, cellsExplored + cellToExplore, edges)
        }

        val nextCells = cellsToExplore.toMutableSet()
        nextCells.remove(cellToExplore)
        edges.addAll(getAvailableDirections(cellToExplore.direction)
            .filter {
                val cellValue = map[cellToExplore.position + it.coordinateDelta]
                cellValue != WALL
            }
            .map {
                val mustRotate = it != cellToExplore.direction
                val cost = if (mustRotate) 1001 else 1
                val newLocation = Location(cellToExplore.position + it.coordinateDelta, it)
                nextCells.add(newLocation)
                Edge(cellToExplore.toString(), newLocation.toString(), cost)
            })
        nextCells.removeAll(cellsExplored)

        return buildEdges(nextCells, cellsExplored + cellToExplore, edges)
    }

    fun getAvailableDirections(currentDirection: Direction) = when (currentDirection) {
        N -> listOf(W, N, E)
        E -> listOf(N, E, S)
        S -> listOf(E, S, W)
        W -> listOf(S, W, N)
    }

}

fun main() {
    val reindeermaze = ReindeerMaze(readGrid(readInputLines(16)))
    println(reindeermaze.part1())
    //println(reindeermaze.part2())
}
