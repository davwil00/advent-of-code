package day24

import day24.NeverTellMeTheOdds.Hailstone
import day24.NeverTellMeTheOdds.LongCoordinate3D
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import utils.readInputLines

class NeverTellMeTheOddsTest {
    private val testInput = """19, 13, 30 @ -2,  1, -2
18, 19, 22 @ -1, -1, -2
20, 25, 34 @ -2, -2, -4
12, 31, 28 @ -1, -2, -1
20, 19, 15 @  1, -5, -3""".lines()

    private val testSubject = NeverTellMeTheOdds(testInput)

    @Test
    fun `should calculate where 2 lines intersect`() {
        val point1 = Hailstone(LongCoordinate3D(19, 13, 30), LongCoordinate3D(-2, 1, -2))
        val point2 = Hailstone(LongCoordinate3D(18, 19, 22), LongCoordinate3D(-1, -1, -2))
        val actual = point1.findIntersectionWith(point2)
        assertThat(actual?.first).isCloseTo(14.33, offset(0.01))
        assertThat(actual?.second).isCloseTo(15.33, offset(0.01))
    }

    @Test
    fun `should count number of hailstones which intersect in range`() {
        assertThat(testSubject.part1(7, 27)).isEqualTo(2)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(NeverTellMeTheOdds(readInputLines(24)).part1())
            .isEqualTo(15558)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(NeverTellMeTheOdds(readInputLines(24)).part2())
        //    .isEqualTo()
    }
}
