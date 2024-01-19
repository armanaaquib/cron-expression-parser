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
            val expressionParser = CronExpressionParser.from(expression)
            return CronJob(
                expressionParser.getMinute(),
                expressionParser.getHour(),
                expressionParser.getDayOfMonth(),
                expressionParser.getMonth(),
                expressionParser.getDayOfWeek(),
                expressionParser.getCommand()
            )
        }
    }
}
