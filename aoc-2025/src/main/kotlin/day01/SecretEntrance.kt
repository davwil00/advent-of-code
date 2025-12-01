package day01

import utils.readInputLines

class SecretEntrance(input: List<String>) {
    private val instructions = parseInput(input)

    fun part1(): Int {
        var position = 50
        var password = 0
        instructions.forEach { instruction ->
            position = moveDial(position, instruction).position
//            println(position)
            if (position == 0) {
                password++
            }
        }

        return password
    }

    fun part2(): Int {
        var position = 50
        var password = 0
        instructions.forEach { instruction ->
            val (newPosition, passwordIncrements) = moveDial(position, instruction)
            position = newPosition
//            println("${instruction.direction}${instruction.numberOfPoints} -> $newPosition $passwordIncrements")
            password += passwordIncrements
        }

        return password
    }

    fun moveDial(startPosition: Int, instruction: Instruction): PositionAndRotations {
        return when (instruction.direction) {
            'L' -> {
                val position = startPosition - (instruction.numberOfPoints % 100)
                val rotationsPast0 = (instruction.numberOfPoints / 100) + if (position <= 0 && startPosition != 0) 1 else 0
                return if (position < 0) {
                    PositionAndRotations(100 + position, rotationsPast0)
                } else PositionAndRotations(position, rotationsPast0)
            }
            'R' -> {
                val fullMovement = startPosition + instruction.numberOfPoints
                val rotationsPast0 = (fullMovement / 100)
                PositionAndRotations(fullMovement % 100, rotationsPast0)
            }
            else -> throw IllegalStateException()
        }
    }

    private fun parseInput(input: List<String>): List<Instruction> {
        return input.map{instruction ->
            Instruction(instruction.first(), instruction.drop(1).toInt())
        }
    }

    data class Instruction(val direction: Char, val numberOfPoints: Int)
    data class PositionAndRotations(val position: Int, val fullRotations: Int)
}

fun main() {
    val secretEntrance = SecretEntrance(readInputLines(1))
    println(secretEntrance.part1())
    println(secretEntrance.part2())
}
