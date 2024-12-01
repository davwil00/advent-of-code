package day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class HistorianHysteriaTest {
    private val testInput = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent().lines()

    private val testSubject = HistorianHysteria(testInput)

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(testSubject.part1())
            .isEqualTo(11)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(testSubject.part2())
            .isEqualTo(31)
    }
}
