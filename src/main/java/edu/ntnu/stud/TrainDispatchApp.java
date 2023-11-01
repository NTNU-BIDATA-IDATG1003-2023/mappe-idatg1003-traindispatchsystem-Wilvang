package edu.ntnu.stud;

import edu.ntnu.stud.ui.*;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.init();
    menu.start();
  }
}