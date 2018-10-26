

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.Set;
import java.util.TreeMap;


public class MapData
{
    /**
    @author <Glenn Roles>
    @version <9-10-18>
    Lab <12>
    <I dont really know yet>
     */


    /**
     * universal arrayslist hashmap
     */
    HashMap<String,ArrayList<Observation> > dataCatalog;
    
    /**
     * Keeps track of all the positions
     */
    TreeMap<String,Integer> paramPositions;
    
    /**
     * keeps the treemap of statistics of the differnt stattypes
     */
    EnumMap<StatsType, TreeMap<String, Statistics>> statistics;

    /**
     * number of stations
     */
    private Integer numberOfStations = null;

    //Ta9m string name

    private String TA9M = "TA9M";

    //TAIR string name

    private String TAIR = "TAIR";

    //SRAD string name

    private String SRAD = "SRAD";

    //STID string name

    private String STID = "STID";

    /**
     * MESONET a string for mesonet
     */
    private static final String MESONET = "Mesonet";

    //-directory:String

   

    //-fileName: String

    private String fileName;

    //-utcDateTime: GregorianCalendar had to import the gregor cal

    private GregorianCalendar utcDateTime;

    /**
     * The year of the data.
     */
    private int year;

    /**
     * The month of the data.
     */
    private int month; 

    /**
     * The month of the data.
     */
    private int day;

    /**
     * The hour of the data.
     */
    private int hour;

    /**
     * The minute of the data.
     */
    private int minute;
    
    private ArrayList<Observation> split1 = new ArrayList<Observation>();
    private ArrayList<Observation> split2 = new ArrayList<Observation>();
    private ArrayList<Observation> split3 = new ArrayList<Observation>();

    /**
     * MapData will read in the day of the data from a text file 
     * and store it in an array
     * 
     * @author coysmac
     * @param year, month, day, hour, minute, and directory
     * @throws IOException 
     */

    public MapData(int year, int month, int day, int hour, int minute, String directory) throws IOException
    {    
        //make all the vairables usable
        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);

        //initalize year ect.
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        
        
        //initialize arrays
        dataCatalog = new HashMap<>();
        paramPositions = new TreeMap<>();

