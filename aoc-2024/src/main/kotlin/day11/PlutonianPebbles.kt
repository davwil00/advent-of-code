package day11

import utils.isEven
import utils.readInput
import utils.readInputLines

class PlutonianPebbles(input: String) {

    val initialArrangement = input.split(" ").map { it.toLong() }

    fun part1(): Int {
        var stones = initialArrangement
        repeat(25) {
            stones = stones.flatMap {
                applyStoneRules(it)
            }
        }
        return stones.size
    }

    fun part2(): Int {
        var stones = initialArrangement
        repeat(75) {
            stones = stones.flatMap {
                applyStoneRules(it)
            }
        }
        return stones.size
    }

    fun applyStoneRules(stoneValue: Long): List<Long> {
        return when {
            stoneValue == 0L -> listOf(1)
            canSplit(stoneValue) -> splitStone(stoneValue)
            else -> listOf(stoneValue * 2024)
        }
    }

    fun splitStone(stoneValue: Long): List<Long> {
        val valueAsString = stoneValue.toString()
        val split = valueAsString.chunked(valueAsString.length / 2)
        return split.map { it.toLong() }
    }

    private fun canSplit(stoneValue: Long) = stoneValue.toString().length.isEven()
}

fun main() {
    val plutonianpebbles = PlutonianPebbles(readInput(11))
    println(plutonianpebbles.part1())
    println(plutonianpebbles.part2())
}
