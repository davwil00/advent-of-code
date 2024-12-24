package day24

import utils.asInt
import utils.readInputLines

class CrossedWires(input: List<String>) {
    val initialStates = parseInitialStates(input.takeWhile { it.isNotBlank() })
    val rules = parseRules(input.drop(initialStates.size + 1))

    fun part1(): Long {
        val resolvedValues = resolveRulesValues(initialStates.toMutableMap(), rules.keys.filterNot { it in initialStates }.toSet())
        return resolvedValues
            .filterKeys { it.startsWith("z") }
            .entries
            .sortedByDescending { it.key }
            .map { it.value }
            .joinToString("")
            .toLong(2)
    }

    private fun parseInitialStates(input: List<String>): Map<String, Int> {
        return input.map {
            it.substringBefore(":") to it.last().asInt()
        }.toMap()
    }

    private fun parseRules(ops: List<String>): Map<String, Rule> {
        return ops.map { op ->
            val (input1, gateStr, input2, _, output) = op.split(" ")
            output to Rule(input1, input2, Gate.valueOf(gateStr), output)
        }.toMap()
    }

    private tailrec fun resolveRulesValues(values: MutableMap<String, Int>, unresolvedValues: Set<String>): Map<String, Int> {
        if (unresolvedValues.isEmpty()) {
            return values
        }

        val resolvedValues = unresolvedValues.filter { value ->
            val rule = rules.getValue(value)
            if (values.containsKey(rule.input1) && values.containsKey(rule.input2)) {
                values[value] = rule(values.getValue(rule.input1), values.getValue(rule.input2))
                true
            } else false
        }

        return resolveRulesValues(values, unresolvedValues - resolvedValues.toSet())
    }

    data class Rule(val input1: String, val input2: String, val gate: Gate, val output: String) {
        operator fun invoke(input1: Int, input2: Int): Int {
            return gate(input1, input2)
        }
    }
    enum class Gate(private val function: (Int, Int) -> Int) {
        AND(Int::and),
        OR(Int::or),
        XOR(Int::xor);

        operator fun invoke(input1: Int, input2: Int): Int {
            return function(input1, input2)
        }
    }
}

fun main() {
    val crossedwires = CrossedWires(readInputLines(24))
    println(crossedwires.part1())
    //println(crossedwires.part2())
}
