package day06

import utils.readInputLines

class TrashCompactor(val input: List<String>) {

    fun part1(): Long {
        val problems = parseInput(input)
        return problems.sumOf { it.solve() }
    }

    fun part2(): Long {
        val problems = parseInputPart2(input)
        return problems.sumOf { it.solve() }
    }

    private fun parseInput(input: List<String>): List<Problem> {
        val splitLines = input.map { it.trim().split("\\s+".toRegex()) }
        val numbersPerProblem = splitLines[0].size
        return (0 until numbersPerProblem).map { colIdx ->
            val col = splitLines.map { row ->
                row[colIdx]
            }
            Problem(col.dropLast(1).map { it.toLong() }, Operation.fromString(col.last()))
        }
    }

    private fun parseInputPart2(input: List<String>): List<Problem> {
        val lineLength = input[0].length
        val numericLines = input.dropLast(1)
        val operations = input.last().trim().split("\\s+".toRegex()).map { Operation.fromString(it) }.iterator()

        return buildList {
            val buffer = mutableListOf<Long>()
            (0 until lineLength).forEach { colIdx ->
                val numbers = numericLines.mapNotNull { line ->
                    val char = line[colIdx]
                    if (char.isDigit()) char else null
                }
                if (numbers.isEmpty()) {
                    add(Problem(buffer.toList(), operations.next()))
                    buffer.clear()
                } else {
                    val number = numbers.joinToString("").toLong()
                    buffer.add(number)
                }
            }
            add(Problem(buffer.toList(), operations.next()))
        }
    }

    data class Problem(val numbers: List<Long>, val operation: Operation) {
        fun solve(): Long {
            return numbers.reduce(operation.function)
        }
    }
    enum class Operation(val function: (Long, Long) -> Long) {
        ADD(Long::plus),
        MULTIPLY(Long::times);

        companion object {
            fun fromString(string: String): Operation {
                return when (string) {
                    "+" -> ADD
                    "*" -> MULTIPLY
                    else -> throw IllegalArgumentException("Unknown operation: $string")
                }
            }
        }
    }
}

fun main() {
    val trashCompactor = TrashCompactor(readInputLines(6))
    println(trashCompactor.part1())
    println(trashCompactor.part2())
}
