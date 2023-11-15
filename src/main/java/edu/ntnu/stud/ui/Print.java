package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import java.time.LocalTime;
import java.util.Iterator;


/**
 * The Print class is used to print messages to the console. The class contains methods to print
 * different types of messages, such as information messages, error messages and status messages.
 * The class uses an object of class ColorPrint to print messages in different colors.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.9.0
 * @since 1.8.0
 */

public class Print {

  private final ColorPrint color;

  /**
   * Creates object of class Print. The object contains an object of class ColorPrint.
   *
   * @since 1.9.0
   */
  public Print() {
    this.color = new ColorPrint();
  }

  /**
   * Prints a separator to the console. The separator is used to separate different sections of the
   * application.
   *
   * @since 1.9.0
   */
  public void printSeparator() {
    color.printCyan("-------------------------------------------------------");
  }

  /**
   * Prints a specified message to the console. The message is printed in white.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void print(String message) {
    color.printWhite(message);
  }

  /**
   * Prints the initial message prompted to the user when the application is started. The message
   * contains information about the application and how to get started.
   *
   * @since 1.9.0
   */
  public void printWelcome() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Welcome to the Train Dispatch System! "));
    color.printBlue("\nThis system is designed to help you manage the train");
    color.printBlue("departures at your station. To get started, please write");
    color.printBlue("the current time in the format (hh:mm). If you are unsure");
    color.printBlue("or don't want to write the time, you can write 'q' and the");
    color.printBlue("time will be set to 00:00.");
    printSeparator();
  }

  /**
   * Prints the message prompted to the user when the application is closed.
   *
   * @since 1.9.0
   */
  public void goodByeMessage() {
    printSeparator();
    color.printWhite("Thank you for using the train dispatch application!");
  }

  /**
   * Prints the current time as a digital clock. The time is printed in purple.
   *
   * @param time The current time.
   * @return The current time as a digital clock.
   * @since 1.9.0
   */
  public String clockMessage(LocalTime time) {
    return ColorPrint.ANSI_PURPLE + "The time is: " + ColorPrint.ANSI_PURPLE_BACKGROUND
        + color.printWhiteBold(" " + time + " ");
  }

  /**
   * Prints the number of train departures due today. The number of train departures is printed in
   * purple.
   *
   * @param numberOfTrains The number of train departures due today.
   * @return The number of train departures due today.
   */
  public String printNumberOfTrains(int numberOfTrains) {
    return ColorPrint.ANSI_PURPLE + "Train departures today: "
        + ColorPrint.ANSI_PURPLE_BACKGROUND + color.printWhiteBold(" " + numberOfTrains + " ");
  }

  /**
   * Prints the status bar. The status bar contains the current time and the number of train
   * departures due today.
   *
   * @param time The current time.
   * @param numberOfTrains The number of train departures due today.
   * @since 1.9.0
   */
  public void printStatusBar(LocalTime time, int numberOfTrains) {
    printSeparator();
    color.printPurple(clockMessage(time) + ColorPrint.ANSI_RESET + "    " +
        printNumberOfTrains(numberOfTrains));
  }

  /**
   * Prints the main menu. The main menu contains the different options that the user can choose
   * from.
   *
   * @since 1.9.0
   */
  public void printMainManu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Please select an option: "));
    color.printWhite("[1]" + ColorPrint.ANSI_BLUE + " View information table");
    color.printWhite("[2]" + ColorPrint.ANSI_BLUE + " Add new train departure");
    color.printWhite("[3]" + ColorPrint.ANSI_BLUE + " Assign track");
    color.printWhite("[4]" + ColorPrint.ANSI_BLUE + " Add delay");
    color.printWhite("[5]" + ColorPrint.ANSI_BLUE + " Search for train");
    color.printWhite("[6]" + ColorPrint.ANSI_BLUE + " Update clock");
    color.printWhite("[9]" + ColorPrint.ANSI_BLUE + " Exit");
    printSeparator();
  }

  /**
   * Prints the search menu. The search menu contains the different options that the user can choose
   * from.
   *
   * @since 1.9.0
   */
  public void printSearchMenu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Please select an option: "));
    color.printWhite("[1]" + ColorPrint.ANSI_BLUE + " Search by train number.");
    color.printWhite("[2]" + ColorPrint.ANSI_BLUE + " Search by destination.");
    color.printWhite("[9]" + ColorPrint.ANSI_BLUE + " Return to main menu.");
    printSeparator();
  }

  /**
   * Prints an information message explaining the UPDATE_CLOCK option.
   *
   * @since 1.9.0
   */
  public void updateClockOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Changing the system time of the application "));
    color.printBlue("\nCAUTION: The train departures that is due to depart");
    color.printBlue("before the new selected time, will be removed.");
    printSeparator();
  }

  /**
   * Prints an information message explaining the ADD_TRAIN option.
   *
   * @since 1.9.0
   */
  public void addTrainDepartureOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Adding a new train departure "));
    color.printBlue("\nThe mandatory information that is required to add a new train departure is");
    color.printBlue("the departure time, train number, destination and train line.");
    color.printBlue("The new train departure must have a unique train number.");
    color.printBlue("If the train number already exists, the new train departure will not be added.");
    color.printBlue("The track number is set to -1 to default. You will be prompted an option to change");
    color.printBlue("this later.");
    printSeparator();
  }

  /**
   * Prints the information of all the train departures in the train iterator.
   *
   * @param trainIterator The train iterator containing the train departures.
   * @since 1.9.0
   */
  public void printTrainDeparture(Iterator<TrainDeparture> trainIterator){
    while (trainIterator.hasNext()){
      TrainDeparture train = trainIterator.next();
      System.out.printf(ColorPrint.ANSI_BLUE + "%-20s %-20s %-20s %-20s %-20s%n",
          train.getTrainNumber(),train.getDepartureTime(), train.getDelay().getMinute() + " minutes",
          train.getDestination(), train.getTrackNumber());
    }
  }

  /**
   * Prints the information table of all the train departures in the train iterator.
   *
   * @param trainIterator The train iterator containing the train departures.
   * @since 1.9.0
   */
  public void printTrainInformationTable(Iterator<TrainDeparture> trainIterator) {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Train Departure Table "));
    color.printBlue("");
    System.out.printf(ColorPrint.ANSI_BLUE + "%-20s %-20s %-20s %-20s %-20s%n",
        "Train number:", "Departure time:", "Delay:", "Destination:", "Track number:");
    printSeparator();
    printTrainDeparture(trainIterator);
    printSeparator();
  }

  /**
   * Prints an error message explaining that the user chose an invalid option.
   *
   * @since 1.9.0
   */
  public void printInvalidOption() {
    System.err.println("You chose an invalid option.");
    System.err.println("Please try again with one of the listed options below.");
  }

  /**
   * Prints an error message explaining that the train departure failed to be created.
   *
   * @since 1.9.0
   */
  public void invalidTrainDeparture() {
    System.err.println("The train departure you are trying to add is invalid.");
    System.err.println("Please make sure that the train number is unique and that the ");
    System.err.println("departure time is not before the current time.");
  }
}
