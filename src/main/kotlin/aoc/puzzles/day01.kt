package aoc.puzzles

import java.io.File

fun sonar1(inputFile: String): Int {
    var prev = 99999999
    var count = 0
    File(inputFile).forEachLine {
        if (it.toInt() > prev) count++
        prev = it.toInt()
    }
    return count
}

fun addSonarInput(list: MutableList<Int>, input: Int) {
    list[0] = list[1]
    list[1] = list[2]
    list[2] = input
}

fun sonar2(inputFile: String): Int {
    var numbers = mutableListOf(0, 0, 0)
    var prevSum = 99999999
    var count = -2
    File(inputFile).forEachLine {
        addSonarInput(numbers, it.toInt())
        if (numbers.sum() > prevSum) count++
        prevSum = numbers.sum()
    }
    return count
}

fun main() {
    println("Solution for puzzle 1: ${sonar1("inputs/day01")}\nSolution for puzzle 2: ${sonar2("inputs/day01")}")
}