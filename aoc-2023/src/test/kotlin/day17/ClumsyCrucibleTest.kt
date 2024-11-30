package day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClumsyCrucibleTest {
    private val testInput = """2413432311323
3215453535623
3255245654254
3446585845452
4546657867536
1438598798454
4457876987766
3637877979653
4654967986887
4564679986453
1224686865563
2546548887735
4322674655533""".lines()

    private val testSubject = ClumsyCrucible(testInput)

    @Test
    fun `should`() {
        assertThat(testSubject.part1()).isEqualTo(102)
    }

    @Test
    fun `should get correct answer for part 1`() {
        // assertThat(ClumsyCrucible(readInputLines(17)).part1())
        //    .isEqualTo()
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(ClumsyCrucible(readInputLines(17)).part2())
        //    .isEqualTo()
    }
}
