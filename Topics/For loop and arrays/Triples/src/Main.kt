fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (idx in 0..intArray.lastIndex) {
        intArray[idx] = readLine()!!.toInt()
    }

    var triples = 0

    for (idx in 0 until intArray.lastIndex - 1) {
        if (intArray[idx + 1] == intArray[idx] + 1 && intArray[idx + 2] == intArray[idx] + 2) {
            ++triples
        }
    }

    println(triples)
}
