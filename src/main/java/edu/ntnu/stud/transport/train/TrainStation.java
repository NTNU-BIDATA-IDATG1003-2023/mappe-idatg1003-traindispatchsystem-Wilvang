package edu.ntnu.stud.transport.train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * The TrainStation class contains a register of the trains departing from the train station. The
 * register is sorted by the departure time, using a TreeSet. The train departures will not be shown
 * in the register after the train has departed. The train register can be navigated using the
 * search methods. The search methods can search for a train number or destination. The train
 * station can also assign a track to a selected train departure. The train station has a station
 * clock that displays the current time at the station. The station clock is represented as a
 * digital clock. The register will be reset upon a new day. The same train number can not be used
 * for multiple train departures on the same day.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.3
 * @since 3.0.0
 */

public final class TrainStation {

  private final TreeSet<TrainDeparture> trainRegister;
  private LocalTime stationClock;

  /**
   * Creates object of class TrainStation. The object contains a register and a station clock. The
   * register is sorted by departure time, then train number if multiple departures have the same
   * departure time. The register will reset upon a new day. The station clock is initially set to
   * 00:00.
   *
   * @since 2.4.0
   */
  public TrainStation() {
    resetClock();
    this.trainRegister = new TreeSet<>(Comparator.comparing(TrainDeparture::getDepartureTime)
        .thenComparing(TrainDeparture::getTrainNumber));
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
   * @since 2.4.0
   */
  public int getNumberOfTrains() {
    Iterator<TrainDeparture> trainIterator = hideDepartedTrains();
    int i = 0;
    while (trainIterator.hasNext()) {
      trainIterator.next();
      i++;
    }
    return i;
  }

  /**
   * Returns an iterator of a list containing the train history of the day.
   *
   * @return An iterator of the train history of the day.
   * @since 2.4.0
   */
  public Iterator<TrainDeparture> getTrainRegister() {
    return trainRegister.iterator();
  }

  /**
   * Returns <code>true</code> if the train number is unique for each train departure. If the train
   * number is not unique, the method will return <code>false</code>.
   *
   * @param trainNumber The train number of the train departure.
   * @return <code>true</code> if the train number is unique for each train departure.
   *     <code>false</code> if the train number is not unique for each train departure.
   * @since 3.0.0
   */
  public boolean isTrainNumberUnique(int trainNumber) {
    return this.trainRegister.stream()
        .noneMatch(train -> train.getTrainNumber() == trainNumber);
  }

  /**
   * Set the time displayed on the station clock. The time is an object form the LocalTime class. If
   * the time is before the current time displayed on the station clock, the time will not be
   * hanged.
   *
   * @param time The time displayed on the station clock.
   * @since 2.2.2
   */
  public void setStationClock(String time) {
    if (LocalTime.parse(time).isAfter(this.stationClock)) {
      this.stationClock = LocalTime.parse(time);
    }
  }

  /**
   * Resets the station clock to 00:00.
   *
   * @since 2.4.0
   */
  public void resetClock() {
    this.stationClock = LocalTime.of(0, 0);
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
   * @since 1.6.0
   */
  public void addTrainDeparture(String departureTime, int trainNumber,
      String destination, String trainLine) {
    if (trainNumber != -1 && LocalTime.parse(departureTime).isAfter(this.stationClock)
        && isTrainNumberUnique(trainNumber)) {
      this.trainRegister.add(
          new TrainDeparture(departureTime, trainNumber, destination, trainLine));
    }
  }

  /**
   * Adds a new train departure to the train register. The train departure is added to the train
   * register if the train number is unique. If the train number is already existing, the train
   * departure will not be added to the train register.
   *
   * @param train The train departure to be added to the train register.
   * @since 3.0.0
   */
  public void addTrainDeparture(TrainDeparture train) {
    if (isTrainNumberUnique(train.getTrainNumber())) {
      this.trainRegister.add(train);
    }
  }

  /**
   * Removes the train departure with the specified train number from the train register.
   *
   * @param trainNumber The train number of the train departure.
   * @since 2.2.1
   */
  public void removeTrainDeparture(int trainNumber) {
    Iterator<TrainDeparture> iterator = searchByTrainNumber(trainNumber);
    if (iterator.hasNext()) {
      TrainDeparture train = iterator.next();
      trainRegister.remove(train);
    }
  }

  /**
   * Search for the train departure with the specified train number. If the train number does not
   * exist in the train register, the method will return <code>null</code>.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number as an iterator.
   * @since 1.6.0
   */
  public Iterator<TrainDeparture> searchByTrainNumber(int trainNumber) {
    return this.trainRegister.stream()
        .filter(train -> train.getTrainNumber() == trainNumber)
        .collect(Collectors.toCollection(ArrayList::new)).iterator();
  }

  /**
   * Search for all train departures with the specified destination. If there isn't any train
   * departures headed to the destination, the method will return <code>null</code>.
   *
   * @param destination The destination of the train departure.
   * @return The train departures with the specified destination as an iterator.
   * @since 1.6.0
   */
  public Iterator<TrainDeparture> searchByDestination(String destination) {
    return this.trainRegister.stream()
        .filter(train -> train.getDestination().equalsIgnoreCase(destination))
        .collect(Collectors.toCollection(ArrayList::new)).iterator();
  }

  /**
   * Assigns a new track to the train departure with the specified train number. The track number is
   * represented as a positive integer. If the train number does not exist in the train register,
   * the track number is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @param trackNumber The new specified track number.
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
    if (trainIterator.hasNext() && isTrainNumberUnique(newTrainNumber) && newTrainNumber > 0) {
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
   * train number does not exist in the train register or the new departure time is before the
   * current time displayed on the station clock, the departure time will not be changed.
   *
   * @param trainNumber      The train number of the train departure.
   * @param newDepartureTime The new departure time of the train departure.
   * @since 2.2.0
   */
  public void setNewDepartureTime(int trainNumber, String newDepartureTime) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext() && LocalTime.parse(newDepartureTime).isAfter(this.stationClock)) {
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
   * Returns an iterator of the remaining train departures in the train register. The train
   * departures that have already departed will not be shown.
   *
   * @return An iterator of the remaining train departures in the train register.
   * @since 3.0.0
   */
  public Iterator<TrainDeparture> hideDepartedTrains() {
    var remainingTrains = this.trainRegister.stream()
        .filter(train -> (train.getDepartureTime().isBefore(train.getRealDepartureTime())
            || train.getDepartureTime().equals(train.getRealDepartureTime()))
            && train.getRealDepartureTime().isAfter(this.stationClock)
            || train.getDepartureTime().isAfter(train.getRealDepartureTime()));

    return remainingTrains.collect(Collectors.toCollection(ArrayList::new)).iterator();
  }

  /**
   * Resets the train register. The train register will be empty after the method is called.
   *
   * @since 3.0.0
   */
  public void emptyTrainRegister() {
    this.trainRegister.clear();
  }
}
