package day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readGrid
import utils.readInputLines

class HoofItTest {
    private val testInput = """
        0123
        1234
        8765
        9876
    """.trimIndent().lines()

    private val testInput2 = """
        1110111
        1111111
        1112111
        6543456
        7111117
        8111118
        9111119
    """.trimIndent().lines()

    private val testInput3 = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent().lines()

    @Test
    fun `should find trail in simple example`() {
        val testSubject = HoofIt(readGrid(testInput))
        assertThat(testSubject.part1()).isEqualTo(1)
    }
    
    @Test
    fun `should find trails in example`() {
        val testSubject = HoofIt(readGrid(testInput2))
        assertThat(testSubject.part1()).isEqualTo(2)
    }

    @Test
    fun `should find trails in larger example`() {
        val testSubject = HoofIt(readGrid(testInput3))
        assertThat(testSubject.part1()).isEqualTo(36)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(HoofIt(readGrid(readInputLines(10))).part1())
            .isEqualTo(816)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(HoofIt(readGrid(readInputLines(10))).part2())
            .isEqualTo(1960)
    }
}
