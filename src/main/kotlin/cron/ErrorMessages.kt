package cron

object ErrorMessages {
    const val minute = "Invalid minute field. Allowed values: 0–59 and Allowed special characters: * , - /"
    const val hour = "Invalid hour field. Allowed values: 0–23 and Allowed special characters: * , - /"
    const val dayOfMonth = "Invalid day of month field. Allowed values: 1–31 and Allowed special characters: * , - /"
    const val month = "Invalid month field. Allowed values: 1–12 and Allowed special characters: * , - /"
    const val dayOfWeek = "Invalid day of week field. Allowed values: 0–6 and Allowed special characters: * , - /"
}