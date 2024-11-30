package day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class SandSlabsTest {
    private val testInput = """1,0,1~1,2,1
0,0,2~2,0,2
0,2,3~2,2,3
0,0,4~0,2,4
2,0,5~2,2,5
0,1,6~2,1,6
1,1,8~1,1,9""".lines()

    private val testSubject = SandSlabs(testInput)

    @Test
    fun `should count number of bricks that can be destroyed`() {
        assertThat(testSubject.part1()).isEqualTo(5)
    }

    @Test
    fun `should find number of bricks that will fall when unsafe bricks destroyed`() {
        assertThat(testSubject.part2()).isEqualTo(7)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(SandSlabs(readInputLines(22)).part1())
            .isEqualTo(524)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(SandSlabs(readInputLines(22)).part2())
        //    .isEqualTo()
    }
}
