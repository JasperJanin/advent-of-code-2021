package aoc.puzzles

import java.io.File

fun solve1(inputFile: String): Int {
    var posX = 0
    var posY = 0
    File(inputFile).forEachLine {
        val list = it.split(" ")
        when (list[0]) {
            "forward" -> posX += list[1].toInt()
            "up" -> posY -= list[1].toInt()
            "down" -> posY += list[1].toInt()
        }
    }
    return posX * posY
}

fun solve2(inputFile: String): Int {
    var posX = 0
    var posY = 0
    var aim = 0
    File(inputFile).forEachLine {
        val list = it.split(" ")
        when (list[0]) {
            "forward" -> {
                posX += list[1].toInt()
                posY += aim * list[1].toInt()
            }
            "up" -> aim -= list[1].toInt()
            "down" -> aim += list[1].toInt()
        }
    }
    return posX * posY
}

fun main() {
    println("Solution for puzzle 1: ${solve1("inputs/day2")}\nSolution for puzzle 2: ${solve2("inputs/day2")}")
}