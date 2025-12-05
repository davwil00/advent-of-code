package day04

import utils.Coordinate
import utils.readGrid
import utils.readInputLines

class PrintingDepartment(input: List<String>) {

    private val grid = readGrid(input)

    fun part1(): Int {
        val rollPositions = grid.filterValues { it == "@" }
        return rollPositions.count { (coordinate, _) ->
            canRemoveRoll(coordinate, rollPositions)
        }
    }

    fun part2(): Int {
        val rollPositions = grid.filterValues { it == "@" }
        return countRollsThatCanBeRemoved(rollPositions, 0)
    }

    private tailrec fun countRollsThatCanBeRemoved(rolls: Map<Coordinate, String>, totalRollsRemoved: Int): Int {
        val rollsToRemove = rolls.filter { coordinate ->
            canRemoveRoll(coordinate.key, rolls)
        }

        if (rollsToRemove.isEmpty()) {
            return totalRollsRemoved
        }

        return countRollsThatCanBeRemoved(
            rolls.filterKeys { !rollsToRemove.containsKey(it) },
            totalRollsRemoved + rollsToRemove.size
        )
    }

    private fun canRemoveRoll(
        coordinate: Coordinate,
        rollPositions: Map<Coordinate, String>
    ): Boolean {
        val adjacentPositions = coordinate.getAdjacentCoordinatesIncludingDiagonals()
        return adjacentPositions.count { it in rollPositions } < 4
    }
}

fun main() {
    val printingDepartment = PrintingDepartment(readInputLines(4))
    println(printingDepartment.part1())
    println(printingDepartment.part2())
}
