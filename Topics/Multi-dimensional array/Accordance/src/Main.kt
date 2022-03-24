private const val THREE = 3

fun main() {
    // put your code here
    val matrix2by3 = arrayOf(Array(THREE) { "" }, Array(THREE) { "" })

    for (mainIndex in matrix2by3.indices) {
        for (nestedIndex in matrix2by3[mainIndex].indices) {
            matrix2by3[mainIndex][nestedIndex] = "[$mainIndex][$nestedIndex]"
        }
    }

    println(
        matrix2by3.contentDeepToString()
    )
}
