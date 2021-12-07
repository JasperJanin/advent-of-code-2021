package aoc.puzzles

import java.io.File

class Fishies(fishiesString: String) {

    private val fishies = mutableMapOf<Int, Long>()

    init {
        for (i in 0..8) fishies[i] = 0

        fishiesString
            .split(",")
            .map { it.toInt() }
            .forEach {
                fishies[it] = fishies[it]!!.plus(1)
            }
    }

    fun amount(): Long {
        return fishies.values.sum()
    }

    fun update() {
        var save = 0L
        for (i in 8 downTo 0) {
            val temp = fishies[i]!!
            fishies[i] = save
            save = temp
        }
        fishies[6] = fishies[6]!!.plus(save)
        fishies[8] = save
    }
}

fun fishies(inputFile: String, repetitions: Int): Long {
    val fishies = Fishies(File(inputFile).readText())
    repeat(repetitions) { fishies.update() }
    return fishies.amount()
}

fun main() {
    println("Solution 1: ${fishies("inputs/day06", 80)}\nSolution 2: ${fishies("inputs/day06", 256)}")
}