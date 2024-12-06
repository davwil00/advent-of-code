package day05

import utils.readInput

class PrintQueue(input: String) {

    private val parsedInput = parseInput(input)

    private fun parseInput(input: String): Input {
        val (rawRules, rawUpdates) = input.split("\n\n").map { it.lines() }
        val rules = rawRules.map {
            val (page1, page2) = it.split("|").map { it.toInt() }
            Rule(page1, page2)
        }
        val updates = rawUpdates.map { it.split(",").map(String::toInt) }
        return Input(rules, updates)
    }

    private fun List<Int>.isValid(ruleGroups: Map<Int, List<Int>>) =
        zipWithNext().all { (page1, page2) ->
            if (ruleGroups.containsKey(page2)) {
                !ruleGroups.getValue(page2).contains(page1)
            } else true
        }

    fun part1(): Int {
        val validUpdates = parsedInput.updates.filter { it.isValid(parsedInput.ruleGroups) }
        return validUpdates.sumOf { update ->
            update[update.size / 2]
        }
    }

    fun part2(): Int {
        val invalidUpdates = parsedInput.updates.filterNot { it.isValid(parsedInput.ruleGroups) }
        val sorted = invalidUpdates.map { update ->
            update.sortedWith { page1, page2 ->
                when {
                    parsedInput.ruleGroups[page2]?.contains(page1) ?: false -> 1
                    parsedInput.ruleGroups[page1]?.contains(page2) ?: false -> -1
                    else -> 0
                }
            }
        }

        return sorted.sumOf { it[it.size / 2] }
    }

    private data class Rule(val page1: Int, val page2: Int)
    private data class Input(val rules: List<Rule>, val updates: List<List<Int>>) {
        val ruleGroups = rules.groupBy({ it.page1 }, { it.page2 })
    }
}

fun main() {
    val printQueue = PrintQueue(readInput(5))
    println(printQueue.part1())
    println(printQueue.part2())
}
