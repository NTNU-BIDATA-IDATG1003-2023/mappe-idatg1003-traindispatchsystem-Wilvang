package edu.ntnu.stud.transport.train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;


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

public final class TrainStation {
  private HashMap<Integer, TrainDeparture> trainRegister;
  private ArrayList<Integer> trainNumberList;
  private LocalTime stationClock;

  /**
   * Creates object of class TrainDispatch. The object contains a list of
   * train departures, sorted by departure time. The object also contains
   * the total amount of tracks in the train station and a unique train number
   * set to 1, incremented by 1 for each new train departure.
   *
   * @since 1.4.0
   */
  public TrainStation(){
    this.trainRegister = new HashMap<>();
    this.trainNumberList = new ArrayList<>();
    this.setStationClock("00:00");
  }

  /**
   * Return the time displayed on the station clock.
   *
   * @return The time displayed on the station clock.
   * @since 1.4.0
   */
  public LocalTime getStationClock(){
    return this.stationClock;
  }

  /**
   * Returns the total amount of trains in the train register.
   *
   * @return The total amount of trains in the train register.
   * @since 1.4.0
   */
  public int getNumberOfTrains(){
    return this.trainRegister.size();
  }

  /**
   * Returns an iterator of a list containing all train departures from the train register.
   * @return An iterator of all train departures from the train register.
   * @since 1.6.0
   */
  public Iterator<TrainDeparture> getTrainRegister(){
    return this.trainRegister.values().iterator();
  }

  /**
   * Set the time displayed on the station clock. The time is an object form the LocalTime class
   * represented as a digital clock.
   *
   * @param time The time displayed on the station clock.
   * @since 1.4.0
   */
  public void setStationClock(String time) {
    if (time.matches("\\d{2}:\\d{2}")){
      this.stationClock = LocalTime.parse(time);
    } else {
      this.stationClock = LocalTime.of(0, 0);
    }
  }

  /**
   * Creates a new train number for the train departure. The train number is unique for each train
   * departure.If the train number already exists in the train register, the train number is set to
   * -1.
   *
   * @param trainNumber The train number to be set.
   * @return The specified train number.
   * @since 1.6.0
   */
  public int makeTrainNumber(int trainNumber){
    int number = -1;
    if (!this.trainNumberList.contains(trainNumber)){
      number = trainNumber;
      this.trainNumberList.add(trainNumber);
    }
    return number;
  }

  /**
   * Adds a new train departure to the train register. The train departure is added to the train
   * register if the train number is unique. If the train number is already existing, the train
   * departure will not be added to the train register.
   *
   * @param departureTime The departure time of the train departure.
   * @param trainNumber The train number of the train departure.
   * @param destination The destination of the train departure.
   * @param trainLine The train line of the train departure.
   * @return <code>true</code> if the train departure was added successfully,
   * @since 1.6.0
   */
  public boolean addTrainDeparture(String departureTime, int trainNumber,
      String destination, String trainLine){
    trainNumber = makeTrainNumber(trainNumber);
    if (trainNumber != -1){
    this.trainRegister.put(trainNumber, new TrainDeparture(departureTime,
        trainNumber, destination, trainLine));
    }
    return trainNumber >= 0;
  }

  /**
   * Assign a track to the train departure with the specified train number. The track number is
   * represented as an integer between 1-100. If the train number does not exist in the train
   * register, the track number is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @param trackNumber The track number of the train departure.
   * @since 1.6.0
   */
  public void assignTrack(int trainNumber, int trackNumber){
    this.trainRegister.get(trainNumber).setTrackNumber(trackNumber);
  }

  /**
   *
   *
   * @param trainNumber
   */
  public void removeTrainDeparture(int trainNumber){
    this.trainRegister.remove(trainNumber);
  }

  public Iterator<TrainDeparture> sortByDepartureTime(){
    return this.trainRegister.values().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime)).iterator();
  }

  public Iterator<TrainDeparture> searchByTrainNumber(int trainNumber){
    return this.trainRegister.values().stream().filter(car -> car.getTrainNumber() == trainNumber)
        .collect(Collectors.toCollection(ArrayList::new)).iterator();
  }
}
