package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import edu.ntnu.stud.transport.train.TrainStation;
import edu.ntnu.stud.utility.ErrorResponse;
import java.time.LocalTime;
import java.util.Iterator;

/**
 * The Menu class is used to display menus to the user and communicates with the application. The
 * class contains an object of the TrainDispatcher class and an object of the Print class. The menu
 * is displayed to the user through the Print object. The menu is a texted based user interface
 * (TUI) that is displayed in the terminal. The menu is divided into different submenus that
 * manipulates the train register in the application.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.3
 * @since 2.4.0
 */

public class Menu {

  private final TrainDispatcher dispatcher;
  private final Print message;

  /**
   * Constructor for objects of class Menu. The constructor initializes an object of the
   * TrainDispatcher class and an object of the Print class. The constructor takes a TrainStation
   * object as a parameter.
   *
   * @since 2.4.0
   */
  public Menu(TrainStation station) {
    this.dispatcher = new TrainDispatcher(station);
    this.message = new Print();
  }

  /**
   * Initializes the application. The method prints the welcome message and sets the clock running
   * in the application.
   *
   * @since 2.0.0
   */
  public void init() {
    message.printStart();
    dispatcher.updateClock(message);
  }

  /**
   * Starts the application. The method displays the main menu and prompts the user to select an
   * option. The method will continue to display the main menu until the user selects the EXIT
   * option.
   *
   * @since 2.0.0
   */
  public void start() {
    int selectedOption = 0;
    while (selectedOption != Selection.EXIT) {
      selectedOption = this.dispatcher.selectMainMenuOption(message);
      mainMenu(selectedOption);
    }
  }

  /**
   * Displays the main menu to the user. The method prompts the user to select an option from the
   * main menu. The method will call the corresponding method based on the user input.
   *
   * @param select The option selected by the user.
   * @since 2.4.0
   */
  private void mainMenu(int select) {
    switch (select) {
      case Selection.INFORMATION_TABLE -> informationTableMenu();
      case Selection.ADD_TRAIN -> addTrainMenu();
      case Selection.REMOVE_TRAIN -> removeTrainMenu();
      case Selection.EDIT_TRAIN -> editTrainMenu();
      case Selection.SET_DELAY -> setDelayMenu();
      case Selection.ASSIGN_TRACK -> assignTrackMenu();
      case Selection.SEARCH_TRAIN -> searchMenu();
      case Selection.UPDATE_CLOCK -> updateClockMenu();
      case Selection.EXIT -> exitMenu();
      default -> {
        message.errorMessage(ErrorResponse.INVALID_OPTION);
        dispatcher.pressToContinue(message);
      }
    }
  }

  /**
   * Displays the menu for train information. The user can choose to display the train information
   * table or the train history. If the train history is empty, the method will display an error
   * message and prompt the user if they want to add a new train departure.
   */
  private void informationTableMenu() {
    if (dispatcher.getTrainHistory().hasNext()) {
      switch (dispatcher.selectInformationOption(message)) {
        case Selection.DISPLAY_TABLE -> displayTable();
        case Selection.DISPLAY_HISTORY -> displayHistory();
        case Selection.RETURN_MAIN_MENU -> message.returnToMainMenu();
        default -> {
          message.errorMessage(ErrorResponse.INVALID_OPTION);
          dispatcher.pressToContinue(message);
          informationTableMenu();
        }
      }
    } else if (dispatcher.askEmptyRegister(message)) {
      addTrainSubMenu();
    }
  }

  /**
   * Displays the information table to the user. If the train register is empty, the method will
   * display an error message and prompt the user an option to add a new train departure.
   *
   * @since 2.4.0
   */
  private void displayTable() {
    if (dispatcher.getTrainRegister().hasNext()) {
      message.printTrainInformationTable(this.dispatcher.displayClock(),
          dispatcher.getTrainRegister());
      dispatcher.pressToContinue(message);

    } else if (dispatcher.askEmptyRegister(message)) {
      addTrainSubMenu();
    }
  }

