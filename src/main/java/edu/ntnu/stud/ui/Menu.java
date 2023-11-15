package edu.ntnu.stud.ui;


/**
 * The Menu class is used to display menus to the user. The class contains methods to print
 * different menus, such as the main menu and the search menu. The class has the methods to start
 * the application and to display the main menu.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 2.0.0
 * @since 2.0.0
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
    while (selectedOption != 9) {
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
   * @since 2.0.0
   */
  public void mainMenu(int select) {
    switch (select) {
      case Selection.INFORMATION_TABLE:
        // print overview
        break;
      case Selection.ADD_TRAIN:
        // add new train departure
        message.addTrainDepartureOption();
        newTrainMenu();
        break;
      case Selection.ASSIGN_TRACK:
        // assign track
        break;
      case Selection.ADD_DELAY:
        // add delay
        break;
      case Selection.SEARCH_TRAIN:
        // search for train (train number or destination)
        searchMenu();
        break;
      case Selection.UPDATE_CLOCK:
        // update clock
        message.updateClockOption();
        option.updateClock();
        break;
      case Selection.EXIT:
        // exit
        break;
      default:
        message.printInvalidOption();
    }
  }

  /**
   * Displays the search menu to the user. The method prompts the user to select an option from the
   * search menu. The method will call the corresponding method based on the user input.
   *
   * @since 2.0.0
   */
  public void searchMenu(){
    this.message.printStatusBar(this.option.displayClock(),
        this.option.numberOfTrainDepartures());
    this.message.printSearchMenu();
    switch(this.option.selectOption()) {
      case Selection.SEARCH_TRAIN_NUMBER:
        // search for train by train number
        break;
      case Selection.SEARCH_DESTINATION:
        // search for train by destination)
        break;
      case Selection.RETURN_MAIN_MENU:
        // exit
        break;
      default:
        this.message.printInvalidOption();
        searchMenu();
    }
  }

  /**
   * Displays the menu for adding a new train departure to the user. The method prompts the user to
   * enter the train number, the train line, the destination, the departure time and the delay. The
   * method will call the corresponding method based on the user input.
   *
   * @since 2.0.0
   */
  public void newTrainMenu(){
    message.addTrainDepartureOption();
    if(!option.addTrainDeparture()){
      message.print("Train departure was added successfully");
    } else {
      message.invalidTrainDeparture();
    }
  }
}

