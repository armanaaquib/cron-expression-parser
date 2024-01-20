package cron

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class CronExpressionTest {

    @Test
    fun `should throw exception when expression is less than 6 fields`() {
        val exception = assertThrows<IllegalArgumentException> {
            CronExpression.from("1 2 3 4 5")
        }

        assertEquals("Invalid cron expression. It should have 6 fields including command.", exception.message)
    }

    @Test
    fun `should create cron job`() {
        val cronExpression = CronExpression.from("0 0 1 1 1 CMD")

        val expectedCronExpression = CronExpression(
            minute = listOf(0),
            hour = listOf(0),
            dayOfMonth = listOf(1),
            month = listOf(1),
            dayOfWeek = listOf(1),
            command = "CMD"
        )
        assertEquals(expectedCronExpression, cronExpression)
    }
}