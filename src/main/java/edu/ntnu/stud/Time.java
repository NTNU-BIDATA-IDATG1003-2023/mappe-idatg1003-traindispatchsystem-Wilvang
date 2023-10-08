package edu.ntnu.stud;

/**
 * The Time class represent information of different time units in a digital
 * clock that can hold numbers from zero to the time limit. The time limit can
 * be specified with the setTimeLimit() methode. the time limit is limited to
 * positive integers greater than zero. The time value range from zero to
 * the time limit-1. By for example creating the seconds in a digital clock,
 * the time values will range from 0-59. The changeTime() methode is used to
 * increase the time value by the time passed by, rolling over to zero if the
 * time limit is reached.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.1.0
 * @since 7, okt, 2023
 */
public class Time {
    private int timeValue;
    private int timeLimit;

    /**
     * Creates a new object of class Time, which will hold the information
     * of different time units, such as seconds, minutes and hours in a
     * digital clock.
     * @param timeValue The time value to be added.
     * @param timeLimit The time limit to be added.
     */
    public Time(int timeValue, int timeLimit) {
        this.setTimeValue(timeValue);
        this.setTimeLimit(timeLimit);
    }

    /**
     * Return the current time value.
     * @return The current time value.
     */
    public int getTimeValue() {
        return timeValue;
    }

    /**
     * Return the current time limit.
     * @return The current time limit.
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Return the clock time, that is a two-digit integer, as a String.
     * If the time value is less than ten, the clock time will add to
     * a leading zero.
     * @return The time value as a String.
     */
    public String getClockValue() {
        String clockTime = "";
        if (timeValue < 10) {
            clockTime = "0" + timeValue;
        } else {
            clockTime = "" + timeValue;
        }
        return clockTime;
    }

    /**
     * Set the time value to a new specified time value. If the
     * specified value is less than zero or more than the
     * time limit, the time value is set to zero.
     * @param timeValue The new specified time value.
     */
    public void setTimeValue(int timeValue) {
        if (timeValue >= 0 && timeValue < timeLimit){
            this.timeValue = timeValue;
        } else {
            this.timeValue = 0;
        }
    }

    /**
     * Set the time limit to a new specified time limit. If the
     * specified limit is less or equal to zero, the limit is set
     * to the invalid parameter -1.
     * @param timeLimit The new specified time limit.
     */
    public void setTimeLimit(int timeLimit) {
        if (timeLimit > 0) {
            this.timeLimit = timeLimit;
        } else {
            this.timeLimit = -1;
        }
    }

    /**
     * Increase the time value by one. The time value will roll over
     * to zero if the time limit is reached.
     */
    public void increaseTime() {
        this.timeValue = (this.timeValue + 1) % this.timeLimit;
    }

}
