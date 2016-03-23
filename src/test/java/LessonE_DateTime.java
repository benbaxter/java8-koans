import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There is a lot that Java 8 has done with dates and times, but here are a few
 * highlights.
 *
 * For further reference:
 * http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
 */
public class LessonE_DateTime {

    /**
     * java.util.Date and java.util.Calendar have not been replaced, but a new
     * package (java.time.*) has been introduced to give us more precise classes
     * when programming with dates and times.
     *
     * First, let's look at dates. Do you care about both the date and the time
     * (like a blog posted 3 minutes ago)?  Or do you only care about the date
     * (like how taxes are due at the end of a day) or just the time (like a
     * repeating appointment at 11:00am)?  There are now different classes to
     * represent each of these concepts.
     *
     * The biggest change is that the new classes are immutable!
     *
     * This test will compare the calendar API to the LocalTime and LocalDate
     * API. There is a LocalDate.now() method but that makes writing
     * tests for this koan a lot harder to code for.
     */
    @Test
    public void _1_dateReplacedWithLocals() {

        Calendar cal = Calendar.getInstance();
        cal.set(2016, Calendar.FEBRUARY, 2);
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        /*
         * Hint: there are 31 days in January.
         */
        assertThat(dayOfYear).isEqualTo(___);

        /*
         * Here's the equivalent using LocalDate.  Note that a LocalDate is not
         * in any time zone -- hence "local".
         */
        LocalDate groundhogDay = LocalDate.of(2016, Month.FEBRUARY, 2);
        dayOfYear = groundhogDay.get(ChronoField.DAY_OF_YEAR);
        assertThat(dayOfYear).isEqualTo(___);

        /*
         * This is how we used to represent a time.
         */
        cal = Calendar.getInstance();
        cal.set(2016, Calendar.FEBRUARY, 2, 2, 30, 45);
        assertThat(cal.get(Calendar.HOUR)).isEqualTo(___);
        assertThat(cal.get(Calendar.MINUTE)).isEqualTo(___);
        assertThat(cal.get(Calendar.SECOND)).isEqualTo(___);

        /*
         * Here's the equivalent with LocalTime.  Note that LocalTime has no
         * date associated with it.  It just represents a time.
         *
         * Notice that unlike Calendar, LocalTime has a nice API using actual
         * methods, rather than a single "get" method where you pass constants.
         */
        LocalTime time = LocalTime.of(2, 30, 45);
        assertThat(time.getHour()).isEqualTo(___);
        assertThat(time.getMinute()).isEqualTo(___);
        assertThat(time.getSecond()).isEqualTo(___);
    }

    /**
     * Time zones have gotten a huge boost with java.time. You can now move
     * through time zones very easily but you will want to watch how the
     * offset changes instead of the hour. Everything is ISO based in
     * java.time by default. There was a huge effort to keep dates and times
     * ISO based and it shows in their implementation.
     */
    @Test
    public void _2_timezones() {

        /*
         * By default, LocalDateTime uses the UTC time zone.
         */
        LocalDateTime localTime = LocalDateTime.of(2016, Month.FEBRUARY, 2, 8, 30, 22);
        assertThat(localTime.getHour()).isEqualTo(___);

        /*
         * This will "move" the time to New York time.  However, the time won't
         * really move. It will just change the offset.
         */
        ZonedDateTime newYorkTime = localTime.atZone(ZoneId.of("America/New_York"));
        assertThat(newYorkTime.getHour()).isEqualTo(___);
        assertThat(newYorkTime.getOffset()).isEqualTo(ZoneOffset.ofHours(___));

        /*
         * If we "move" to Los Angeles, the offset will adjust but the hour of
         * the day will remain the same.
         */
        ZonedDateTime losAngelesTime = localTime.atZone(ZoneId.of("America/Los_Angeles"));
        assertThat(losAngelesTime.getHour()).isEqualTo(___);
        assertThat(losAngelesTime.getOffset()).isEqualTo(ZoneOffset.ofHours(___));
    }

