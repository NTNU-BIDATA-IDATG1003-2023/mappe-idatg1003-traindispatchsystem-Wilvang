package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.TrainDeparture;
import edu.ntnu.stud.transport.TrainStation;
import edu.ntnu.stud.util.ErrorResponse;
import edu.ntnu.stud.util.InputValidator;
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
 * @version 3.0.5
 * @since 3.0.0
 */

public class TrainDispatcher {

  private final InputValidator handler;
  private final TrainStation station;

  /**
   * Constructor for objects of class Option. The constructor initializes an object of class
   * InputValidator and an object of class TrainStation.
   *
   * @since 2.0.0
   */
  public TrainDispatcher(TrainStation station) {
    this.handler = new InputValidator();
    this.station = station;
    initTrainDepartures();
  }

  /**
   * Sets the clock on the train station to the specified time by the user. The clock will not be
   * updated if the user writes the keyword 'q' to quit the action.
   *
   * @since 2.4.0
   */
  public String updateClock(Print message) {
    String time = handler.inputStationTime(message);
    if (!time.equalsIgnoreCase("q")) {
      this.station.setStationClock(time);
    }
    return time;
  }

  /**
   * Sets the clock on the train station to the specified time. The clock will not be updated if the
   * user writes the keyword 'q' to quit the action.
   *
   * @param time The time to set the clock to.
   * @since 2.4.0
   */
  public void updateClock(String time) {
    if (!time.equalsIgnoreCase("q")) {
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
   * Displays the status bar on the console. The status bar contains the time displayed on the
   * station clock and the number of remaining train departures in the train register.
   *
   * @param message The object of class Print used to print messages to the console.
   * @since 2.4.0
   */
  private void displayStatusBar(Print message) {
    message.printStatusBar(displayClock(), station.getNumberOfTrains());
  }

  /**
   * Resets the station. The method resets the station clock and empties the train register to start
   * a new day.
   *
   * @since 2.4.0
   */
  public void resetStation() {
    this.station.resetClock();
    this.station.emptyTrainRegister();
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the main menu.
   *
   * @return The number associated with the action the user wants to perform.
   * @since 2.0.0
   */
  private int selectOption(Print message) {
    message.inputSelectOption();
    return this.handler.inputPositiveInteger(message);
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the main menu.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return The number associated with the action the user wants to perform.
   * @since 2.4.0
   */
  public int selectMainMenuOption(Print message) {
    displayStatusBar(message);
    message.printMainManu();
    return selectOption(message);
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the search menu.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return The number associated with the action the user wants to perform.
   * @since 2.4.0
   */
  public int selectSearchOption(Print message) {
    displayStatusBar(message);
    message.printSearchMenu();
    return selectOption(message);
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the edit menu.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return The number associated with the action the user wants to perform.
   * @since 2.4.0
   */
  public int selectEditOption(Print message, int trainNumber) {
    message.printSelectedTrain(this.station.getStationClock(), searchByTrainNumber(trainNumber));
    displayStatusBar(message);
    message.printEditTrainMenu();
    return selectOption(message);
  }

  /**
   * Returns the number associated with the action the user wants to perform. The method prompts the
   * user to select an option from the information menu.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return The number associated with the action the user wants to perform.
   * @since 2.4.0
   */
  public int selectInformationOption(Print message) {
    displayStatusBar(message);
    message.printInformationMenu();
    return selectOption(message);
  }

  /**
   * The user is prompted to add a new train. Returns <code>true</code> if the user enters 'y',
   * <code>false</code> if the user enters 'n'
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean selectAddTrainMenu(Print message) {
    message.addTrainDepartureOption();
    message.askTrainDeparture();
    return this.handler.inputAnswer(message);
  }

  /**
   * The user is prompted to update the station clock. Returns <code>true</code> if the user enters
   * 'y', <code>false</code> if the user enters 'n'.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean selectUpdateClockMenu(Print message) {
    message.endOfDay();
    return this.handler.inputAnswer(message);
  }

  /**
   * Adds a train departure to the train register. The method prompts the user to enter the
   * departure time, train number, train line and destination for the train departure. The train
   * departure will not be added if the train number is already existing in the train register or
   * the train departure is departing before the current time.
   *
   * @return The train number of the train departure.
   * @since 2.0.0
   */
  public int addTrainDeparture(Print message) {
    displayStatusBar(message);
    message.printSeparator();
    String departureTime = handler.inputDepartureTime(message, station);
    int trainNumber = handler.inputTrainNumber(message, station);
    String trainLine = handler.inputTrainLine(message);
    String destination = handler.inputDestination(message);
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
  public Iterator<TrainDeparture> searchByTrainNumber(Print message) {
    return this.station.searchByTrainNumber(handler.inputSearchTrainNumber(message, station));
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
   * Searches for all train departures with the specified destination The method prompts the user to
   * enter the destination of the train departure. The method will return an iterator containing all
   * train departures with the specified destination.
   *
   * @return An iterator containing all train departures with the specified destination.
   * @since 2.3.0
   */
  public Iterator<TrainDeparture> searchByDestination(Print message) {
    return this.station.searchByDestination(handler.inputSearchDestination(message));
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
  public boolean askToContinue(Print message) {
    message.askIfSure();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @param time    The time displayed on the station clock.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askToContinue(Print message, String time) {
    message.newTime(time);
    message.askIfSure();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askIfEdit(Print message) {
    message.askEditThisDeparture();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   */
  public boolean askTrackNumber(Print message) {
    message.askTrackNumber();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askNewTrain(Print message) {
    message.errorMessage(ErrorResponse.INVALID_TRAIN);
    message.printSeparator();
    message.askTrainDeparture();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askAgainEmptyIterator(Print message) {
    message.errorMessage(ErrorResponse.EMPTY_ITERATOR);
    message.printSeparator();
    message.askTryAgain();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askEmptyRegister(Print message) {
    message.errorMessage(ErrorResponse.EMPTY_ITERATOR);
    message.printSeparator();
    message.askTrainDeparture();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @param time    The time displayed on the station clock, as a string.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askNewDay(Print message, String time) {
    message.printSeparator();
    message.askNewDay(time);
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to enter 'y' or 'n' to continue the action. If the user types 'y', the method
   * will return <code>true</code>. If the user types 'n', the method will return
   * <code>false</code>.
   *
   * @param message The object of class Print used to print messages to the console.
   * @return <code>true</code> if the user enters 'y', <code>false</code> if the user enters 'n'.
   * @since 2.4.0
   */
  public boolean askIfExit(Print message) {
    message.exitOption();
    message.askExit();
    return handler.inputAnswer(message);
  }

  /**
   * Prompts the user to press enter to continue the action.
   *
   * @param message The object of class Print used to print messages to the console.
   * @since 2.4.0
   */
  public void pressToContinue(Print message) {
    message.pressToContinue();
    handler.inputEnter(message);
  }

  /**
   * Returns an iterator containing all the train departures in the train register. The iterator
   * will be sorted by the departure time.
   *
   * @return An iterator containing all the remaining train departures.
   * @since 2.1.0
   */
  public Iterator<TrainDeparture> getTrainRegister() {
    return this.station.hideDepartedTrains();
  }

  /**
   * Returns an iterator containing all the train departures in the train register, including the
   * departed trains. The iterator will be sorted by the departure time.
   *
   * @return An iterator containing all the train departures.
   * @since 3.0.0
   */
  public Iterator<TrainDeparture> getTrainHistory() {
    return this.station.getTrainRegister();
  }

  /**
   * Sets the departure time of the selected train departure. The departure time is represented as a
   * string. If no train departure is selected, the method will return an empty iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   * @since 3.0.0
   */
  public Iterator<TrainDeparture> setDepartureTime(Print message, int trainNumber) {
    Iterator<TrainDeparture> iterator = searchByTrainNumber(trainNumber);
    if (iterator.hasNext()) {
      TrainDeparture train = iterator.next();
      station.setNewDepartureTime(trainNumber, handler.inputDepartureTime(message, station));
      station.removeTrainDeparture(train.getTrainNumber());
      station.addTrainDeparture(train);
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the train number of the selected train departure. If no train departure is selected or the
   * train number is already existing in the train register, the method will return an empty
   * iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   * @since 3.0.0
   */
  public Iterator<TrainDeparture> setTrainNumber(Print message, int trainNumber) {
    int newTrainNumber = handler.inputTrainNumber(message, station);
    if (searchByTrainNumber(trainNumber).hasNext() && station.isTrainNumberUnique(newTrainNumber)) {
      station.setNewTrainNumber(trainNumber, newTrainNumber);
    } else {
      newTrainNumber = trainNumber;
      message.errorMessage(ErrorResponse.INVALID_TRAIN_NUMBER);
    }
    return searchByTrainNumber(newTrainNumber);
  }

  /**
   * Sets the train line of the selected train departure. If no train departure is selected, the
   * method will return an empty iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setTrainLine(Print message, int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewTrainLine(trainNumber, handler.inputTrainLine(message));
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the destination of the selected train departure. If no train departure is selected, the
   * method will return an empty iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setDestination(Print message, int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      message.inputDestination();
      station.setNewDestination(trainNumber, handler.inputDestination(message));
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the delay of the selected train departure. If no train departure is selected, the method
   * will return an empty iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   * @since 2.2.0
   */
  public Iterator<TrainDeparture> setDelay(Print message, int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewDelay(trainNumber, handler.inputDelay(message));
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Sets the track number of the selected train departure. If no train departure is selected, the
   * method will return an empty iterator.
   *
   * @param message     The object of class Print used to print messages to the console.
   * @param trainNumber The train number of the train departure.
   * @return An iterator containing the edited train departure.
   */
  public Iterator<TrainDeparture> setTrackNumber(Print message, int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.setNewTrackNumber(trainNumber, handler.inputTrackNumber(message));
    }
    return searchByTrainNumber(trainNumber);
  }

  /**
   * Removes the train departure with the specified train number permanently from the train
   * register. If the selected train departure does not exist in the train register, no train will
   * be removed.
   *
   * @param trainNumber The train number of the train departure.
   * @since 2.4.0
   */
  public void removeTrainDeparture(int trainNumber) {
    if (searchByTrainNumber(trainNumber).hasNext()) {
      station.removeTrainDeparture(trainNumber);
    }
  }

  /**
   * Displays the newly added train departure. If the train was not added, the method will return an
   * empty iterator.
   *
   * @param message The object of class Print used to print messages to the console.
   * @since 2.3.0
   */
  public void displayNewTrainDeparture(Print message, int trainNumber) {
    Iterator<TrainDeparture> trainIterator = searchByTrainNumber(trainNumber);
    if (trainIterator.hasNext()) {
      message.printNewTrainDeparture(station.getStationClock(), trainIterator);
    }
  }

  /**
   * This method is just used for testing purposes. The method adds four train departures to the
   * train station.
   *
   * @since 2.1.0
   */
  private void initTrainDepartures() {
    station.addTrainDeparture("12:43", 601, "F2", "Bergen");
    station.addTrainDeparture("12:45", 63, "L4", "Oslo");
    station.addTrainDeparture("14:13", 31, "RE10", "Ås");
    station.addTrainDeparture("14:43", 201, "F3", "Voss");
    station.setNewDelay(201, 70);
    station.setNewTrackNumber(201, 2);
    station.setNewDelay(63, 10);
  }
}


