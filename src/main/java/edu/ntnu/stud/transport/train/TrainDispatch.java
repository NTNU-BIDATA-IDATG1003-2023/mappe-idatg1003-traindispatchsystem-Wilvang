package edu.ntnu.stud.transport.train;

import edu.ntnu.stud.transport.train.TrainDeparture.TrainBuilder;
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
 * @version 1.5.0
 * @since 1.4.0
 */

public class TrainDispatch {
  private ArrayList<TrainDeparture> trainDepartureList;
  private int totalTracks;
  private int uniqueTrainNumber;

  /**
   * Creates object of class TrainDispatch. The object contains a list of
   * train departures, sorted by departure time. The object also contains
   * the total amount of tracks in the train station and a unique train number
   * set to 1, incremented by 1 for each new train departure.
   *
   * @since 1.4.0
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
   * @since 1.4.0
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
   * @since 1.4.0
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
   * @since 1.4.0
   */
  public void setTotalTracks(int totalTracks) {
    if (totalTracks > 0) {
      this.totalTracks = totalTracks;
    } else {
      this.totalTracks = 0;
    }
  }

  public ArrayList<TrainDeparture> getTrainDepartureList(){
    return this.trainDepartureList;
  }

  /**
   * Creates a new train departure and adds it to the list of train departures.
   * The list is sorted by departure time. The unique train number is incremented
   * by one after each new train departure.
   *
   * @param departureHour the specified hour of the train departure.
   * @param departureMinute the specified minute of the train departure.
   * @since 1.4.0
   */
  public void newTrainDeparture(int departureHour, int departureMinute){
    TrainBuilder trainBuilder = new TrainBuilder(departureHour, departureMinute, this.uniqueTrainNumber);
    TrainDeparture trainDeparture = trainBuilder.build();
    this.trainDepartureList.add(trainDeparture);
    this.trainDepartureList.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    this.uniqueTrainNumber++;
  }



}
