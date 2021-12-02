package aoc.puzzles.day2

import java.io.File

fun solve(inputFile: String): Int {
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
