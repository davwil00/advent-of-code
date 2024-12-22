package day22

import utils.readInputLines

class MonkeyMarket(input: List<String>) {

    private val initialSecrets = input.map { it.toLong() }

    fun part1(): Long {
        return initialSecrets.sumOf { secret ->
            val sequence = sequence {
                yieldAll(generateSequence(secret) { calculateNextNumber(it) })
            }
            sequence.elementAt(2000)
        }
    }

    fun part2(): Long {
        val differences = initialSecrets.map { secret ->
            buildMap {
                val initial = PriceAndDifference(secret, secret % 10, 0)
                sequence {
                    yieldAll(generateSequence(initial) { calculatePriceAndDifference(it) })
                }
                    .take(2001)
                    .windowed(4)
                    .forEach { window ->
                        val key = window.joinToString("") { it.difference.toString() }
                        putIfAbsent(key, window.last().price)
                    }
            }
        }

        val differencesToTry = differences.flatMap { it.keys }.toSet()
        return differencesToTry.parallelStream().map { differenceToTry ->
            differences.sumOf { difference -> difference.getOrDefault(differenceToTry, 0) }
        }.toList().max()
    }


    /**
     * Calculate the result of multiplying the secret number by 64.
     * Then, mix this result into the secret number.
     * Finally, prune the secret number.
     *
     * Calculate the result of dividing the secret number by 32.
     * Round the result down to the nearest integer.
     * Then, mix this result into the secret number.
     * Finally, prune the secret number.
     *
     * Calculate the result of multiplying the secret number by 2048.
     * Then, mix this result into the secret number.
     * Finally, prune the secret number.
     *
     */
    fun calculateNextNumber(secretNumber: Long): Long {
        val step1 = prune(mix(secretNumber, secretNumber * 64))
        val step2 = prune(mix(step1, step1 / 32))
        val step3 = prune(mix(step2, step2 * 2048))
        return step3
    }

    fun calculatePriceAndDifference(secretNumber: PriceAndDifference): PriceAndDifference {
        val newSecretNumber = calculateNextNumber(secretNumber.secretNumber)
        val price = newSecretNumber % 10 // gets the 1s digit
        return PriceAndDifference(newSecretNumber, price, price - secretNumber.price)
    }

    data class PriceAndDifference(val secretNumber: Long, val price: Long, val difference: Long)

    /**
     * To mix a value into the secret number, calculate the bitwise XOR of the given value and the secret number.
     * Then, the secret number becomes the result of that operation.
     * (If the secret number is 42 and you were to mix 15 into the secret number, the secret number would become 37.)
     */
    fun mix(secretNumber: Long, mix: Long): Long {
        return secretNumber.xor(mix)
    }

    /**
     * To prune the secret number, calculate the value of the secret number modulo 16777216.
     * Then, the secret number becomes the result of that operation.
     * (If the secret number is 100000000 and you were to prune the secret number, the secret number would become 16113920.)
     */
    fun prune(secretNumber: Long): Long {
        return secretNumber % 16777216
    }
}

fun main() {
    val monkeyMarket = MonkeyMarket(readInputLines(22))
    println(monkeyMarket.part1())
    println(monkeyMarket.part2())
}
