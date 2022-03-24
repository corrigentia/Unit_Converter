fun main() {
    // write your code here
    val word = readLine()!!
    val indexPlus1 = readLine()!!.toInt()
    println("Symbol # $indexPlus1 of the string \"$word\" is '${word[indexPlus1 - 1]}'")
}
