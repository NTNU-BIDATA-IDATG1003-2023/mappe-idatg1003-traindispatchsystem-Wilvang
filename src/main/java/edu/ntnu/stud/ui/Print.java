package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import edu.ntnu.stud.utility.ErrorResponse;
import java.time.LocalTime;
import java.util.Iterator;

/**
 * The Print class is used to print message output to the console. The class contains methods to
 * print different types of messages, such as information messages, error messages and status
 * messages. The class uses an object of class ConsoleColor to print message outputs in different
 * colors.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.2
 * @since 2.4.0
 */

public class Print {

  private final ConsoleColor color;
  private final Object[] trainInformationHeader;
  private static final String TABLE_FORMAT = "%-20s %-20s %-20s %-20s %-20s %-20s%n";
  private static final String ASK_TO_CONTINUE = "Please enter 'y' for yes or 'n' for no ::";
  private static final String PLEASE_ENTER = "Please enter ";
  private static final String DEPARTURE = ConsoleColor.ANSI_CYAN + " for this departure";
  private static final String SET = " Set";
  private static final String SEARCH = " Search";
  private static final String SELECT = " Select";
  private static final String RETURN = " Return";
  private static final String MAIN_MENU = " to main menu";

  /**
   * Creates object of class Print. The object contains an object of class ColorPrint and the train
   * information header that is used to print the information table. The header contains the
   * different headers for the information table.
   *
   * @since 2.3.0
   */
  public Print() {
    this.color = new ConsoleColor();
    this.trainInformationHeader = new Object[]{"Departure time:", "Line:", "Train number:",
        "Destination:", "Delay:", "Track:"};
  }

