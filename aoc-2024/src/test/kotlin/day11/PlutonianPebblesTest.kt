package day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readSingleInputLineOfIntsFromCsv

class PlutonianPebblesTest {
    private val testInput = "125 17"

    private val testSubject = PlutonianPebbles(testInput)

    @Test
    fun `should find correct number of stones after 25 iterations for test input`() {
        assertThat(testSubject.part1()).isEqualTo(55312)
    }

    @Test
    fun `should get correct answer for part 1`() {
        // assertThat(PlutonianPebbles(readInputLines(11)).part1())
        //    .isEqualTo()
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(PlutonianPebbles(readInputLines(11)).part2())
        //    .isEqualTo()
    }
}
