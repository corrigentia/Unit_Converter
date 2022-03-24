private const val THREE = 3

fun main() {
    println(
        Array(THREE) {
            Array(THREE) {
                IntArray(THREE)
            }
        }.contentDeepToString()
    )
}
