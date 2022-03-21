package converter

import converter.MeasurementUnit.Companion.convertDistances
import converter.MeasurementUnit.Companion.convertWeights
import converter.MeasurementUnit.Companion.isValid
import converter.MeasurementUnit.Companion.isValidLength
import converter.MeasurementUnit.Companion.isValidTemperature
import converter.MeasurementUnit.Companion.isValidWeight
import converter.MeasurementUnit.Companion.pluralise
import converter.MeasurementUnit.Companion.validate

private const val METERS_IN_CENTIMETER = 1 / 100.0
private const val METERS_IN_KILOMETER = 1000.0

private const val METERS_IN_METER = 1.0
private const val METERS_IN_MILLIMETER = 1 / 1000.0
private const val METERS_IN_MILE = 1609.35
private const val METERS_IN_YARD = 0.9144
private const val METERS_IN_FOOT = 0.3048
private const val METERS_IN_INCH = 0.0254

private const val GRAMS_IN_GRAM = 1.0
private const val GRAMS_IN_KILOGRAM = 1000.0
private const val GRAMS_IN_MILLIGRAM = 1 / 1000.0
private const val GRAMS_IN_POUND = 453.592
private const val GRAMS_IN_OUNCE = 28.3495

enum class UnitType(vararg val category: String) {
    LENGTH("Length"), WEIGHT("Weight"), TEMPERATURE("Temperature"), NULL("Null")
}

private const val THIRTY_TWO = 32
private const val FIVE = 5
private const val NINE = 9
private const val CELSIUS_KELVIN_DIFFERENCE = 273.15

private const val FAHRENHEIT_KELVIN_DIFFERENCE = 459.67

enum class MeasurementUnit(
    val type: UnitType, val ratio: Double, val pattern: Regex, val singular: String, val plural: String
) {
    M(UnitType.LENGTH, METERS_IN_METER, Regex("^m(eters?)?$"), "meter", "meters"),

    KM(
        UnitType.LENGTH, METERS_IN_KILOMETER, Regex("^k(ilo)?m(eters?)?$"), "kilometer", "kilometers"
    ),
    MM(UnitType.LENGTH, METERS_IN_MILLIMETER, Regex("^m(illi)?m(eters?)?$"), "millimeter", "millimeters"),

    CM(
        UnitType.LENGTH, METERS_IN_CENTIMETER, Regex("^c(enti)?m(eters?)?$"), "centimeter", "centimeters"
    ),
    MI(UnitType.LENGTH, METERS_IN_MILE, Regex("^mi(les?)?$"), "mile", "miles"),

    YD(
        UnitType.LENGTH, METERS_IN_YARD, Regex("^y(ar)?ds?$"), "yard", "yards"
    ),
    FT(UnitType.LENGTH, METERS_IN_FOOT, Regex("^f(oo|ee)?t$"), "foot", "feet"),

    IN(
        UnitType.LENGTH, METERS_IN_INCH, Regex("^in(ch(es)?)?$"), "inch", "inches"
    ),

    G(
        UnitType.WEIGHT, GRAMS_IN_GRAM, Regex("^g(rams?)?$"), "gram", "grams"
    ),
    KG(
        UnitType.WEIGHT, GRAMS_IN_KILOGRAM, Regex("^k(ilo)?g(rams?)?$"), "kilogram", "kilograms"
    ),
    MG(
        UnitType.WEIGHT, GRAMS_IN_MILLIGRAM, Regex("^m(illi)?g(rams?)?$"), "milligram", "milligrams"
    ),
    LB(
        UnitType.WEIGHT, GRAMS_IN_POUND, Regex("^lb|pounds?$"), "pound", "pounds"
    ),
    OZ(
        UnitType.WEIGHT, GRAMS_IN_OUNCE, Regex("^o(z|unces?)$"), "ounce", "ounces"
    ),

    K(UnitType.TEMPERATURE, 1.0, Regex("^k(elvins?)?$"), "kelvin", "kelvins"),

    F(
        UnitType.TEMPERATURE, 1.0, Regex("^(d(egrees? )?)?f(ahrenheit)?$"), "degree Fahrenheit", "degrees Fahrenheit"
    ),
    C(
        UnitType.TEMPERATURE, 1.0, Regex("^(d(egrees? )?)?c(elsius)?$"), "degree Celsius", "degrees Celsius"
    ),
    NULL(UnitType.NULL, 1.0, Regex("\\*+"), "???", "???");

    companion object {

        fun validate(unitProvided: String): MeasurementUnit {
            for (unit in values()) {
                if (unit.pattern.matches(unitProvided)) {
                    return unit
                }
            }
            return NULL
        }

        fun isValidLength(unit: String): Boolean {
            return validate(unit).type == UnitType.LENGTH
        }

        fun isValidWeight(unit: String): Boolean {
            return validate(unit).type == UnitType.WEIGHT
        }

        fun isValidTemperature(unit: String): Boolean {
            return validate(unit).type == UnitType.TEMPERATURE
        }

        fun isValid(unit: String): Boolean {
            return isValidLength(unit) || isValidWeight(unit) || isValidTemperature(unit)
        }

        fun pluralise(amount: Double, unit: String): String {
            val validUnit = validate(unit)
            return if (amount == 1.0) validUnit.singular else validUnit.plural
        }

        fun pluralise(unit: String): String {
            return validate(unit).plural
        }

        private fun toM(sourceAmount: Double, sourceUnit: String): Double = sourceAmount * validate(sourceUnit).ratio

        private fun mTo(targetUnit: String, meterAmount: Double): Double = meterAmount / validate(targetUnit).ratio

        fun convertDistances(sourceAmount: Double, sourceUnit: String, targetUnit: String): Double {
            var intermediateAmount: Double = sourceAmount
            if (!M.pattern.matches(sourceUnit)) {
                intermediateAmount = toM(sourceAmount, sourceUnit)
            }

            var targetAmount: Double = intermediateAmount

            if (!M.pattern.matches(targetUnit)) {
                targetAmount = mTo(targetUnit, intermediateAmount)
            }

            return targetAmount
        }

        private fun toG(sourceAmount: Double, sourceUnit: String): Double = sourceAmount * validate(sourceUnit).ratio

        private fun gTo(targetUnit: String, gramAmount: Double): Double = gramAmount / validate(targetUnit).ratio

        fun convertWeights(sourceAmount: Double, sourceUnit: String, targetUnit: String): Double {
            var intermediateAmount: Double = sourceAmount
            if (!G.pattern.matches(sourceUnit)) {
                intermediateAmount = toG(sourceAmount, sourceUnit)
            }

            var targetAmount: Double = intermediateAmount

            if (!G.pattern.matches(targetUnit)) {
                targetAmount = gTo(targetUnit, intermediateAmount)
            }

            return targetAmount
        }

        private fun convertFtoC(degreesFahrenheit: Double) = (degreesFahrenheit - THIRTY_TWO) * FIVE / NINE
        private fun convertCtoF(degreesCelsius: Double) = (degreesCelsius * NINE / FIVE) + THIRTY_TWO

        private fun convertCtoK(degreesCelsius: Double) = degreesCelsius + CELSIUS_KELVIN_DIFFERENCE
        private fun convertKtoC(kelvins: Double) = kelvins - CELSIUS_KELVIN_DIFFERENCE

        private fun convertKtoF(kelvins: Double) = (kelvins * NINE / FIVE) - FAHRENHEIT_KELVIN_DIFFERENCE
        private fun convertFtoK(degreesFahrenheit: Double) =
            (degreesFahrenheit + FAHRENHEIT_KELVIN_DIFFERENCE) * FIVE / NINE

        private fun identity(doubleNumber: Double) = doubleNumber

        fun convertTemperatures(sourceAmount: Double, sourceUnit: String, targetUnit: String): Double {
            val convert: (Double) -> Double = when (validate(sourceUnit).name) {
                "C" -> when (validate(targetUnit).name) {
                    "F" -> ::convertCtoF
                    "K" -> ::convertCtoK
                    else -> ::identity
                }
                "F" -> when (validate(targetUnit).name) {
                    "C" -> ::convertFtoC
                    "K" -> ::convertFtoK
                    else -> ::identity
                }
                "K" -> when (validate(targetUnit).name) {
                    "C" -> ::convertKtoC
                    "F" -> ::convertKtoF
                    else -> ::identity
                }
                else -> ::identity
            }
            return convert(sourceAmount)
        }
    }
}

