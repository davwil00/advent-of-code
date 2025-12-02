package day02

import utils.readInput

class GiftShop(input: String) {

    val ranges = parseInput(input)

    fun part1(): Long {
        return ranges.flatMap { range ->
            range.filter { it.isInvalid() }//.also { println(it) }
        }.sum()
    }

    fun part2(): Long {
        return ranges.flatMap { range ->
            range.filter { it.isInvalidEnhanced() }//.also { println(it) }
        }.sum()
    }

    private fun Long.isInvalid(): Boolean {
        val str = this.toString()
        val first = str.take(str.length / 2)
        val last = str.drop(str.length / 2)
        return first == last
    }

    private fun Long.isInvalidEnhanced(): Boolean {
        val str = this.toString()
        return (1..str.length / 2).any { chunkSize ->
            val chunked = str.chunked(chunkSize)
            chunked.zipWithNext().all { pair -> pair.first == pair.second }
        }
    }

    private fun parseInput(input: String): List<LongRange> {
        return input.split(",").map { range ->
            val (start, end) = range.split("-")
            LongRange(start.toLong(), end.toLong())
        }
    }
}

fun main() {
    val giftshop = GiftShop(readInput(2))
    println(giftshop.part1())
    println(giftshop.part2())
}
