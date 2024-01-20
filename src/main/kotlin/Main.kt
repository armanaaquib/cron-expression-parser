import cron.CronExpression

fun main(args: Array<String>) {
    try {
        val cronExpression = CronExpression.from(args.joinToString(" "))
        displayCronExpression(cronExpression)
    } catch (ex: Exception) {
        println(ex.message)
    }
}

fun displayCronExpression(cronExpression: CronExpression) {
    val labelLength = 14
    println("minute".padEnd(labelLength) + cronExpression.minute.join())
    println("hour".padEnd(labelLength) + cronExpression.hour.join())
    println("day of month".padEnd(labelLength) + cronExpression.dayOfMonth.join())
    println("month".padEnd(labelLength) + cronExpression.month.join())
    println("day of week".padEnd(labelLength) + cronExpression.dayOfWeek.join())
    println("command".padEnd(labelLength) + cronExpression.command)
}

fun List<Int>.join(): String = this.joinToString(" ")