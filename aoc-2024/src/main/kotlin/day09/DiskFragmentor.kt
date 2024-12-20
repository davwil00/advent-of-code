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
                List(blockCount) { IdBlock(idx / 2L) }
            } else {
                List(blockCount) { FreeSpaceBlock }
            }
        }
    }

    private fun expandBlocksWithSize(block: String): List<BlockWithSize> {
        return block.splitToString().mapIndexedNotNull { idx, count ->
            val blockCount = count.toInt()
            if (blockCount != 0) {
                if (idx.isEven()) {
                    IdBlockWithSize(idx / 2L, blockCount)
                } else {
                    FreeSpaceBlockWithSize(blockCount)
                }
            } else null
        }
    }

    fun runFragmentor(blocks: List<Block>): List<Block> {
        val compactedList = LinkedList(blocks)
        while (compactedList.count { it is FreeSpaceBlock } > 0) {
            val currentBlock = compactedList.removeLast()
            if (currentBlock !is FreeSpaceBlock) {
                val spaceIdx = compactedList.indexOfFirst { it is FreeSpaceBlock }
                compactedList[spaceIdx] = currentBlock
            }
        }
        return compactedList
    }

    private tailrec fun runFragmentor(blocks: LinkedList<BlockWithSize>, id: Long): List<BlockWithSize> {
        if (id == 0L) {
            return blocks
        }
        val idx = blocks.indexOfFirst { it is IdBlockWithSize && it.id == id }
        val sizeOfBlockToProcess = blocks[idx].size
        val freeSpaceIdx = blocks.indexOfFirst { it is FreeSpaceBlockWithSize && it.size >= sizeOfBlockToProcess }
        if (freeSpaceIdx in 0 until idx) {
            val blockToProcess = blocks.removeAt(idx)
            blocks.add(idx, FreeSpaceBlockWithSize(blockToProcess.size))
            val spaceAvailable = blocks[freeSpaceIdx].size
            blocks.removeAt(freeSpaceIdx)
            if (spaceAvailable > sizeOfBlockToProcess) {
                blocks.add(freeSpaceIdx, FreeSpaceBlockWithSize(spaceAvailable - sizeOfBlockToProcess))
            }
            blocks.add(freeSpaceIdx, blockToProcess)
        }
        return runFragmentor(blocks, id - 1)
    }

    fun calculateChecksum(blocks: List<Block>): Long {
        return blocks.mapIndexed { idx, block ->
            (block as IdBlock).id * idx
        }.sum()
    }

    private tailrec fun calculateChecksum(blocks: List<BlockWithSize>, blockIdx: Int = 0, positionIdx: Int = 0, sum: Long = 0): Long {
        if (blockIdx == blocks.size) {
            return sum
        }

        val block = blocks[blockIdx]
        return if (block is IdBlockWithSize) {
            val blockSum = (0 until block.size).sumOf { block.id * (positionIdx + it) }
            calculateChecksum(blocks, blockIdx + 1, positionIdx + block.size, sum + blockSum)
        } else {
            calculateChecksum(blocks, blockIdx + 1, positionIdx + block.size, sum)
        }
    }

    fun part1(): Long {
        val expandedBlock = expandBlock(input)
        val fragmented = runFragmentor(expandedBlock)
        return calculateChecksum(fragmented)
    }

    fun part2(): Long {
        val expandedBlock = LinkedList(expandBlocksWithSize(input))
        val fragmented = runFragmentor(expandedBlock, (expandedBlock.last as IdBlockWithSize).id)
        return calculateChecksum(fragmented)
    }

    interface Block
    object FreeSpaceBlock : Block
    data class IdBlock(val id: Long) : Block

    abstract class BlockWithSize(val size: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as BlockWithSize

            return size == other.size
        }

        override fun hashCode(): Int {
            return size
        }
    }

    class FreeSpaceBlockWithSize(size: Int) : BlockWithSize(size) {
        override fun toString(): String {
            return ".".repeat(size)
        }
    }

    class IdBlockWithSize(val id: Long, size: Int) : BlockWithSize(size) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false

            other as IdBlockWithSize

            return id == other.id
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + id.hashCode()
            return result
        }

        override fun toString(): String {
            return "$id".repeat(size)
        }
    }
}

fun main() {
    val diskFragmentor = DiskFragmentor(readInput(9))
    println(diskFragmentor.part1())
    println(diskFragmentor.part2())
}
