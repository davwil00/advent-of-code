package day12

import utils.LongCoordinate
import utils.readInputLines
import kotlin.math.round
import kotlin.text.Regex

class GardenGroups(input: List<String>) {

    private val buttonRegex = Regex("""Button [AB]: X\+(\d+), Y\+(\d+)""")
    private val prizeRegex = Regex("""Prize: X=(\d+), Y=(\d+)""")

    val machineButtonBehaviours = parseInput(input)

    fun parseInput(input: List<String>): List<MachineButtonBehaviour> {
        return input.chunked(4).map { def ->
            val (ax, ay) = buttonRegex.matchEntire(def[0])!!.destructured
            val (bx, by) = buttonRegex.matchEntire(def[1])!!.destructured
            val (px, py) = prizeRegex.matchEntire(def[2])!!.destructured

            MachineButtonBehaviour(
                LongCoordinate(ax.toLong(), ay.toLong()),
                LongCoordinate(bx.toLong(), by.toLong()),
                LongCoordinate(px.toLong(), py.toLong())
            )
        }
    }

    fun part1(): Long {
        return machineButtonBehaviours.sumOf {
            it.calculateCost(false)
        }
    }

    fun part2(): Long {
        return machineButtonBehaviours.sumOf {
            it.calculateCost(true)
        }
    }

    data class MachineButtonBehaviour(
        val buttonA: LongCoordinate,
        val buttonB: LongCoordinate,
        val prizeLocation: LongCoordinate
    ) {
        /**
         * Uses the cross multiplication rule to find the intersection
         * (b1c2-b2c1)/(a1b2-a2b1), (c1a2-c2a1)/a1b2-a2b1)
         */
        private fun findIntersection(withUnitConversionError: Boolean): Pair<Long, Long>? {
            val target = if (withUnitConversionError) { // to get in the form ax + by + c = 0
                (prizeLocation + LongCoordinate(10000000000000, 10000000000000)) * -1
            } else {
                prizeLocation * -1
            }
            val buttonAPresses =
                ((buttonB.x * target.y) - (buttonB.y * target.x)).toDouble() / ((buttonA.x * buttonB.y) - (buttonA.y * buttonB.x))
            val buttonBPresses =
                ((target.x * buttonA.y) - (target.y * buttonA.x)).toDouble() / ((buttonA.x * buttonB.y) - (buttonA.y * buttonB.x))
            if (round(buttonAPresses) != buttonAPresses || round(buttonBPresses) != buttonBPresses) {
                return null
            }
            return Pair(buttonAPresses.toLong(), buttonBPresses.toLong())
        }

        fun calculateCost(withUnitConversionError: Boolean): Long {
            val intersection = findIntersection(withUnitConversionError)
            return if (intersection == null) 0 else intersection.first * 3 + intersection.second * 1
        }
    }
}

fun main() {
    val gardenGroups = GardenGroups(readInputLines(12))
    println(gardenGroups.part1())
    println(gardenGroups.part2())
}
