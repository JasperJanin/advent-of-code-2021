package aoc.puzzles

import java.io.File
import kotlin.math.abs
import kotlin.math.min

val crabsOneLine: (String, Int) -> Int =  { inputFile: String, part: Int -> File(inputFile).readLines()[0].split(",").map { it.toInt() }.let { input -> (input.minOrNull()!!..input.maxOrNull()!!).toList().map { pos -> input.sumOf { if (part == 1) abs(it - pos) else (abs(it - pos) * (abs(it - pos)+1)/2) } } }.minOrNull()!! }

fun crabs(inputFile: String, part: Int): Int {
    val input = File(inputFile).readLines()[0].split(",").map { it.toInt() }
    var minFuel = 999999999
    for (pos in input.minOrNull()!!..input.maxOrNull()!!) {
        minFuel = min(input.sumOf { if (part == 1) abs(it - pos) else (abs(it - pos) * (abs(it - pos)+1)/2) }, minFuel)
    }
    return minFuel
}

fun main() {
    println("Solution 1: ${crabs("inputs/day07", 1)}\nSolution 2: ${crabs("inputs/day07", 2)}")
}