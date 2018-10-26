
public abstract class AbstractObservation 
{
    protected boolean valid;
    
    /**
     * empty because its abstract
     */
    public AbstractObservation()
    {
        //default constructor
    }
    
    public abstract boolean isValid();
}
