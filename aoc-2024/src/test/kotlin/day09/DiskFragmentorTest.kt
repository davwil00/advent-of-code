package day09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput
import utils.splitToString

class DiskFragmentorTest {

    private val testSubject = DiskFragmentor("")

    @Test
    fun `should expand a simple block`() {
        assertThat(testSubject.expandBlock("12345")).isEqualTo("0..111....22222")
    }

    @Test
    fun `should expand a complex block`() {
        assertThat(testSubject.expandBlock("2333133121414131402")).isEqualTo("00...111...2...333.44.5555.6666.777.888899")
    }

//    @Test
//    fun `should fragment simple block correctly`() {
//        assertThat(testSubject.runFragmenter("0..111....22222")).isEqualTo("022111222")
//    }
//
//    @Test
//    fun `should fragment complex block correctly`() {
//        assertThat(testSubject.runFragmenter("00...111...2...333.44.5555.6666.777.888899")).isEqualTo("0099811188827773336446555566")
//    }
//
//    @Test
//    fun `should calculate checksum correctly`() {
//        assertThat(testSubject.calculateChecksum("0099811188827773336446555566".splitToString())).isEqualTo(1928)
//    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(DiskFragmentor(readInput(9)).part1())
            .isEqualTo(6435922584968)
    }

    @Test
    fun `should get correct answer for part 2`() {
        // assertThat(DiskFragmentor(readInputLines(9)).part2())
        //    .isEqualTo()
    }
}
