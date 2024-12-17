package day14

import utils.Coordinate
import utils.productOf
import utils.readInputLines
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import java.io.File

class RestroomRedoubt(input: List<String>, private val gridSize: Pair<Int, Int>) {
    private val inputRegex = Regex("""p=(\d+),(\d+) v=(-?\d+),(-?\d+)""")
    private val robots = parseInput(input)

    fun parseInput(input: List<String>): List<Robot> {
        return input.map {
            val (px, py, vx, vy) = inputRegex.matchEntire(it)!!.destructured
            Robot(Coordinate(px.toInt(), py.toInt()), Coordinate(vx.toInt(), vy.toInt()))
        }
    }

    fun part1(): Long {
        var updatedRobots = robots.toList()
        repeat(100) {
            updatedRobots = updatedRobots.map { robot ->
                val newLocation = robot.position + robot.velocity
                val wrapped = wrapIfNecessary(newLocation)
                Robot(wrapped, robot.velocity)
            }

        }

        printMap(updatedRobots)
        return calculateSafetyFactor(updatedRobots)
    }

    fun part2() {
        var updatedRobots = robots.toList()
        repeat(10000) { i ->
            updatedRobots = updatedRobots.map { robot ->
                val newLocation = robot.position + robot.velocity
                val wrapped = wrapIfNecessary(newLocation)
                Robot(wrapped, robot.velocity)
            }
            printMapToImage(updatedRobots, i)
        }
    }

    private fun printMapToImage(updatedRobots: List<Robot>, iteration: Int) {
        // only print the clustered ones
        if (updatedRobots.filter { it.position.y in 0 until 20 }.size < 50) {
            val image = BufferedImage(101, 103, BufferedImage.TYPE_INT_RGB)
            val graphics: Graphics2D = image.createGraphics()
            graphics.paint = Color.BLACK
            graphics.fillRect(0, 0, image.width, image.height)

            updatedRobots.forEach { robot ->
                image.setRGB(robot.position.x, robot.position.y, Color.WHITE.rgb)
            }

            val file = File("/home/david/Desktop/imgs2/${iteration.toString().padStart(5, '0')}.png")
            ImageIO.write(image, "png", file)
        }
    }

    private fun printMap(updatedRobots: List<Robot>) {
        val map = updatedRobots.associateBy({ r -> r.position }, { _ -> "#" })
        (0 until gridSize.second).forEach { y ->
            (0 until gridSize.first).forEach { x ->
                if (map.containsKey(Coordinate(x, y))) {
                    print("#")
                } else print(".")
            }
            println()
        }
        println()
    }

    private fun calculateSafetyFactor(robots: List<Robot>): Long {
        val xFirstHalf = 0 until gridSize.first / 2
        val xSecondHalf = (gridSize.first / 2) + 1 .. gridSize.first
        val yFirstHalf = 0 until gridSize.second / 2
        val ySecondHalf = (gridSize.second / 2) + 1 .. gridSize.second

        val quadrant1X = xFirstHalf
        val quadrant1Y = yFirstHalf

        val quadrant2X = xSecondHalf
        val quadrant2Y = yFirstHalf

        val quadrant3X = xFirstHalf
        val quadrant3Y = ySecondHalf

        val quadrant4X = xSecondHalf
        val quadrant4Y = ySecondHalf

        return listOf(
            Pair(quadrant1X, quadrant1Y),
            Pair(quadrant2X, quadrant2Y),
            Pair(quadrant3X, quadrant3Y),
            Pair(quadrant4X, quadrant4Y)
        ).productOf { (quadrantX, quadrantY) ->
            robots.filter { it.position.x in quadrantX && it.position.y in quadrantY }
                .size.toLong()
        }
    }

    private fun wrapIfNecessary(coordinate: Coordinate): Coordinate {
        val newX = when {
            coordinate.x >= gridSize.first -> coordinate.x - gridSize.first
            coordinate.x < 0 -> coordinate.x + gridSize.first
            else -> coordinate.x
        }
        val newY = when {
            coordinate.y >= gridSize.second -> coordinate.y - gridSize.second
            coordinate.y < 0 -> coordinate.y + gridSize.second
            else -> coordinate.y
        }
        return Coordinate(newX, newY)
    }

    data class Robot(val position: Coordinate, val velocity: Coordinate)
}

fun main() {
    val gridSize = Pair(101, 103)
    val restroomredoubt = RestroomRedoubt(readInputLines(14), gridSize)
    println(restroomredoubt.part1())
    println(restroomredoubt.part2())
}


