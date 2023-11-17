package edu.ntnu.stud.ui;


/**
 * The Selection class is used to store the different options in the application. The class contains
 * constants for the different options in the different menus in the application.
 *
 * @author Johan Fredrik Wilvang
 * @version 2.3.0
 * @since 2.1.1
 */

public class Selection {

  // main menu options
  public static final int INFORMATION_TABLE = 1;
  public static final int ADD_TRAIN = 2;
  public static final int EDIT_TRAIN = 3;
  public static final int ASSIGN_TRACK = 4;
  public static final int ADD_DELAY = 5;
  public static final int SEARCH_TRAIN = 6;
  public static final int UPDATE_CLOCK = 7;
  public static final int NEW_DAY = 8;
  public static final int EXIT = 9;
  // search menu options
  public static final int SEARCH_TRAIN_NUMBER = 1;
  public static final int SEARCH_DESTINATION = 2;
  public static final int RETURN_MAIN_MENU = 9;
  // edit menu options
  public static final int SET_DEPARTURE_TIME = 1;
  public static final int SET_TRAIN_NUMBER = 2;
  public static final int SET_TRAIN_LINE = 3;
  public static final int SET_DESTINATION = 4;
  public static final int SET_DELAY = 5;
  public static final int SET_TRACK_NUMBER = 6;
  public static final int SELECT_NEW_TRAIN = 7;

  private Selection() {
  }
}