  /**
   * Prints a separator to the console. The separator is used to separate different sections of the
   * application.
   *
   * @since 1.6.2
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
   * Prints the initial message prompted to the user when the application is started. The message
   * contains information about the application and how to get started.
   *
   * @since 1.6.2
   */
  public void printStart() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Welcome to the Train Dispatch System! "));
    color.printBlue("\nThis system is designed to help you manage the train");
    color.printBlue("departures at your station. To get started, please ");
    color.printBlue("write the time of day at your location. If you are ");
    color.printBlue("unsure or simply don't want to write the time, you can");
    color.printBlue("enter the keyword 'q' to set the time to 00:00.");
    printSeparator();
  }

  /**
   * Prints the message prompted to the user when the application is closed.
   *
   * @since 1.6.2
   */
  public void printExit() {
    printSeparator();
    color.printBlueBackground(
        color.printWhiteBold(" Thank you for using the train dispatch system! "));
    color.printBlue("\nWe hope to see you again soon! Have a great rest of ");
    color.printBlue("your day!");
  }

  /**
   * Prints an information message explaining the UPDATE_CLOCK option.
   *
   * @since 1.6.2
   */
  public void updateClockOption() {
    printSeparator();
    color.printBlueBackground(
        color.printWhiteBold(" Changing the system time of the application "));
    color.printBlue("\nCAUTION: The train departures that is due to depart");
    color.printBlue("before the new selected time, "
        + color.printBlueBold("will be removed."));
    printSeparator();
  }

  /**
   * Prints an information message explaining the ADD_TRAIN option.
   *
   * @since 1.6.2
   */
  public void addTrainDepartureOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Adding a new train departure "));
    color.printBlue("\nThe mandatory information that is required to add a new ");
    color.printBlue("train departure is the departure time, train number, ");
    color.printBlue("destination and train line. The train number is unique for");
    color.printBlue("this day.");
    color.printBlue("\nCATION: If the train number already exists or the train");
    color.printBlue("already has departed, the new train departure "
        + color.printBlueBold("will \nnot be added"));
    printSeparator();
  }

  /**
   * Prints an information message explaining the REMOVE_TRAIN option.
   *
   * @since 2.4.0
   */
  public void removeTrainDepartureOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Removing a train departure "));
    color.printBlue("\nCATION: The selected train departure will be "
        + color.printBlueBold("permanently"));
    color.printBlue(color.printBlueBold("removed ")
        + ConsoleColor.ANSI_BLUE + "from the train register.");
    printSeparator();
  }

  /**
   * Prints an information message explaining the program reset when starting a new day. The message
   * explains that all the train departures that has departed will be removed from the train
   * register.
   *
   * @since 2.3.0
   */
  public void newDayOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Starting a new day "));
    color.printBlue("\nCAUTION: When starting a new day, all the train departures will be");
    color.printBlue(color.printBlueBold("permanently removed")
        + ConsoleColor.ANSI_BLUE + " from the train register.");
    printSeparator();
  }

  /**
   * Prints an information message explaining the EXIT option. The message is printed when the user
   * wants to exit the application. The message explains that all the train departures that has been
   * added will be removed from the train register.
   *
   * @since 2.3.2
   */
  public void exitOption() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Exiting the application "));
    color.printBlue("\nCAUTION: When exiting the application, all train");
    color.printBlue("departures that has been added will be removed.");
    printSeparator();
  }


  /**
   * Prints the current time as a digital clock. The time is printed in purple.
   *
   * @param time The current time.
   * @return The current time as a digital clock.
   * @since 1.6.2
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
   * @param time           The current time.
   * @param numberOfTrains The number of train departures due today.
   * @since 1.6.2
   */
  public void printStatusBar(LocalTime time, int numberOfTrains) {
    printSeparator();
    System.out.printf("%-50s %-50s%n", clockMessage(time), printNumberOfTrains(numberOfTrains));
  }

  /**
   * Prints the main menu. The main menu contains the different options that the user can choose
   * from.
   *
   * @since 2.4.0
   */
  public void printMainManu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" MAIN MENU "));
    color.printWhite("[1]" + color.printBlueBold(" View")
        + ConsoleColor.ANSI_BLUE + " information table");
    color.printWhite("[2]" + color.printBlueBold(" Add")
        + ConsoleColor.ANSI_BLUE + " new train departure");
    color.printWhite("[3]" + color.printBlueBold(" Remove")
        + ConsoleColor.ANSI_BLUE + " train departure");
    color.printWhite("[4]" + color.printBlueBold(" Edit")
        + ConsoleColor.ANSI_BLUE + " train departure");
    color.printWhite("[5]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " delay");
    color.printWhite("[6]" + color.printBlueBold(" Assign")
        + ConsoleColor.ANSI_BLUE + " track number");
    color.printWhite("[7]" + color.printBlueBold(SEARCH)
        + ConsoleColor.ANSI_BLUE + " for train");
    color.printWhite("[8]" + color.printBlueBold(" Update")
        + ConsoleColor.ANSI_BLUE + " clock");
    color.printWhite("[9]" + color.printBlueBold(" Exit"));
    printSeparator();
  }

  /**
   * Prints the search menu. The search menu contains the different options that the user can choose
   * from.
   *
   * @since 2.4.0
   */
  public void printSearchMenu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" SEARCH MENU "));
    color.printWhite("[1]" + color.printBlueBold(SEARCH)
        + ConsoleColor.ANSI_BLUE + " by train number");
    color.printWhite("[2]" + color.printBlueBold(SEARCH)
        + ConsoleColor.ANSI_BLUE + " by destination");
    color.printWhite("[9]" + color.printBlueBold(RETURN)
        + ConsoleColor.ANSI_BLUE + MAIN_MENU);
    printSeparator();
  }

  /**
   * Prints the edit train menu. The edit train menu contains the different options that the user
   * can choose from.
   *
   * @since 2.4.0
   */
  public void printEditTrainMenu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" EDIT TRAIN MENU "));
    color.printWhite("[1]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " departure time");
    color.printWhite("[2]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " train line");
    color.printWhite("[3]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " train number");
    color.printWhite("[4]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " end destination");
    color.printWhite("[5]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " delay");
    color.printWhite("[6]" + color.printBlueBold(SET)
        + ConsoleColor.ANSI_BLUE + " track number");
    color.printWhite("[7]" + color.printBlueBold(SELECT)
        + ConsoleColor.ANSI_BLUE + " new train");
    color.printWhite("[9]" + color.printBlueBold(RETURN)
        + ConsoleColor.ANSI_BLUE + MAIN_MENU);
    printSeparator();
  }

  /**
   * Prints the train information menu. The menu contains the different options that the user can
   * choose from.
   *
   * @since 2.4.0
   */
  public void printInformationMenu() {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" TRAIN INFORMATION MENU "));
    color.printWhite("[1]" + color.printBlueBold(" Display")
        + ConsoleColor.ANSI_BLUE + " information table");
    color.printWhite("[2]" + color.printBlueBold(" Display")
        + ConsoleColor.ANSI_BLUE + " today's train history");
    color.printWhite("[9]" + color.printBlueBold(RETURN)
        + ConsoleColor.ANSI_BLUE + MAIN_MENU);
    printSeparator();
  }

  /**
   * Prints a message informing the user that the application is returning to the main menu.
   *
   * @since 2.4.0
   */
  public void returnToMainMenu() {
    color.printCyan("Returning to the main menu...");
  }

  /**
   * Prints the information of all the train departures in the train iterator.
   *
   * @param trainIterator The train iterator containing the train departures.
   * @param stationClock  The clock containing the current time of the station.
   * @since 2.4.0
   */
  public void printTrainDeparture(LocalTime stationClock, Iterator<TrainDeparture> trainIterator) {
    while (trainIterator.hasNext()) {
      System.out.printf(ConsoleColor.ANSI_WHITE + TABLE_FORMAT,
          trainDepartureInformation(stationClock, trainIterator));
    }
  }

  /**
   * Prints the information of the selected train departure. The method will print the information
   * of the train departures in the train iterator. The information about the track number and will
   * only be printed if it is specified in the train departure
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @return The information of the selected train departure.
   * @since 2.4.0
   */
  public Object[] trainDepartureInformation(LocalTime stationClock,
      Iterator<TrainDeparture> trainIterator) {
    TrainDeparture train = trainIterator.next();
    Object[] trainInformation = {train.getDepartureTime(), train.getTrainLine(),
        +train.getTrainNumber(), train.getDestination(), "",
        ""};
    if (train.getMinutesDelay() > 0) {
      trainInformation[0] = color.printStrikeThrough("" + train.getDepartureTime())
          + "  " + train.getRealDepartureTime() + "        ";
      trainInformation[4] = train.getDelay();
    }
    if (train.getTrackNumber() != -1) {
      trainInformation[5] = train.getTrackNumber();
    }
    if (train.getRealDepartureTime().isBefore(stationClock)) {
      trainInformation[0] = color.printPurpleBold("DEPARTED")
          + ConsoleColor.ANSI_WHITE + "            ";
    }
    return trainInformation;
  }

  /**
   * Prints the information table of all the train departures in the train iterator.
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @since 2.4.0
   */
  public void printTable(LocalTime stationClock, Iterator<TrainDeparture> trainIterator) {
    color.printBlue("");
    System.out.printf(ConsoleColor.BLUE_BOLD + TABLE_FORMAT,
        this.trainInformationHeader);
    printLargeSeparator();
    printTrainDeparture(stationClock, trainIterator);
    printLargeSeparator();
  }

  /**
   * Prints the information table of all the train departures in the train iterator.
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @since 2.4.0
   */
  public void printTrainInformationTable(LocalTime stationClock,
      Iterator<TrainDeparture> trainIterator) {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Train Information Table "));
    printTable(stationClock, trainIterator);
  }

  /**
   * Prints the selected train departures.
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @since 2.2.0
   */
  public void printSelectedTrain(LocalTime stationClock, Iterator<TrainDeparture> trainIterator) {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Selected Train Departure "));
    printTable(stationClock, trainIterator);
  }

  /**
   * Prints the newly added train departure.
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @since 2.2.0
   */
  public void printNewTrainDeparture(LocalTime stationClock,
      Iterator<TrainDeparture> trainIterator) {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Newly Added Train Departure "));
    printTable(stationClock, trainIterator);
  }

  /**
   * Prints the train history of the selected train.
   *
   * @param stationClock  The clock containing the current time of the station.
   * @param trainIterator The train iterator containing the train departures.
   * @since 2.4.0
   */
  public void printTrainHistory(LocalTime stationClock, Iterator<TrainDeparture> trainIterator) {
    printSeparator();
    color.printBlueBackground(color.printWhiteBold(" Train History "));
    printTable(stationClock, trainIterator);
  }

  /**
   * Prompts the user to enter the number associated with the option they want to select.
   *
   * @since 2.3.0
   */
  public void inputSelectOption() {
    color.printCyan("Please enter the number associated with " + color.printCyanBold("the option"));
    color.printCyan("you want to select ::");
  }

  /**
   * Prompts the user to enter the time in the format (HH:mm).
   *
   * @since 2.3.0
   */
  public void inputTime() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the time")
        + ConsoleColor.ANSI_CYAN + " in the format (HH:mm)");
    color.printCyan("or enter 'q' quit this action ::");
  }

  /**
   * Prompts the user to enter the departure time in the format (HH:mm).
   *
   * @since 2.3.0
   */
  public void inputDepartureTime() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the departure time")
        + ConsoleColor.ANSI_CYAN + " in the format (HH:mm)");
    color.printCyan("Make sure to select a time after the system clock ::");
  }

  /**
   * Prompts the user to enter the train number associated with the departure.
   *
   * @since 2.3.0
   */
  public void inputTrainNumber() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the train number")
        + ConsoleColor.ANSI_CYAN + " associated with the departure");
    color.printCyan("The train number must be unique and cannot be negative ::");
  }

  /**
   * Prompts the user to enter the train line for the train departure.
   *
   * @since 2.3.0
   */
  public void inputTrainLine() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the train line")
        + DEPARTURE);
    color.printCyan("A train line could be (RE12, L2, F3, etc.) ::");
  }

  /**
   * Prompts the user to enter the destination for the train departure.
   *
   * @since 2.3.0
   */
  public void inputDestination() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the destination")
        + DEPARTURE);
    color.printCyan("The destination must be a sequence of characters ::");
  }

  /**
   * Prompts the user to enter the delay in minutes.
   *
   * @since 2.3.0
   */
  public void inputDelay() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the delay")
        + ConsoleColor.ANSI_CYAN + " in minutes");
    color.printCyan("The delay must be a positive integer ::");
  }

  /**
   * Prompts the user to enter the track number for the train departure.
   *
   * @since 2.3.0
   */
  public void inputTrackNumber() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the track number")
        + DEPARTURE);
    color.printCyan("The track number must be a positive integer ::");
  }

  /**
   * Prompts the user to enter the train number they want to search for.
   *
   * @since 2.3.0
   */
  public void inputSearchTrainNumber() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the train number")
        + ConsoleColor.ANSI_CYAN + " you want to search for");
    color.printCyan("The train numbers are unique for this day ::");
  }

  /**
   * Prompts the user to enter the destination they want to search for.
   *
   * @since 2.3.0
   */
  public void inputSearchDestination() {
    color.printCyan(PLEASE_ENTER + color.printCyanBold("the destination")
        + ConsoleColor.ANSI_CYAN + " you want to search for");
    color.printCyan("The destination must be a sequence of characters ::");
  }

  /**
   * Asks the user if they want to add a new train departure.
   *
   * @since 2.3.0
   */
  public void askTrainDeparture() {
    color.printCyan("Do you want to add a new train departure?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to set a track number for the train departure.
   *
   * @since 2.3.0
   */
  public void askTrackNumber() {
    color.printCyan("Do you want to assign a track number?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to try again.
   *
   * @since 2.3.0
   */
  public void askTryAgain() {
    color.printCyan("Do you want to try again?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to continue.
   *
   * @since 2.3.0
   */
  public void askIfSure() {
    color.printCyan("Are you sure you want to continue?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to edit the selected train departure.
   *
   * @since 2.3.1
   */
  public void askEditThisDeparture() {
    color.printCyan("Do you want to edit this train departure?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to exit the application.
   *
   * @since 2.3.2
   */
  public void askExit() {
    color.printCyan("Are you sure you want to exit the application?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to start a new day.
   *
   * @param time The time that has passed.
   * @since 2.3.2
   */
  public void askNewDay(String time) {
    color.printCyan("The time " + color.printCyanBold(time) + ConsoleColor.ANSI_CYAN
        + " has passed. Do you want to start a new day?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Asks the user if they want to update the clock.
   *
   * @since 2.4.0
   */
  public void pressToContinue() {
    color.printWhite("\nPress " + color.printWhiteBold("enter")
        + ConsoleColor.ANSI_WHITE + " to continue...");
  }

  /**
   * Prints a message informing the user that the new time will be set to the time they entered.
   *
   * @param time The time that will be set.
   * @since 2.4.0
   */
  public void newTime(String time) {
    color.printCyan("The new time will be set to: " + color.printCyanBold(time));
  }

  /**
   * Prints a message informing the user that the current time is at the end of the day.
   *
   * @since 2.4.0
   */
  public void endOfDay() {
    color.printCyan("The time is at the end of the day.");
    color.printCyan("Do you want to start a new day?");
    color.printCyan(ASK_TO_CONTINUE);
  }

  /**
   * Prints a message informing the user that the new train departure has been added.
   *
   * @param message The message that will be printed as an error response.
   * @since 2.4.0
   */
  public void errorMessage(ErrorResponse message) {
    System.err.println(message.getResponse());
  }

  /**
   * Prints a message informing the user that the new train departure has been added.
   *
   * @param message The message that will be printed as a string.
   * @since 2.4.0
   */
  public void errorMessage(String message) {
    System.err.println(message);
  }
}
