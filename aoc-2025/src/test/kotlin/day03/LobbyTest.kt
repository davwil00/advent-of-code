package day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class LobbyTest {
    private val testInput = """
        987654321111111
        811111111111119
        234234234234278
        818181911112111
    """.trimIndent().lines()

    private val testSubject = Lobby(testInput)

    @Test
    fun `should find jotage for single battery`() {
        assertThat(Lobby(listOf("987654321111111")).part1()).isEqualTo(98)
    }

    @Test
    fun `should find joltage sum`() {
        assertThat(testSubject.part1()).isEqualTo(357)
    }

    @Test
    fun `should find joltage sum for part 2`() {
        assertThat(testSubject.part2()).isEqualTo(3121910778619)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(Lobby(readInputLines(3)).part1()).isEqualTo(17244)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(Lobby(readInputLines(3)).part2())
            .isEqualTo(171435596092638)
    }
}
