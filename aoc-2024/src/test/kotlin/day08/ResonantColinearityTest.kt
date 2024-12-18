package day08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readGrid
import utils.readInputLines

class ResonantColinearityTest {
    private val testInput1 = """
        ..........
        ..........
        ..........
        ....a.....
        ..........
        .....a....
        ..........
        ..........
        ..........
        ..........
    """.trimIndent().lines()

    private val testInput2 = """
        ..........
        ..........
        ..........
        ....a.....
        ........a.
        .....a....
        ..........
        ......A...
        ..........
        ..........
    """.trimIndent().lines()

    private val testInput3 = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent().lines()

    private val testInput4 = """
        T.........
        ...T......
        .T........
        ..........
        ..........
        ..........
        ..........
        ..........
        ..........
        ..........
    """.trimIndent().lines()

    @Test
    fun `should find the antinodes in a simple example`() {
        val testSubject = ResonantColinearity(readGrid(testInput1))
        assertThat(testSubject.findAntinodes("a").size).isEqualTo(2)
    }

    @Test
    fun `should find the antinodes in a more complex example`() {
        val testSubject = ResonantColinearity(readGrid(testInput2))
        assertThat(testSubject.findAntinodes("a").size).isEqualTo(4)
    }

    @Test
    fun `should find the antinodes in a more complex example with more than one frequency`() {
        val testSubject = ResonantColinearity(readGrid(testInput3))
        assertThat(testSubject.part1()).isEqualTo(14)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(ResonantColinearity(readGrid(readInputLines(8))).part1())
            .isEqualTo(318)
    }

    @Test
    fun `using the updated model, should find antinodes in a complex example`() {
        val testSubject = ResonantColinearity(readGrid(testInput4))
        assertThat(testSubject.part2()).isEqualTo(9)
    }

    @Test
    fun `using the updated model, should find antinodes in an example with more than one frequency`() {
        val testSubject = ResonantColinearity(readGrid(testInput3))
        assertThat(testSubject.part2()).isEqualTo(34)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(ResonantColinearity(readGrid(readInputLines(8))).part2())
            .isEqualTo(1126)
    }
}
