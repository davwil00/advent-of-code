package day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readGrid
import utils.readInputLines

class CeresSearchTest {
    private val testInput = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent().lines()

    private val testSubject = CeresSearch(readGrid(testInput))

    @Test
    fun `should get the correct answer for part 1 with test input`() {
        assertThat(testSubject.part1()).isEqualTo(18)
    }

    @Test
    fun `should get the correct answer for part 2 with test input`() {
        assertThat(testSubject.part2()).isEqualTo(9)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(CeresSearch(readGrid(readInputLines(4))).part1())
            .isEqualTo(2378)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(CeresSearch(readGrid(readInputLines(4))).part2())
            .isEqualTo(1796)
    }
}
