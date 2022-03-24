private const val SPACE = " "

fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    val lastNumber = intArray.last()

    println("$lastNumber ${intArray.dropLast(1).joinToString(SPACE)}")
}