  /**
   * Displays the train history to the user.
   *
   * @since 2.4.0
   */
  private void displayHistory() {
    message.printTrainHistory(dispatcher.displayClock(), dispatcher.getTrainHistory());
    dispatcher.pressToContinue(message);
  }

  /**
   * Returns <code>true</code> if the train register is not empty, then displays the information
   * table to the user. If the train register is empty, returns <code>false</code>. The user will
   * then be prompted an option to add a new train departure.
   *
   * @return <code>true</code> if the train register is not empty, <code>false</code> if the train
   * register is empty.
   * @since 2.3.0
   */
  private boolean isInformationTableEmpty() {
    if (dispatcher.getTrainRegister().hasNext()) {
      message.printTrainInformationTable(this.dispatcher.displayClock(),
          dispatcher.getTrainRegister());

    } else if (dispatcher.askEmptyRegister(message)) {
      addTrainSubMenu();
    }
    return dispatcher.getTrainRegister().hasNext();
  }

  /**
   * Displays the menu for adding a new train departure to the user. The method prompts the user to
   * enter the train number, the train line, the destination, the departure time and the delay. If
   * the station clock is 23:59, the method will prompt the user to update the clock to a new day.
   *
   * @since 2.0.0
   */
  private void addTrainMenu() {
    if (!dispatcher.displayClock().equals(LocalTime.parse("23:59"))
        && dispatcher.selectAddTrainMenu(message)) {
      addTrainSubMenu();

    } else if (dispatcher.selectUpdateClockMenu(message)) {
      updateClockMenu();
    }
  }

  /**
   * The user will be prompted an option to assign a track number to the new train departure. If the
   * train departure is not successfully added, the method will prompt the user an option to try
   * again.
   *
   * @since 2.4.0
   */
  private void addTrainSubMenu() {
    int trainNumber = dispatcher.addTrainDeparture(message);
    if (dispatcher.searchByTrainNumber(trainNumber).hasNext()) {
      dispatcher.displayNewTrainDeparture(message, trainNumber);

      if (dispatcher.askTrackNumber(message)) {
        dispatcher.setTrackNumber(message, trainNumber);
      }
    } else if (dispatcher.askNewTrain(message)) {
      addTrainSubMenu();
    }
  }

  /**
   * Displays the train information table and prompts the user to select a train departure to
   * remove. If the train register is empty, the method will prompt the user an option to add a new
   * train departure.
   *
   * @since 2.4.0
   */
  private void removeTrainMenu() {
    if (dispatcher.getTrainRegister().hasNext()) {
      message.removeTrainDepartureOption();

      if (dispatcher.askToContinue(message)) {
        removeTrainSubMenu();
      }
    } else if (dispatcher.askEmptyRegister(message)) {
      addTrainSubMenu();
    }
  }

  /**
   * Displays the train information table to the user. The method prompts the user to enter the
   * train number of the train departure to be removed. If the selected train departure does not
   * exist, the method will display an error message and prompt the user to try again.
   *
   * @since 2.4.0
   */

  private void removeTrainSubMenu() {
    message.printTrainInformationTable(dispatcher.displayClock(),
        dispatcher.getTrainRegister());

    Iterator<TrainDeparture> trainIterator = dispatcher.searchByTrainNumber(message);
    if (trainIterator.hasNext()) {
      dispatcher.removeTrainDeparture(trainIterator.next().getTrainNumber());

    } else if (dispatcher.askAgainEmptyIterator(message)) {
      removeTrainMenu();
    }
  }

