package day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class TrashCompactorTest {
    private val testInput = """
        123 328  51 64 
         45 64  387 23 
          6 98  215 314
        *   +   *   +  
    """.trimIndent().lines()

    private val testSubject = TrashCompactor(testInput)

    @Test
    fun `should solve problems`() {
        assertThat(testSubject.part1()).isEqualTo(4277556)
    }

    @Test
    fun `should solve problems part2`() {
        assertThat(testSubject.part2()).isEqualTo(3263827)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(TrashCompactor(readInputLines(6)).part1())
            .isEqualTo(4412382293768)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(TrashCompactor(readInputLines(6)).part2())
            .isEqualTo(7858808482092)
    }
}
