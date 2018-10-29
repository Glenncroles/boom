import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.TestCase;

public class TestStatistics1 extends TestCase
{
    @Test
    public void testStatistics11() throws IOException
    {
        ZonedDateTime ztd = null;
        Statistics test = new Statistics(0.0," ", ztd, 0, StatsType.AVERAGE);
        int i = test.getNumberOfReportingStations();
        assertEquals(i, 0);
    }
    
    
    @Test
    public void testStatisticsGC() throws IOException
    {
        GregorianCalendar utcDateTime = null;
        Statistics test = new Statistics(0.0," ", utcDateTime, 0, StatsType.AVERAGE);
        int i = test.getNumberOfReportingStations();
        assertEquals(i, 0);
        
    }
    
    
    @Test
    public void testcreateDateFromString() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        String dateTimeStr = "1999-10-19'T'19:19:19 z";
        int g = i.get(Calendar.YEAR);
        GregorianCalendar j = test.createDateFromString(dateTimeStr);
        int h = j.get(Calendar.YEAR);
        assertEquals(g,h);
         
         
    }
    
    
    @Test
    public void testcreateStringFromDate() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        
        String j = test.createStringFromDate(i);
        String h = i.toString();
        assertEquals(j, h);
    }
    
    /**
    @Test
    public void testzcreateStringFromDate() throws IOException, ParseException
    {
        ZonedDateTime i = ZonedDateTime.of(1999, 10, 9, 9, 9, 9, 9, ZoneId.of("Europe/Paris"));
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
         //createZDateFromString
        String t = test.createStringFromDate(i);
        ZonedDateTime j = test.createZDateFromString(t);
        j.toString();
        assertEquals(j, i);
    }
    **/
    
    @Test
    public void testgetNumberOfReportingStations() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        int c = test.getNumberOfReportingStations();
        assertEquals(c, 0);
        
    }
    
    
    @Test
    public void testgetUTCDateTimeString() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        
        String j = test.createStringFromDate(i);
        String g = test.getUTCDateTimeString();
        assertEquals(j, g);
        
    }
    
    
    @Test
    public void testnewerThan() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.newerThan(i),false);
    }
    
    
    @Test
    public void testznewerThan() throws IOException
    {
        ZonedDateTime i = ZonedDateTime.of(1999, 10, 9, 9, 9, 9, 9, ZoneId.of("Europe/Paris"));
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.newerThan(i),false);
    }
    
    
    @Test
    public void testolderThan() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.olderThan(i),false);
    }
    
    
    @Test
    public void testzolderThan() throws IOException
    {
        ZonedDateTime i = ZonedDateTime.of(1999, 10, 9, 9, 9, 9, 9, ZoneId.of("Europe/Paris"));
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.olderThan(i),false);
    }
    
    @Test
    public void testsameAs() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.sameAs(i),true);
    }
    
    
    @Test
    public void testzSameAs() throws IOException
    {
        ZonedDateTime i = ZonedDateTime.of(1999, 10, 9, 9, 9, 9, 9, ZoneId.of("Europe/Paris"));
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.sameAs(i),true);
    }
    
    
    @Test
    public void testStatToSting() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        Statistics test = new Statistics(0.0," ", i, 0, StatsType.AVERAGE);
        assertEquals(test.toString(), " ");
    }

}
