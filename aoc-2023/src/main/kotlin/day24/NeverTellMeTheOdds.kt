package day24

import utils.generateCombinations
import utils.readInputLines

class NeverTellMeTheOdds(input: List<String>) {

    private val hailstones = parseInput(input)

    fun part1(minRange: Long = 200000000000000, maxRange: Long = 400000000000000): Int {
        val range = minRange .. maxRange
        return generateCombinations(hailstones).mapNotNull { (hailstone1, hailstone2) ->
            val intersection = hailstone1.findIntersectionWith(hailstone2)
            if (isInFuture(hailstone1, intersection) && isInFuture(hailstone2, intersection)) {
                intersection
            } else {
                null
            }
        }.count { it.first.toLong() in range && it.second.toLong() in range }
    }

    private fun isInFuture(hailstone: Hailstone, intersection: Pair<Double, Double>?): Boolean {
        if (intersection == null) return false

        return isInFuture(hailstone.position.x, hailstone.velocity.x, intersection.first) &&
                isInFuture(hailstone.position.y, hailstone.velocity.y, intersection.second)
    }

    private fun isInFuture(position: Long, velocity: Long, intersection: Double) = ((intersection - position) / velocity) > 0

    private fun parseInput(input: List<String>): List<Hailstone> {
        return input.map { line ->
            val (position, velocity) = line.split(" @ ")
            val (px, py, pz) = position.split(", ").map { it.trim().toLong() }
            val (vx, vy, vz) = velocity.split(", ").map { it.trim().toLong() }

            Hailstone(LongCoordinate3D(px, py, pz), LongCoordinate3D(vx, vy, vz))
        }
    }

    data class Hailstone(val position: LongCoordinate3D, val velocity: LongCoordinate3D) {
        private val m = velocity.y.toDouble() / velocity.x.toDouble()
        private val c = position.y - (m * position.x)

        fun findIntersectionWith(other: Hailstone): Pair<Double, Double>? {
            val xMultiplier = (m - other.m) * -1
            if (xMultiplier == 0.0) {
                return null
            }
            val totalC = c - other.c
            val x = totalC / xMultiplier
            val y = (m * x) + c
            return Pair(x, y)
        }
    }

    data class LongCoordinate3D(val x: Long, val y: Long, val z: Long)
}

fun main() {
    val neverTellMeTheOdds = NeverTellMeTheOdds(readInputLines(24))
    println(neverTellMeTheOdds.part1())
    //println(neverTellMeTheOdds.part2())
}
