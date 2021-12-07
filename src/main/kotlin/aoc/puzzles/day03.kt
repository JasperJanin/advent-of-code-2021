package aoc.puzzles

import java.io.File

fun diagnostic1(inputFile: String): Int {
    var length = File(inputFile).readLines()[0].length
    var count = 0
    val bitIndex = mutableMapOf<Int, Int>()
    for (i in 0 until length) bitIndex[i] = 0
    File(inputFile).forEachLine {
        count++
        it.forEachIndexed{i, element ->
            if (element == '1') bitIndex[i] = bitIndex[i]!!.plus(1)
        }
    }
    var res = 0
    for (i in 0 until length) {
        res = res shl 1
        if (bitIndex[i]!! >= count/2) res += 1
    }
    return res * ((1 shl length)-res-1)
}

fun diagnostic2(inputFile: String): Int {
    val oxygen = mutableSetOf<String>()
    val co2 = mutableSetOf<String>()
    File(inputFile).forEachLine { oxygen.add(it); co2.add(it) }
    var index = 0
    while (oxygen.size > 1) {
        var one = 0
        var zero = 0
        oxygen.forEach { line ->
            if (line[index]  == '1') one++ else zero++
        }
        val correct = if (one >= zero) '1' else '0'
        oxygen.removeIf{ value -> (value[index] != correct) }
        index++
    }
    index = 0
    while (co2.size > 1) {
        var one = 0
        var zero = 0
        co2.forEach { line ->
            if (line[index]  == '1') one++ else zero++
        }
        val correct = if (one >= zero) '0' else '1'
        co2.removeIf{ value -> (value[index] != correct) }
        index++
    }
    println("${oxygen.iterator().next()} : ${co2.iterator().next()}")
    println("${Integer.parseInt(oxygen.iterator().next(), 2)} : ${Integer.parseInt(co2.iterator().next(), 2)}")
    return (Integer.parseInt(oxygen.iterator().next(), 2))*(Integer.parseInt(co2.iterator().next(), 2))
}

fun main() {
    println("Solution for puzzle 1: ${diagnostic1("inputs/day03")}\nSolution for puzzle 2: ${diagnostic2("inputs/day03")}")
}