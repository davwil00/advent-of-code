package day03

import utils.readInputLines
import utils.splitToString

class Lobby(private val input: List<String>) {
    fun part1() = input.sumOf { batteryBank ->
            findJoltage(batteryBank)
        }

    fun part2() = input.sumOf { batteryBank ->
        findJoltage(batteryBank, 12)
    }

    private fun findJoltage(batteryBank: String, numberOfBatteries: Int = 2): Long {
        val batteries = batteryBank.splitToString().map { it.toInt() }
        return findBatteryCombo(batteries, numberOfBatteries)
    }

    tailrec fun findBatteryCombo(batteriesRemaining: List<Int>, numberOfBatteries: Int, batteriesUsed: List<Int> = emptyList()): Long {
        if (numberOfBatteries == 0) {
            return batteriesUsed.joinToString("").toLong()
        }

        val highestVal = batteriesRemaining.dropLast(numberOfBatteries -1).max()
        val indexOfHighestVal = batteriesRemaining.indexOf(highestVal)
        return findBatteryCombo(batteriesRemaining.drop(indexOfHighestVal + 1), numberOfBatteries - 1, batteriesUsed + highestVal)
    }
}

fun main() {
    val lobby = Lobby(readInputLines(3))
    println(lobby.part1())
    println(lobby.part2())
}
