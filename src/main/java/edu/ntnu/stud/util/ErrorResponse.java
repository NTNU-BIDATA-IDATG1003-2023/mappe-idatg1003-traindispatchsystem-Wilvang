package edu.ntnu.stud.util;

/**
 * This enum contains all the error messages that are printed to the user. The enum contains a
 * constructor that takes a string as a parameter. The string is the error message that is printed
 * to the user. The enum also contains a method that returns the error message.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.5
 * @since 2.4.0
 */

public enum ErrorResponse {
  EMPTY_STRING("The input provided was empty. Please try again."),
  EMPTY_ITERATOR("No train departures were found."
      + "\nPlease make sure to search for an existing train."),
  INVALID_ENTER("Please make sure to only press enter to continue."),
  INVALID_TIME("Please make sure to enter the time in a 24-hour clock format."),
  TIME_ALREADY_PASSED("The time you entered has already passed for this day."
      + "\nPlease try again with a time that is within this day."),
  INVALID_TRAIN_LINE("Please make sure to enter up to 4 characters."),
  INVALID_CHARACTERS("Please make sure to only enter characters in the alphabet."),
  CHARACTERS_AND_INTEGERS("Please make sure to only enter numbers and characters in the alphabet."),
  INVALID_ANSWER("Please make sure to only enter 'y' for yes or 'n' for no."),
  INVALID_OPTION("You chose an invalid option.\nPlease try again with one of the listed options."),
  INVALID_DELAY("Please make sure to enter a delay in minutes, that does not surpass a day."
      + " (<1440 minutes)"),
  INVALID_SEARCH("The train was not found.\nPlease make sure to enter a valid train number."),
  INVALID_INTEGER("Please make sure to enter a whole numbers."),
  INVALID_NUMBER_FORMAT("Please try a smaller whole number."),
  INVALID_NATURAL_NUMBER("Please make sure to enter a number greater than zero."),
  INVALID_TRAIN_NUMBER("Please make sure to enter a unique train number for the day."),
  INVALID_TRAIN("The train was not added to the station."
      + "\nPlease make sure to enter a valid train number."),
  INVALID_POSITIVE_INTEGER("Please make sure to enter a positive whole number.");
  private final String response;

  /**
   * Constructor for the enum ErrorResponse. Takes a string as a parameter.
   *
   * @param response The response to be printed to the user.
   * @since 2.4.0
   */
  ErrorResponse(String response) {
    this.response = response;
  }

  /**
   * Returns the response to be printed to the user.
   *
   * @return The response to be printed to the user.
   * @since 2.4.0
   */
  public String getResponse() {
    return response;
  }
}
