package day05

import utils.readInput
import kotlin.collections.component1
import kotlin.collections.component2

class Cafeteria(input: String) {
    private val state = parseInput(input)

    fun part1(): Int {
        val (ingredientsInStock, ingredientsToCheck) = state
        val freshIngredients = ingredientsToCheck.filter { ingredient ->
            ingredientsInStock.any { it.contains(ingredient)}
        }

        return freshIngredients.size
    }

    fun part2(): Long {
        return sortAndCount(state.ingredientsInStock)
    }

    fun sortAndCount(ranges: List<LongRange>): Long {
        return ranges.sortedBy { it.first }.fold(State(0, 0)) { (total, max), range ->
            if (range.first <= max) {
                if (range.last >= max) {
                    State(total + range.last - max, range.last)
                } else {
                    State(total, max)
                }
            } else {
                State(total + (range.last - range.first) + 1, range.last)
            }
        }.total
    }

    data class State(val total: Long, val max: Long)

    private fun parseInput(input: String): CafeteriaState {
        val (ingredientsInStock, ingredientsToCheck) = input.split("\n\n")
        val ingredientsInStockRanges = ingredientsInStock.lines().map { range ->
            val (start, end) = range.split("-")
            LongRange(start.toLong(), end.toLong())
        }

        return CafeteriaState(ingredientsInStockRanges, ingredientsToCheck.lines().map { it.toLong() })
    }

    data class CafeteriaState(val ingredientsInStock: List<LongRange>, val ingredientsToCheck: List<Long>)
}

fun main() {
    val cafeteria = Cafeteria(readInput(5))
    println(cafeteria.part1())
    println(cafeteria.part2())
}
