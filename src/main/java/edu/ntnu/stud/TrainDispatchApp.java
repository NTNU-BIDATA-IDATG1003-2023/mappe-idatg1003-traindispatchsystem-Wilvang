package edu.ntnu.stud;
import edu.ntnu.stud.transport.train.TrainDispatch;
import edu.ntnu.stud.ui.Menu;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  private static TrainDispatch trainDispatch = new TrainDispatch();
  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.init();
    menu.start();
  }
}