package aoc.puzzles

import java.io.File
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

class Input(input: String) {
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0
    var coord1 = Pair(0, 0)
    var coord2 = Pair(0, 0)

    init {
        val regex = "([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)".toRegex()
        regex.matchEntire(input)
            ?.destructured
            ?.toList()
            ?.let { (x1, y1, x2, y2) ->
                this.x1 = x1.toInt()
                this.y1 = y1.toInt()
                this.x2 = x2.toInt()
                this.y2 = y2.toInt()
                coord1 = Pair(this.x1, this.y1)
                coord2 = Pair(this.x2, this.y2)
            }
    }
}

class SeaFloor {

    private val map = mutableMapOf<Pair<Int, Int>, Int>()
    var dangerCount = 0

    private fun addToMap(coord: Pair<Int, Int>) {
        if (!map.contains(coord)) map[coord] = 0
        else if (map[coord] == 1) {
            dangerCount++
        }
        map[coord] = map[coord]!!.plus(1)
    }

    fun addLine(line: String, part: Int) {
        val input = Input(line)
        if (input.coord1 == input.coord2) addToMap(input.coord1)
        else if (input.x1 == input.x2) {
            for (y in min(input.y1, input.y2)..max(input.y1, input.y2)) addToMap(Pair(input.x1, y))
        } else if (input.y1 == input.y2) {
            for (x in min(input.x1, input.x2)..max(input.x1, input.x2)) addToMap(Pair(x, input.y1))
        } else if (part == 2 && abs(input.x2 - input.x1) == abs(input.y2 - input.y1)) {
            for (i in 0..abs(input.x2 - input.x1)) {
                val x = if (input.x1 < input.x2) input.x1 + i else input.x1 - i
                val y = if (input.y1 < input.y2) input.y1 + i else input.y1 - i
                addToMap(Pair(x, y))
            }
        }
    }
}

fun vents1(inputFile: String): Int {
    val seaFloor = SeaFloor()
    File(inputFile).forEachLine {
        seaFloor.addLine(it, 1)
    }
    return seaFloor.dangerCount
}

fun vents2(inputFile: String): Int {
    val seaFloor = SeaFloor()
    File(inputFile).forEachLine {
        seaFloor.addLine(it, 2)
    }
    return seaFloor.dangerCount
}

fun main() {
    println("Solution 1: ${vents1("inputs/day05")}\nSolution 2: ${vents2("inputs/day05")}")
}