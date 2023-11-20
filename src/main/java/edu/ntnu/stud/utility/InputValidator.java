package edu.ntnu.stud.utility;

import edu.ntnu.stud.ui.Print;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * The InputValidator class is used to validate the user input. The class contains methods to
 * validate different types of user inputs, such as integers, strings and 24-hour clock format.
 *
 * @author Johan Fredrik Wilvang
 * @version 2.4.0
 * @since 2.3.3
 */

public class InputValidator {

  private final Scanner inputReader;
  private final Print message;
  private boolean correctInput;

  /**
   * Constructor for the InputValidator class. Creates a validator object to validate the user
   * input. The constructor creates a scanner object to read the user input. The constructor also
   * initializes a check variable used to check if the user input is correct.
   *
   * @since 1.6.2
   */
  public InputValidator() {
    this.inputReader = new Scanner(System.in);
    this.message = new Print();
    this.correctInput = false;
  }

  /**
   * Returns the user input as a string. The method validates the user input to make sure that the
   * input is a sequence of characters. If the user input is not a sequence of characters, the user
   * will be prompted to enter a new input. The method will continue to prompt the user until the
   * format is correct.
   *
   * @return The user input as a string.
   * @since 2.3.0
   */
  public String validateString() {
    String word = "";
    correctInput = false;
    while (!correctInput) {
      if (!inputReader.hasNextInt()) {
        word = inputReader.nextLine();
        correctInput = true;
      } else {
        message.errorStringFormat();
        inputReader.next();
      }
    }
    return word;
  }

  /**
   * Returns the user input as an integer. The method validates the user input to make sure that the
   * input is a whole number. If the user input is not a whole number, the user will be prompted to
   * enter a new input. The method will continue to prompt the user until the format is correct.
   *
   * @return The user input as an integer.
   * @since 2.3.0
   */
  public int validateInteger() {
    correctInput = false;
    int number = 0;
    while (!correctInput) {
      if (inputReader.hasNextInt()) {
        number = inputReader.nextInt();
        inputReader.nextLine();
        correctInput = true;
      } else {
        message.errorIntegerFormat();
        inputReader.next();
      }
    }
    return number;
  }

  /**
   * Returns the user input as an integer. The method validates the user input to make sure that the
   * input is a positive number. If the user input is not a whole number, the user will be prompted
   * to enter a new input. The method will continue to prompt the user until the format is correct.
   *
   * @return The user input as an integer.
   * @since 2.3.3
   */
  public int validatePositiveInteger() {
    correctInput = false;
    int number = 0;
    while (!correctInput) {
      if (inputReader.hasNextInt()) {
        number = inputReader.nextInt();
        inputReader.nextLine();
        if (number > 0) {
          correctInput = true;
        } else {
          message.errorPositiveIntegerFormat();
        }
      } else {
        message.errorIntegerFormat();
        inputReader.next();
      }
    }
    return number;
  }

  /**
   * Returns the user input as a string. The method validates the user input to make sure that the
   * input is written in the 24-hour clock format. If the user input is not written in the correct
   * format, the user will be prompted to enter a new input. The method will continue to prompt the
   * user until the format is correct, unless the user writes the keyword 'q' to quit the action.
   *
   * @return The user input as a string.
   * @since 2.1.1
   */
  public String validateTimeOption() {
    correctInput = false;
    String time = "";
    while (!correctInput) {
      time = inputReader.nextLine();
      if (time.equalsIgnoreCase("q")) {
        correctInput = true;
      } else {
        try {
          LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
          correctInput = true;
        } catch (Exception e) {
          message.errorTimeFormat();
        }
      }
    }
    return time;
  }

  /**
   * Returns the user input as a string. The method validates the user input to make sure that the
   * input is written in the 24-hour clock format. If the user input is not written in the correct
   * format, the user will be prompted to enter a new input. The method will continue to prompt the
   * user until the format is correct.
   *
   * @return The user input as a string.
   * @since 2.1.1
   */
  public String validateTime() {
    correctInput = false;
    String time = "";
    while (!correctInput) {
      time = inputReader.nextLine();
      try {
        LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        correctInput = true;
      } catch (Exception e) {
        message.errorTimeFormat();
      }
    }
    return time;
  }

  /**
   * Returns the user input as a boolean. The method validates the user input to make sure that the
   * input is either 'y' for yes or 'n' for no. If the user input is not 'y' or 'n', the user will
   * be prompted to enter a new input. The method will continue to prompt the user until the format
   * is correct.
   *
   * @return The user input as a boolean.
   * @since 2.1.0
   */
  public boolean validateBoolean() {
    correctInput = false;
    String answer = "";
    while (!correctInput) {
      answer = inputReader.nextLine();
      if (answer.equalsIgnoreCase("y")
          || answer.equalsIgnoreCase("n")) {
        correctInput = true;
      } else {
        message.errorBooleanFormat();
      }
    }
    return answer.equalsIgnoreCase("y");
  }
}
