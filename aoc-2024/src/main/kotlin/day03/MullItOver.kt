package day03

import utils.readInputLines

class MullItOver(input: List<String>) {
    private val memEntry = input.joinToString("")
    private val mulRegex = Regex("""mul\((\d+),(\d+)\)""")
    private val doDontMulRegex = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""")

    interface Op

    class Mul(private val left: Long, private val right: Long) : Op {
        fun result() = left * right

        companion object {
            fun fromMatchResult(matchResult: MatchResult): Mul {
                val (left, right) = matchResult.destructured
                return Mul(left.toLong(), right.toLong())
            }
        }
    }

    class DoDont(val enable: Boolean) : Op

    fun extractValidMulOps(memEntry: String) = mulRegex.findAll(memEntry).map { Mul.fromMatchResult(it) }

    fun extractValidOps(memEntry: String): Sequence<Op> {
        return doDontMulRegex.findAll(memEntry).map { matchResult ->
            when {
                matchResult.value.startsWith("don't") -> DoDont(false)
                matchResult.value.startsWith("do") -> DoDont(true)
                else -> Mul.fromMatchResult(matchResult)
            }
        }
    }

    fun part1(): Long {
        return extractValidMulOps(memEntry).sumOf { it.result() }
    }

    fun part2(): Long {
        val ops = extractValidOps(memEntry)
        var isEnabled = true
        return ops.sumOf { op ->
            when {
                op is Mul && isEnabled -> op.result()
                op is DoDont -> {
                    isEnabled = op.enable
                    0L
                }

                else -> 0
            }
        }
    }
}

fun main() {
    val mullItOver = MullItOver(readInputLines(3))
    println(mullItOver.part1())
    println(mullItOver.part2())
}
