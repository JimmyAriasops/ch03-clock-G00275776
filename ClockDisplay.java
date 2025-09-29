
/**
 * The ClockDisplay class implements a digital clock display for an
 * American-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock internally is 00:00 (midnight) to 23:59 (one minute before 
 * midnight). But the clock should display 12:00 AM through 11:59 am and 12:00 pm and 11:59 pm
 * 
 * The code needs to translate the hours to the equivalent 12 hour time bloc and must append the appropriate meridain designation (am or pm)
 * 
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(0, 0);
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour % 24);
        minutes.setValue(minute % 60);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        int hourValue = hours.getValue();
        int minuteValue = minutes.getValue();
        
        String period;
        if (hourValue < 12) {
            period = "AM";
        } else {
            period = "PM";
        }
        
        int displayHour;
        if (hourValue == 0) {
            displayHour = 12;
        } else if (hourValue > 12) {
            displayHour = hourValue - 12;
        } else {
            displayHour = hourValue;
        }
        
        String hourText; 
        if (displayHour < 10 ){
            hourText = "0" + displayHour;
        }else {
            hourText = "" + displayHour;
        }
        
        String minuteText;
        if (minuteValue <10) {
            minuteText = "0" + minuteValue;
        }else {
            minuteText = "" + minuteValue;
        }
        displayString = hourText + ":" + minuteText + " " + period;
    }
}
