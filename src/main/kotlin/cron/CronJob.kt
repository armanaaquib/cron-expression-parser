package cron

data class CronJob(
    val minute: List<Int>,
    val hour: List<Int>,
    val dayOfMonth: List<Int>,
    val month: List<Int>,
    val dayOfWeek: List<Int>,
    val command: String
) {
    companion object {
        fun from(expression: String): CronJob {
            val fields = expression.split(" ")
            require(fields.size >= 6) { "Invalid cron job expression. It should have 6 fields including command." }

            val expressionParser = CronExpressionParser.from(expression)
            return CronJob(
                expressionParser.getMinute(),
                expressionParser.getHour(),
                expressionParser.getDayOfMonth(),
                expressionParser.getMonth(),
                expressionParser.getDayOfWeek(),
                command = fields[5]
            )
        }
    }
}
