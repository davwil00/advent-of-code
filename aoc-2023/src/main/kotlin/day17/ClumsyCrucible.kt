package day17

import utils.*

class ClumsyCrucible(val input: List<String>) {

    fun part1(): Int {
        val edges = input.flatMapIndexed { y, row ->
            row.splitToString().flatMapIndexed { x, heatLoss ->
                val block = Coordinate(x, y)
                block.getAdjacentCoordinates()
                    .filter { it.x < row.length && it.y < input.size }
                    .map { coordinate -> Edge(coordinate.toString(), block.toString(), heatLoss.toInt()) }
            }
        }

        val startingCoordinate = Coordinate(0, 0)
        val graph = Graph(edges, true)
        graph.dijkstra(startingCoordinate.toString())

        return graph.getWeightToPath(Coordinate(input.size - 1, input[0].length - 1).toString())
    }
}

fun main() {
    val clumsycrucible = ClumsyCrucible(readInputLines(17))
    //println(clumsycrucible.part1())
    //println(clumsycrucible.part2())
}
