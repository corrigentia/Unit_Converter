fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    val (p, m) = readLine()!!.split(" ").map { it.toInt() }
    var pPresent = false
    var mPresent = false
    for (number in intArray) {
        if (number == p) pPresent = true
        if (number == m) mPresent = true
        if (pPresent && mPresent) break
    }

    println(
        if (pPresent && mPresent) "YES" else "NO"
    )
}
