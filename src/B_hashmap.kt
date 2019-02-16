import java.io.*
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val names = HashMap<String, Int>()
    val n = nextInt()
    for (i in 0 until n) {
        val name = next()
        val num = names.getOrDefault(name, 0)
        names[name] = num + 1
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

