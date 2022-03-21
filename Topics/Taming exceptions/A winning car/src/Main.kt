private const val MIN_SPEED = 120
private const val MAX_ACCELERATION = 4

fun findCar(): String {
    val maxSpeed = readln().toInt()
    val accTime = readln().toInt()

    // write your code here
    if (maxSpeed >= MIN_SPEED && accTime in 1..MAX_ACCELERATION) {
        return "I will definitely win!"
    } else {
        throw Exception("The race can't be won with this car")
    }
}
