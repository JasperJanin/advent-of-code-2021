package aoc.puzzles

import java.io.File

class Bingo {

    private val board: MutableList<MutableList<Int>> = mutableListOf()
    private val marks = Array(5) { Array(5) { false } }

    var won = false

    fun addRow(row: String) {
        board.add(
            row
                .split(" ")
                .filter { it != "" }
                .map { it.toInt() }
                .toMutableList()
        )
    }

    fun mark(bingoNumber: Int) {
        board.forEachIndexed { rowNumber, row ->
            row.forEachIndexed { columnNumber, boardNumber ->
                if (bingoNumber == boardNumber) {
                    marks[rowNumber][columnNumber] = true
                    updateWin(rowNumber, columnNumber)
                }
            }
        }
    }

    private fun updateWin(row: Int, column: Int) {
        var horWin = true
        var verWin = true

        for (newRow in 0..4) {
            if (!marks[newRow][column]) {
                verWin = false
                break
            }
        }
        for (newColumn in 0..4) {
            if (!marks[row][newColumn]) {
                horWin = false
                break
            }
        }
        won = horWin || verWin
    }

    fun winScore(number: Int): Int {
        if (!won) return 0

        var score = 0
        marks.forEachIndexed { r, row ->
            row.forEachIndexed { c, mark ->
                if (!mark) score += board[r][c]
            }
        }
        return score * number
    }
}

fun bingo1(inputFile: String): Int {
    val lines = File(inputFile).readLines()
    val bingoNumbers = lines[0].split(",").map { it.toInt() }
    val bingoBoards: MutableList<Bingo> = mutableListOf()

    var currentBingo: Bingo? = null
    lines.forEach {
        if (it == "") {
            currentBingo = Bingo()
            bingoBoards.add(currentBingo!!)
        } else currentBingo?.addRow(it)
    }

    bingoNumbers.forEach { bingoNumber ->
        bingoBoards.forEach { bingoBoard ->
            bingoBoard.mark(bingoNumber)
            if (bingoBoard.won) return bingoBoard.winScore(bingoNumber)
        }
    }
    return 0
}

fun bingo2(inputFile: String): Int {
    val lines = File(inputFile).readLines()
    val bingoNumbers = lines[0].split(",").map { it.toInt() }
    val bingoBoards: MutableList<Bingo> = mutableListOf()

    var currentBingo: Bingo? = null
    lines.forEach {
        if (it == "") {
            currentBingo = Bingo()
            bingoBoards.add(currentBingo!!)
        } else currentBingo?.addRow(it)
    }

    var winCount = 0

    bingoNumbers.forEach { bingoNumber ->
        bingoBoards.forEach { bingoBoard ->
            if (!bingoBoard.won) {
                bingoBoard.mark(bingoNumber)
                if (bingoBoard.won) winCount++
                if (bingoBoard.won && winCount == bingoBoards.size) return bingoBoard.winScore(bingoNumber)
            }
        }
    }
    return 0
}

fun main() {
    println("Solution for puzzle 1: ${bingo1("inputs/day4")}\nSolution for puzzle 2: ${bingo2("inputs/day4")}")

}