import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class CronExpressionParserTest {

    //Minute
    @Test
    fun `should get minute for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(minuteField = "*")

        assertEquals((0..59).toList(), cronExpressionParser.getMinute())
    }

    //Hour
    @Test
    fun `should get hour for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(hourField = "*")

        assertEquals((0..23).toList(), cronExpressionParser.getHour())
    }

    //Day of month
    @Test
    fun `should get day of month for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfMonthField = "*")

        assertEquals((1..31).toList(), cronExpressionParser.getDayOfMonth())
    }

    //Month
    @Test
    fun `should get month for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(monthField = "*")

        assertEquals((1..12).toList(), cronExpressionParser.getMonth())
    }

    //Day of week
    @Test
    fun `should get day of week for wildcard`() {
        val cronExpressionParser = createCronExpressionParser(dayOfWeekField = "*")

        assertEquals((1..7).toList(), cronExpressionParser.getDayOfWeek())
    }

    //Command
    @Test
    fun `should get command`() {
        val command = "/bin/bash"
        val cronExpressionParser = createCronExpressionParser(commandField = command)

        assertEquals(command, cronExpressionParser.getCommand())
    }

    //From
//    @Test
//    fun `should create CronExpressionParser from expression`() {
//        val cronExpressionParser = CronExpressionParser.from("0 0 1 1 1 CMD")
//
//        assertAll({
//            assertEquals(cronExpressionParser.getMinute(), listOf(0))
//            assertEquals(cronExpressionParser.getHour(), listOf(0))
//            assertEquals(cronExpressionParser.getDayOfMonth(), listOf(1))
//            assertEquals(cronExpressionParser.getMonth(), listOf(1))
//            assertEquals(cronExpressionParser.getDayOfWeek(), listOf(1))
//            assertEquals(cronExpressionParser.getCommand(), "CMD")
//        })
//    }

    private fun createCronExpressionParser(
        minuteField: String = "0",
        hourField: String = "0",
        dayOfMonthField: String = "1",
        monthField: String = "1",
        dayOfWeekField: String = "1",
        commandField: String = "CMD"
    ) = CronExpressionParser(
        minuteField,
        hourField,
        dayOfMonthField,
        monthField,
        dayOfWeekField,
        commandField
    )
}
