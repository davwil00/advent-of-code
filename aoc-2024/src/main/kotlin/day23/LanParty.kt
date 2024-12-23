package day23

import utils.readInputLines
import java.util.LinkedList

class LanParty(private val input: List<String>) {
    fun part1(): Int {
        val map = buildBidirectionalConnectionsMap()
        val linkedComputers = findLinkedComputersOfThree(map)
        val setsContainingNamesStartingWithT = linkedComputers
            .filter { set -> set.any { it.startsWith("t") } }
        return setsContainingNamesStartingWithT.size
    }

    fun part2(): String {
        val map = buildBidirectionalConnectionsMap()
        val linkedComputers = findLinkedComputers(map)
        val fullyNetworkedComputers = linkedComputers.filter { checkLinks(LinkedList(it), map) }
        return fullyNetworkedComputers.maxByOrNull { it.size }!!.joinToString(",")
    }

    private fun buildBidirectionalConnectionsMap(): Map<String, Set<String>> {
        return buildMap {
            input.forEach { connection ->
                val (node1, node2) = connection.split("-")
                merge(node1, setOf(node2)) { current, _ -> current + node2 }
                merge(node2, setOf(node1)) { current, _ -> current + node1 }
            }
        }
    }

    private fun findLinkedComputersOfThree(map: Map<String, Set<String>>): Set<Set<String>> {
        return buildSet {
            map.entries.forEach { (key, nodes) ->
                nodes.forEach { node ->
                    val intersection = map[node]?.intersect(nodes)
                    intersection?.forEach { intersectedNode ->
                        add(setOf(intersectedNode, node, key))
                    }
                }
            }
        }
    }

    private fun findLinkedComputers(map: Map<String, Set<String>>): Set<Set<String>> {
        return buildSet {
            map.entries.forEach { (key, nodes) ->
                nodes.forEach { node ->
                    val intersection = map[node]?.intersect(nodes)
                    if (intersection?.isNotEmpty() == true) {
                        add((intersection + node + key).toSortedSet())
                    }
                }
            }
        }
    }

    private tailrec fun checkLinks(computers: LinkedList<String>, map: Map<String, Set<String>>): Boolean {
        if (computers.size == 1) {
            return true
        }

        val computerToCheck = computers.removeFirst()
        return if (computers.all { map.getValue(computerToCheck).contains(it) }) {
            checkLinks(computers, map)
        } else false
    }
}

fun main() {
    val lanParty = LanParty(readInputLines(23))
    println(lanParty.part1())
    println(lanParty.part2())
}
