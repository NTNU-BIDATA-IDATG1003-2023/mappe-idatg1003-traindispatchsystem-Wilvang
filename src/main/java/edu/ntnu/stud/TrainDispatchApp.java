package edu.ntnu.stud;

import edu.ntnu.stud.ui.Menu;

/**
 * This is the main class for the train dispatch application. The main class has two methods, init()
 * and start(). These methods are called to initialize the application and start the program. The
 * manu is final since
 *
 * @author Johan Fredrik Wilvang
 * @version 2.2.0
 * @since 1.3.0
 */
public class TrainDispatchApp {

  /**
   * The main method for the train dispatch application. This method creates an object of the menu
   * class and calls the init() and start() methods to run the program.
   *
   * @param args the command line parameters.
   */

  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.init();
    menu.start();
  }
}

