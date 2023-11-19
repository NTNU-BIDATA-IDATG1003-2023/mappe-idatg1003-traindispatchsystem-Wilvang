package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import edu.ntnu.stud.transport.train.TrainStation;
import edu.ntnu.stud.utility.InputValidator;
import java.time.LocalTime;
import java.util.Iterator;


/**
 * The Option class is used to perform actions on the train station. The class contains methods to
 * perform different actions, such as adding a train departure, searching for a train departure and
 * updating the station clock. The class uses an object of class InputValidator to validate the user
 * input. The class uses an object of class TrainStation to perform actions on the train station.
 * The class uses an object of class Print to print messages to the console.
 *
 * @author Johan Fredrik Wilvang
 * @version 2.3.2
 * @since 2.3.1
 */

public class Option {
  private final InputValidator handler;
  private final TrainStation station;
  private final Print message;

  /**
   * Constructor for objects of class Option. The constructor initializes an object of class
   * InputValidator and an object of class TrainStation.
   *
   * @since 2.0.0
   */
  public Option() {
    this.handler = new InputValidator();
    this.station = new TrainStation();
    this.message = new Print();
  }

  /**
   * Sets the clock on the train station to the specified time by the user. The clock will not be
   * updated if the user writes the keyword 'q' to quit the action.
   *
   * @since 2.0.0
   */
  public void updateClock() {
    message.inputTime();
    String time = this.handler.validateTimeOption();
    if (!time.equals("q")) {
      this.station.setStationClock(time);
    }
  }

  /**
   * Return the time displayed on the station clock.
   *
   * @return The time displayed on the station clock.
   * @since 2.0.0
   */
  public LocalTime displayClock() {
    return this.station.getStationClock();
  }

