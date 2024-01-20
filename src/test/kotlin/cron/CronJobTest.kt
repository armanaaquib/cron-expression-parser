package cron

import cron.CronJob
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class CronJobTest {

    @Test
    fun `should throw exception when expression is less than 6 fields`() {
        val exception = assertThrows<IllegalArgumentException> {
            CronJob.from("1 2 3 4 5")
        }

        assertEquals("Invalid cron job expression. It should have 6 fields including command.", exception.message)
    }

    @Test
    fun `should create cron job`() {
        val cronJob = CronJob.from("0 0 1 1 1 CMD")

        val expectedCronJob = CronJob(
            minute = listOf(0),
            hour = listOf(0),
            dayOfMonth = listOf(1),
            month = listOf(1),
            dayOfWeek = listOf(1),
            command = "CMD"
        )
        assertEquals(expectedCronJob, cronJob)
    }
}