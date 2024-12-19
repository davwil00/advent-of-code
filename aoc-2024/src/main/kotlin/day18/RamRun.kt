package day18

import utils.*

class RamRun(private val input: List<String>, private val gridSize: Int = 71) {
    private val startPoint = Coordinate(0, 0)
    private val endPoint = Coordinate(gridSize - 1, gridSize - 1)

    fun part1(bytesToSimulate: Int = 1024): Int {
        val grid = buildGrid(bytesToSimulate)
        val edgeList = buildEdges(grid, setOf(startPoint))
        val graph = Graph(edgeList, true)
        graph.dijkstra(startPoint.toString())
        return graph.getWeightToPath(endPoint.toString())
    }

    /**
     * Use a binary search to find the answer quicker
     */
    fun part2(lowerBound: Int = 1024): String {
        var range = (lowerBound until input.size)
        do {
            val bytesToSimulate = range.midpoint()
            val grid = buildGrid(bytesToSimulate).toMutableMap()
            range = if (navigate(grid, setOf(startPoint))) {
                bytesToSimulate + 1 .. range.last
            } else {
                range.first until bytesToSimulate
            }
        } while (range.size() > 1)

        return input[range.first - 1]
    }

    private fun IntRange.midpoint() = last - (last - first) / 2
    private fun IntRange.size() = last - first + 1

    private fun buildGrid(bytesToSimulate: Int): Map<Coordinate, String> {
        val byteLocations = input
            .take(bytesToSimulate)
            .map { line -> line.split(",").map { it.toInt() } }
            .map { Coordinate(it[0], it[1]) }

        return (0 until gridSize).flatMap { y ->
            (0 until gridSize).map { x ->
                val coordinate = Coordinate(x, y)
                coordinate to if (byteLocations.contains(coordinate)) "#" else "."
            }
        }.toMap()
    }

    private tailrec fun buildEdges(
        grid: Map<Coordinate, String>,
        coordinatesToVisit: Set<Coordinate>,
        visitedCoordinates: Set<Coordinate> = emptySet(),
        edges: MutableList<Edge<String>> = mutableListOf()
    ): List<Edge<String>> {
        if (coordinatesToVisit.isEmpty()) {
            return edges
        }

        val currentCoordinate = coordinatesToVisit.first()
        val nextCoordinates = currentCoordinate.getAdjacentCoordinates()
            .filter { grid[it] == "." }
            .filterNot { visitedCoordinates.contains(it) }
        edges.addAll(nextCoordinates.map { Edge(currentCoordinate.toString(), it.toString(), 1) })

        return buildEdges(
            grid,
            coordinatesToVisit - currentCoordinate + nextCoordinates,
            visitedCoordinates + currentCoordinate,
            edges
        )
    }

    private tailrec fun navigate(
        grid: Map<Coordinate, String>,
        coordinatesToVisit: Set<Coordinate>,
        visitedCoordinates: Set<Coordinate> = emptySet()
    ): Boolean {
        if (coordinatesToVisit.isEmpty()) {
            return false
        }
        val currentCoordinate = coordinatesToVisit.first()
        val nextCoordinates = currentCoordinate.getAdjacentCoordinates()
            .filter { grid[it] == "." }
            .filterNot { visitedCoordinates.contains(it) }
        if (nextCoordinates.contains(endPoint)) {
            return true
        }

        return navigate(
            grid,
            coordinatesToVisit - currentCoordinate + nextCoordinates,
            visitedCoordinates + currentCoordinate
        )
    }
}

fun main() {
    val ramRun = RamRun(readInputLines(18))
    println(ramRun.part1())
    println(ramRun.part2())
}