  /**
   * Returns the total amount of train departures in the train register.
   *
   * @return The total amount of train departures in the train register.
   * @since 2.0.0
   */
  public int numberOfTrainDepartures() {
    return this.station.getNumberOfTrains();
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the main menu.
   *
   * @return The number associated with the action the user wants to perform.
   * @since 2.0.0
   */
  public int selectOption() {
    message.inputSelectOption();
    return this.handler.inputInteger();
  }

  /**
   * Adds a train departure to the train register. The method prompts the user to enter the
   * departure time, train number, train line and destination for the train departure. The method
   * The train departure will not be added if the train number is already existing in the train
   * register.
   *
   * @return <code>true</code> if the train departure was not added to the train register,
   *         <code> false</code> if the train
   * @since 2.0.0
   */
  public int addTrainDeparture() {
    message.inputDepartureTime();
    String departureTime = handler.validateTime();
    message.inputTrainNumber();
    int trainNumber = handler.inputInteger();
    message.inputTrainLine();
    String trainLine = handler.inputString();
    message.inputDestination();
    String destination = handler.inputString();
    this.station.addTrainDeparture(departureTime, trainNumber, trainLine, destination);
    return trainNumber;
  }

  /**
   * Searches for the train departure associated with the specified train number. The method prompts
   * the user to enter the train number of the train departure. The method will return the train
   * departure if the train number is existing in the train register. If the train number is not
   * existing in the train register, the method will return <code>null</code>.
   *
   * @return The train departure associated with the specified train number.
   * @since 2.0.0
   */
  public Iterator<TrainDeparture> searchByTrainNumber() {
    message.inputSearchTrainNumber();
    int trainNumber = handler.inputInteger();
    return this.station.searchByTrainNumber(trainNumber);
  }

  /**
   * Searches for the train departure associated with the specified train number. The method will
   * return the train departure if the train number is existing in the train register. If the train
   * number is not existing in the train register, the method will return <code>null</code>.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure associated with the specified train number.
   * @since 2.1.0
   */
  public Iterator<TrainDeparture> searchByTrainNumber(int trainNumber) {
    return this.station.searchByTrainNumber(trainNumber);
  }

  /**
   * Searches for all train departures with the specified destination The method prompts the
   * user to enter the destination of the train departure. The method will return an iterator
   * containing all train departures with the specified destination.
   *
   * @return An iterator containing all train departures with the specified destination.
   * @since 2.3.0
   */
  public Iterator<TrainDeparture> searchByDestination() {
    message.inputSearchDestination();
    return this.station.searchByDestination(handler.inputString());
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>. The method will continue to prompt the user until the user enters the
   * correct format.
   *
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.1.0
   */
  public boolean askToContinue() {
    return handler.validateBoolean();
  }

  /**
   * Initializes the train register with four specified train departures. The method will only be
   * called in the initialization of the program.
   *
   * @since 2.1.0
   */
  public void initTrainDepartures() {
    this.station.addTrainDeparture("12:43", 601,
        "F2", "Bergen");
    this.station.addTrainDeparture("12:45", 63,
        "RE12", "Gjøvik");
    this.station.addTrainDeparture("14:13", 31,
        "L1", "Kjelsås");
    this.station.addTrainDeparture("14:43", 201,
        "F3", "Trondheim");
    this.station.setNewDelay(201, 10);
    this.station.setNewTrackNumber(201, 2);
    this.station.setNewDelay(63, 10);
  }

  /**
   * Returns an iterator containing all train departures in the train register. The iterator will be
   * sorted by the departure time.
   *
   * @return An iterator containing all train departures in the train register.
   * @since 2.1.0
   */
  public Iterator<TrainDeparture> getTrainRegister() {
    return this.station.getTrainRegister();
  }

  /**
   * Sets the departure time of the train departure with the specified train number. The departure
   * time is represented as a string in the format HH:mm. If the train number does not exist in the
   * train register, the new departure time is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setDepartureTime(int trainNumber) {
    message.inputDepartureTime();
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDepartureTime(trainNumber, handler.validateTime());
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the train number of the train departure with the specified train number. The train number
   * is represented as an integer with up to 4 digits. If the train number does not exist in the
   * train register, the new train number is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.2.1
   */
  public Iterator<TrainDeparture> setTrainNumber(int trainNumber) {
    message.inputTrainNumber();
    int newTrainNumber = handler.inputInteger();
    if (searchByTrainNumber(trainNumber).hasNext() && station.isTrainNumberUnique(newTrainNumber)
        && newTrainNumber > 0) {
      station.setNewTrainNumber(trainNumber, newTrainNumber);
    } else {
      newTrainNumber = trainNumber;
      message.errorTrainNumberExists();
    }
    return searchByTrainNumber(newTrainNumber);
  }

  /**
   * Sets the train line of the train departure with the specified train number. The train line is
   * represented as a string. If the train number does not exist in the train register, the new
   * train line is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setTrainLine(int trainNumber) {
    message.inputTrainLine();
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewTrainLine(trainNumber, handler.inputString());
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the destination of the train departure with the specified train number. The destination is
   * represented as a string. If the train number does not exist in the train register, the new
   * destination is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setDestination(int trainNumber) {
    message.inputDestination();
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDestination(trainNumber, handler.inputString());
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the delay of the train departure with the specified train number. The delay is set by the
   * specified amount of minutes delayed. If the train number does not exist in the train register,
   * the new delay is not assigned to the selected train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setDelay(int trainNumber) {
    message.inputDelay();
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDelay(trainNumber, handler.inputInteger());
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Assigns a track number to the train departure with the specified train number. The track number
   * is represented as an integer between 1-100. If the train number does not exist in the train
   * register, the track number is not assigned to any train departure.
   *
   * @param trainNumber The train number of the train departure.
   * @return The train departure with the specified train number.
   * @since 2.3.1
   */
  public Iterator<TrainDeparture> setTrackNumber(int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      message.inputTrackNumber();
      station.setNewTrackNumber(trainNumber,
          handler.inputInteger());
    }
    return searchByTrainNumber(trainNumber);
  }
}
