package day12

import utils.Coordinate
import utils.readGrid
import utils.readInputLines

class GardenGroups(private val map: Map<Coordinate, String>) {
    fun part1(): Int {
        val allCoordinatesInMap = map.keys.toMutableSet()
        val regions = exploreRegions(allCoordinatesInMap)
        return regions.sumOf { it.getPrice(map) }
    }

    private tailrec fun exploreRegions(coordinatesToExplore: Set<Coordinate>, regions: List<Region> = emptyList()): List<Region> {
        if (coordinatesToExplore.isEmpty()) {
            return regions
        }

        val nextCoordinateToExplore = coordinatesToExplore.first()
        val plantType = map.getValue(nextCoordinateToExplore)
        val region = exploreRegion(plantType, setOf(nextCoordinateToExplore))
        return exploreRegions(coordinatesToExplore - region.coordinates, regions + region)
    }

    private tailrec fun exploreRegion(plantType: String, coordinatesToExplore: Set<Coordinate>, regionCoordinates: Set<Coordinate> = emptySet()): Region {
        if (coordinatesToExplore.isEmpty()) {
            return Region(plantType, regionCoordinates)
        }

        val currentCoordinate = coordinatesToExplore.first()
        val adjacentCoordinatesInRegion = currentCoordinate.getAdjacentCoordinates()
            .filterNot { it in regionCoordinates }
            .filter { map[it] == plantType }
        return exploreRegion(plantType, coordinatesToExplore - currentCoordinate + adjacentCoordinatesInRegion, regionCoordinates + currentCoordinate)
    }

    data class Region(val plantType: String, val coordinates: Set<Coordinate>) {
        private fun calculatePerimeter(map: Map<Coordinate, String>): Int {
            return coordinates.sumOf { coordinate ->
                val adjacentCoordinates = coordinate.getAdjacentCoordinates(Int.MIN_VALUE, Int.MIN_VALUE)
                adjacentCoordinates.count {
                    map[it] == null || map[it] != plantType
                }
            }
        }

        fun getPrice(map: Map<Coordinate, String>) = calculatePerimeter(map) * coordinates.size
    }

}

fun main() {
    val gardenGroups = GardenGroups(readGrid(readInputLines(12)))
    println(gardenGroups.part1())
//    println(gardenGroups.part2())
}
