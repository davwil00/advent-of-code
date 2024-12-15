package day09

import utils.isEven
import utils.readInput
import utils.splitToString
import java.util.LinkedList

class DiskFragmentor(private val input: String) {

    fun expandBlock(block: String): List<Block> {
        return block.splitToString().flatMapIndexed { idx, count ->
            val blockCount = count.toInt()
            if (idx.isEven()) {
                List(blockCount) { IdBlock(idx/2L) }
            } else {
                List(blockCount) {FreeSpaceBlock() }
            }
        }
    }

    fun runFragmenter(blocks: List<Block>): List<Block> {
        val compactedList = LinkedList<Block>(blocks)
        while(compactedList.count { it is FreeSpaceBlock } > 0 ) {
            val currentBlock = compactedList.removeLast()
            if (currentBlock !is FreeSpaceBlock) {
                val spaceIdx = compactedList.indexOfFirst { it is FreeSpaceBlock }
                compactedList[spaceIdx] = currentBlock
            }
        }
        return compactedList
    }

    fun calculateChecksum(blocks: List<Block>): Long {
        return blocks.mapIndexed { idx, block ->
            (block as IdBlock).id * idx
        }.sum()
    }

    fun part1(): Long {
        val expandedBlock = expandBlock(input)
        val fragmented = runFragmenter(expandedBlock)
//        println(fragmented.joinToString(""))
        return calculateChecksum(fragmented)
    }

    interface Block
    class FreeSpaceBlock: Block
    class IdBlock(val id: Long): Block
}

fun main() {
    val diskfragmentor = DiskFragmentor(readInput(9))
    println(diskfragmentor.part1())
    //println(diskfragmentor.part2())
}
