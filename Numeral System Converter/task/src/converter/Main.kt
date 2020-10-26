package converter

import kotlin.math.pow

fun main() {
    val input = readLine()!!
    if (!input.matches("\\d+".toRegex()) || input == "0" || input.toInt() > 36) {
        println ("$input is error")
        return
    }
    var radix = input.toInt()
    val input2 = readLine()!!
    if (!input2.matches("[-+]?[0-9a-zA-Z]*\\.?[0-9a-zA-Z]+".toRegex())) {
        println ("error")
        return
    }
    var maxDigit = Character.forDigit(radix, 36)
    if (radix == 1) maxDigit = Character.forDigit(radix - 1, 36)

    if (input2.contains(maxDigit.toString(), ignoreCase = true)) {
        println ("error")
        return
    }
    val number = input2
    val input3 = readLine()!!
    if (!input3.matches("\\d+".toRegex()) || input3 == "0" || input3.toInt() > 36) {
        println ("error")
        return
    }
    val newRadix = input3.toInt()
    var res = ""
    var intPart = ""
    var convertFract = ""

    intPart = number.split("\\.".toRegex())[0]
    if (number.split("\\.".toRegex()).size > 1) {
        var fractPart = 0
        var multiply = 0.0
        val fract = number.split("\\.".toRegex())[1]
        if (radix != 10) {
            for (i in fract.indices) {
                multiply += Character.getNumericValue(fract[i]) / radix.toDouble().pow(i + 1.0)
            }
        } else {
            multiply = ("0.$fract").toDouble()
        }

        var multString = ""

        for (i in 0..4) {
            multiply *= newRadix.toDouble()
            multString = multiply.toString()
            val integerPartF = multString.split("\\.".toRegex())[0].toInt()
            val fractPartF = multString.split("\\.".toRegex())[1]
            multiply = ("0.$fractPartF").toDouble()
            convertFract += Character.forDigit(integerPartF, 36).toString()
        }

    }

    when {
        radix != 1 && newRadix == 1 -> res = "1".repeat(Integer.parseInt(intPart, radix))
        radix == 1 && newRadix != 1 -> res = Integer.toString(intPart.length, newRadix)
        else -> res = Integer.toString(Integer.parseInt(intPart, radix), newRadix)
    }
    println("$res.$convertFract")
}