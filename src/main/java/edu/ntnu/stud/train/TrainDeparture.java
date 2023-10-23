package edu.ntnu.stud.train;

import java.time.LocalTime;


/**
 * The TrainDeparture class represent a train departure from a train station.
 * The train departure contains information about the train's departure time,
 * delay, destination, train line, train number and track number. The train
 * departure time is represented as a digital clock. The train departure time
 * and delay is an object form the LocalTime class. The train line is represented
 * as a String. The string is made up of a prefix letter and a route number of
 * one or two positive digits (1-99). The train number does not have any mutator
 * method, since the TrainDispatch class is responsible for setting a unique train
 * number and to the train departure.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.4.0
 * @since 1.3.0
 */

public class TrainDeparture {

  private LocalTime departureTime;
  private LocalTime delay;
  private String trainLine;
  private int trainNumber;
  private String destination;
  private int trackNumber;


  /**
   * Constructor for objects of class TrainDeparture. Set the departure time, 
   * destination, train line, train number and track number, specified by the 
   * parameters.
   *
   * @param departureHour the specified hour of the train departure.
   * @param departureMinute the specified minute of the train departure.
   * @param destination the specified destination of the train.
   * @param trainType the specified train type of the train line.
   * @param routeNumber the specified rout number of the train line.
   * @param trainNumber the specified train number to the train departure.
   * @param trackNumber the specified track number the train departure takes place.
   * @param totalTracks the total amount of tracks in the train station
   * @since 1.3.0
   */
  public TrainDeparture(int departureHour, int departureMinute, String destination,
      String trainType, int routeNumber, int trainNumber, int trackNumber, int totalTracks) {
    this.setDepartureTime(departureHour, departureMinute);
    this.delay = LocalTime.of(0, 0);
    this.setDestination(destination);
    this.trainNumber = trainNumber;
    this.setTrainLine(trainType, routeNumber);
    this.setTrackNumber(trackNumber, totalTracks);
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
   * Return the train line.
   *
   * @return the train line.
   * @since 1.0.0
   */
  public String getTrainLine() {
    return this.trainLine;
  }

  /**
   * Return the train number.
   *
   * @return the train number.
   * @since 1.0.0
   */
  public int getTrainNumber() {
    return this.trainNumber;
  }

  /**
   * Return the train destination.
   *
   * @return the train destination.
   * @since 1.0.0
   */
  public String getDestination() {
    return this.destination;
  }

  /**
   * Return the train's track number.
   *
   * @return the track number.
   * @since 1.0.0
   */
  public int getTrackNumber() {
    return this.trackNumber;
  }

  /**
   * Increment the train's delay with a specified number of minutes delayed. 
   * If the specified number of minutes delayed is less than zero, do nothing. 
   * The train departure time is incremented proportionally to the delayed 
   * time.
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
   * minutes delayed. The specified number of hours delayed or minutes delayed 
   * is only incremented with positive values. The train departure time is 
   * incremented proportionally to the delayed time.
   *
   * @param hoursDelay   the specified number of hours delayed.
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
   * specified time is an invalid clock time, the departure time is set 
   * to 00:00.
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

  /**
  * Set train line to the new specified train line. The train line is
  * represented as a String. The string is made up of a prefix letter and 
  * a route number of one or two positive digits (1-99). The train type 
  * must be one of the following; 'L' (local train), 'F' (long-distance train), 
  * 'R' (regional train), "RE" (regional express train), "FLY" (flight train). 
  * If the prefix letter or the route number is outside the area of validity, 
  * the train line is set to "INVALID".
  *
  * @param trainType the specified train type.
  * @param routeNumber the specified route number.
  * @since 1.2.0
  */
  public void setTrainLine(String trainType, int routeNumber) {
    trainType = trainType.toUpperCase();
    if (checkTrainType(trainType) && routeNumber > 0 && routeNumber < 100) {
      this.trainLine = trainType + routeNumber;
    } else {
      this.trainLine = "INVALID";
    }
  }

  /**
   * Return true if the train type is one of the following prefix letters;
   * "L" (local train), "F" (long-distance train), "R" (regional train),
   * "RE" (regional express train), "FLY" (flight train). If the train 
   * type is not a valid prefix letter, the check returns false.
   *
   * @param trainType the specified train type.
   * @return boolean value as the result.
   * @since 1.2.0
   */
  private boolean checkTrainType(String trainType) {
    boolean flag = false;
    if ((trainType.charAt(0) == 'R' || trainType.charAt(0) == 'L'
        || trainType.charAt(0) == 'F' && trainType.length() == 1)

        || (trainType.charAt(0) == 'R' && trainType.charAt(1) == 'E'
        && trainType.length() == 2)

        || (trainType.charAt(0) == 'F' && trainType.charAt(1) == 'L'
        && trainType.charAt(2) == 'Y' && trainType.length() == 3)) {
      flag = true;
    }
    return flag;
  }

  /**
   * Set the train's destination to a new specified destination. If the
   * specified destination is equal to null, the destination is set to
   * "INVALID".
   *
   * @param destination the new specified destination.
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
   * Set the track number to a new specified track number. The track
   * number's area of validity is a positive number up to the total
   * amount of tracks in the station. If the specified track number is
   * outside the area of validity, the track number is set to zero.
   *
   * @param trackNumber the new specified track number.
   * @param totalTracks the total amount of tracks in the station.
   * @since 1.3.0
   */
  public void setTrackNumber(int trackNumber, int totalTracks) {
    if (trackNumber > 0 && trackNumber <= totalTracks) {
      this.trackNumber = trackNumber;
    } else {
      this.trackNumber = 0;
    }
  }
}