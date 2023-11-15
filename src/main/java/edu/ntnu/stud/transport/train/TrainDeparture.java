package edu.ntnu.stud.transport.train;

import java.time.LocalTime;


/**
 * The TrainDeparture class represent a train departure from a train station.The train departure
 * contains information about the train's departure time, delay, destination, train line, train
 * number and track number. The train departure time is represented as a digital clock. The train
 * departure time and delay is an object form the LocalTime class. The departure time is incremented
 * in proportion to the delay.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.8.0
 * @since 1.6.0
 */

public class TrainDeparture {
  // required fields
  private LocalTime departureTime;
  private int trainNumber;
  private String trainLine;
  private String destination;
  // optional fields
  private LocalTime delay;
  private int trackNumber;

  /**
   * Creates object of class TrainDeparture. The object contains information about the train's
   * departure time, delay, destination, train line, train number and track number. The departure
   * time, train number, train line and destination are required fields specified by the parameters.
   * The delay and track number is initially set to 00:00 and -1.
   *
   * @param departureTime The specified time of the departure.
   * @param trainNumber The specified train number.
   * @param trainLine The specified train line.
   * @param destination The specified destination.
   * @since 1.4.0
   */
  public TrainDeparture(String departureTime, int trainNumber, String trainLine, String destination) {
    this.setDepartureTime(departureTime);
    this.setTrainNumber(trainNumber);
    this.setTrainLine(trainLine);
    this.setDestination(destination);
    this.trackNumber = -1;
    this.delay = LocalTime.of(0, 0);

  }

  /**
   * Return the train's current delay.
   *
   * @return The current delay, displayed as a digital clock.
   * @since 1.0.0
   */
  public LocalTime getDelay() {
    return this.delay;
  }

  /**
   * Return the train's departure time.
   *
   * @return The departure time as a digital clock.
   * @since 1.0.0
   */
  public LocalTime getDepartureTime() {
    return this.departureTime;
  }

  /**
   * Return the train line.
   *
   * @return The train line.
   * @since 1.0.0
   */
  public String getTrainLine() {
    return this.trainLine;
  }

  /**
   * Return the train number.
   *
   * @return The train number.
   * @since 1.0.0
   */
  public int getTrainNumber() {
    return this.trainNumber;
  }

  /**
   * Return the train destination.
   *
   * @return The train destination.
   * @since 1.0.0
   */
  public String getDestination() {
    return this.destination;
  }

  /**
   * Return the train's track number.
   *
   * @return The track number.
   * @since 1.0.0
   */
  public int getTrackNumber() {
    return this.trackNumber;
  }


  /**
   * Increment the train's delay with a specified number of minutes delayed. If the specified
   * number of minutes delayed is less than zero, do nothing. The train departure time is
   * incremented proportionally to the delayed time.
   *
   * @param minutesDelay The specified number of minutes delayed.
   * @since 1.0.0
   */
  public void setDelay(int minutesDelay) {
    if (minutesDelay > 0) {
      this.delay = this.delay.plusMinutes(minutesDelay);
      this.departureTime = this.departureTime.plusMinutes(minutesDelay);
    }
  }
  /**
   * Increment the train's delay with a specified number hours delayed and minutes delayed. The
   * specified number of hours delayed or minutes delayed is only incremented with positive values.
   * The train departure time is incremented proportionally to the delayed time.
   *
   * @param hoursDelay The specified number of hours delayed.
   * @param minutesDelay The specified number of minutes delayed.
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
   * Set the train's departure time to the specified time of the departure. If the specified time
   * is an invalid 24-hour clock format (HH:mm), the departure time is set to 00:00.
   *
   * @param departureTime The specified time of the departure.
   * @since 1.6.0
  */
  public void setDepartureTime(String departureTime) {
    if (departureTime.matches("\\d{2}:\\d{2}")
        && departureTime.length() == 5
        && Integer.parseInt(departureTime.substring(0, 2)) < 24
        && Integer.parseInt(departureTime.substring(3, 5)) < 60) {
      this.departureTime = LocalTime.parse(departureTime);
    } else {
      this.departureTime = LocalTime.of(0, 0);
    }
  }

  /**
   * Set train line to the new specified train line. If the specified train line is equal to null,
   * the train line is set to "INVALID".
   *
   * @param trainLine The new specified train line.
   * @since 1.2.0
   */
  public void setTrainLine(String trainLine) {
    if (trainLine != null) {
      this.trainLine = trainLine.toUpperCase();
    } else {
      this.trainLine = "INVALID";
    }
  }
  /**
   * Set the train's destination to a new specified destination. If the specified destination is
   * equal to null, the destination is set to "INVALID".
   *
   * @param destination The new specified destination.
   * @since 1.3.0
   */
  public void setDestination(String destination) {
    if (destination != null) {
      this.destination = destination;
    } else {
      this.destination = "INVALID";
    }
  }

  /**
   * Set the train number to a new specified train number. The train number's area of validity is
   * a positive number up to 3 digits. If the specified train number is outside the area of
   * validity, the train number is set to -1.
   *
   * @param trainNumber The new specified train number.
   */
  public void setTrainNumber(int trainNumber){
    if (trainNumber > 0 && trainNumber < 1000){
      this.trainNumber = trainNumber;
    } else {
      this.trainNumber = -1;
    }
  }

  /**
   * Set the track number to a new specified track number. The track number's area of validity is
   * a positive number up to 100. If the specified track number is outside the area of validity, the
   * track number is set to zero.
   *
   * @param trackNumber The new specified track number.
   * @since 1.6.0
   */
  public void setTrackNumber(int trackNumber) {
    if (trackNumber > 0 && trackNumber <= 100) {
      this.trackNumber = trackNumber;
    } else {
      this.trackNumber = 0;
    }
  }
}