    /**
     * Doing "arithmetic" with dates is also a lot easier now! Imagine we know
     * when Spring Break begins, and we know that it's 7 days long. We can
     * quickly find out when it ends.
     *
     * There are really simple minus and add methods, but you will need to
     * remember that we are dealing with immutable objects. So when you perform
     * arithmetic, you will need to capture the returned value, since the
     * original value will not be modified.
     */
    @Test
    public void _3_math() {

        LocalDate springBreakStart = LocalDate.of(2016, Month.MARCH, 12);
        LocalDate springBreakEnd = springBreakStart.plusDays(7);

        assertThat(springBreakStart.getDayOfMonth()).isEqualTo(___);
        assertThat(springBreakEnd.getDayOfMonth()).isEqualTo(___);
    }

    /**
     * One of the most common things we do as humans is create recurring events.
     * For example, if you have a payroll system that pays the employees every
     * two weeks, creating a calendar of pay days can be tedious. Java 8 has
     * introduced something called Periods that represent a symbolic chunk of
     * time in days, weeks or months.
     */
    @Test
    public void _4_periods() {

        Period quarter = Period.ofMonths(3);
        LocalDate quarterlyMeeting = LocalDate.of(2016, Month.FEBRUARY, 2);
        LocalDate nextMeeting = quarterlyMeeting.plus(quarter);

        assertThat(nextMeeting.getDayOfMonth()).isEqualTo(___);
    }

    /**
     * Sometimes we want to know how long it has been between two time stamps.
     * Caching and analytics are two prime candidates that will love this new
     * class called {@link Duration}. You can create a duration given two
     * dates or times.
     *
     * The difference between a Period and a Duration is that Duration is a
     * concrete, specific amount of time, accurate down to the nanosecond.  A
     * Period, on the other hand, represents a symbolic, calendar-based time
     * period (days, weeks, months, or years).  February 1 to March 1 is always
     * a Period of 1 month, but the Duration is different during leap years.
     */
    @Test
    public void _5_duration() {

        Period oneYearPeriod = Period.ofYears(1);

        /*
         * Danger, Will Robinson! 2016 is a leap year.
         */
        Duration oneYearDuration = Duration.ofDays(365);

        LocalDateTime groundhogDay2016 = LocalDateTime.of(2016, Month.FEBRUARY, 2, 1, 0); // 2-2-2016 1:00am
        LocalDateTime groundhogDay2017 = groundhogDay2016.plus(oneYearPeriod);
        LocalDateTime notGroundhogDay2017 = groundhogDay2016.plus(oneYearDuration);
        Duration leapYearDuration = Duration.between(groundhogDay2016, groundhogDay2017);

        assertThat(groundhogDay2017.getDayOfMonth()).isEqualTo(___);
        assertThat(notGroundhogDay2017.getDayOfMonth()).isEqualTo(___);

        long leapYearDays = leapYearDuration.toDays();
        assertThat(leapYearDays).isEqualTo(___);
        assertThat(leapYearDays != oneYearDuration.toDays());

        /*
         * Durations are typically used to move by a certain number of hours, minutes or
         * seconds, not days -- and now you see why.
         */
        Duration fiveHours = _____;
        LocalDateTime groundhogDayAlarmClock = groundhogDay2016.plus(fiveHours);
        assertThat(groundhogDayAlarmClock.getHour()).isEqualTo(6);
    }

    /**
     * One of the most common things that we do with the old Dates is to show
     * them to people.  Happily, we can format our new date classes in a
     * similar way.
     */
    @Test
    public void _6_formatting() {

        /**
         * Notice no "new SimpleDateFormat(pattern)".  That's because we're
         * formatting LocalDateTimes, not Dates.
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        LocalDateTime july4th = LocalDateTime.of(2016, Month.JULY, 4, 2, 33); // 7-4-2016 2:33

        assertThat(formatter.format(july4th)).isEqualTo(____);
    }

    /*
     * Please do not change these variables.  They are required for the tests to
     * compile with the underscores in them.
     */
    int ___;
    String ____;
    Duration _____;
}
