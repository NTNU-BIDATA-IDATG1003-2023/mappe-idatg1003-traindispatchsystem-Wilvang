package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import java.util.Iterator;

/**
 * The Menu class is used to display menus to the user. The class contains methods to print
 * different menus, such as the main menu and the search menu. The class has the methods to start
 * the application and to display the main menu.
 *
 * @author Johan Fredrik Wilvang
 * @version 2.4.0
 * @since 2.4.0
 */

public class Menu {

  private final Option option;
  private final Print message;

  /**
   * Constructor for objects of class Menu. The constructor initializes an object of class Option
   * and an object of class Print.
   *
   * @since 2.0.0
   */
  public Menu() {
    this.option = new Option();
    this.message = new Print();
  }

  /**
   * Initializes the application. The method prints the welcome message and sets the clock running
   * in the application.
   *
   * @since 2.0.0
   */
  public void init() {
    option.initTrainDepartures();
    message.printWelcome();
    option.updateClock();
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
      this.message.printStatusBar(this.option.displayClock(),
          this.option.numberOfTrainDepartures());
      this.message.printMainManu();
      selectedOption = this.option.selectOption();
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
  public void mainMenu(int select) {
    switch (select) {
      case Selection.INFORMATION_TABLE:
        informationTableMenu();
        break;
      case Selection.ADD_TRAIN:
        addTrainMenu();
        break;
      case Selection.REMOVE_TRAIN:
        removeTrainMenu();
        break;
      case Selection.EDIT_TRAIN:
        editTrainMenu();
        break;
      case Selection.SET_DELAY:
        setDelayMenu();
        break;
      case Selection.ASSIGN_TRACK:
        assignTrackMenu();
        break;
      case Selection.SEARCH_TRAIN:
        searchMenu();
        break;
      case Selection.UPDATE_CLOCK:
        updateClockMenu();
        break;
      case Selection.EXIT:
        exitMenu();
        break;
      default:
        message.errorOption();
    }
  }

  /**
   * Displays the search menu to the user. The method prompts the user to select an option from the
   * search menu. The method will call the corresponding method based on the user input.
   *
   * @since 2.0.0
   */
  public void searchMenu() {
    this.message.printStatusBar(this.option.displayClock(),
        this.option.numberOfTrainDepartures());
    this.message.printSearchMenu();
    switch (this.option.selectOption()) {
      case Selection.SEARCH_TRAIN_NUMBER:
        int trainNumber = option.searchByTrainNumber().next().getTrainNumber();
        message.printSelectedTrain(option.searchByTrainNumber(trainNumber));
        message.askEditThisDeparture();
        if (option.askToContinue()) {
          editTrainSubmenu(option.searchByTrainNumber(trainNumber));
        }
        break;
      case Selection.SEARCH_DESTINATION:
        message.printSelectedTrain(option.searchByDestination());
        break;
      case Selection.RETURN_MAIN_MENU:
        break;
      default:
        this.message.errorOption();
        searchMenu();
    }
  }

  /**
   * Displays the menu for assigning a track number to the user. The method prompts the user to
   * select an option from the assign track menu. The method will call the corresponding method
   * based on the user input.
   *
   * @since 2.2.0
   */
  public void assignTrackMenu() {
    message.printTrainInformationTable(option.getTrainRegister());
    option.setTrackNumber(option.searchByTrainNumber().next().getTrainNumber());
  }

  /**
   * Displays the menu for editing a train departure to the user. The method prompts the user to
   * enter the train number of the train departure to edit. If a train departure is selected, the
   * method will open the submenu. If no train departure is selected, the method will display an
   * error message and prompt the user to try again.
   *
   * @since 2.2.0
   */
  public void editTrainMenu() {
    message.printTrainInformationTable(option.getTrainRegister());
    Iterator<TrainDeparture> trainIterator = option.searchByTrainNumber();
    if (trainIterator.hasNext()) {
      editTrainSubmenu(trainIterator);
    } else {
      message.errorEmptyIterator();
      message.askTrainDeparture();
      if (option.askToContinue()) {
        addTrainMenu();
      }
    }
  }

  /**
   * Displays the submenu for editing a train departure to the user. The method will call the
   * corresponding method based on the selected option. The method will continue to display the
   * submenu until the user selects the RETURN_TO_MAIN_MENU option.
   *
   * @param trainIterator The iterator containing the train departure to edit.
   * @since 2.3.2
   */
  public void editTrainSubmenu(Iterator<TrainDeparture> trainIterator) {
    int trainNumber = trainIterator.next().getTrainNumber();
    this.message.printSelectedTrain(option.searchByTrainNumber(trainNumber));
    this.message.printStatusBar(this.option.displayClock(),
        this.option.numberOfTrainDepartures());
    this.message.printEditTrainMenu();
    switch (this.option.selectOption()) {
      case Selection.SET_DEPARTURE_TIME:
        trainIterator = this.option.setDepartureTime(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SET_TRAIN_NUMBER:
        trainIterator = option.setTrainNumber(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SET_TRAIN_LINE:
        trainIterator = this.option.setTrainLine(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SET_DESTINATION:
        trainIterator = this.option.setDestination(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SET_DELAY:
        trainIterator = this.option.setDelay(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SET_TRACK_NUMBER:
        trainIterator = this.option.setTrackNumber(trainNumber);
        editTrainSubmenu(trainIterator);
        break;
      case Selection.SELECT_NEW_TRAIN:
        editTrainMenu();
        break;
      case Selection.RETURN_MAIN_MENU:
        break;
      default:
        this.message.errorOption();
        editTrainSubmenu(option.searchByTrainNumber(trainNumber));
    }
  }

  /**
   * Displays the menu for adding a new train departure to the user. The method prompts the user to
   * enter the train number, the train line, the destination, the departure time and the delay. The
   * method will call the corresponding method based on the user input.
   *
   * @since 2.0.0
   */
  public void addTrainMenu() {
    message.addTrainDepartureOption();
    message.askTrainDeparture();
    if (option.askToContinue()) {
      addTrainSubmenu();
    }
  }

  /**
   * Displays the submenu for adding a new train departure to the user. The method prompts the user
   * to enter the train number, the train line, the destination, the departure time and the delay.
   * If the train departure is successfully added, the method will prompt the user an option to
   * assign a track number to the train departure. If the train departure is not successfully added,
   * the method will prompt the user an option to try again.
   *
   * @since 2.1.0
   */
  public void addTrainSubmenu() {
    message.printTrainInformationTable(option.getTrainRegister());
    message.printStatusBar(option.displayClock(), option.numberOfTrainDepartures());
    message.printSeparator();
    int before = option.numberOfTrainDepartures();
    int trainNumber = option.addTrainDeparture();
    int after = option.numberOfTrainDepartures();
    if (before < after) {
      message.printAddedTrain(option.searchByTrainNumber(trainNumber));
      message.askTrackNumber();
      if (option.askToContinue()) {
        option.setTrackNumber(trainNumber);
      }
    } else {
      message.errorCreateTrain();
      message.printSeparator();
      message.askTryAgain();
      if (option.askToContinue()) {
        addTrainSubmenu();
      }
    }
  }

  /**
   * Displays the train information table to the user. The method prompts the user to enter the
   * train number of the train departure to be removed. If the selected train departure does not
   * exist, the method will display an error message and prompt the user to try again.
   *
   * @since 2.4.0
   */
  public void removeTrainMenu() {
    message.printTrainInformationTable(option.getTrainRegister());
    removeTrainSubMenu();
  }

  /**
   * Displays the submenu for removing a train departure to the user. The method prompts the user to
   * enter the train number of the train departure to be removed. If the selected train departure
   * does not exist, the method will display an error message and prompt the user to try again.
   *
   * @since 2.4.0
   */
  public void removeTrainSubMenu() {
    Iterator<TrainDeparture> trainIterator =  option.searchByTrainNumber();
    if (trainIterator.hasNext()) {
      option.removeTrainDeparture(trainIterator.next().getTrainNumber());
    } else {
      message.errorEmptyIterator();
      message.printSeparator();
      message.askTryAgain();
      if (option.askToContinue()) {
        removeTrainSubMenu();
      }
    }
  }

  /**
   * Displays the information table to the user. If the train register is empty, the method will
   * display an error message and prompt the user if they want to add a new train departure.
   *
   * @since 2.3.0
   */
  public void informationTableMenu() {
    if (option.getTrainRegister().hasNext()) {
      message.printTrainInformationTable(option.getTrainRegister());
    } else {
      message.errorEmptyIterator();
      if (option.askToContinue()) {
        addTrainMenu();
      }
    }
  }

  /**
   * Displays the menu for adding a delay to a specified train departure. The method prompts the
   * user to enter the train number of the train departure to add a delay to. The specified delay
   * will be added to the train departure.
   *
   * @since 2.3.0
   */
  public void setDelayMenu() {
    message.printTrainInformationTable(option.getTrainRegister());
    setDelaySubMenu();
  }

  /**
   * Displays the submenu for adding a delay to a specified train departure. The method prompts the
   * user to enter the train number of the train departure to add a delay to. The specified delay
   * will be added to the train departure.
   *
   * @since 2.4.0
   */
  public void setDelaySubMenu() {
    Iterator<TrainDeparture> trainIterator = option.searchByTrainNumber();
    if (trainIterator.hasNext()) {
      int trainNumber = trainIterator.next().getTrainNumber();
      option.setDelay(trainNumber);
    } else {
      message.errorEmptyIterator();
      message.printSeparator();
      message.askTryAgain();
      if (option.askToContinue()) {
        setDelaySubMenu();
      }
      setDelaySubMenu();
    }
  }

  /**
   * Displays the menu for updating the clock to the user. The method prompts the user to enter the
   * new time. The train departures that has departed will be removed from the train register.
   *
   * @since 2.4.0
   */
  public void updateClockMenu() {
    message.updateClockOption();
    if (option.updateClock()) {
      message.printSeparator();
      message.askNewDay();
      if (option.askToContinue()) {
        newDay();
      }
    }
  }

  /**
   * Displays the menu for starting a new day to the user. The method prompts the user to enter the
   * time for the new day. The train departures that has departed will be removed from the train
   * register. The user has to confirm that they want to start a new day before the method will
   * continue.
   *
   * @since 2.4.0
   */
  public void newDay() {
    message.newDayOption();
    message.askIfSure();
    if (option.askToContinue()) {
      option.resetClock();
      option.updateClock();
    }
  }

  /**
   * Displays the menu for exiting the application to the user. The method prompts the user to
   * confirm that they want to exit the application. If the user does not confirm that they want
   * to exit the application, the method will return to the main menu.
   *
   * @since 2.3.2
   */
  public void exitMenu() {
    message.exitOption();
    message.askExit();
    if (option.askToContinue()) {
      message.printExit();
    } else {
      start();
    }
  }
}

