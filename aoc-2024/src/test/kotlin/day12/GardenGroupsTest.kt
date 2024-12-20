package day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readGrid
import utils.readInputLines

class GardenGroupsTest {
    private val simpleTestInput = """
        AAAA
        BBCD
        BBCC
        EEEC
    """.trimIndent().lines()

    private val complexTestInput = """
        OOOOO
        OXOXO
        OOOOO
        OXOXO
        OOOOO
    """.trimIndent().lines()

    private val largerInput = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent().lines()

    @Test
    fun `should calculate the price of the fence for the simple input`() {
        val testSubject = GardenGroups(readGrid(simpleTestInput))
        assertThat(testSubject.part1()).isEqualTo(140)
    }

    @Test
    fun `should calculate the price of the fence for the complex input`() {
        val testSubject = GardenGroups(readGrid(complexTestInput))
        assertThat(testSubject.part1()).isEqualTo(772)
    }

    @Test
    fun `should calculate the price of the fence for the larger input`() {
        val testSubject = GardenGroups(readGrid(largerInput))
        assertThat(testSubject.part1()).isEqualTo(1930)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(GardenGroups(readGrid(readInputLines(12))).part1())
            .isEqualTo(1533644)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(GardenGroups(readGrid(readInputLines(12))).part2())
        //    .isEqualTo()
    }
}
