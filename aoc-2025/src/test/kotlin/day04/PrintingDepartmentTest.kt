package day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class PrintingDepartmentTest {
    private val testInput = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent().lines()

    private val testSubject = PrintingDepartment(testInput)

    @Test
    fun `should find the number of rolls accessible by a forklift`() {
        assertThat(testSubject.part1()).isEqualTo(13)
    }

    @Test
    fun `should find the number of rolls accessible by a forklift with removals`() {
        assertThat(testSubject.part2()).isEqualTo(43)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(PrintingDepartment(readInputLines(4)).part1())
            .isEqualTo(1489)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(PrintingDepartment(readInputLines(4)).part2())
            .isEqualTo(8890)
    }
}
