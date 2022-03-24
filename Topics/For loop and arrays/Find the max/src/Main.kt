fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    var max = intArray[0]
    for (idx in 1..intArray.lastIndex) {
        val currentNumber = intArray[idx]
        if (currentNumber > max) max = currentNumber
    }
    println(intArray.indexOf(max))
}
