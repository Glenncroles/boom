import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class tests
{
    //my own smaller data set fro easier calculation
    public static int year = 2118;
    public static int month = 8;
    public static int day = 30;
    public static int hour = 17;
    public static int min = 45;
    public static String dir = "directory";

    
    
    @Test
    public void testToString() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        test.parseFile();
        assertEquals(test.toString(), " ");
    }
    
    
    @Test
    public void testMapData() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        test.parseFile();
        assertEquals(test.toString(), " ");
        
    }
    
    
    @Test
    public void testCreateFileName() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        String result = test.createFileName(year, month, day, hour, min, dir);
        assertEquals(result, "directory/211808301745.mdf");
    }
    

    @Test
    public void testParseFile() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        test.parseFile();
        Assert.assertEquals(test.getStatistics(StatsType.MINIMUM, "SRAD").getValue(), 31.4 , 0.1);
    }
    
    
    @Test
    public void testgetIndexOf() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        test.parseFile();
        int number =test.getIndexOf("SRAD");
        assertEquals(number, 13);
    }
    
    
    @Test
    public void testgetStatistics() throws IOException
    {
        MapData test = new MapData(year, month, day, hour, min, dir);
        test.parseFile();
        double number = test.getStatistics(StatsType.MINIMUM, "SRAD").getValue();
        assertEquals(number, 31.4, 0.1);
    }
    
    
    @Test
    public void testObservation()
    {
        Observation test = new Observation(0, null);
        double result = test.getValue();
        assertEquals(result, 0, 0.1);
    }
    
    
    @Test
    public void testvalue()
    {
        Observation test = new Observation(0, null);
        assertEquals(test.getValue(), 0 , 0.1);
    }
    
    
    @Test
    public void testvalid()
    {
        Observation test = new Observation(-999, null);
        assertEquals(test.isValid(), false );
    }
    
    
    @Test
    public void testStid()
    {
        Observation test = new Observation(0, null);
        String result = test.getStid();
        assertEquals(null, result);
    }
    
    
    @Test
    public void testToStringO()
    {
        Observation test = new Observation(0, null);
        String result = test.toString();
        assertEquals(result, " ");
        
    }

    
    
    @Test
    public void tesAbstractObservation() throws IOException
    {
        Observation test = new Observation(-999, null);
        assertEquals(test.isValid(), false );
        
    }

    
   
    @Test
    public void testStatistics() throws IOException
    {
        Statistics test = new Statistics(0.0," ", " ", 0, StatsType.AVERAGE);
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
        Statistics test = new Statistics(0.0," ", "1999-09-09'T'09:09:09 z", 0, StatsType.AVERAGE);
        String dateTimeStr = "1999-10-19'T'19:19:19 z";
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        int g = i.get(Calendar.YEAR);
        GregorianCalendar j = test.createDateFromString(dateTimeStr);
        int h = j.get(Calendar.YEAR);
        assertEquals(g,h);
         
         
    }
    
    
    @Test
    public void testcreateStringFromDate() throws IOException
    {
        Statistics test = new Statistics(0.0," ", " ", 0, StatsType.AVERAGE);
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
        String j = test.createStringFromDate(i);
        String h = i.toString();
        assertEquals(j, h);
    }
    
    
    @Test
    public void testgetNumberOfReportingStations() throws IOException
    {
        Statistics test = new Statistics(0.0," ", " ", 0, StatsType.AVERAGE);
        int i = test.getNumberOfReportingStations();
        assertEquals(i, 0);
        
    }
    
    
    @Test
    public void testgetUTCDateTimeString() throws IOException
    {
        Statistics test = new Statistics(0.0," ", " ", 0, StatsType.AVERAGE);
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
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
    public void testolderThan() throws IOException
    {
        GregorianCalendar i = new GregorianCalendar(1999, 10, 19, 19, 19, 19);
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
    public void testStatToSting() throws IOException
    {
        Statistics test = new Statistics(0.0," ", " ", 0, StatsType.AVERAGE);
        assertEquals(test.toString(), " ");
    }

}
