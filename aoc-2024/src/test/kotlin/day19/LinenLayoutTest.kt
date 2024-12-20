package day19

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import utils.readInputLines

class LinenLayoutTest {
    private val testInput = """
        r, wr, b, g, bwu, rb, gb, br

        brwrr
        bggr
        gbbr
        rrbgbr
        ubwu
        bwurrg
        brgr
        bbrgwb
    """.trimIndent().lines()

    private val testSubject = LinenLayout(testInput)

    @Test
    fun `should get the correct number for the test input`() {
        assertThat(testSubject.part1()).isEqualTo(6)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(LinenLayout(readInputLines(19)).part1())
            .isEqualTo(371)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(LinenLayout(readInputLines(19)).part2())
        //    .isEqualTo()
    }
}
