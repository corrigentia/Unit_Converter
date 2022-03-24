fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    val (p, m) = readLine()!!.split(" ").map { it.toInt() }

    var nonAdjacent = true
    for (idx in 0 until intArray.lastIndex) {
        if (intArray[idx] == p && intArray[idx + 1] == m || intArray[idx] == m && intArray[idx + 1] == p) {
            nonAdjacent = false
            break
        }
    }

    println(
        if (nonAdjacent) "YES" else "NO"
    )
}
