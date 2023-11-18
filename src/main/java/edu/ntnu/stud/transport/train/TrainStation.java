package edu.ntnu.stud.transport.train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;


/**
 * The TrainStation class represent a train dispatch from a train station. The train station
 * contains a list of the trains departing from the train station, sorted by departure time. The
 * train departures will be removed from the list after the train has departed. The train station
 * has a station clock that displays the current time. The station clock is represented as a digital
 * clock. To validate that the same train number is not used for multiple train departures, the
 * train station contains a list of all train numbers used for the train departures. The list of
 * train departures can be navigated using the search methods. The search methods can search for a
 * train number or destination. The train station can also assign a track.
 *
 * @author Johan Fredrik Wilvang
 * @version 2.3.1
 * @since 2.2.2
 */

public final class TrainStation {

  private final HashMap<Integer, TrainDeparture> trainRegister;
  private LocalTime stationClock;

  /**
   * Creates object of class TrainStation. The object contains two lists and a station clock. The
   * first list is a list of all train departures from the train station. The second list is a list
   * of all train numbers used for the train departures. The station clock is initially set to
   * 00:00.
   *
   * @since 1.4.0
   */
  public TrainStation() {
    this.trainRegister = new HashMap<>();
    this.stationClock = LocalTime.parse("00:00");
  }

  /**
   * Return the time displayed on the station clock.
   *
   * @return The time displayed on the station clock.
   * @since 1.4.0
   */
  public LocalTime getStationClock() {
    return this.stationClock;
  }

  /**
   * Returns the total amount of trains in the train register.
   *
   * @return The total amount of trains in the train register.
   * @since 1.4.0
   */
  public int getNumberOfTrains() {
    return this.trainRegister.size();
  }

