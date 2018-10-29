import org.junit.Test;

import junit.framework.TestCase;

public class TestObsevation extends TestCase
{
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

}
