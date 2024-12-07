package day07

import day07.BridgeRepair.CalibrationEquation.Operator.*
import utils.readInputLines

class BridgeRepair(input: List<String>) {

    private val equations = input.map { line ->
        val (testValue, numbers) = line.split(": ")
        CalibrationEquation(testValue.toLong(), numbers.split(" ").map { it.toLong() })
    }

    class CalibrationEquation(val testValue: Long, private val numbers: List<Long>) {
        private fun generateOperatorPermutations(allowedOperators: List<Operator>): List<List<Operator>> {
            val operatorsNeeded = numbers.size - 1
            val radix = allowedOperators.size
            val noOfPermutations = "${radix - 1}".repeat(operatorsNeeded).toInt(radix)
            return (0..noOfPermutations).map { num ->
                num.toString(radix).padStart(operatorsNeeded, '0').map {
                    when (it) {
                        '0' -> PLUS
                        '1' -> MULTIPLY
                        '2' -> CONCATENATE
                        else -> throw IllegalStateException()
                    }
                }
            }
        }

        fun canBeSolved(allowedOperators: List<Operator>): Boolean {
            val operatorPermutations = generateOperatorPermutations(allowedOperators)
            val solution = operatorPermutations.firstOrNull() { operators ->
                val total = numbers.reduceIndexed { index, acc, curr ->
                    if (index == 0) {
                        curr
                    } else {
                        operators[index - 1].function(acc, curr)
                    }
                }
                total == testValue
            }

            return solution != null
        }

        enum class Operator(val function: (Long, Long) -> Long) {
            MULTIPLY(Long::times),
            PLUS(Long::plus),
            CONCATENATE({ a, b -> "$a$b".toLong() });
        }
    }

    fun part1(): Long {
        return equations.filter { equation ->
            equation.canBeSolved(listOf(PLUS, MULTIPLY))
        }.sumOf { it.testValue }
    }

    fun part2(): Long {
        val canBeSolvedWith2Operators = equations.filter { equation ->
            equation.canBeSolved(listOf(PLUS, MULTIPLY))
        }
        val canBeSolvedWith3Operators = equations.filterNot { equation -> equation in canBeSolvedWith2Operators }
            .filter { equation ->
                equation.canBeSolved(listOf(PLUS, MULTIPLY, CONCATENATE))
            }
        return (canBeSolvedWith3Operators + canBeSolvedWith3Operators).sumOf { it.testValue }
    }
}

fun main() {
    val bridgeRepair = BridgeRepair(readInputLines(7))
    println(bridgeRepair.part1())
    println(bridgeRepair.part2())
}
