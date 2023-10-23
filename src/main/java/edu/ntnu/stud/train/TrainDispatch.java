package edu.ntnu.stud.train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The TrainDispatch class represent a train dispatch from a train station.
 * The train dispatch contains information about the total amount of tracks
 * in the train station and a list of train departures. The list of train
 * departures is sorted by departure time. The train dispatch is responsible
 * for creating new unique train numbers for each new train departure added
 * to the list. The unique train number starts at the integer 1 and is
 * incremented by 1 for each new train departure. The list is final
 * because the list on
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.4.0
 * @since 1.4.0
 */

public class TrainDispatch {
  private final ArrayList<TrainDeparture> trainDepartureList;
  private LocalTime currentTime;
  private int totalTracks;
  private int uniqueTrainNumber;

  /**
   * Creates object of class TrainDispatch. The object contains a list of
   * train departures, sorted by departure time. The object also contains
   * the total amount of tracks in the train station and a unique train number
   * set to 1, incremented by 1 for each new train departure.
   *
   */
  public TrainDispatch() {
    this.trainDepartureList = new ArrayList<>();
    this.uniqueTrainNumber = 1;
    this.setTotalTracks(0);
  }

  /**
   * Creates object of class TrainDispatch. The object contains a list of
   * train departures, sorted by departure time. The object also contains
   * the total amount of tracks in the train station and a unique train number
   * set to 1, incremented by 1 for each new train departure.
   *
   * @param totalTracks the total amount of tracks in the train station.
   */
  public TrainDispatch(int totalTracks) {
    this.trainDepartureList = new ArrayList<>();
    this.uniqueTrainNumber = 1;
    this.setTotalTracks(totalTracks);
  }

  /**
   * Returns the total amount of tracks in the train station.
   *
   * @return the total amount of tracks in the train station.
   */
  public int getTotalTracks() {
    return this.totalTracks;
  }

  /**
   * Set the total amount of tracks in the train station. The total amount of
   * tracks must be greater than 0. If the total amount of tracks is less than
   * 0, the total amount of tracks is set to 0.
   *
   * @param totalTracks the total amount of tracks in the train station.
   */
  public void setTotalTracks(int totalTracks) {
    if (totalTracks > 0) {
      this.totalTracks = totalTracks;
    } else {
      this.totalTracks = 0;
    }
  }

  /**
   * Creates a new train departure and adds it to the list of train departures.
   * The list is sorted by departure time. The unique train number is incremented
   * by one after each new train departure.
   *
   * @param departureHour the specified hour of the train departure.
   * @param departureMinute the specified minute of the train departure.
   * @param destination the specified destination of the train.
   * @param trainType the specified train type of the train line.
   * @param routeNumber the specified rout number of the train line.
   * @param trackNumber the specified track number the train departure takes place.
   */
  public void newTrainDeparture(int departureHour, int departureMinute,
      String destination, String trainType, int routeNumber, int trackNumber) {
    TrainDeparture trainDeparture = new TrainDeparture(departureHour, departureMinute,
        destination, trainType, routeNumber, this.uniqueTrainNumber, trackNumber,
        this.totalTracks);
    this.trainDepartureList.add(trainDeparture);
    this.trainDepartureList.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    this.uniqueTrainNumber++;
  }
}
