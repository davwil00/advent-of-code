package day01

import utils.readInputLines
import kotlin.math.abs

class HistorianHysteria(input: List<String>) {

    private val numberLists = parseInput(input)

    private fun parseInput(input: List<String>): Pair<List<Int>, List<Int>> =
        input.map {
            val (first, second) = it.split("   ")
            first.toInt() to second.toInt()
        }.unzip()

    fun part1(): Int {
        val sortedLeftList1 = numberLists.first.sorted()
        val sortedRight2 = numberLists.second.sorted()
        return sortedLeftList1.zip(sortedRight2).sumOf { (a, b) ->
            abs(a - b)
        }
    }

    fun part2(): Int {
        val occurrenceCount = numberLists.second.groupingBy { it }.eachCount()
        return numberLists.first.sumOf { it * occurrenceCount.getOrDefault(it, 0) }
    }
}

fun main() {
    val historianHysteria = HistorianHysteria(readInputLines(1))
    println(historianHysteria.part1())
    println(historianHysteria.part2())
}