  /**
   * Displays the menu for editing a train departure to the user. The method prompts the user to
   * enter the train number of the train departure to edit. If the train register is empty, the
   * method will prompt the user an option to add a new train departure.
   *
   * @since 2.2.0
   */
  private void editTrainMenu() {
    if (isInformationTableEmpty()) {
      Iterator<TrainDeparture> trainIterator = dispatcher.searchByTrainNumber(message);

      if (trainIterator.hasNext()) {
        editTrainSubMenu(trainIterator);

      } else if (dispatcher.askAgainEmptyIterator(message)) {
        editTrainMenu();
      }
    }
  }

  /**
   * Displays the submenu for editing a train departure to the user. The method will call the
   * corresponding method based on the selected option. The method will continue to display the
   * submenu until the user selects the RETURN_TO_MAIN_MENU option. To edit another train departure,
   * the user has to select the SELECT_NEW_TRAIN option.
   *
   * @param trainIterator The iterator containing the train departure to edit.
   * @since 2.3.2
   */
  private void editTrainSubMenu(Iterator<TrainDeparture> trainIterator) {
    int trainNumber = trainIterator.next().getTrainNumber();
    switch (dispatcher.selectEditOption(message, trainNumber)) {
      case Selection.SET_DEPARTURE_TIME ->
          editTrainSubMenu(dispatcher.setDepartureTime(message, trainNumber));
      case Selection.SET_TRAIN_LINE ->
          editTrainSubMenu(dispatcher.setTrainLine(message, trainNumber));
      case Selection.SET_TRAIN_NUMBER ->
          editTrainSubMenu(dispatcher.setTrainNumber(message, trainNumber));
      case Selection.SET_DESTINATION ->
          editTrainSubMenu(dispatcher.setDestination(message, trainNumber));
      case Selection.SET_DELAY -> editTrainSubMenu(dispatcher.setDelay(message, trainNumber));
      case Selection.SET_TRACK_NUMBER ->
          editTrainSubMenu(dispatcher.setTrackNumber(message, trainNumber));
      case Selection.SELECT_NEW_TRAIN -> editTrainMenu();
      case Selection.RETURN_MAIN_MENU -> message.returnToMainMenu();
      default -> {
        message.errorMessage(ErrorResponse.INVALID_OPTION);
        dispatcher.pressToContinue(message);
        editTrainSubMenu(dispatcher.searchByTrainNumber(trainNumber));
      }
    }
  }

  /**
   * Displays the menu for setting a delay to a specified train departure. The method prompts the
   * user to enter the train number of the train departure to add a delay to. The specified delay
   * will be added to the train departure. If the train register is empty, the method will prompt
   * the user an option to add a new train departure.
   *
   * @since 2.4.0
   */
  private void setDelayMenu() {
    if (isInformationTableEmpty()) {
      Iterator<TrainDeparture> trainIterator = dispatcher.searchByTrainNumber(message);

      if (trainIterator.hasNext()) {
        dispatcher.setDelay(message, trainIterator.next().getTrainNumber());

      } else if (dispatcher.askAgainEmptyIterator(message)) {
        setDelayMenu();
      }
    }
  }

  /**
   * Displays the menu for assigning a track number to the user. The method prompts the user to
   * select an option from the assign track menu. The method will call the corresponding method
   * based on the user input. If the train register is empty, the method will prompt the user an
   * option to add a new train departure.
   *
   * @since 2.2.0
   */
  private void assignTrackMenu() {
    if (isInformationTableEmpty()) {
      Iterator<TrainDeparture> trainIterator = dispatcher.searchByTrainNumber(message);

      if (trainIterator.hasNext()) {
        dispatcher.setTrackNumber(message, trainIterator.next().getTrainNumber());

      } else if (dispatcher.askAgainEmptyIterator(message)) {
        assignTrackMenu();
      }
    }
  }


