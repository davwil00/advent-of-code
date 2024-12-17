package day15

import utils.Coordinate
import utils.printGrid
import utils.readGrid
import utils.readInputLines

private const val BOX = "O"
private const val ROBOT = "@"
private const val WALL = "#"
private const val BLANK_SPACE = "."

class WarehouseWoes(input: List<String>) {
    private val initialGrid = readGrid( input.takeWhile { it.startsWith(WALL) })
    private val directions = input.dropWhile { it.startsWith(WALL) }.joinToString("")

    fun part1(): Int {
        var robotPosition = initialGrid.entries.first { it.value == ROBOT }.key
        val map = initialGrid.toMutableMap()

        directions.forEach { direction ->
            robotPosition = move(robotPosition, direction, map)
//            printGrid(map)
//            println()
        }

        return getGpsCoordinates(map)
    }

    private fun getGpsCoordinates(map: Map<Coordinate, String>): Int {
        return map.filterValues { it == BOX }.keys.sumOf { coordinate ->
            100 * coordinate.y + coordinate.x
        }
    }

    private fun move(robotPosition: Coordinate, direction: Char, map: MutableMap<Coordinate, String>): Coordinate {
        val coordinate = when(direction) {
            '^' -> Coordinate(0, -1)
            '>' -> Coordinate(1, 0)
            'v' -> Coordinate(0, 1)
            '<' -> Coordinate(-1, 0)
            else -> throw IllegalStateException("Unknown direction $direction")
        }
        val destination = robotPosition + coordinate
        return when (map.getValue(destination)) {
            WALL -> robotPosition
            BLANK_SPACE -> {
                map[robotPosition] = BLANK_SPACE
                map[destination] = ROBOT
                destination
            }
            BOX -> {
                // find next blank space
                // find next wall
                var nextFreeSpaceOrWall = destination
                while (map[nextFreeSpaceOrWall] != WALL && map[nextFreeSpaceOrWall] != BLANK_SPACE) {
                    nextFreeSpaceOrWall += coordinate
                }

                if (map[nextFreeSpaceOrWall] == WALL) {
                    robotPosition
                } else {
                    // if blank space < wall, blank space = 0, dest = robot
                    // else can't move so nothing changes
                    map[robotPosition] = BLANK_SPACE
                    map[destination] = ROBOT
                    map[nextFreeSpaceOrWall] = BOX
                    destination
                }
            }
            else -> throw IllegalStateException("Encountered ${map.getValue(destination)}")
        }
    }
}

fun main() {
    val warehousewoes = WarehouseWoes(readInputLines(15))
    println(warehousewoes.part1())
    //println(warehousewoes.part2())
}
