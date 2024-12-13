package day07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class BridgeRepairTest {
    private val testInput = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent().lines()

    private val testSubject = BridgeRepair(testInput)

    @Test
    fun `should get the right answer for part 1 with test input`() {
        assertThat(testSubject.part1()).isEqualTo(3749)
    }

    @Test
    fun `should get the right answer for part 2 with test input`() {
        assertThat(testSubject.part2()).isEqualTo(11387)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(BridgeRepair(readInputLines(7)).part1())
            .isEqualTo(1298300076754)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(BridgeRepair(readInputLines(7)).part2())
            .isEqualTo(494257637791070)
    }
}
