package day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readGrid
import utils.readInputLines

class ReindeerMazeTest {
    private val testInput = """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############
    """.trimIndent().lines()

    private val testInput2 = """
        #################
        #...#...#...#..E#
        #.#.#.#.#.#.#.#.#
        #.#.#.#...#...#.#
        #.#.#.#.###.#.#.#
        #...#.#.#.....#.#
        #.#.#.#.#.#####.#
        #.#...#.#.#.....#
        #.#.#####.#.###.#
        #.#.#.......#...#
        #.#.###.#####.###
        #.#.#...#.....#.#
        #.#.#.#####.###.#
        #.#.#.........#.#
        #.#.#.#########.#
        #S#.............#
        #################
    """.trimIndent().trimIndent().lines()


    @Test
    fun `should find the length of the shortest path for the test input`() {
        val testSubject = ReindeerMaze(readGrid(testInput))
        assertThat(testSubject.part1()).isEqualTo(7036)
    }
    @Test
    fun `should find the length of the shortest path for the more complex test input`() {
        val testSubject = ReindeerMaze(readGrid(testInput2))
        assertThat(testSubject.part1()).isEqualTo(11048)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(ReindeerMaze(readGrid(readInputLines(16))).part1())
            .isEqualTo(108504)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(ReindeerMaze(readInputLines(16)).part2())
        //    .isEqualTo()
    }
}
