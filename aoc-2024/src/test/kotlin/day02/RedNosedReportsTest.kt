package day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class RedNosedReportsTest {
    private val testInput = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().lines()

    private val testSubject = RedNosedReports(testInput)

    @Test
    fun `should get the correct answer for test 1 input`() {
        assertThat(testSubject.part1()).isEqualTo(2)
    }

    @Test
    fun `should get the correct answer for test 2 input`() {
        assertThat(testSubject.part2()).isEqualTo(4)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(RedNosedReports(readInputLines(2)).part1())
            .isEqualTo(371)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(RedNosedReports(readInputLines(2)).part2())
            .isEqualTo(426)
    }
}
