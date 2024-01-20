package cron

class CronExpressionParser(
    private val minuteField: String,
    private val hourField: String,
    private val dayOfMonthField: String,
    private val monthField: String,
    private val dayOfWeekField: String
) {
    fun getMinute(): List<Int> {
        return parseFields(minuteField, 0, 59, ErrorMessages.minute)
    }

    fun getHour(): List<Int> {
        return parseFields(hourField, 0, 23, ErrorMessages.hour)
    }

    fun getDayOfMonth(): List<Int> {
        return parseFields(dayOfMonthField, 1, 31, ErrorMessages.dayOfMonth)
    }

    fun getMonth(): List<Int> {
        return parseFields(monthField, 1, 12, ErrorMessages.month)
    }

    fun getDayOfWeek(): List<Int> {
        return parseFields(dayOfWeekField, 0, 6, ErrorMessages.dayOfWeek)
    }

    private fun parseFields(field: String, min: Int, max: Int, errorMessage: String): List<Int> {
        return try {
            val fields = field.split(",")
            fields.fold(emptyList()) { values, f ->
                values + parseField(f, min, max)
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(errorMessage)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(errorMessage)
        }
    }

    private fun parseField(field: String, min: Int, max: Int): List<Int> {
        return if (field.contains('/')) {
            parseValuesWithStep(field, min, max)
        } else {
            parseValues(field, min, max)
        }
    }

    private fun parseValuesWithStep(field: String, min: Int, max: Int): List<Int> {
        val (fieldValue, step) = field.split("/")
        val values = parseValues(fieldValue, min, max)
        val start = values.first()
        val end = if (values.size == 1) max else values.last()
        return (start..end step step.toInt()).toList()
    }

    private fun parseValues(field: String, min: Int, max: Int) = when {
        field == "*" -> {
            (min..max).toList()
        }

        field.contains('-') -> {
            val (start, end) = field.split("-")
            val startValue = start.toInt()
            val endValue = end.toInt()

            require(startValue <= endValue)
            require(startValue in min..max)
            require(endValue in min..max)

            (startValue..endValue).toList()
        }

        else -> {
            val value = field.toInt()
            require(value in min..max)
            listOf(value)
        }
    }

    companion object {
        fun from(expression: String): CronExpressionParser {
            val fields = expression.split(" ")
            require(fields.size >= 5) { "Invalid cron expression. It should have 5 fields excluding command." }
            return CronExpressionParser(
                fields[0], fields[1], fields[2], fields[3], fields[4]
            )
        }
    }
}