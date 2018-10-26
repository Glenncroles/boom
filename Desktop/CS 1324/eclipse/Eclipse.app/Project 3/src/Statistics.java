import java.text.ParseException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.GregorianCalendar;

public class Statistics extends Observation 
{

    final protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    
    protected DateTimeFormatter format;
    
    private GregorianCalendar utcDateTime;
    
    private ZonedDateTime zdtDateTime;
    
    private int numberOfReportingStations;
    
    @SuppressWarnings({ "unused" })
    private StatsType statType;
    
    
 
    
    //This is a constructor that bring in a str as date and time
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        statType = inStatType;
        numberOfReportingStations = numberOfValidStations;
        zdtDateTime = dateTime;
    }
    
    //This is a constructor that bring in a gregorianCalender as date and time
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        statType = inStatType;
        numberOfReportingStations = numberOfValidStations;
        utcDateTime = dateTime;
    }
    
    //brings in the dateTimeStr and creates a Gregorian date
    //"yyyy-MM-dd'T'HH:mm:ss z";
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {
        //take the information needed from the string and store into approprate varables
        int year = Integer.parseInt(dateTimeStr.substring(0, 4));
        int month = Integer.parseInt(dateTimeStr.substring(5,6));
        int day = Integer.parseInt(dateTimeStr.substring(8,9));
        int hour = Integer.parseInt(dateTimeStr.substring(13,14));
        int min = Integer.parseInt(dateTimeStr.substring(16,17));
        int sec = Integer.parseInt(dateTimeStr.substring(19,20));
        //set the calender to the variables
        utcDateTime = new GregorianCalendar(year, month, day, hour, min, sec);
        return utcDateTime;
    }
    
    
    public ZonedDateTime createZDateFromString(String dateTimeStr) throws ParseException
    {
        //take the information needed from the string and store into approprate varables
        //found a much easier way to do this on stack overflow author ssf
        DateTimeFormatter a = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        //return the new calendar 
        return ZonedDateTime.parse(dateTimeStr, a);
    }
    
    
    
    //brings in a calender date and creates a string
    public String createStringFromDate(GregorianCalendar calendar)
    {
        String dateTimeStr = calendar.toString();
        return dateTimeStr;
    }
    
    /**
     * creates a string from a ztd calendar
     * @param calendar
     * @return
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        String dateTimeStr = calendar.toString();
        return dateTimeStr;
    }
    
    
    
    //finds out how many stations are reporting
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }
    
    //gets the utcDateTimeStr
    public String getUTCDateTimeString()
    { 
        String dateTimeStr = createStringFromDate(zdtDateTime);
        return dateTimeStr.toString();
    } 
    
    //Finds out if your date is newer than the old date
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.before(inDateTime);
    } 
    
    //Finds out if your date is older than the old date
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        
        return utcDateTime.after(inDateTime);
    }
    
    //Finds out if your date is the same as the old date
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return utcDateTime.equals(inDateTime);
    }
    
    //Finds out if your date is newer than the old date
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return zdtDateTime.isBefore(inDateTime);
    } 
    
    //Finds out if your date is older than the old date
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        
        return zdtDateTime.isAfter(inDateTime);
    }
    
    //Finds out if your date is the same as the old date
    
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        return zdtDateTime.equals(inDateTime);
    }
    
    //dont know what we need to print here
    public String toString()
    {
        return " ";
    }
}
