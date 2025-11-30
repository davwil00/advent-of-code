package day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput

class CodeChronicleTest {
    private val testInput = """
        #####
        .####
        .####
        .####
        .#.#.
        .#...
        .....

        #####
        ##.##
        .#.##
        ...##
        ...#.
        ...#.
        .....

        .....
        #....
        #....
        #...#
        #.#.#
        #.###
        #####

        .....
        .....
        #.#..
        ###..
        ###.#
        ###.#
        #####

        .....
        .....
        .....
        #....
        #.#..
        #.#.#
        #####
    """.trimIndent()

    private val testSubject = CodeChronicle(testInput)

    @Test
    fun `should get the right answer for the test input`() {
        assertThat(testSubject.part1()).isEqualTo(3)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(CodeChronicle(readInput(25)).part1())
            .isEqualTo(2770)
    }
}
