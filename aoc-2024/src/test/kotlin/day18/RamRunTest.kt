package day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class RamRunTest {
    private val testInput = """
        5,4
        4,2
        4,5
        3,0
        2,1
        6,3
        2,4
        1,5
        0,6
        3,3
        2,6
        5,1
        1,2
        5,5
        2,5
        6,5
        1,4
        0,4
        6,4
        1,1
        6,1
        1,0
        0,5
        1,6
        2,0
    """.trimIndent().lines()

    private val testSubject = RamRun(testInput, 7)

    @Test
    fun `should find the number of steps for the test input`() {
        assertThat(testSubject.part1(12)).isEqualTo(22)
    }

    @Test
    fun `should find the coordinate which blocks the path`() {
        assertThat(testSubject.part2(12)).isEqualTo("6,1")
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(RamRun(readInputLines(18)).part1())
            .isEqualTo(416)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(RamRun(readInputLines(18)).part2(416))
            .isEqualTo("50,23")
    }
}
