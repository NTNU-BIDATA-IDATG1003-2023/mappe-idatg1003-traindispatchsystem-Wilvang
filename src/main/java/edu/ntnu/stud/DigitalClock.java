package edu.ntnu.stud;

public class DigitalClock {
    private Time hours;
    private Time minutes;
    private String display;

    public DigitalClock(){
        this.hours = new Time(0,24);
        this.minutes = new Time(0,60);
        updateDisplay();
    }

    public DigitalClock(int hour, int minute){
        this.hours = new Time(hour,24);
        this.minutes = new Time(minute,60);
        updateDisplay();
    }

    public String updateDisplay(){
        this.display = this.hours.getClockValue() + ":"
                    + this.minutes.getClockValue();
        if (this.hours.getTimeLimit() == -1
            || this.minutes.getTimeLimit() == -1){
            this.display = "INVALID";
        }
        return this.display;
    }

    public void setTime(int hour, int minute) {
        this.hours.setTimeValue(hour);
        this.minutes.setTimeValue(minute);
        updateDisplay();
    }

    public void changeTime(int minutesPassedBy){
        for (int i = 0; i < minutesPassedBy; i++){
            this.minutes.increaseTime();
            if (this.minutes.getTimeValue() == 0){
                this.hours.increaseTime();
            }
        }
        updateDisplay();
    }

    public void changeTime(int hoursPassedBy, int minutesPassedBy) {
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

