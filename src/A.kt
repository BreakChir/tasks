import java.io.*
import java.util.*

private const val size = 'z' - 'a' + 1

fun main(args: Array<String>) {
    var maxLen = 0
    val nameList = Array<String>(nextInt()) {
        val name = next()
        if (maxLen > name.length) {
            println("Impossible")
            return
        }
        maxLen = name.length
        name
    }
    val letters = setOrder(nameList, maxLen)
    if (isCycle(letters)) {
        println("Impossible")
    } else {
        topologicalSort(letters).forEach { c ->
            print('a' + c)
        }
    }
}

class Letter {
    val next = arrayOfNulls<Letter>(size)
}

private fun setOrder(nameList: Array<String>, len: Int): Array<Letter> {
    val letters = Array(size) { Letter() }
    var startLine = 0
    for (i in 0 until len) {
        while (i >= nameList[startLine].length) {
            startLine++
        }
        for (line in startLine + 1 until nameList.size) {
            val symbol = nameList[line - 1][i] - 'a'
            val other = nameList[line][i] - 'a'
            if (other != symbol) {
                val from = letters[symbol]
                val to = letters[other]
                from.next[other] = to
            }
        }
    }
    return letters
}

private fun isCycle(letters: Array<Letter>): Boolean {
    val color = IntArray(letters.size)

    fun dfs(v: Int): Boolean {
        color[v] = 1
        val next = letters[v].next
        for (u in 0 until next.size) {
            if (next[u] != null) {
                if (color[u] == 1 || color[u] == 0 && dfs(u)) {
                    return true
                }
            }
        }
        color[v] = 2
        return false
    }

    for (i in 0 until letters.size) {
        if (color[i] == 0 && dfs(i)) {
            return true
        }
    }
    return false
}

private fun topologicalSort(letters: Array<Letter>): ArrayList<Int> {
    val isVisit = BooleanArray(letters.size)
    val res = ArrayList<Int>(letters.size)

    fun dfs(v: Int) {
        isVisit[v] = true
        val next = letters[v].next
        for (u in 0 until next.size) {
            if (next[u] != null) {
                if (!isVisit[u]) {
                    dfs(u)
                }
            }
        }
        res.add(v)
    }

    for (v in 0 until letters.size) {
        if (!isVisit[v]) {
            dfs(v)
        }
    }
    res.reverse()
    return res
}

// InputReader

private val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
private var st: StringTokenizer? = null

private fun next(): String {
    while (st == null || !st!!.hasMoreTokens()) {
        st = StringTokenizer(br.readLine())
    }
    return st!!.nextToken()
}

private fun nextInt(): Int {
    return Integer.parseInt(next())
}

