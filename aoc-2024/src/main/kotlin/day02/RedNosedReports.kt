package day02

import utils.readInputLines
import kotlin.math.abs

class RedNosedReports(input: List<String>) {
    val reports = input.map { report -> report.split(" ").map { it.toInt() } }

    private fun areAllIncreasing(report: List<Int>): Boolean {
        return report.zipWithNext().all { (a, b) -> a < b }
    }

    private fun areAllDecreasing(report: List<Int>): Boolean {
        return report.zipWithNext().all { (a, b) -> a > b}
    }

    private fun differenceIsAtLeastOneAndAtMostThree(report: List<Int>): Boolean {
        return report.zipWithNext().all { (a, b) ->
            abs(a - b) in 1..3
        }
    }

    private fun tryWithDrop(report: List<Int>, function: (List<Int>) -> Boolean): Boolean {
        for (i in report.indices) {
            val partialReport = report.toMutableList()
            partialReport.removeAt(i)
            if (function.invoke(partialReport)) {
                return true
            }
        }

        return false
    }

    fun part1() =
        reports.count {
            (areAllIncreasing(it) || areAllDecreasing(it)) && differenceIsAtLeastOneAndAtMostThree(it)
        }

    fun part2(): Int {
        val all = reports.map { report ->
            tryWithDrop(report) {
                (areAllIncreasing(it) || areAllDecreasing(it)) && differenceIsAtLeastOneAndAtMostThree(it)
            }
        }

        return all.count { it == true }
    }
}

fun main() {
    val redNosedReports = RedNosedReports(readInputLines(2))
    println(redNosedReports.part1())
    println(redNosedReports.part2())
}
