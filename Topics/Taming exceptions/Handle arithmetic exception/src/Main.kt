fun main() {
    // put your code here
    val (firstNumber, secondNumber) = MutableList(2) { readLine()!!.toInt() }
    if (secondNumber == 0) {
        println("Division by zero, please fix the second argument!")
    } else println(firstNumber / secondNumber)
}
