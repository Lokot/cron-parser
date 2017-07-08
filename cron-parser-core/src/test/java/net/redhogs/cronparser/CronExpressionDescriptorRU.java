package net.redhogs.cronparser;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lokot
 * @since 8 Jul 2017 19:33:00
 */
public class CronExpressionDescriptorRU {
	
	private static final Locale RUSSIAN = new Locale("ru");

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Каждую секунду", CronExpressionDescriptor.getDescription("* * * * * *", RUSSIAN));
        Assert.assertEquals("Каждую секунду", CronExpressionDescriptor.getDescription("* * * * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Каждые 45 секунд", CronExpressionDescriptor.getDescription("*/45 * * * * *", RUSSIAN));
        Assert.assertEquals("Каждые 45 секунд", CronExpressionDescriptor.getDescription("*/45 * * * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Каждую минуту между 11:00 AM и 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", RUSSIAN));
        Assert.assertEquals("Каждую минуту между 11:00 и 11:10", CronExpressionDescriptor.getDescription("0-10 11 * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Каждую минуту", CronExpressionDescriptor.getDescription("* * * * *", RUSSIAN));
        Assert.assertEquals("Каждую минуту", CronExpressionDescriptor.getDescription("*/1 * * * *", RUSSIAN));
        Assert.assertEquals("Каждую минуту", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", RUSSIAN));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Каждые 5 минут", CronExpressionDescriptor.getDescription("*/5 * * * *", RUSSIAN));
        Assert.assertEquals("Каждые 5 минут", CronExpressionDescriptor.getDescription("0 */5 * * * *", RUSSIAN));
        Assert.assertEquals("Каждые 10 минут", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", RUSSIAN));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Каждый час", CronExpressionDescriptor.getDescription("0 0 * * * ?", RUSSIAN));
        Assert.assertEquals("Каждый час", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", RUSSIAN));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("В 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", RUSSIAN));
        Assert.assertEquals("В 11:30", CronExpressionDescriptor.getDescription("30 11 * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
    	Assert.assertEquals("В 11:00 PM, с понедельник по пятница", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", RUSSIAN));
        Assert.assertEquals("В 23:00, с понедельник по пятница", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", Options.twentyFourHour(), RUSSIAN));
        Assert.assertEquals("В 11:30 AM, с понедельник по пятница", CronExpressionDescriptor.getDescription("30 11 * * 1-5", RUSSIAN));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Каждую минуту, только в марта", CronExpressionDescriptor.getDescription("* * * 3 *", RUSSIAN));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Каждую минуту, только в марта и июня", CronExpressionDescriptor.getDescription("* * * 3,6 *", RUSSIAN));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("В 2:30 PM и 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", RUSSIAN));
        Assert.assertEquals("В 14:30 и 16:30", CronExpressionDescriptor.getDescription("30 14,16 * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("В 6:30 AM, 2:30 PM и 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", RUSSIAN));
        Assert.assertEquals("В 6:30, 14:30 и 16:30", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("В 9:46 AM, только в воскресенье", CronExpressionDescriptor.getDescription("46 9 * * 0", RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в воскресенье", CronExpressionDescriptor.getDescription("46 9 * * 7", RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в понедельник", CronExpressionDescriptor.getDescription("46 9 * * 1", RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в суббота", CronExpressionDescriptor.getDescription("46 9 * * 6", RUSSIAN));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("В 9:46 AM, только в воскресенье", CronExpressionDescriptor.getDescription("46 9 * * 1", options, RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в понедельник", CronExpressionDescriptor.getDescription("46 9 * * 2", options, RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в суббота", CronExpressionDescriptor.getDescription("46 9 * * 7", options, RUSSIAN));
    }

    @Test
    public void testTwiceAWeek() throws Exception {
    	Assert.assertEquals("В 9:46 AM, только в понедельник и вторник", CronExpressionDescriptor.getDescription("46 9 * * 1,2", RUSSIAN));
    	Assert.assertEquals("В 9:46 AM, только в воскресенье и суббота", CronExpressionDescriptor.getDescription("46 9 * * 0,6", RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в суббота и воскресенье", CronExpressionDescriptor.getDescription("46 9 * * 6,7", RUSSIAN));
    }

    @Test
    public void testTwiceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("В 9:46 AM, только в воскресенье и понедельник", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, RUSSIAN));
        Assert.assertEquals("В 9:46 AM, только в пятница и суббота", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, RUSSIAN));
    }

    @Test
    public void testDayOfMonth() throws Exception {
    	Assert.assertEquals("В 12:23 PM, в 15 день месяца", CronExpressionDescriptor.getDescription("23 12 15 * *", RUSSIAN));
        Assert.assertEquals("В 12:23, в 15 день месяца", CronExpressionDescriptor.getDescription("23 12 15 * *", Options.twentyFourHour(), RUSSIAN));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("В 12:23 PM, только в января", CronExpressionDescriptor.getDescription("23 12 * JAN *", RUSSIAN));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("В 12:23 PM, только в января", CronExpressionDescriptor.getDescription("23 12 ? JAN *", RUSSIAN));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("В 12:23 PM, с января по февраля", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", RUSSIAN));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("В 12:23 PM, с января по марта", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", RUSSIAN));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("В 12:23 PM, только в воскресенье", CronExpressionDescriptor.getDescription("23 12 * * SUN", RUSSIAN));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Каждые 5 минут, в 3:00 PM, с понедельник по пятница", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", RUSSIAN));
        Assert.assertEquals("Каждые 5 минут, в 3:00 PM, с воскресенье по суббота", CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", RUSSIAN));
        Assert.assertEquals("Каждые 5 минут, в 3:00 PM, с суббота по воскресенье", CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", RUSSIAN));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
    	Assert.assertEquals("Каждую минуту, на третий понедельник в течение месяца", CronExpressionDescriptor.getDescription("* * * * MON#3", RUSSIAN));
        Assert.assertEquals("Каждую минуту, на третий воскресенье в течение месяца", CronExpressionDescriptor.getDescription("* * * * 0#3", RUSSIAN));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
    	Assert.assertEquals("Каждую минуту, в последней четверг месяца", CronExpressionDescriptor.getDescription("* * * * 4L", RUSSIAN));
    	Assert.assertEquals("Каждую минуту, в последней воскресенье месяца", CronExpressionDescriptor.getDescription("* * * * 0L", RUSSIAN));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Каждые 5 минут, в последний день месяца, только в января", CronExpressionDescriptor.getDescription("*/5 * L JAN *", RUSSIAN));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("В 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", RUSSIAN));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("С 5 по 10 секунду", CronExpressionDescriptor.getDescription("5-10 * * * * *", RUSSIAN));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("С 5 по 10 секунду, c 30 по 35 минуты, между 10:00 AM и 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", RUSSIAN));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("На 30 секунде, каждые 5 минут", CronExpressionDescriptor.getDescription("30 */5 * * * *", RUSSIAN));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("В 30 минут каждого часа, между 10:00 AM и 1:00 PM, только в среда и пятница",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", RUSSIAN));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("На 10 секунде, каждые 5 минут", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", RUSSIAN));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Каждые 3 минут, c 02 по 59 минуты, в 1:00 AM, 9:00 AM и 10:00 PM, с 11 и 26 число месяца, с января по июня",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", RUSSIAN));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("В 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", RUSSIAN));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("В 05 минут каждого часа", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", RUSSIAN));
    }

    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("В 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50 и 55 минут каждого часа", CronExpressionDescriptor.getDescription("0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", RUSSIAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/10
     */
    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("Каждые 2 минут, c 00 по 30 минуты, в 5:00 PM, с понедельник по пятница", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", RUSSIAN));
    }

    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("Каждую секунду, только в 2013 году", CronExpressionDescriptor.getDescription("* * * * * * 2013", RUSSIAN));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("Каждую минуту, только в 2013 году", CronExpressionDescriptor.getDescription("* * * * * 2013", RUSSIAN));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("Каждую минуту, только в 2013 и 2014 году", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", RUSSIAN));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("В 12:23 PM, с января по февраля, с 2013 по 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", RUSSIAN));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("В 12:23 PM, с января по марта, с 2013 по 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", RUSSIAN));
    }

    @Test
    public void testIssue26() throws Exception {
    	Assert.assertEquals("В 05 и 10 минут каждого часа", CronExpressionDescriptor.getDescription("5,10 0 * * *", RUSSIAN));
    	Assert.assertEquals("В 05 и 10 минут каждого часа, в 2 день месяца", CronExpressionDescriptor.getDescription("5,10 0 2 * *", RUSSIAN));
    	Assert.assertEquals("Каждые 10 минут, в 2 день месяца", CronExpressionDescriptor.getDescription("5/10 0 2 * *", RUSSIAN));

        Assert.assertEquals("На 5 и 6 секунде", CronExpressionDescriptor.getDescription("5,6 0 0 * * *", RUSSIAN));
        Assert.assertEquals("На 5 и 6 секунде, в 1:00 AM", CronExpressionDescriptor.getDescription("5,6 0 1 * * *", RUSSIAN));
        Assert.assertEquals("На 5 и 6 секунде, в 2 день месяца", CronExpressionDescriptor.getDescription("5,6 0 0 2 * *", RUSSIAN));
    }

}