  /**
   * Displays the search menu to the user. The method prompts the user to select an option from the
   * search menu. The method will call the corresponding method based on the user input. If the
   * train history is empty, the method will display an error message and prompt the user if they
   * want to add a new train departure.
   *
   * @since 2.4.0
   */
  private void searchMenu() {
    if (dispatcher.getTrainHistory().hasNext()) {
      switch (dispatcher.selectSearchOption(message)) {
        case Selection.SEARCH_TRAIN_NUMBER -> searchByTrainNumberMenu();
        case Selection.SEARCH_DESTINATION -> searchByDestinationMenu();
        case Selection.RETURN_MAIN_MENU -> message.returnToMainMenu();
        default -> {
          message.errorMessage(ErrorResponse.INVALID_OPTION);
          dispatcher.pressToContinue(message);
          searchMenu();
        }
      }
    } else if (dispatcher.askEmptyRegister(message)) {
      addTrainSubMenu();
    }
  }

  /**
   * Displays the train history to the user. The method prompts the user to enter the train number
   * of the train departure to display. The user can choose to edit the selected train departure. If
   * the train history is empty, the method will prompt the user an option to add a new train
   * departure.
   *
   * @since 2.4.0
   */
  private void searchByTrainNumberMenu() {
    message.printTrainInformationTable(dispatcher.displayClock(),
        dispatcher.getTrainHistory());

    Iterator<TrainDeparture> withTrainNumber = dispatcher.searchByTrainNumber(message);
    if (withTrainNumber.hasNext()) {
      int trainNumber = withTrainNumber.next().getTrainNumber();
      message.printSelectedTrain(dispatcher.displayClock(),
          dispatcher.searchByTrainNumber(trainNumber));

      if (dispatcher.askIfEdit(message)) {
        editTrainSubMenu(dispatcher.searchByTrainNumber(trainNumber));
      }
    } else if (dispatcher.askAgainEmptyIterator(message)) {
      searchByTrainNumberMenu();
    }
  }

  /**
   * Displays the train history to the user. The method prompts the user to enter the destination of
   * the train departure to display. If there are no train departures headed to the destination, the
   * user will be prompted to try a new destination.
   *
   * @since 2.4.0
   */
  private void searchByDestinationMenu() {
    message.printTrainInformationTable(dispatcher.displayClock(), dispatcher.getTrainHistory());
    Iterator<TrainDeparture> withDestination = dispatcher.searchByDestination(message);

    if (withDestination.hasNext()) {
      message.printSelectedTrain(dispatcher.displayClock(), withDestination);
      dispatcher.pressToContinue(message);

    } else if (dispatcher.askAgainEmptyIterator(message)) {
      searchByDestinationMenu();
    }
  }

  /**
   * Displays the menu for updating the clock to the user. The method prompts the user to enter the
   * new time for the application. The train departures that has departed will not be shown in the
   * train information table. If the user enters a time that is before the current time, the user
   * can choose to start a new day.
   *
   * @since 2.4.0
   */
  private void updateClockMenu() {
    message.updateClockOption();
    String time = dispatcher.updateClock(message);
    if (!time.equalsIgnoreCase("q")
        && (LocalTime.parse(time).isBefore(dispatcher.displayClock())
        && dispatcher.askNewDay(message, time))) {
      newDay(time);
    }
  }

  /**
   * Displays the menu for starting a new day to the user. The user has to confirm that they want to
   * start a new day before the method will continue. If the user confirms, the train register will
   * be reset and the clock will be updated to the new day.
   *
   * @since 2.4.0
   */
  private void newDay(String time) {
    message.newDayOption();
    if (dispatcher.askToContinue(message, time)) {
      dispatcher.resetStation();
      dispatcher.updateClock(time);
    }
  }

  /**
   * Displays the menu for exiting the application to the user. The method prompts the user to
   * confirm that they want to exit the application. If the user does not confirm that they want to
   * exit the application, the method will return to the main menu.
   *
   * @since 2.3.2
   */
  private void exitMenu() {
    if (dispatcher.askIfExit(message)) {
      message.printExit();
    } else {
      start();
    }
  }
}

