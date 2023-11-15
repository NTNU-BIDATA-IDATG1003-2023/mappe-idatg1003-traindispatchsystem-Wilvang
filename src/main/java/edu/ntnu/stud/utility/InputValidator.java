package edu.ntnu.stud.utility;

import edu.ntnu.stud.ui.ColorPrint;
import java.time.LocalTime;
import java.util.Scanner;


/**
 * The InputValidator class is used to validate the user input. The class contains methods to
 * validate different types of user inputs, such as integers, strings and 24-hour clock format.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.9.0
 * @since 1.8.0
 */

public class InputValidator {
  private final Scanner inputReader;
  private boolean correctInput;

  /**
   * Constructor for the InputValidator class. Creates a validator object to validate the user
   * input. The constructor creates a scanner object to read the user input. The constructor also
   * initializes a check variable used to check if the user input is correct.
   *
   * @since 1.8.0
   */
  public InputValidator(){
    this.inputReader = new Scanner(System.in);
    this.correctInput = false;
  }

  /**
   * Returns the user input as a string. The method validates the user input to make sure that the
   * input is a sequence of characters. If the user input is not a sequence of characters, the user
   * will be prompted to enter a new input. The method will continue to prompt the user until the
   * format is correct.
   *
   *
   * @param typeOfInput The type of input that the user is prompted to enter.
   * @return The user input as a string.
   * @since 1.8.0
   */
  public String inputString(String typeOfInput){
    String inputString = "";
    correctInput = false;
    System.out.println(ColorPrint.ANSI_CYAN + "Please input the " + typeOfInput + " ::");
    while (!correctInput) {
      if(!inputReader.hasNextInt()) {
        inputString = inputReader.nextLine();
        correctInput = true;
      } else {
        System.err.println("The format provided was not accepted."
            + "\nPlease make sure to enter a sequence of characters ::");
        inputReader.next();
      }
    }
    return inputString;
  }

  /**
   * Returns the user input as an integer. The method validates the user input to make sure that the
   * input is a whole number. If the user input is not a whole number, the user will be prompted to
   * enter a new input. The method will continue to prompt the user until the format is correct.
   *
   * @param typeOfInput The type of input that the user is prompted to enter.
   * @return The user input as an integer.
   * @since 1.8.0
   */
  public int inputInteger(String typeOfInput){
    correctInput  = false;
    int inputInteger = 0;
    System.out.println(ColorPrint.ANSI_CYAN + "Please input the " + typeOfInput + " ::");
    while (!correctInput) {
      if (inputReader.hasNextInt()){
        inputInteger = inputReader.nextInt();
        inputReader.nextLine();
        correctInput = true;
      } else {
        System.err.println("The format provided was not accepted."
            + "\nPlease make sure to enter a whole number ::");
        inputReader.next();
      }
    }
    return inputInteger;
  }

  /**
   * Returns the user input as a string. The method validates the user input to make sure that the
   * input is written in the 24-hour clock format. If the user input is not written in the correct
   * format, the user will be prompted to enter a new input. The method will continue to prompt the
   * user until the format is correct, unless the user writes the keyword 'q' to quit the action.
   *
   * @return The user input as a string.
   * @since 1.8.0
   */
  public String validateTime(){
    correctInput = false;
    String time = "";
    System.out.println(ColorPrint.ANSI_CYAN +
        "Please enter what time it is. Use the format (hh:mm)"
        + "\nYou can press 'q' if you want to to quit this action ::");
    while (!correctInput) {
      time = inputReader.nextLine();
      if (time.equals("q")){
        correctInput = true;
      } else {
        try {
          LocalTime.parse(time);
          correctInput = true;
        } catch (Exception e) {
          System.err.println("The format provided was not accepted. Press 'q' to quit this action."
              + "\nPlease make sure to use the 24-hour clock format (hh:mm) ::");
        }
      }
    }
    return time;
  }
}
