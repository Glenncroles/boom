import java.util.GregorianCalendar;

public class Statistics extends Observation 
{

    final protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    
    private GregorianCalendar utcDateTime;
    
    private int numberOfReportingStations;
    
    @SuppressWarnings({ "unused" })
    private StatsType statType;
    
    private String dateTimeStr;
 
    
    //This is a constructor that bring in a str as date and time
    public Statistics(double value, String stid, String dateTimeStr, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        statType = inStatType;
        numberOfReportingStations = numberOfValidStations;
        this.dateTimeStr = dateTimeStr;
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
    
    //brings in a calender date and creates a string
    public String createStringFromDate(GregorianCalendar calendar)
    {
        dateTimeStr = calendar.toString();
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
    
    //dont know what we need to print here
    public String toString()
    {
        return " ";
    }
}
