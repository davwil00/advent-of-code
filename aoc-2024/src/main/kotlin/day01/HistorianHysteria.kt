package day01

import utils.readInputLines
import kotlin.math.abs

class HistorianHysteria(private val input: List<String>) {
    fun parseInput(): List<List<Int>> {
        val rows = input.map {
            val items = it.split(Regex("\\s+"))
            items[0].toInt()to items[1].toInt()
        }
        return listOf(rows.map{it.first}, rows.map{it.second})
    }

    fun part1(): Int {
        val (list1, list2) = parseInput()
        val sortedList1 = list1.sorted()
        val sortedList2 = list2.sorted()
        return sortedList1.zip(sortedList2).sumOf { (a, b) ->
            abs(a - b)
        }
    }

    fun part2(): Int {
        val (list1, list2) = parseInput()
        val occurrenceCount = list2.groupBy { it }.mapValues { it.value.size }
        return list1.sumOf { it * occurrenceCount.getOrDefault(it, 0) }
    }
}

fun main() {
    val historianhysteria = HistorianHysteria(readInputLines(1))
    println(historianhysteria.part1())
    println(historianhysteria.part2())
}
