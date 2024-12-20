package day09

import day09.DiskFragmentor.FreeSpaceBlock
import day09.DiskFragmentor.IdBlock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput
import utils.readInputLines
import utils.splitToString

class DiskFragmentorTest {

    private val testSubject = DiskFragmentor("")

    @Test
    fun `should expand a simple block`() {
        assertThat(testSubject.expandBlock("12345"))
            .isEqualTo("0..111....22222".toBlocks())
    }

    @Test
    fun `should expand a complex block`() {
        assertThat(testSubject.expandBlock("2333133121414131402"))
            .isEqualTo("00...111...2...333.44.5555.6666.777.888899".toBlocks())
    }

    @Test
    fun `should fragment simple block correctly`() {
        assertThat(testSubject.runFragmentor("0..111....22222".toBlocks()))
            .isEqualTo("022111222".toBlocks())
    }

    @Test
    fun `should fragment complex block correctly`() {
        assertThat(testSubject.runFragmentor("00...111...2...333.44.5555.6666.777.888899".toBlocks()))
            .isEqualTo("0099811188827773336446555566".toBlocks())
    }

    @Test
    fun `should calculate checksum correctly`() {
        assertThat(testSubject.calculateChecksum("0099811188827773336446555566".toBlocks()))
            .isEqualTo(1928)
    }

    @Test
    fun `should get correct answer for part 1`() {
        assertThat(DiskFragmentor(readInput(9)).part1())
            .isEqualTo(6435922584968)
    }

    @Test
    fun `should expand part 2 correctly`() {
        assertThat(DiskFragmentor("2333133121414131402").part2())
            .isEqualTo(2858)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(DiskFragmentor(readInput(9)).part2())
            .isEqualTo(6469636832766)
    }

    private fun String.toBlocks(): List<DiskFragmentor.Block> {
        return splitToString().map { if (it == ".") FreeSpaceBlock else IdBlock(it.toLong()) }
    }
}
