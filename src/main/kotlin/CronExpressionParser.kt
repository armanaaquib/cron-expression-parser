class CronExpressionParser(
    private val minuteField: String,
    private val hourField: String,
    private val dayOfMonthField: String,
    private val monthField: String,
    private val dayOfWeekField: String,
    private val commandField: String
) {
    fun getMinute(): List<Int> {
        return (0..59).toList()
    }

    fun getHour(): List<Int> {
        return (0..23).toList()
    }

    fun getDayOfMonth(): List<Int> {
        return (1..31).toList()
    }

    fun getMonth(): List<Int> {
        return (1..12).toList()
    }

    fun getDayOfWeek(): List<Int> {
        return (1..7).toList()
    }

    fun getCommand(): String {
        return commandField
    }

    companion object {
        fun from(expression: String): CronExpressionParser {
            val fieldList = expression.split(" ")
            return CronExpressionParser(
                fieldList[0],
                fieldList[1],
                fieldList[2],
                fieldList[3],
                fieldList[4],
                fieldList[5]
            )
        }
    }
}