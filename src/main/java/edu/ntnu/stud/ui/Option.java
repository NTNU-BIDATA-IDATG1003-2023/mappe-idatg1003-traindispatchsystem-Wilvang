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
 * @version 2.2.1
 * @since 2.2.1
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
    String time = this.handler.validateTime();
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
    message.promtSelectOption();
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
    String departureTime = handler.validateTime("the departure time");
    int trainNumber = handler.inputInteger("train number associated with the departure"
        + "\nThe train number must be a positive whole number.");
    String trainLine = handler.inputString("train line for this train departure"
        + "\n(e.g. L2, RE11, F3, etc.)");
    String destination = handler.inputString("train's final destination");
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
    int trainNumber = handler.inputInteger("train number");
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
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>. The method will continue to prompt the user until the user enters the
   * correct format.
   *
   * @param typeOfInput The type of input that the user is prompted to enter.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.1.0
   */
  public boolean askToContinue(String typeOfInput) {
    return handler.validateBoolean(typeOfInput);
  }

  /**
   * Initializes the train register with four specified train departures. The method will only be
   * called in the initialization of the program.
   *
   * @since 2.1.0
   */
  public void initTrainDepartures() {
    this.station.addTrainDeparture("12:43", 601, "F2", "Bergen");
    this.station.addTrainDeparture("13:43", 63, "RE12", "Skien");
    this.station.addTrainDeparture("12:13", 31, "L1", "Skøyen");
    this.station.addTrainDeparture("14:43", 201, "F3", "Trondheim");
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
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDepartureTime(trainNumber, handler.validateTime("the departure time"));
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
    int newTrainNumber = handler.inputInteger("the new train number");
    if (searchByTrainNumber(trainNumber).hasNext() && station.isTrainNumberUnique(newTrainNumber)
        && newTrainNumber > 0) {
      station.setNewTrainNumber(trainNumber, newTrainNumber);
    } else {
      newTrainNumber = trainNumber;
      // TODO: 2021-10-06 Add error message.
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
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewTrainLine(trainNumber, handler.inputString("the train line"));
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
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDestination(trainNumber, handler.inputString("the destination"));
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
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDelay(trainNumber, handler.inputInteger("the delay"));
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
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> assignTrack(int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewTrackNumber(trainNumber,
          handler.inputInteger("track number. The track number must "
              + "\nbe a positive whole number up to 100"));
    }
    return searchByTrainNumber(trainNumber);
  }
}