        numberOfStations = 116;
        fileName = createFileName(year, month, day, hour, minute, directory);
        parseParamHeader(fileName);

    }



    /**
     * takes the year, month, day, hour, and minute and makes a filename
     * %%%%%% need to figure out how to pad it with 0 %%%%%%
     * @author coysmac
     * @return the data as a string
     * @throws FileNotFoundException 
     */

    public String createFileName(int year, int month, int day, int hour, int minute, String directory) throws FileNotFoundException
    {
        //build the string with format
        return String.format("%s/%4d%02d%02d%02d%02d.mdf",directory, year, month, day, hour, minute);
    }


    /**
     * -parseParamHeader(String inParamStr):void
     * not sure what this is to do
     * @throws IOException 
     */

    private void parseParamHeader(String inParamStr) throws IOException
    {
        

        //split 
        String[] split = inParamStr.trim().split("\\s+");

        for(Integer i = 0; i < split.length; i++)
        {
            if(split[i].equalsIgnoreCase(SRAD))
            {
                paramPositions.put(SRAD, i);
            }

            if(split[i].equalsIgnoreCase(TAIR))
            {
                paramPositions.put(TAIR, i);
            }

            if(split[i].equalsIgnoreCase(TA9M))
            {
                paramPositions.put(TA9M, i);
            }

            if(split[i].equalsIgnoreCase(STID))
            {
                paramPositions.put(STID, i);
            }
        }
    }
    
    /**
     * Finds the index of the string
     */
    public Integer getIndexOf(String inParamStr)
    {
        return paramPositions.get(inParamStr);
    }


    /**
     * take the file and breaks it into usable variables
     * I took this from the Team.java file in lab 3
     * also had help from one of the TA's
     * @throws IOException 
     * @
     */

    public void parseFile() throws IOException
    {

        // creating the bufferedReader that reads in the file selected 
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String strg;  // String containing a line of data from the file.

        // TODO: Throw out header
        strg = br.readLine();
        strg = br.readLine();
        strg = br.readLine();
        
        //parse header
        parseParamHeader(strg);
        strg = br.readLine();



        // TODO: Read in first row containing data, create and add a Mapdata object to the array.
        while (strg != null)
        {
            
            
            //creating a string array to hold the lines data
            String[] split = strg.trim().split("\\s+");
            


            //creating a new observation srad to hold the data doing the same for the others
            Observation srad = new Observation(Double.parseDouble(split[paramPositions.get(SRAD)]), split[paramPositions.get(STID)]); 
            split1.add(srad);
            

            Observation tair = new Observation(Double.parseDouble(split[paramPositions.get(TAIR)]), split[paramPositions.get(STID)]);
            split2.add(tair);
            
            

            Observation ta9m = new Observation(Double.parseDouble(split[paramPositions.get(TA9M)]), split[paramPositions.get(STID)]);
            split3.add(ta9m);
  
            
            
            //call the prepareDataCatalog to put the observations in the right place
            //putting the arrayslits into dataCatalog 
            prepareDataCatalog();
            
            //call the calculateStatics method to caluculate all the stats
            calculateStatistics();
            
            
            //read in next line
            strg = br.readLine();

            
        }
        



        //close the bufferedReader
        br.close();

    }



    /**
     * Prints the data 
     * 
     * @return the printable data
     */

    
    
    
    /**
     * calculates the statics of all the String types
     */
    private void calculateAllStatistics()
    {
        statistics = new EnumMap<>(StatsType.class);
        
        //create TreeMaps for storing the new stats 
        TreeMap<String, Statistics> treeMin = new TreeMap<>();
        TreeMap<String, Statistics> treeMax = new TreeMap<>();
        TreeMap<String, Statistics> treeTtl = new TreeMap<>();
        TreeMap<String, Statistics> treeAvg = new TreeMap<>();

        //need a min, max, total, and average
        double min = 10000000000000000000000000000000.0;
        double max = -10000000000000000000000000000000.00;
        double total = 0;
        double average = 0;
        
        //temp for both min and max to put the values into to test against
        String minTemp = null;
        String maxTemp = null;
        
        Set<String> parameterIds = dataCatalog.keySet();
        
        ArrayList<Observation> data;
        
        //for loop to go threw the parameterIds
        for(String paramId: parameterIds)
        {
        
        //Arraylist for the data hence the name
        data = dataCatalog.get(paramId);
        
        
        
        if(!paramId.equalsIgnoreCase(STID))
        {
        //Iterating over data 
        for(Observation stats: data)
        {
            
            
            //finds the min value
            if(stats.getValue() < min && stats.isValid() && stats != null)
            {
                min = stats.getValue();
                minTemp = stats.getStid();
            }
            
            //finds the max value
            else if(stats.getValue() > max && stats.isValid() && stats != null)
            {
                max = stats.getValue();
                maxTemp = stats.getStid();
            }
            
            //adds the total if the value is valid
            if(stats.isValid() && stats != null)
            {
                total += stats.getValue();
                
            }
            
            
        }  
        }
        numberOfStations = data.size();
        //compute the average
        average = total/numberOfStations;
        
        
        
        //making a statistic
        Statistics statMin = new Statistics(min, minTemp, utcDateTime, numberOfStations, StatsType.MINIMUM);
        Statistics statMax = new Statistics(max, maxTemp, utcDateTime, numberOfStations, StatsType.MAXIMU);
        Statistics statTtl = new Statistics(total, MESONET, utcDateTime, numberOfStations, StatsType.TOTAL);
        Statistics statAvg = new Statistics(average, MESONET, utcDateTime, numberOfStations, StatsType.AVERAGE);
        
        //put the new stats into their TreeMaps
        treeMin.put(paramId, statMin);
        treeMax.put(paramId, statMax);
        treeTtl.put(paramId, statTtl);
        treeAvg.put(paramId, statAvg);
        }
        
        //put the new stats into their statistics
        statistics.put(StatsType.MINIMUM, treeMin);
        statistics.put(StatsType.MAXIMU, treeMax);
        statistics.put(StatsType.TOTAL, treeTtl);
        statistics.put(StatsType.AVERAGE, treeAvg);
        

        
  
      
    }
    
    
    private void prepareDataCatalog()
    {
      //putting the arrayslits into dataCatalog 
        dataCatalog.put(SRAD,split1);
        dataCatalog.put(TAIR,split2);
        dataCatalog.put(TA9M,split3);
    }
    

    
    /**
     * 
     * @param inData
     * @param paramId
     */
    private void calculateStatistics()
    {
        calculateAllStatistics();
    }
    
    
    /**
     * gets the Statistics
     * got help from Camron Bartlow
     * @param type
     * @param paramId
     * @return
     */
    public Statistics getStatistics(StatsType type, String paramId)
    {
      return statistics.get(type).get(paramId);
        
    }
    
    
    /**
     * builds the toString to spesifics
     */
    public String toString()
    {

        //build the toString 
        System.out.println("=========================================================");
        String d = String.format("=== %4d-%02d-%02d %02d:%02d ===", year, month, day, hour, minute);
        System.out.println(d);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        //String a = String.format("Maximum Air Temperature[1.5m] = %3d C at %3d", tairMax.getValue(), tairMax.getStid());
        /**String a = String.format("Maximum Air Temperature[1.5m] = %3d C at %3d", getStatistics(StatsType.MAXIMU, TAIR).getValue(), 
                getStatistics(StatsType.MAXIMU, TAIR).getStid());
                **/
        System.out.println("Maximum Air Temperature[1.5m] = " + getStatistics(StatsType.MAXIMU, TAIR).getValue() + 
                " C at " + statistics.get(StatsType.MAXIMU).get(TAIR).getStid());
        System.out.println("Minimum Air Temperature[1.5m] = " + statistics.get(StatsType.MINIMUM).get(TAIR).getValue() + 
                " C at " + statistics.get(StatsType.MINIMUM).get(TAIR).getStid());
        
        String b = String.format("Average Air Temperature[1.5m] = %.1f", getStatistics(StatsType.AVERAGE, TAIR).getValue());
        System.out.println(b + " C at " + MESONET);
        
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println("Maximum Air Temperature[9.0m] = " + getStatistics(StatsType.MAXIMU, TA9M).getValue() + 
                " C at " + statistics.get(StatsType.MAXIMU).get(TA9M).getStid());
        System.out.println("Minimum Air Temperature[9.0m] = " + statistics.get(StatsType.MINIMUM).get(TA9M).getValue() + 
                " C at " + statistics.get(StatsType.MINIMUM).get(TA9M).getStid());
        
        String c = String.format("Average Air Temperature[9.0m] = %.1f", getStatistics(StatsType.AVERAGE, TA9M).getValue());
        System.out.println(c + " C at " + MESONET);
        
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println("Maximum Solar Radiation[1.5m] = " + getStatistics(StatsType.MAXIMU, SRAD).getValue() + 
                " C at " + statistics.get(StatsType.MAXIMU).get(SRAD).getStid());
        System.out.println("Minimum Solar Radiation[1.5m] = " + getStatistics(StatsType.MINIMUM, SRAD).getValue() +
                " C at " + getStatistics(StatsType.MINIMUM, SRAD).getStid());
        
        String e = String.format("Average Solar Radiation[1.5m] = %.1f", getStatistics(StatsType.AVERAGE, SRAD).getValue());
        System.out.println(e + " C at " + MESONET);
        
        System.out.println("=========================================================");

        return " ";
    }    
    
    
   }