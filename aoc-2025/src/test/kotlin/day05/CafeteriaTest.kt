package day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput
import utils.readInputLines

class CafeteriaTest {
    private val testInput = """
        3-5
        10-14
        16-20
        12-18

        1
        5
        8
        11
        17
        32
    """.trimIndent()

    private val testSubject = Cafeteria(testInput)

    @Test
    fun `should find the number of ingredients that are fresh`() {
        assertThat(testSubject.part1()).isEqualTo(3)
    }

    @Test
    fun `should find the total number of ingredients in stock`() {
        assertThat(testSubject.part2()).isEqualTo(14)
    }

    @Test
    fun `should get correct answer for part 1`() {
        assertThat(Cafeteria(readInput(5)).part1()).isEqualTo(828)
    }

    @Test
    fun `should get correct answer for part 2`() {
        assertThat(Cafeteria(readInput(5)).part2()).isEqualTo(352681648086146)
    }
}
