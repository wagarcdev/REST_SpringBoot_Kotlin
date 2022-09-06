package br.com.wagarcdev

import br.com.wagarcdev.exceptions.ExceptionResponse
import br.com.wagarcdev.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value="numberOne") numberOne: String?,
        @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {

        if ( !isNumeric(numberOne) || !isNumeric(numberTwo) ) {
            throw UnsupportedMathOperationException("Please, set numeric values")
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    private fun convertToDouble(strNumber: String?): Double {

        if (strNumber.isNullOrBlank()) return 0.0

        val number = strNumber.replace(",".toRegex(), ".")

        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {

        if (strNumber.isNullOrBlank()) return false

        val number = strNumber.replace(",".toRegex(), ".")

        return number.matches("""[-+]?\d*\.?\d+""".toRegex())
    }

}