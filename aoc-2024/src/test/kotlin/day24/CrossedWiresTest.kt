package day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class CrossedWiresTest {
    private val testInput = """
        x00: 1
        x01: 1
        x02: 1
        y00: 0
        y01: 1
        y02: 0

        x00 AND y00 -> z00
        x01 XOR y01 -> z01
        x02 OR y02 -> z02
    """.trimIndent().lines()

    private val testSubject = CrossedWires(testInput)

    @Test
    fun `should get the right value for the test input`() {
        assertThat(testSubject.part1()).isEqualTo(4)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(CrossedWires(readInputLines(24)).part1())
            .isEqualTo(51715173446832)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(CrossedWires(readInputLines(24)).part2())
        //    .isEqualTo()
    }
}
