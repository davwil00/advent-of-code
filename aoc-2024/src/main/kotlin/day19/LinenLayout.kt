package day19

import utils.readInputLines

class LinenLayout(input: List<String>) {
    private val patternsAvailable = input.first().split(", ")
    private val patternsToCheck = input.drop(2)

    fun part1(): Int {
        val regex = Regex("""^(${patternsAvailable.joinToString("|")})*""")
        return patternsToCheck.count {
            regex.matches(it)
        }
    }
}

fun main() {
    val linenlayout = LinenLayout(readInputLines(19))
    println(linenlayout.part1())
    //println(linenlayout.part2())
}
