fun main() {
    // hyperskill-4699-test-05.txt
    /*
2
0
1
100
1
     */
    val size = readLine()!!.toInt() // 2
    val annualIncomes = IntArray(size) // intArrayOf(0, 1)

    for (index in 0..annualIncomes.lastIndex) {
        annualIncomes[index] = readLine()!!.toInt()
    }
    val taxPercentages = IntArray(size) // intArrayOf(100, 1)

    for (index in 0..taxPercentages.lastIndex) {
        taxPercentages[index] = readLine()!!.toInt()
    }
    val absoluteTaxes = IntArray(size)

    for (index in 0..absoluteTaxes.lastIndex) {
        absoluteTaxes[index] = annualIncomes[index] * taxPercentages[index]
    }

    println(absoluteTaxes.indexOf(absoluteTaxes.maxOrNull()!!) + 1)
}
