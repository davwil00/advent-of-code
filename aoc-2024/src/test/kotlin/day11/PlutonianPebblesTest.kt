package day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput
import utils.readSingleInputLineOfIntsFromCsv

class PlutonianPebblesTest {
    private val testInput = "125 17"

    private val testSubject = PlutonianPebbles(testInput)

    @Test
    fun `should find correct number of stones after 25 iterations for test input`() {
        assertThat(testSubject.part1()).isEqualTo(55312)
    }

    @Test
    fun `should find correct number of stones after 6 iterations for test input with part2`() {
        assertThat(testSubject.part2()).isEqualTo(22)
    }

    @Test
    fun oneit() {
        val input = "512 72 2024 2 0 2 4 2867 6032"
        val testSubject = PlutonianPebbles(input)
        val map = buildMap<Long, Long> {
            put(512, 1)
            put(72, 1)
            put(2024, 1)
            put(2, 2)
            put(0, 1)
            put(4, 1)
            put(2867, 1)
            put(6032, 1)
        }.toMutableMap()
        testSubject.applyStoneRules(map)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(PlutonianPebbles(readInput(11)).part1())
            .isEqualTo(200446)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(PlutonianPebbles(readInput(11)).part2())
            .isEqualTo(238317474993392)
    }
}
