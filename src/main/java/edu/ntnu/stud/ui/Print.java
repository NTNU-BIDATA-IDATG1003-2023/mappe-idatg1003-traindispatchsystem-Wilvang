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
 * @version 2.1.0
 * @since 2.1.0
 */

public class Print {

  private final ConsoleColor color;

  /**
   * Creates object of class Print. The object contains an object of class ColorPrint.
   *
   * @since 1.9.0
   */
  public Print() {
    this.color = new ConsoleColor();
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
   * Prints a large separator to the console. The large separator is used to separate the train
   * information table
   *
   * @since 2.1.0
   */
  public void printLargeSeparator() {
    color.printBlue("--------------------------------------------------------------------------"
        + "----------------------------------------------");
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
    color.printBlue("departures at your station. To get started, please ");
    color.printBlue("write the current time in the format (HH:mm). If you");
    color.printBlue("are unsure or don't want to write the time, you can");
    color.printBlue("write the keyword 'q' to set the time to 00:00.");
    printSeparator();
  }

  /**
   * Prints the message prompted to the user when the application is closed.
   *
   * @since 1.9.0
   */
  public void printExit() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Thank you for using the train dispatch system! "));
    color.printBlue("\nWe hope to see you again soon! Have a great rest of ");
    color.printBlue("your day!");
  }

  /**
   * Prints the current time as a digital clock. The time is printed in purple.
   *
   * @param time The current time.
   * @return The current time as a digital clock.
   * @since 1.9.0
   */
  public String clockMessage(LocalTime time) {
    return ConsoleColor.ANSI_PURPLE + "The time is: " + ConsoleColor.ANSI_PURPLE_BACKGROUND
        + color.printWhiteBold(" " + time + " ") + ConsoleColor.ANSI_RESET;
  }

  /**
   * Prints the number of train departures due today. The number of train departures is printed in
   * purple.
   *
   * @param numberOfTrains The number of train departures due today.
   * @return The number of train departures due today.
   */
  public String printNumberOfTrains(int numberOfTrains) {
    return ConsoleColor.ANSI_PURPLE + "Train departures today: "
        + ConsoleColor.ANSI_PURPLE_BACKGROUND + color.printWhiteBold(" " + numberOfTrains + " ")
        + ConsoleColor.ANSI_RESET;
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
    System.out.printf("%-50s %-50s%n", clockMessage(time), printNumberOfTrains(numberOfTrains));
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
    color.printWhite("[1]" + ConsoleColor.ANSI_BLUE + " View information table");
    color.printWhite("[2]" + ConsoleColor.ANSI_BLUE + " Add new train departure");
    color.printWhite("[3]" + ConsoleColor.ANSI_BLUE + " Assign track");
    color.printWhite("[4]" + ConsoleColor.ANSI_BLUE + " Add delay");
    color.printWhite("[5]" + ConsoleColor.ANSI_BLUE + " Search for train");
    color.printWhite("[6]" + ConsoleColor.ANSI_BLUE + " Update clock");
    color.printWhite("[9]" + ConsoleColor.ANSI_BLUE + " Exit");
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
    color.printWhite("[1]" + ConsoleColor.ANSI_BLUE + " Search by train number.");
    color.printWhite("[2]" + ConsoleColor.ANSI_BLUE + " Search by destination.");
    color.printWhite("[9]" + ConsoleColor.ANSI_BLUE + " Return to main menu.");
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
    color.printBlue("before the new selected time, " +
        color.printBlueBold("will be removed."));
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
    color.printBlue("\nThe mandatory information that is required to add a new ");
    color.printBlue("train departure is the departure time, train number, ");
    color.printBlue("destination and train line. The new train departure must ");
    color.printBlue("have a unique train number. The track number is set to -1" );
    color.printBlue("to default. You will be prompted an option to change this ");
    color.printBlue("later.");
    color.printBlue("\nCATION: If the train number already exists or the train");
    color.printBlue("already has departed, the new train departure "
        + color.printBlueBold("will \nnot be added"));
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
      System.out.printf(ConsoleColor.ANSI_WHITE + "%-20s %-20s %-20s %-20s %-20s %-20s%n",
          train.getTrainNumber(),train.getRealDepartureTime(), train.getDelay().getMinute()
              + " minutes", train.getTrainLine(), train.getDestination(), train.getTrackNumber());
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
    color.printBlueBackground(color.printWhiteBold(" Train Information Table "));
    color.printBlue("");
    System.out.printf(ConsoleColor.BLUE_BOLD + "%-20s %-20s %-20s %-20s %-20s %-20s%n",
        "Train number:", "Departure time:", "Delay:", "Line:", "Destination:", "Track number:");
    printLargeSeparator();
    printTrainDeparture(trainIterator);
    printLargeSeparator();
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
    System.err.println("Please make sure that the train number is unique and ");
    System.err.println("that thedeparture time is not before the current time.");
  }

  /**
   * Prints the newly added train departure.
   *
   * @param trainIterator The train iterator containing the train departures.
   */
  public void printAddedTrain(Iterator<TrainDeparture> trainIterator){
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" New Train Departure "));
    color.printBlue("");

    System.out.printf(ConsoleColor.BLUE_BOLD + "%-20s %-20s %-20s %-20s %-20s %-20s%n",
        "Train number:", "Departure time:", "Delay:", "Line:", "Destination:", "Track number:");
    printLargeSeparator();
    printTrainDeparture(trainIterator);
    printLargeSeparator();
    printSeparator();
  }
}
