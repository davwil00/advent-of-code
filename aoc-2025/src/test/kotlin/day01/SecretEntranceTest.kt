package day01

import day01.SecretEntrance.Instruction
import day01.SecretEntrance.PositionAndRotations
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import utils.readInputLines

class SecretEntranceTest {
    private val testInput = """
        L68
        L30
        R48
        L5
        R60
        L55
        L1
        L99
        R14
        L82
    """.trimIndent().lines()

    private val testSubject = SecretEntrance(testInput)

    @Test
    fun `should find password for test input`() {
        assertThat(testSubject.part1()).isEqualTo(3)
    }

    @Test
    fun `should find password for test input using password method 0x434C49434B`() {
        assertThat(testSubject.part2()).isEqualTo(6)
    }

    @ParameterizedTest
    @CsvSource(
        "50,L,68,82,1",
        "0,L,5,95,0",
        "95,R,60,55,1",
        "55,L,55,0,1",
        "55,R,45,0,1"
    )
    fun `should find correct position and rotations`(start: Int, direction: Char, points: Int, end: Int, rots: Int) {
        assertThat(testSubject.moveDial(start, Instruction(direction, points))).isEqualTo(PositionAndRotations(end, rots))
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(SecretEntrance(readInputLines(1)).part1())
            .isEqualTo(1023)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(SecretEntrance(readInputLines(1)).part2())
            .isEqualTo(5899)
    }
}
