package cron

class CronExpressionParser(
    private val minuteField: String,
    private val hourField: String,
    private val dayOfMonthField: String,
    private val monthField: String,
    private val dayOfWeekField: String
) {
    fun getMinute(): List<Int> {
        val minuteErrorMessage = "Invalid minute field. Allowed values: 0–59 and Allowed special characters: * , - /"
        return parseFields(minuteField, 0, 59, minuteErrorMessage)
    }

    fun getHour(): List<Int> {
        val hourErrorMessage = "Invalid hour field. Allowed values: 0–23 and Allowed special characters: * , - /"
        return parseFields(hourField, 0, 23, hourErrorMessage)
    }

    fun getDayOfMonth(): List<Int> {
        val dayOfMonthErrorMessage =
            "Invalid day of month field. Allowed values: 1–31 and Allowed special characters: * , - /"
        return parseFields(dayOfMonthField, 1, 31, dayOfMonthErrorMessage)
    }

    fun getMonth(): List<Int> {
        val monthErrorMessage = "Invalid month field. Allowed values: 1–12 and Allowed special characters: * , - /"
        return parseFields(monthField, 1, 12, monthErrorMessage)
    }

    fun getDayOfWeek(): List<Int> {
        val dayOfWeekErrorMessage =
            "Invalid day of week field. Allowed values: 0–6 and Allowed special characters: * , - /"
        return parseFields(dayOfWeekField, 0, 6, dayOfWeekErrorMessage)
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