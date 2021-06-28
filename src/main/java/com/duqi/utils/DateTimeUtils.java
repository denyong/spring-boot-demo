package com.duqi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dengyong
 */
@Slf4j
public class DateTimeUtils {

    private DateTimeUtils() {
        throw new IllegalStateException("DateTimeUtils class");
    }

    public static final String YYYY_MM_dd = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String getLocalDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYY_MM_dd));
    }

    public static String getLocalDate(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    public static String getYesterday() {
        return LocalDateTime.now().plusDays(-1).format(DateTimeFormatter.ofPattern(YYYY_MM_dd));
    }

    public static String getBeforeDay(long days) {
        return LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern(YYYY_MM_dd));
    }

    public static String getAfterDay(String date, long days) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_dd);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate.plusDays(days).format(DateTimeFormatter.ofPattern(YYYY_MM_dd));
    }

    public static String getEarlierDate(String date1, String date2) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_dd);
        LocalDate localDate1 = LocalDate.parse(date1, dateTimeFormatter);
        LocalDate localDate2 = LocalDate.parse(date2, dateTimeFormatter);
        boolean after = localDate1.isAfter(localDate2);
        if (after) {
            return date2;
        } else {
            return date1;
        }
    }

    public static String getMinusDate(long minus) {
        return LocalDateTime.now().plusDays(minus).format(DateTimeFormatter.ofPattern(YYYY_MM_dd));
    }
    /**
     * 获取两个日期之间的所有日期 yyyy-mm-dd
     *
     * @param startTime 开始日期 yyyy-mm-dd
     * @param endTime   结束日期 yyyy-mm-dd
     */
    public static List<String> getBetweenDays(String startTime, String endTime) {
        return getBetweenDays(startTime, endTime, YYYY_MM_dd);
    }

    public static List<String> getBetweenDays(String startTime, String endTime, String pattern) {
        List<String> days = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);
            while (tempStart.before(tempEnd)) {
                days.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            log.error(e.toString());
        }
        return days;
    }

    public static String stampToDate(String timeStamp) {
        if (StringUtils.isBlank(timeStamp)) {
            return null;
        }
        long stamp = Long.parseLong(timeStamp);
        return stampToDate(stamp);
    }

    public static String stampToDate(long time) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
            return dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
        } catch (Exception e) {
            log.error("stampToDate error:{}", e.toString());
            return "";
        }
    }

    public static Date parseDate(String date, String pattern) {
        return dateParseLocalDateTime(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)));
    }

    public static Date dateParseLocalDateTime(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static String dealTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return StringUtils.isBlank(date) ? date : format.format(DateTimeUtils.parseDate(date, DateTimeUtils.YYYY_MM_DD_HH_MM_SS));
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(new Date());
    }

    public static String nowDayZero() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

}
