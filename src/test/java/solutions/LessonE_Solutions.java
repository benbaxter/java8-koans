package solutions;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There is a lot that java 8 has done with dates and time but here
 * are a few highlights.
 *
 * For more reference:
 * http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
 */
public class LessonE_Solutions {


    int ___;

    /**
     * java.util.Date and java.util.Calendar have not been replaced
     * but a new package (java.time.*) has been introduced to give
     * us a better handle when it comes to programming for time.
     *
     * There are multiple concepts when it comes to dates, but let's
     * start with creating dates. When approaching dates and times,
     * you will want to be explicit. Do you care about the time of
     * day (like a blog posted 3 minutes ago) or do you care about
     * the day in general (like how taxes are due at the end of a day)
     *
     * The biggest change to note is that dates are now immutable!
     *
     * This test will compare the calendar api to the LocalTime and LocalDate
     * api. There is a LocalDate.now() method but that makes writing
     * tests for this koan a lot harder to code for.
     */
    @Test
    public void _1_dateReplacedWithLocals() {

        Calendar cal = Calendar.getInstance();
        cal.set(2016, Calendar.FEBRUARY, 2);;
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        //hint there are 31 days in January
        assertThat(dayOfYear).isEqualTo(33);

        LocalDate groundHogsDay = LocalDate.of(2016, Month.FEBRUARY, 2);
        dayOfYear = groundHogsDay.get(ChronoField.DAY_OF_YEAR);
        assertThat(dayOfYear).isEqualTo(33);

        cal = Calendar.getInstance();
        cal.set(2016, Calendar.FEBRUARY, 2, 2, 30, 45);
        assertThat(cal.get(Calendar.HOUR)).isEqualTo(2);
        assertThat(cal.get(Calendar.MINUTE)).isEqualTo(30);
        assertThat(cal.get(Calendar.SECOND)).isEqualTo(45);

        //notice that unlike calendar, LocalTime has a well defined api
        //compared to a flag based api
        LocalTime time = LocalTime.of(2, 30, 45);
        assertThat(time.getHour()).isEqualTo(2);
        assertThat(time.getMinute()).isEqualTo(30);
        assertThat(time.getSecond()).isEqualTo(45);
    }

    /**
     * Time zones have gotten a huge boost with java.time. You can now move
     * through time zones very easily but you will want to watch how the
     * offset changes instead of the hour. Everything is ISO based with
     * java.time by default. The was a huge effort to keep dates and time
     * ISO based and it shows in their implementation.
     */
    @Test
    public void _2_timezones() {

        //this will default to UTC time
        LocalDateTime localTime = LocalDateTime.of(2016, Month.FEBRUARY, 2, 8, 30, 22);
        assertThat(localTime.getHour()).isEqualTo(8);

        //This will 'move' the time to New York time, however the time
        //will not really move. It will just keep track of the offset
        ZonedDateTime newYorkTime = localTime.atZone(ZoneId.of("America/New_York"));
        assertThat(newYorkTime.getHour()).isEqualTo(8);
        assertThat(newYorkTime.getOffset()).isEqualTo(ZoneOffset.ofHours(-5));

        //if we move to Los Angeles, the offset will adjust but the hour of day will
        //remain the same
        ZonedDateTime losAngelesTime = localTime.atZone(ZoneId.of("America/Los_Angeles"));
        assertThat(losAngelesTime.getHour()).isEqualTo(8);
        assertThat(losAngelesTime.getOffset()).isEqualTo(ZoneOffset.ofHours(-8));
    }

    /**
     * Doing 'arithmetic' with dates is also a lot easier now! Image we know
     * when spring break begins and know that spring break is 7 days long. We
     * can quickly find out when spring break ends.
     *
     * There are really simple minus and add methods but you will need to remember
     * that we are dealing with immutable objects so when you perform arithmetic
     * you will need to capture the returned value as the original value will not
     * be modified.
     */
    @Test
    public void _3_math() {

        LocalDate springBreakStart = LocalDate.of(2016, Month.MARCH, 12);
        LocalDate springBreakEnd = springBreakStart.plusDays(7);

        assertThat(springBreakStart.getDayOfMonth()).isEqualTo(12);
        assertThat(springBreakEnd.getDayOfMonth()).isEqualTo(19);
    }

    /**
     * One of the most common things we do as humans is create recurring events.
     * For example, if you have a pay role system that pays the employees every
     * two weeks, creating a calendar of pay days can be tedious. Java 8 has
     * introduced something called Periods that represent a chunk of time that
     * is a certain distance apart.
     */
    @Test
    public void _4_periods() {

        Period payPeriod = Period.ofWeeks(2);
        LocalDate payDay = LocalDate.of(2016, Month.FEBRUARY, 2);
        LocalDate nextPayDay = payDay.plus(payPeriod);

        assertThat(nextPayDay.getDayOfMonth()).isEqualTo(16);
    }

    /**
     * Sometimes we want to know how long it has been between two time stamps.
     * Caching and analytics are two prime candidates who will love this new
     * class called {@link Duration}. You can create a duration given two
     * dates or times.
     *
     * When thinking about duration, the focus is on the difference between the
     * two times. The difference between duration and periods are that periods
     * are points on a time line where as a duration is the distance between
     * those points. Aka, duration is more for fine grained operations with
     * hours and seconds whereas periods are more for days and months. Duration
     * is to time as Period is to date.
     *
     */
    @Test
    public void _5_duration() {

        LocalDateTime firstVisit = LocalDateTime.of(2016, Month.JULY, 4, 2, 33); // 7-4-2016 2:33
        LocalDateTime secondVisit = LocalDateTime.of(2016, Month.JULY, 4, 7, 33); // 7-4-2016 7:33

        Duration duration = Duration.between(firstVisit, secondVisit);
        assertThat(duration.toHours()).isEqualTo(5);
    }

    /**
     * One of the most common things that we do with the old Dates is to show
     * them to people.  Happily, we can format our new date classes in a
     * similar way.
     */
    @Test
    public void _6_formatting() {

        /**
         * Notice no "new SimpleDateFormat(pattern)" and this is because we are
         * using a different class for formatting. We are no longer formatting
         * dates but rather LocalDateTimes.
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        LocalDateTime july4th = LocalDateTime.of(2016, Month.JULY, 4, 2, 33); // 7-4-2016 2:33

        assertThat(formatter.format(july4th)).isEqualTo("07/04/2016 02:33");
    }
}
