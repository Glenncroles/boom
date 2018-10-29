import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class TestMapData1
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
        Assert.assertEquals(test.getStatistics(StatsType.MINIMUM, "SRAD").getValue(), 639.0 , 0.1);
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
        assertEquals(number, 639.0, 0.1);
    }
    
    

    

    
   
    
    

}
