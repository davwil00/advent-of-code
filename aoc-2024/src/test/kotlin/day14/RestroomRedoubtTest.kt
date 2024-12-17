package day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class RestroomRedoubtTest {
    private val testInput = """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """.trimIndent().lines()

    private val testInput2 = """
        p=2,4 v=2,-3
    """.trimIndent().lines()

    private val gridSize = Pair(11, 7)
    private val testSubject = RestroomRedoubt(testInput, gridSize)

    @Test
    fun `should work with test input`() {
        assertThat(testSubject.part1()).isEqualTo(12)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(RestroomRedoubt(readInputLines(14), Pair(101, 103)).part1())
            .isEqualTo(216027840)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(RestroomRedoubt(readInputLines(14)).part2())
        //    .isEqualTo()
    }
}
