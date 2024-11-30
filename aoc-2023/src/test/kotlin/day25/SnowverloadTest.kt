package day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SnowverloadTest {
    private val testInput = """jqt: rhn xhk nvd
rsh: frs pzl lsr
xhk: hfx
cmg: qnr nvd lhk bvb
rhn: xhk bvb hfx
bvb: xhk hfx
pzl: lsr hfx nvd
qnr: nvd
ntq: jqt hfx bvb xhk
nvd: lhk
lsr: lhk
rzs: qnr cmg lsr rsh
frs: qnr lhk lsr""".lines()

    private val testSubject = Snowverload(testInput)

    @Test
    fun `should`() {
        testSubject.part1()
    }

    @Test
    fun `should get correct answer for part 1`() {
        // assertThat(Snowverload(readInputLines(25)).part1())
        //    .isEqualTo()
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(Snowverload(readInputLines(25)).part2())
        //    .isEqualTo()
    }
}
