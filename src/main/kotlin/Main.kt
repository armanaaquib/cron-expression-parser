fun main(args: Array<String>) {
    val cronJob = CronJob.from(args.joinToString(" "))
    displayCronJob(cronJob)
}

fun displayCronJob(cronJob: CronJob) {
    val labelLength = 14
    println("minute".padEnd(labelLength) + cronJob.minute.join())
    println("hour".padEnd(labelLength) + cronJob.hour.join())
    println("day of month".padEnd(labelLength) + cronJob.dayOfMonth.join())
    println("month".padEnd(labelLength) + cronJob.month.join())
    println("day of week".padEnd(labelLength) + cronJob.dayOfWeek.join())
    println("command".padEnd(labelLength) + cronJob.command)
}

fun List<Int>.join(): String = this.joinToString(" ")