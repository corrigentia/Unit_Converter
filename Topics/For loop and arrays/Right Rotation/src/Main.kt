private const val SPACE = " "

fun main() {
    // write your code here
    val size = readLine()!!.toInt()
    val intArray = IntArray(size)

    for (index in 0..intArray.lastIndex) {
        intArray[index] = readLine()!!.toInt()
    }

    val shifts = readLine()!!.toInt() % size

    print(
        intArray.drop(size - shifts).joinToString(SPACE)
    )
    print(SPACE)
    println(
        intArray.dropLast(shifts).joinToString(SPACE)
    )
}
