package day25

import utils.readInput

class CodeChronicle(input: String) {
    private val locksAndKeys = input.split("\n\n")
    private val locks = locksAndKeys.filter { it.startsWith("#") }
    private val keys = locksAndKeys.filter { it.startsWith(".") }

    fun part1(): Int {
        val lockCounts = locks.map { countCharInColumns(it) }
        val keyCounts = keys.map { countCharInColumns(it) }
        return lockCounts.sumOf { lock ->
            keyCounts.count { key ->
                lock.zip(key).all { (lock, key) -> lock + key <= 5 }
            }
        }
    }

    private fun countCharInColumns(str: String): List<Int> {
        val lines = str.lines()
        return (0..4).map { colIdx ->
            lines.count { it[colIdx] == '#' } - 1
        }
    }
}

fun main() {
    val codeChronicle = CodeChronicle(readInput(25))
    println(codeChronicle.part1())
}
