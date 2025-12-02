package day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.readInput
import utils.readInputLines

class GiftShopTest {
    private val testInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

    private val testSubject = GiftShop(testInput)

    @Test
    fun `should find invalid ids for test input`() {
        assertThat(testSubject.part1()).isEqualTo(1227775554)
    }

    @Test
    fun `should find invalid ids for test input and part 2`() {
        assertThat(testSubject.part2()).isEqualTo(4174379265)
    }

    @Test
    fun `should get correct answer for part 1`() {
         assertThat(GiftShop(readInput(2)).part1())
            .isEqualTo(23534117921)
    }

    @Test
    fun `should get correct answer for part 2`() {
         assertThat(GiftShop(readInput(2)).part2())
            .isEqualTo(31755323497)
    }
}
