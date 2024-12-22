package day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class MonkeyMarketTest {
    private val testInput = """
        1
        10
        100
        2024
    """.trimIndent().lines()

    private val testInputForPart2 = """
        1
        2
        3
        2024
    """.trimIndent().lines()

    private val testInput2 = """
        3765927
        1817375
        11823314
    """.trimIndent().lines()

    private val testSubject = MonkeyMarket(testInput)

    @Test
    fun `should mix correctly`() {
        assertThat(testSubject.mix(42, 15))
            .isEqualTo(37)
    }

    @Test
    fun `should prune correctly`() {
        assertThat(testSubject.prune(100000000))
            .isEqualTo(16113920)
    }

    @Test
    fun `calculate next number`() {
        assertThat(testSubject.calculateNextNumber(123))
            .isEqualTo(15887950)
    }

    @Test
    fun `calculate sum of 2000th secret`() {
        assertThat(testSubject.part1()).isEqualTo(37327623)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(MonkeyMarket(readInputLines(22)).part1())
            .isEqualTo(16039090236)
    }

    @Test
    fun `should find the right sequence`() {
        MonkeyMarket(testInputForPart2).part2()
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(MonkeyMarket(readInputLines(22)).part2())
             .isEqualTo(1808)
    }
}
