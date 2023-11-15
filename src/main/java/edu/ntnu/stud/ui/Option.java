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
 *
 * @author Johan Fredrik Wilvang
 * @version 2.0.0
 * @since 2.0.0
 */

public class Option {
  private final InputValidator handler;
  private final TrainStation station;

  /**
   * Constructor for objects of class Option. The constructor initializes an object of class
   * InputValidator and an object of class TrainStation.
   *
   * @since 2.0.0
   */
  public Option() {
    this.handler = new InputValidator();
    this.station = new TrainStation();
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
   * Returns the number associated with the action the user wants to perform. The method prompts
   * the user to select an option from the main menu.
   *
   * @return The number associated with the action the user wants to perform.
   * @since 2.0.0
   */
  public int selectOption(){
    return this.handler.inputInteger("the number associated with the action you want to "
        + "perform");
  }

  /**
   * Adds a train departure to the train register. The method prompts the user to enter the
   * departure time, train number, train line and destination for the train departure. The method
   * The train departure will not be added if the train number is already existing in the train
   * register.
   *
   * @return <code>true</code> if the train departure was not added to the train register,
   * <code> false</code> if the train
   * @since 2.0.0
   */
  public boolean addTrainDeparture() {
    int before = numberOfTrainDepartures();
    String departureTime = handler.validateTime();
    int trainNumber = handler.inputInteger("train number associated with the train "
        + "departure");
    String trainLine = handler.inputString("train line for this train departure");
    String destination = handler.inputString("train's destination");
    this.station.addTrainDeparture(departureTime, trainNumber, trainLine, destination);
    int after = numberOfTrainDepartures();
    return before == after;
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
  public Iterator<TrainDeparture> searchByTrainNumber(){
    int trainNumber = handler.inputInteger("train number");
    return this.station.searchByTrainNumber(trainNumber);
  }
}
