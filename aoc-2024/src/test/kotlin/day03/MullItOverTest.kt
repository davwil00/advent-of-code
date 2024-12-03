package day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class MullItOverTest {
    private val testInputPt1 = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""".lines()
    private val testInputPt2 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""".lines()

    @Test
    fun `should get the correct answer for part 1 test input`() {
        assertThat(MullItOver(testInputPt1).part1()).isEqualTo(161)
    }

    @Test
    fun `should get the correct answer for part 2 test input`() {
        assertThat(MullItOver(testInputPt2).part2()).isEqualTo(48)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(MullItOver(readInputLines(3)).part1())
            .isEqualTo(174561379)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(MullItOver(readInputLines(3)).part2())
            .isEqualTo(106921067)
    }
}
