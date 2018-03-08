package com.yuanshun.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeHelper
{
  private static String CurrentTime;
  private static String CurrentDate;

  public static String getCurrentYear()
  {
    Date NowDate = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return formatter.format(NowDate);
  }

  public static String getCurrentMonth()
  {
    Date NowDate = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("MM");
    return formatter.format(NowDate);
  }

  public static String getCurrentDay()
  {
    Date NowDate = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("dd");
    return formatter.format(NowDate);
  }

  public static String getCurrentTime()
  {
    Date NowDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    CurrentTime = formatter.format(NowDate);
    return CurrentTime;
  }

  public static String getCurrentCompactTime() {
    Date NowDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    CurrentTime = formatter.format(NowDate);
    return CurrentTime;
  }

  public static String getYesterdayCompactTime()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(5, -1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    CurrentTime = formatter.format(cal.getTime());
    return CurrentTime;
  }

  public static String getYesterdayCompactTimeForFileName()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(5, -1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    CurrentTime = formatter.format(cal.getTime());
    return CurrentTime;
  }

  public static String getCurrentDate()
  {
    Date NowDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    CurrentDate = formatter.format(NowDate);
    return CurrentDate;
  }

  public static String getCurrentDate1()
  {
    Date NowDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
    CurrentDate = formatter.format(NowDate);
    return CurrentDate;
  }

  public static String getCurrentFirstDate()
  {
    Date NowDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
    CurrentDate = formatter.format(NowDate);
    return CurrentDate;
  }

  public static String getCurrentLastDate()
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = null;
    try {
      Date date = formatter.parse(getCurrentFirstDate());
      calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(2, 1);
      calendar.add(6, -1);
      return formatDate(calendar.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String formatDate(Date date)
  {
    return formatDateByFormat(date, "yyyy-MM-dd");
  }

  public static String formatDateByFormat(Date date, String format)
  {
    String result = "";
    if (date != null)
    {
      try
      {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(date);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return result;
  }

  public static String addDay(String currentdate, int add_day)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      int year = Integer.parseInt(currentdate.substring(0, 4));
      int month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
      int day = Integer.parseInt(currentdate.substring(8, 10));

      gc = new GregorianCalendar(year, month, day);
      gc.add(5, add_day);

      return formatter.format(gc.getTime());
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  public static String addMonth(String currentdate, int add_month)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      int year = Integer.parseInt(currentdate.substring(0, 4));
      int month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
      int day = Integer.parseInt(currentdate.substring(8, 10));

      gc = new GregorianCalendar(year, month, day);
      gc.add(2, add_month);

      return formatter.format(gc.getTime());
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  public static int monthDiff(String beforeTime, String endTime)
  {
    if ((beforeTime == null) || (endTime == null))
      return 0;

    try
    {
      int beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
      int endYear = Integer.parseInt(endTime.substring(0, 4));
      int beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
      int endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
      return ((endYear - beforeYear) * 12 + endMonth - beforeMonth);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static String addMinute(String currentdatetime, int add_minute)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      int year = Integer.parseInt(currentdatetime.substring(0, 4));
      int month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
      int day = Integer.parseInt(currentdatetime.substring(8, 10));

      int hour = Integer.parseInt(currentdatetime.substring(11, 13));
      int minute = Integer.parseInt(currentdatetime.substring(14, 16));
      int second = Integer.parseInt(currentdatetime.substring(17, 19));

      gc = new GregorianCalendar(year, month, day, hour, minute, second);
      gc.add(12, add_minute);
      return formatter.format(gc.getTime());
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  public static String reductionMinute(String currentdatetime, int minute)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      Date date = format.parse(currentdatetime);
      long Time = date.getTime() / 1000L - 60 * minute;
      date.setTime(Time * 1000L);
      return format.format(Long.valueOf(date.getTime()));
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  public static String addSecond(String currentdatetime, int add_second)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      int year = Integer.parseInt(currentdatetime.substring(0, 4));
      int month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
      int day = Integer.parseInt(currentdatetime.substring(8, 10));

      int hour = Integer.parseInt(currentdatetime.substring(11, 13));
      int minute = Integer.parseInt(currentdatetime.substring(14, 16));
      int second = Integer.parseInt(currentdatetime.substring(17, 19));

      gc = new GregorianCalendar(year, month, day, hour, minute, second);
      gc.add(13, add_second);

      return formatter.format(gc.getTime());
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }

  public static String addMinute1(String currentdatetime, int add_minute)
  {
    GregorianCalendar gc = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    try
    {
      int year = Integer.parseInt(currentdatetime.substring(0, 4));
      int month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
      int day = Integer.parseInt(currentdatetime.substring(8, 10));

      int hour = Integer.parseInt(currentdatetime.substring(8, 10));
      int minute = Integer.parseInt(currentdatetime.substring(8, 10));
      int second = Integer.parseInt(currentdatetime.substring(8, 10));

      gc = new GregorianCalendar(year, month, day, hour, minute, second);
      gc.add(12, add_minute);

      return formatter.format(gc.getTime());
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }




  public static int getTotalDaysOfMonth(String strDate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = new GregorianCalendar();

    Date date = null;
    try {
      date = sdf.parse(strDate);
      calendar.setTime(date);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    int day = calendar.getActualMaximum(5);
    return day;
  }

  public static long getDateSubDay(String startDate, String endDate)
  {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    long theday = 2761244709505466368L;
    try {
      calendar.setTime(sdf.parse(startDate));
      long timethis = calendar.getTimeInMillis();
      calendar.setTime(sdf.parse(endDate));
      long timeend = calendar.getTimeInMillis();
      theday = (timeend - timethis) / 86400000L;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return theday;
  }

  public static String getPlusTime(String sDateTime, String eDateTime)
    throws ParseException
  {
    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date ssDateTime = myFormatter.parse(sDateTime);
    Date eeDateTime = myFormatter.parse(eDateTime);
    long l = eeDateTime.getTime() - ssDateTime.getTime();
    long day = l / 86400000L;
    long hour = l / 3600000L - day * 24L;
    long min = l / 60000L - day * 24L * 60L - hour * 60L;
    long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
    return day + "天" + hour + "小时" + min + "分" + s + "秒";
  }

  public static int getTime(String sDateTime, String eDateTime)
    throws ParseException
  {
    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
    Date ssDateTime = myFormatter.parse(sDateTime);
    Date eeDateTime = myFormatter.parse(eDateTime);
    long l = eeDateTime.getTime() - ssDateTime.getTime();
    long day = l / 86400000L;
    return (int)day;
  }

  public static long getPlusTotalTime(String sDateTime, String eDateTime)
    throws ParseException
  {
    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date ssDateTime = myFormatter.parse(sDateTime);
    Date eeDateTime = myFormatter.parse(eDateTime);
    return (eeDateTime.getTime() - ssDateTime.getTime());
  }

  public static String getWeek(String sdate)
  {
    Date date = strToDate(sdate);
    Calendar c = Calendar.getInstance();
    c.setTime(date);

    return new SimpleDateFormat("EEEE").format(c.getTime());
  }

  public static Date strToDate(String strDate)
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = formatter.parse(strDate, pos);
    return strtodate;
  }

  public static String convertToString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return formatter.format(date);
    } catch (Exception e) {
    }
    return null;
  }


  public static String getTimeByMillis(long now)
    throws ParseException
  {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(now);
    return formatter.format(Long.valueOf(calendar.getTimeInMillis()));
  }

  public static String getTimeByMinute(String time)
    throws Exception
  {
    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
    Date d = sd.parse(time.substring(0, time.length() - 2));
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return format.format(d);
  }
}