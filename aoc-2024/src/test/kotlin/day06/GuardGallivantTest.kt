package day06

import day06.GuardGallivant.Direction
import day06.GuardGallivant.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.Coordinate
import utils.readInputLines

class GuardGallivantTest {
    private val testInput = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent().lines()

    private val testSubject = GuardGallivant(testInput)

    @Test
    fun `should get the correct answer for part 1 with the test input`() {
        assertThat(testSubject.part1()).isEqualTo(41)
    }

    @Test
    fun `should get the correct answer for part 2 with the test input`() {
        assertThat(testSubject.part2()).isEqualTo(6)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(GuardGallivant(readInputLines(6)).part1())
            .isEqualTo(4515)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(GuardGallivant(readInputLines(6)).part2())
            .isEqualTo(1309)
    }

    @Test
    fun containsLoop() {
        val test = GuardGallivant(readInputLines(6))
        val map = test.parseMap().toMutableMap()
        val startingPosition = Position(map.filterValues { it == "^" }.keys.first(), Direction.N)
        map[Coordinate(101, 104)] = "#"
        assertThat(test.containsLoop(map, startingPosition)).isTrue
    }
}