  /**
   * Returns an iterator of a list containing all train departures from the train register. The
   * train departures are sorted by departure time.
   *
   * @return An iterator of all train departures from the train register.
   * @since 1.7.0
   */
  public Iterator<TrainDeparture> getTrainRegister() {
    return this.trainRegister.values().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime)).iterator();
  }

  /**
   * Returns <code>true</code> if the train number is unique for each train departure. If the train
   * number is not unique, the method will return <code>false</code>.
   *
   * @param trainNumber The train number of the train departure.
   * @return <code>true</code> if the train number is unique for each train departure.
   * @since 2.2.0
   */
  public boolean isTrainNumberUnique(int trainNumber) {
    return !this.trainRegister.containsKey(trainNumber);
  }

  /**
   * Set the time displayed on the station clock. The time is an object form the LocalTime class
   * represented as a digital clock. If the time is before the current time displayed on the station
   * clock, the time will not be changed.
   *
   * @param time The time displayed on the station clock.
   * @since 2.2.2
   */
  public void setStationClock(String time) {
    if (LocalTime.parse(time).isAfter(this.stationClock)) {
      this.stationClock = LocalTime.parse(time);
    }
    removeDepartedTrains();
  }

  /**
   * Creates a new train number for the train departure. The train number is unique for each train
   * departure.If the train number already exists in the train register, the train number is set to
   * -1.
   *
   * @param trainNumber The train number to be set.
   * @return The specified train number.
   * @since 2.2.1
   */
  public int makeTrainNumber(int trainNumber) {
    int number = -1;
    if (!this.trainRegister.containsKey(trainNumber)) {
      number = trainNumber;
    }
    return number;
  }

  /**
   * Adds a new train departure to the train register. The train departure is added to the train
   * register if the train number is unique. If the train number is already existing, the train
   * departure will not be added to the train register.
   *
   * @param departureTime The departure time of the train departure.
   * @param trainNumber   The train number of the train departure.
   * @param destination   The destination of the train departure.
   * @param trainLine     The train line of the train departure.
   * @return <code>true</code> if the train departure was added successfully,
   * @since 1.6.0
   */
  public boolean addTrainDeparture(String departureTime, int trainNumber,
      String destination, String trainLine) {
    trainNumber = makeTrainNumber(trainNumber);
    if (trainNumber != -1 && LocalTime.parse(departureTime).isAfter(this.stationClock)) {
      this.trainRegister.put(trainNumber, new TrainDeparture(departureTime,
          trainNumber, destination, trainLine));
    }
    return trainNumber >= 0;
  }

  /**
   * Removes the train departure with the specified train number from the train register.
   *
   * @param trainNumber The train number of the train departure.
   * @since 1.7.0
   */
  public void removeTrainDeparture(int trainNumber) {
    this.trainRegister.remove(trainNumber);
  }

  /**
   * Search for the train departure with the specified train number. If the train number does not
   * exist in the train register, the method will return <code>null</code>.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number as an iterator.
   */
  public Iterator<TrainDeparture> searchByTrainNumber(int trainNumber) {
    return this.trainRegister.values().stream()
        .filter(train -> train.getTrainNumber() == trainNumber)
        .collect(Collectors.toCollection(ArrayList::new)).iterator();
  }

  /**
   * Search for all train departures with the specified destination. If there isn't any train
   * departures headed to the destination, the method will return <code>null</code>.
   *
   * @param destination The destination of the train departure.
   * @return The train departures with the specified destination as an iterator.
   * @since 1.7.0
   */
  public Iterator<TrainDeparture> searchByDestination(String destination) {
    return this.trainRegister.values().stream()
        .filter(train -> train.getDestination().equalsIgnoreCase(destination))
        .collect(Collectors.toCollection(ArrayList::new)).iterator();
  }

  /**
   * Assign a track to the train departure with the specified train number. The track number is
   * represented as an integer between 1-100. If the train number does not exist in the train
   * register, the track number is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @param trackNumber The track number of the train departure.
   * @since 2.2.0
   */
  public void setNewTrackNumber(int trainNumber, int trackNumber) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      trainIterator.next().setTrackNumber(trackNumber);
    }
  }

  /**
   * Set a new train number for the train departure associated with specified train number. The
   * train number is unique for each train departure. If the new train number already exists in the
   * train register, the train number will not be changed.
   *
   * @param trainNumber    The train number of the train departure.
   * @param newTrainNumber The new train number of the train departure.
   * @since 2.2.1
   */
  public void setNewTrainNumber(int trainNumber, int newTrainNumber) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext() && !trainRegister.containsKey(newTrainNumber)) {
      trainIterator.next().setTrainNumber(newTrainNumber);
    }
  }

  /**
   * Set a new end destination for the train departure associated with specified train number. If
   * the train number does not exist in the train register, the destination will not be changed.
   *
   * @param trainNumber    The train number of the train departure.
   * @param newDestination The new destination of the train departure.
   * @since 2.2.0
   */
  public void setNewDestination(int trainNumber, String newDestination) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      trainIterator.next().setDestination(newDestination);
    }
  }

  /**
   * Set a new departure time for the train departure associated with specified train number. If the
   * train number does not exist in the train register, the departure time will not be changed.
   *
   * @param trainNumber      The train number of the train departure.
   * @param newDepartureTime The new departure time of the train departure.
   * @since 2.2.0
   */
  public void setNewDepartureTime(int trainNumber, String newDepartureTime) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      trainIterator.next().setDepartureTime(newDepartureTime);
    }
  }

  /**
   * Set a new train line for the train departure associated with specified train number. If the
   * train number does not exist in the train register, the train line will not be changed.
   *
   * @param trainNumber  The train number of the train departure.
   * @param newTrainLine The new train line of the train departure.
   * @since 2.2.0
   */
  public void setNewTrainLine(int trainNumber, String newTrainLine) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      trainIterator.next().setTrainLine(newTrainLine);
    }
  }

  /**
   * Set a new delay for the train departure associated with specified train number. The delay is
   * set by the number of minutes delayed. If the train number does not exist in the train register,
   * the delay will not be changed.
   *
   * @param trainNumber The train number of the train departure.
   * @param newDelay    The specified amount of minutes delayed.
   * @since 2.2.0
   */
  public void setNewDelay(int trainNumber, int newDelay) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      trainIterator.next().setDelay(newDelay);
    }
  }

  /**
   * Removes all train departures that already departed in the train station, from the train
   * register.
   *
   * @since 2.2.2
   */
  public void removeDepartedTrains() {
    this.trainRegister.values().stream().filter(train -> train.getDepartureTime()
            .isBefore(this.stationClock)).collect(Collectors.toCollection(ArrayList::new))
        .forEach(train -> this.trainRegister.remove(train.getTrainNumber()));
  }
}
