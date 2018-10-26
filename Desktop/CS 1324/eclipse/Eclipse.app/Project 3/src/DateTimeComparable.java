import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public interface DateTimeComparable
{
    //for gregorianCalendar
    boolean newerThan(GregorianCalendar inDateTimeUTC);
    boolean olderThan(GregorianCalendar inDateTimeUTC);
    boolean sameAs(GregorianCalendar inDateTimeUTC);  
    
    //for SonedDateTime
    boolean newerThan(ZonedDateTime inDateTimeUTC);
    boolean olderThan(ZonedDateTime inDateTimeUTC);
    boolean sameAs(ZonedDateTime inDateTimeUTC);
}
