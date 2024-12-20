package day13

import day12.GardenGroups
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class ClawContraptionTest {

    private val testInput = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent().lines()

    private val testSubject = ClawContraption(testInput)

    @Test
    fun `should find costs for sample input`() {
        assertThat(testSubject.part1())
            .isEqualTo(480)
    }

    @Test
    fun `should get correct answer for part 1`() {
        assertThat(ClawContraption(readInputLines(13)).part1())
            .isEqualTo(36758)
    }

    @Test
    fun `should get correct answer for part 2`() {
        assertThat(ClawContraption(readInputLines(13)).part2())
            .isEqualTo(76358113886726)
    }
}
