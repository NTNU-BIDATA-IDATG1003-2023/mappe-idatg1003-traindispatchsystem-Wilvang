package edu.ntnu.stud;

import edu.ntnu.stud.transport.train.TrainStation;
import edu.ntnu.stud.ui.Menu;


/**
 * This is the main class for the train dispatch application. The main class has two methods, init()
 * and start(). These methods are called to initialize the application and start the program. The
 * manu is final since
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.0
 * @since 2.4.0
 */

public class TrainDispatchApp {

  private static final TrainStation station = new TrainStation();

  /**
   * This method is called to initialize the application. It creates a new TrainStation object and
   * runs the application.
   *
   * @param args The arguments passed to the application.
   * @since 2.4.0
   */
  public static void main(String[] args) {
    Menu menu = new Menu(station);
    menu.init();
    menu.start();
  }
}