package day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput

class PrintQueueTest {
    private val testInput = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    private val testSubject = PrintQueue(testInput)

    @Test
    fun `should get the correct answer for part 1 with test input`() {
        assertThat(testSubject.part1()).isEqualTo(143)
    }

    @Test
    fun `should get the correct answer for part 2 with test input`() {
        assertThat(testSubject.part2()).isEqualTo(123)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(PrintQueue(readInput(5)).part1())
            .isEqualTo(5991)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(PrintQueue(readInput(5)).part2())
            .isEqualTo(5479)
    }
}
