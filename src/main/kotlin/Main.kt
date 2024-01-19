fun main(args: Array<String>) {
    val cronJob = CronJob.from(args.joinToString(" "))
    displayCronJob(cronJob)
}

fun displayCronJob(cronJob: CronJob) {
    println("minute        ${cronJob.minute.joinToString(" ")}")
    println("hour          ${cronJob.hour.joinToString(" ")}")
    println("day of month  ${cronJob.dayOfMonth.joinToString(" ")}")
    println("month         ${cronJob.month.joinToString(" ")}")
    println("day of week   ${cronJob.dayOfWeek.joinToString(" ")}")
    println("command       ${cronJob.command}")
}
