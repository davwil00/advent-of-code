package day23

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInputLines

class LanPartyTest {
    private val testInput = """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
    """.trimIndent().lines()

    private val testSubject = LanParty(testInput)

    @Test
    fun `should find linked computers for test input`() {
        assertThat(testSubject.part1()).isEqualTo(7)
    }

    @Test
    fun `should find largest set of computers for test input`() {
        assertThat(testSubject.part2()).isEqualTo("co,de,ka,ta")
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(LanParty(readInputLines(23)).part1())
            .isEqualTo(1218)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(LanParty(readInputLines(23)).part2())
            .isEqualTo("ah,ap,ek,fj,fr,jt,ka,ln,me,mp,qa,ql,zg")
    }
}
