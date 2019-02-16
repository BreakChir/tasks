import java.io.*
import java.util.*

private val head = Trie()
private const val size = 'z' - 'a' + 1

class Trie {
    var count = 0
    val edges = arrayOfNulls<Pair<String, Trie>>(size)
}

private fun insertNode(name: String): Int {
    var curNode = head
    var pos = 0
    while (pos < name.length) {
        val letter = name[pos] - 'a'
        val edge = curNode.edges[letter] ?: run {
            curNode.edges[letter] = Pair(name.substring(pos), Trie())
            return 0
        }
        val str = edge.first
        val next = edge.second

        var i = 1
        while (i < str.length && pos + i < name.length && str[i] == name[pos + i]) {
            i++
        }
        if (i == str.length && pos + i == name.length) {
            return ++next.count
        } else if (i < str.length) {
            val newNode = Trie()
            newNode.edges[str[i] - 'a'] = Pair(str.substring(i), next)
            curNode.edges[letter] = Pair(str.substring(0, i), newNode)
            return 0
        } else {
            curNode = next
            pos += i
        }
    }
    // unreachable
    return -1
}

fun main(args: Array<String>) {
    val n = nextInt()
    for (i in 0 until n) {
        val name = next()
        val num = insertNode(name)
        if (num == 0) {
            println("OK")
        } else {
            println(name + num)
        }
    }
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

