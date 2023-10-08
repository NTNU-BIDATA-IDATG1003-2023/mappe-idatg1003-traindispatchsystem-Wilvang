package edu.ntnu.stud;

/**
 * The DigitalClock class represent a 24-hour digital clock display. The
 * clock shows hours and minutes. The digital clock displays time in range
 * from 00:00 (midnight) to 23:59 (one minute before midnight). The clock
 * can be set to the current time of day with the setTime() methode. If the
 * time is set to a value greater than the time limit, the time is set to
 * zero. Add a specific amount of hours and/or minutes with the timePassed()
 * methode. The digital clock is updated when using any of the methods via
 * the updateDisplay() methode.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.1.0
 * @since 8, okt, 2023
 */
public class DigitalClock {
    private Time hours;
    private Time minutes;
    private String display;

    /**
     * Create a new clock in class DigitalClock witch is set at 00:00.
     */
    public DigitalClock(){
        this.hours = new Time(0,24);
        this.minutes = new Time(0,60);
        updateDisplay();
    }

    /**
     * Create a new digital clock set at a specific time of day. If the
     * specified time value is greater than the time limit or less than
     * zero, the value is set to zero.
     * @param hours The specified hour of the day.
     * @param minutes The specified amount of minutes past that hour.
     */
    public DigitalClock(int hours, int minutes){
        this.hours = new Time(hours,24);
        this.minutes = new Time(minutes,60);
        updateDisplay();
    }

    /**
     * Update the display to the current hours and minutes in the clock,
     * displayed as a 24-hour digital clock display. If the time limit
     * has an invalid input, the display is set to "INVALID".
     * @return The digital clock display as a String.
     */
    public void updateDisplay(){
        this.display = this.hours.getClockValue() + ":"
                    + this.minutes.getClockValue();
    }

    /**
     * Set the time to the specified time of day. If the specified time
     * value is greater than the time limit or less than zero, the value
     * is set to zero. Updates the display with the new specified time.
     * @param hours The specified hour of the day.
     * @param minutes The specified amount of minutes past that hour.
     */
    public void setTime(int hours, int minutes) {
        this.hours.setTimeValue(hours);
        this.minutes.setTimeValue(minutes);
        updateDisplay();
    }

    /**
     * Change the time value to increase by the specified amount of minutes
     * passed by. If the minutes roll over to zero, the hours increase by one.
     * Updates the display with the new specified time.
     * @param minutesPassedBy The specified amount of minutes passed by.
     */
    public void timePassed(int minutesPassedBy){
        for (int i = 0; i < minutesPassedBy; i++){
            this.minutes.increaseTime();
            if (this.minutes.getTimeValue() == 0){
                this.hours.increaseTime();
            }
        }
        updateDisplay();
    }

    /**
     * Change the time value to increase by the specified amount of hours and
     * minutes passed by. If the minutes roll over to zero, the hours increase
     * by one. If the hours roll over zero, do nothing. Updates the display with
     * the new specified time.
     * @param hoursPassedBy The specified amount of hours passed by.
     * @param minutesPassedBy The specified amount of minutes passed by.
     */
    public void timePassed(int hoursPassedBy, int minutesPassedBy) {
        for (int i = 0; i < hoursPassedBy; i++) {
            this.hours.increaseTime();
        }
        for (int i = 0; i < minutesPassedBy; i++) {
            this.minutes.increaseTime();
            if (minutes.getTimeValue() == 0) {
                this.hours.increaseTime();
            }
        }
        updateDisplay();
    }
}

