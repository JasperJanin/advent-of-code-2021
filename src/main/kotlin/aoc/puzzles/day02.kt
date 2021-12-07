package aoc.puzzles

import java.io.File

fun dive1(inputFile: String): Int {
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

fun dive2(inputFile: String): Int {
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
    println("Solution for puzzle 1: ${dive1("inputs/day02")}\nSolution for puzzle 2: ${dive2("inputs/day02")}")
}