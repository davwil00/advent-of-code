package day22

import utils.Coordinate3D
import utils.readInputLines

class SandSlabs(input: List<String>) {
    private val stackSnapshot = parseInput(input)
    private val locationsAfterFalling = simulateFall(stackSnapshot.sortedBy { it.startCoordinate.z })

    fun part1(): Int {
        return locationsAfterFalling.count { brick ->
            val snapshotWithoutBrick = locationsAfterFalling.filter { it != brick }
            !wouldAnyBricksDrop(snapshotWithoutBrick)
        }
    }

    fun part2(): Int {
        return locationsAfterFalling.sumOf { brick ->
            countNumberOfBricksThatCanFallWithout(brick)
        }
    }

    private fun simulateFall(currentSnapshot: List<Brick>): List<Brick> {
        val newSnapshot = mutableListOf<Brick>()
        currentSnapshot.forEach { brick ->
            newSnapshot.add(dropBrick(newSnapshot, brick))
        }

        return newSnapshot
    }

    private tailrec fun dropBrick(currentSnapshot: List<Brick>, brickToDrop: Brick): Brick {
        if (brickToDrop.startCoordinate.z == 1) {
            return brickToDrop
        }
        val otherBricks = currentSnapshot.filter { it != brickToDrop && it.startCoordinate.z <= it.startCoordinate.z }
        val newBrickLocation = brickToDrop.drop()
        return if (otherBricks.any { it.intersectsWith(newBrickLocation)} ) {
            brickToDrop
        } else {
            dropBrick(currentSnapshot, newBrickLocation)
        }
    }

    private fun canBrickDrop(currentSnapshot: List<Brick>, brickToDrop: Brick): Boolean {
        if (brickToDrop.startCoordinate.z == 1) {
            return false
        }

        val otherBricks = currentSnapshot.filter { it != brickToDrop && it.startCoordinate.z <= it.startCoordinate.z }
        val newBrickLocation = brickToDrop.drop()
        return otherBricks.none { it.intersectsWith(newBrickLocation)}
    }

    private fun wouldAnyBricksDrop(snapshot: List<Brick>): Boolean {
        return snapshot.any { brick -> canBrickDrop(snapshot, brick) }
    }

    private fun countNumberOfBricksThatCanFallWithout(brickToTest: Brick): Int {
        val snapshotWithoutBrick = locationsAfterFalling.filter { it != brickToTest }
        val resultWithBrickRemoved = simulateFall(snapshotWithoutBrick)
        return (snapshotWithoutBrick - resultWithBrickRemoved.toSet()).size
    }

    private fun parseInput(input: List<String>): List<Brick> {
        return input.map { line ->
            val (start, end) = line.split('~')
            val (startX, startY, startZ) = start.split(',').map { it.toInt() }
            val startCoordinate = Coordinate3D(startX, startY, startZ)
            val (endX, endY, endZ) = end.split(',').map { it.toInt() }
            val endCoordinate = Coordinate3D(endX, endY, endZ)

            Brick(startCoordinate, endCoordinate)
        }
    }

    data class Brick(val startCoordinate: Coordinate3D, val endCoordinate: Coordinate3D) {
        private fun zRange() = startCoordinate.z .. endCoordinate.z
        private fun yRange() = startCoordinate.y .. endCoordinate.y
        private fun xRange() = startCoordinate.x .. endCoordinate.x

        fun intersectsWith(other: Brick): Boolean {
            return (zRange() overlapsWith other.zRange() && xRange() overlapsWith other.xRange() && yRange() overlapsWith other.yRange())
        }

        fun drop(): Brick {
            return this.copy(startCoordinate.copy(z = startCoordinate.z -1), endCoordinate.copy(z = endCoordinate.z - 1))
        }
    }

}

infix fun IntRange.overlapsWith(other: IntRange): Boolean {
    return this.toSet().intersect(other.toSet()).isNotEmpty()
}

fun main() {
    val sandSlabs = SandSlabs(readInputLines(22))
    println(sandSlabs.part1())
    println(sandSlabs.part2()) // too low 75878
}
