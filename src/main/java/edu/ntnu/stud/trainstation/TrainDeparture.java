package edu.ntnu.stud.trainstation;

import java.time.LocalTime;
import java.util.Objects;

/**
 * The TrainDeparture class represent a train departure from a train station.
 * The train departure shows...
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.1.0
 * @since 1.1.0
 */
public class TrainDeparture {

  private LocalTime departureTime;
  private LocalTime delay;
  private String line;
  private int trainNumber; // HashMap to get unique ID.
  private String destination;
  private int track;

  public TrainDeparture(int departureHour, int departureMinute) {
    this.setDepartureTime(departureHour, departureMinute);
    this.delay = LocalTime.of(0, 0);
    // Not implemented yet:
    this.line = "";
    this.trainNumber = 0;
    this.destination = "";
    this.track = 0;
  }

  /**
   * Return the train number.
   *
   * @return the train number.
   * @since 1.0.0
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Return the train's current delay.
   *
   * @return the current delay, displayed as a digital clock.
   * @since 1.0.0
   */
  public LocalTime getDelay() {
    return this.delay;
  }

  /**
   * Return the train's departure time.
   *
   * @return the departure time as a digital clock.
   * @since 1.0.0
   */
  public LocalTime getDepartureTime() {
    return this.departureTime;
  }

  /**
   * Increment the train's delay with a specified number of minutes delayed.
   * If the specified number of minutes delayed is less than zero, do
   * nothing.The train departure time is incremented proportionally to the
   * delayed time.
   *
   * @param minutesDelay the specified number of minutes delayed.
   * @since 1.0.0
   */
  public void setDelay(int minutesDelay) {
    if (minutesDelay > 0) {
      this.delay = this.delay.plusMinutes(minutesDelay);
      this.departureTime = this.departureTime.plusMinutes(minutesDelay);
    }
  }

  /**
   * Increment the train's delay with a specified number hours delayed and
   * minutes delayed. The specified number of hours delayed or minutes
   * delayed is only incremented with positive values. The train departure time
   * is incremented proportionally to the delayed time.
   *
   * @param hoursDelay the specified number of hours delayed.
   * @param minutesDelay the specified number of minutes delayed.
   * @since 1.0.0
   */
  public void setDelay(int hoursDelay, int minutesDelay) {
    if (hoursDelay > 0) {
      this.delay = this.delay.plusHours(hoursDelay);
      this.departureTime = this.departureTime.plusHours(hoursDelay);
    }
    if (minutesDelay > 0) {
      this.delay = this.delay.plusMinutes(minutesDelay);
      this.departureTime = this.departureTime.plusMinutes(minutesDelay);
    }
  }

  /**
   * Set the train's departure time to the specified time of day. If the
   * specified time is an invalid clock time, the departure time is set to
   * 00:00.
   *
   * @param departureHours the specified hour of the day.
   * @param departureMinutes the specified minute of the hour.
   * @since 1.1.0
   */
  public void setDepartureTime(int departureHours, int departureMinutes) {
    if (departureHours >= 0 && departureHours < 24
        && departureMinutes >= 0 && departureMinutes < 60) {
      this.departureTime = LocalTime.of(departureHours, departureMinutes);
    } else {
      this.departureTime = LocalTime.of(0, 0);
    }
  }
}