fun main() {
    do {
        print("Enter what you want to convert (or exit): ")

        val inputParts = readLine()!!.lowercase().split(" ")
        if (inputParts.size > 1 && inputParts.first() != "exit") {

            val sourceAmountProvided = inputParts.first()
            val firstWordOfSourceUnit = inputParts[1]

            val sourceUnit = if (firstWordOfSourceUnit.startsWith("degree")) {
                firstWordOfSourceUnit + " " + inputParts[2]
            } else firstWordOfSourceUnit

            val targetUnit = inputParts.last()
            val sourceAmount = sourceAmountProvided.toDoubleOrNull()

            if (sourceAmount is Double) {
                if (isValid(sourceUnit) && isValid(targetUnit) && ((isValidLength(sourceUnit) && isValidLength(
                        targetUnit
                    )) || (isValidWeight(
                        sourceUnit
                    ) && isValidWeight(
                        targetUnit
                    )) || (isValidTemperature(sourceUnit) && isValidTemperature(
                        targetUnit
                    )))
                ) {
                    if (sourceAmount < 0 && (isValidLength(sourceUnit) || isValidWeight(sourceUnit))) {
                        println("${validate(sourceUnit).type.category.first()} shouldn't be negative")
                    } else {

                        val convert: (Double, String, String) -> Double = if (isValidLength(sourceUnit)) {
                            ::convertDistances
                        } else if (isValidWeight(sourceUnit)) {
                            ::convertWeights
                        } else {
                            MeasurementUnit.Companion::convertTemperatures
                        }

                        val targetAmount: Double = convert(sourceAmount, sourceUnit, targetUnit)

                        println(
                            "$sourceAmount ${pluralise(sourceAmount, sourceUnit)} is $targetAmount ${
                                pluralise(targetAmount, targetUnit)
                            }\n"
                        )
                    }
                } else {
                    println("Conversion from ${pluralise(sourceUnit)} to ${pluralise(targetUnit)} is impossible")
                }
            } else println("Parse error")
        } else return
    } while (inputParts.first() != "exit")
}
