fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    val sought = readLine()!!.toInt()
    var count = 0
    for (number in intArray) if (number == sought) ++count
    println(count)
}
