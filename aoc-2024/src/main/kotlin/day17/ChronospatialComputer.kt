package day17

import utils.readInputLines
import kotlin.math.pow
import kotlin.math.roundToInt

class ChronospatialComputer(input: List<String>) {
    val initialRegisterA = input[0].substringAfter(": ").toInt()
    val initialRegisterB = input[1].substringAfter(": ").toInt()
    val initialRegisterC = input[2].substringAfter(": ").toInt()
    val program = input[4].substringAfter(": ").split(",").map { it.toInt() }

    fun part1(): String {
        val state = State(initialRegisterA, initialRegisterB, initialRegisterC)
        while (program.size > state.pointer + 1) {
            applyInstruction(state, program[state.pointer], program[state.pointer + 1])
        }
        return state.output.joinToString(",")
    }

    fun part2(): Int {
        var a = 0
        val target = program.joinToString("")
        while (true) {
            val state = State(initialRegisterA, initialRegisterB, initialRegisterC)
            while (program.size > state.pointer + 1) {
                applyInstruction(state, program[state.pointer], program[state.pointer + 1])
            }
            val output = state.output.joinToString(",")
            if (output == target) {
                return a
            }
            a++
        }
    }

    fun applyComboOperand(state: State, operator: Int) = when (operator) {
        in 0..3 -> operator
        4 -> state.registerA
        5 -> state.registerB
        6 -> state.registerC
        else -> throw IllegalStateException("unknown operand $operator")
    }

    fun applyInstruction(state: State, instruction: Int, operand: Int) {
        when (instruction) {
            0 -> {
                state.registerA = adv(state, operand)
                state.pointer += 2
            }

            1 -> {
                state.registerB = state.registerB.xor(operand)
                state.pointer += 2
            }

            2 -> {
                state.registerB = applyComboOperand(state, operand) % 8
                state.pointer += 2
            }

            3 -> if (state.registerA != 0) {
                state.pointer = operand
            } else {
                state.pointer += 2
            }

            4 -> {
                state.registerB = state.registerB.xor(state.registerC)
                state.pointer += 2
            }

            5 -> {
                state.output.add(applyComboOperand(state, operand) % 8)
                state.pointer += 2
            }

            6 -> {
                state.registerB = adv(state, operand)
                state.pointer += 2
            }

            7 -> {
                state.registerC = adv(state, operand)
                state.pointer += 2
            }
        }
    }

    fun adv(state: State, operand: Int) = state.registerA / 2.0.pow(applyComboOperand(state, operand).toDouble()).roundToInt()

    data class State(
        var registerA: Int,
        var registerB: Int,
        var registerC: Int,
        var pointer: Int = 0,
        val output: MutableList<Int> = mutableListOf()
    )
}

fun main() {
    val chronospatialcomputer = ChronospatialComputer(readInputLines(17))
    println(chronospatialcomputer.part1())
    //println(chronospatialcomputer.part2())
}
