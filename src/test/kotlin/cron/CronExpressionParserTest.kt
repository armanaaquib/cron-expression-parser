package cron

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class CronExpressionParserTest {

    private val minuteErrorMessage =
        "Invalid minute field. Allowed values: 0–59 and Allowed special characters: * , - /"
    private val hourErrorMessage = "Invalid hour field. Allowed values: 0–23 and Allowed special characters: * , - /"
    private val dayOfMonthErrorMessage =
        "Invalid day of month field. Allowed values: 1–31 and Allowed special characters: * , - /"
    private val monthErrorMessage = "Invalid month field. Allowed values: 1–12 and Allowed special characters: * , - /"
    private val dayOfWeekErrorMessage =
        "Invalid day of week field. Allowed values: 0–6 and Allowed special characters: * , - /"

    //minute
    @Test
    fun `should get minute for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "*")

        assertEquals((0..59).toList(), cronExpressionParser.getMinute())
    }

    @Test
    fun `should get minute for minute value`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "25")

        assertEquals(listOf(25), cronExpressionParser.getMinute())
    }

    @Test
    fun `should throw exception when minute value in more maximum value 59`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "60")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when minute value in less than minimum value 0`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should get minute for minute range`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "2-5")

        assertEquals(listOf(2, 3, 4, 5), cronExpressionParser.getMinute())
    }

    @Test
    fun `should throw exception when start of minute range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "-1-2")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when end of minute range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "1-60")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when minute range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "2-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should get minute for comma separated minute fields`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "2,5,9-11")

        assertEquals(listOf(2, 5, 9, 10, 11), cronExpressionParser.getMinute())
    }

    @Test
    fun `should throw exception when comma separated minute fields are invalid`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "2,")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    @Test
    fun `should get minute for step values with wildcard`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "*/15")

        assertEquals(listOf(0, 15, 30, 45), cronExpressionParser.getMinute())
    }

    @Test
    fun `should get minute for step values with range`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "1-10/2")

        assertEquals(listOf(1, 3, 5, 7, 9), cronExpressionParser.getMinute())
    }

    @Test
    fun `should get minute for step values`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "10/15")

        assertEquals(listOf(10, 25, 40, 55), cronExpressionParser.getMinute())
    }

    @Test
    fun `should throw exception when minute step value is invalid`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "*/a")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMinute()
        }

        assertEquals(minuteErrorMessage, exception.message)
    }

    //hour
    @Test
    fun `should get hour for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "*")

        assertEquals((0..23).toList(), cronExpressionParser.getHour())
    }

    @Test
    fun `should get hour for hour value`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "10")

        assertEquals(listOf(10), cronExpressionParser.getHour())
    }

    @Test
    fun `should throw exception when hour value in more maximum value 23`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "24")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when hour value in less than minimum value 0`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should get hour for hour range`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "2-5")

        assertEquals(listOf(2, 3, 4, 5), cronExpressionParser.getHour())
    }

    @Test
    fun `should throw exception when start of hour range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "-1-2")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when end of hour range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "1-24")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception hour range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "2-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should get hour for comma separated hour fields`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "2,5,9-11")

        assertEquals(listOf(2, 5, 9, 10, 11), cronExpressionParser.getHour())
    }

    @Test
    fun `should throw exception when comma separated hour fields are invalid`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "2,")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    @Test
    fun `should get hour for step values with wildcard`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "*/5")

        assertEquals(listOf(0, 5, 10, 15, 20), cronExpressionParser.getHour())
    }

    @Test
    fun `should get hour for step values with range`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "1-10/2")

        assertEquals(listOf(1, 3, 5, 7, 9), cronExpressionParser.getHour())
    }

    @Test
    fun `should get hour for step values`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "10/5")

        assertEquals(listOf(10, 15, 20), cronExpressionParser.getHour())
    }

    @Test
    fun `should throw exception when hour step value is invalid`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "*/a")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getHour()
        }

        assertEquals(hourErrorMessage, exception.message)
    }

    //day of month
    @Test
    fun `should get day of month for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "*")

        assertEquals((1..31).toList(), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should get day of month for day of month value`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "10")

        assertEquals(listOf(10), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should throw exception when day of month value in more maximum value 31`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "32")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when day of month value in less than minimum value 1`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "0")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should get day of month for day of month range`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "2-5")

        assertEquals(listOf(2, 3, 4, 5), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should throw exception when start of day of month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "0-2")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when end of day of month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "1-32")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception day of month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "2-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should get day of month for comma separated day of month fields`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "2,5,9-11")

        assertEquals(listOf(2, 5, 9, 10, 11), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should throw exception when comma separated day of month fields are invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "2,")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    @Test
    fun `should get day of month for step values with wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "*/10")

        assertEquals(listOf(1, 11, 21, 31), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should get day of month for step values with range`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "1-10/2")

        assertEquals(listOf(1, 3, 5, 7, 9), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should get day of month for step values`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "10/5")

        assertEquals(listOf(10, 15, 20, 25, 30), cronExpressionParser.getDayOfMonth())
    }

    @Test
    fun `should throw exception when day of month step value is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "*/a")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfMonth()
        }

        assertEquals(dayOfMonthErrorMessage, exception.message)
    }

    //month
    @Test
    fun `should get month for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "*")

        assertEquals((1..12).toList(), cronExpressionParser.getMonth())
    }

    @Test
    fun `should get month for month value`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "10")

        assertEquals(listOf(10), cronExpressionParser.getMonth())
    }

    @Test
    fun `should throw exception when month value in more maximum value 12`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "13")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when month value in less than minimum value 1`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "0")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should get month for month range`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "2-5")

        assertEquals(listOf(2, 3, 4, 5), cronExpressionParser.getMonth())
    }

    @Test
    fun `should throw exception when start of month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "0-2")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when end of month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "1-13")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception month range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "2-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should get month for comma separated month fields`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "2,5,9-11")

        assertEquals(listOf(2, 5, 9, 10, 11), cronExpressionParser.getMonth())
    }

    @Test
    fun `should throw exception when comma separated month fields are invalid`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "2,")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    @Test
    fun `should get month for step values with wildcard`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "*/3")

        assertEquals(listOf(1, 4, 7, 10), cronExpressionParser.getMonth())
    }

    @Test
    fun `should get month for step values with range`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "1-10/2")

        assertEquals(listOf(1, 3, 5, 7, 9), cronExpressionParser.getMonth())
    }

    @Test
    fun `should get month for step values`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "5/2")

        assertEquals(listOf(5, 7, 9, 11), cronExpressionParser.getMonth())
    }

    @Test
    fun `should throw exception when month step value is invalid`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "*/a")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getMonth()
        }

        assertEquals(monthErrorMessage, exception.message)
    }

    //day of week
    @Test
    fun `should get day of week for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "*")

        assertEquals((0..6).toList(), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should get day of week for day of week value`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "1")

        assertEquals(listOf(1), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should throw exception when day of week value in more maximum value 6`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "7")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when day of week value in less than minimum value 0`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should get day of week for day of week range`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "2-5")

        assertEquals(listOf(2, 3, 4, 5), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should throw exception when start of day of week range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "-1-2")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception when end of day of week range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "1-7")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should throw exception day of week range is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "2-1")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should get day of week for comma separated day of week fields`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "2,4-6")

        assertEquals(listOf(2, 4, 5, 6), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should throw exception when comma separated day of week fields are invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "2,")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    @Test
    fun `should get day of week for step values with wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "*/2")

        assertEquals(listOf(0, 2, 4, 6), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should get day of week for step values with range`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "1-3/2")

        assertEquals(listOf(1, 3), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should get day of week for step values`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "2/2")

        assertEquals(listOf(2, 4, 6), cronExpressionParser.getDayOfWeek())
    }

    @Test
    fun `should throw exception when day of week step value is invalid`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "*/a")

        val exception = assertThrows<IllegalArgumentException> {
            cronExpressionParser.getDayOfWeek()
        }

        assertEquals(dayOfWeekErrorMessage, exception.message)
    }

    //from
    @Test
    fun `should through exception when expression has less than 6 fields`() {
        val exception = assertThrows<IllegalArgumentException> {
            CronExpressionParser.from("1 2 3 4")
        }

        assertEquals("Invalid cron expression. It should have 5 fields.", exception.message)
    }

    @Test
    fun `should create CronExpressionParser from expression`() {
        val cronExpressionParser = CronExpressionParser.from("0 0 1 1 1 CMD")

        assertAll({
            assertEquals(cronExpressionParser.getMinute(), listOf(0))
            assertEquals(cronExpressionParser.getHour(), listOf(0))
            assertEquals(cronExpressionParser.getDayOfMonth(), listOf(1))
            assertEquals(cronExpressionParser.getMonth(), listOf(1))
            assertEquals(cronExpressionParser.getDayOfWeek(), listOf(1))
        })
    }

    private fun createCronExpressionParser(
        minuteField: String = "0",
        hourField: String = "0",
        dayOfMonthField: String = "1",
        monthField: String = "1",
        dayOfWeekField: String = "1"
    ) = CronExpressionParser(
        minuteField,
        hourField,
        dayOfMonthField,
        monthField,
        dayOfWeekField
    )
}
