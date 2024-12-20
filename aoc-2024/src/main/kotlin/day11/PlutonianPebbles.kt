package day11

import utils.isEven
import utils.readInput

class PlutonianPebbles(input: String) {

    val initialArrangement = input.split(" ").map { it.toLong() }

    fun part1(): Int {
        var stones = initialArrangement
        repeat(25) {
            stones = stones.flatMap {
                applyStoneRules(it)
            }
//            println(stones.groupingBy { it }.eachCount().toSortedMap())
        }
        return stones.size
    }

    fun part2(): Long {
        val stones = initialArrangement.associateWith { 1L }.toMutableMap()
        repeat(75) {
            applyStoneRules(stones)
//            println(stones.filterValues { it != 0L }.toSortedMap())
        }
        return stones.values.sum()
    }

    private fun applyStoneRules(stoneValue: Long): List<Long> {
        return when {
            stoneValue == 0L -> listOf(1)
            canSplit(stoneValue) -> splitStone(stoneValue)
            else -> listOf(stoneValue * 2024)
        }
    }

    fun applyStoneRules(map: MutableMap<Long, Long>) {
        val entries = map.entries.map { (key, value) -> key to value }
        entries.filterNot { it.second == 0L }
            .forEach { (stoneValue, numberOfStones) ->
                map.decrement(stoneValue, numberOfStones)
                when {
                    stoneValue == 0L -> map.increment(1, numberOfStones)
                    canSplit(stoneValue) -> splitStone(stoneValue, map, numberOfStones)
                    else -> map.increment(stoneValue * 2024, numberOfStones)
                }
            }
    }

    private fun splitStone(stoneValue: Long): List<Long> {
        val valueAsString = stoneValue.toString()
        val split = valueAsString.chunked(valueAsString.length / 2)
        return split.map { it.toLong() }
    }

    private fun splitStone(stoneValue: Long, map: MutableMap<Long, Long>, numberOfStones: Long) {
        val valueAsString = stoneValue.toString()
        val split = valueAsString.chunked(valueAsString.length / 2)
        return split.forEach { map.increment(it.toLong(), numberOfStones) }
    }

    private fun canSplit(stoneValue: Long) = stoneValue.toString().length.isEven()
    private fun MutableMap<Long, Long>.increment(key: Long, amount: Long = 1) {
        put(key, getOrDefault(key, 0) + amount)
    }

    private fun MutableMap<Long, Long>.decrement(key: Long, amount: Long) {
        put(key, getValue(key) - amount)
    }
}

fun main() {
    val plutonianPebbles = PlutonianPebbles(readInput(11))
    println(plutonianPebbles.part1())
    println(plutonianPebbles.part2())
}
