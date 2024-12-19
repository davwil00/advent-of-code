package day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChronospatialComputerTest {
    private val testInput = """
        Register A: 729
        Register B: 0
        Register C: 0

        Program: 0,1,5,4,3,0
    """.trimIndent().lines()

    private val testSubject = ChronospatialComputer(testInput)

    @Test
    fun `should return the correct output for the test program`() {
        assertThat(testSubject.part1()).isEqualTo("4,6,3,5,6,3,5,2,1,0")
    }

    @Test
    fun `should return the correct value of registerA for the test program`() {
        assertThat(testSubject.part2()).isEqualTo(117440)
    }

    @Test
    fun `should get correct answer for part 1`() {
        // assertThat(ChronospatialComputer(readInputLines(17)).part1())
        //    .isEqualTo()
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(ChronospatialComputer(readInputLines(17)).part2())
        //    .isEqualTo()
    }
}
