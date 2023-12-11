package edu.ntnu.stud.util;

import edu.ntnu.stud.transport.TrainStation;
import edu.ntnu.stud.ui.Print;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * The InputValidator class is used to validate the input from a user. The class contains methods to
 * validate different types of user inputs, such as integers and strings in different formats. The
 * class also contains methods to validate the user input for different actions, such as adding a
 * train or searching for a train. The class uses the Scanner class to read the user input. The
 * class also contains a boolean variable to check if the user input is correct.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.4
 * @since 3.0.0
 */

public class InputValidator {

  private final Scanner inputReader;
  private boolean correctInput;
  private String response;

  /**
   * The constructor for the InputValidator class creates a validator object. The validator object
   * initializes the Scanner object and sets the response variable to an empty string. The
   * correctInput variable is set to false.
   *
   * @since 1.6.2
   */
  public InputValidator() {
    this.inputReader = new Scanner(System.in);
    this.response = "";
    this.correctInput = false;
  }

  /**
   * Validates the user input to make sure that the input is a string, containing only letters from
   * the norwegian alphabet. If the user input is not in the correct format, the method will throw
   * an InputMismatchException. If the user input is empty, the method will throw a
   * NullPointerException.
   *
   * @since 3.0.0
   */
  private void validateString() {
    if (!response.matches("[a-zA-ZæøåÆØÅ]+")) {
      throw new InputMismatchException(ErrorResponse.INVALID_CHARACTERS.getResponse());
    } else if (response.isEmpty()) {
      throw new NullPointerException(ErrorResponse.EMPTY_STRING.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is an integer. If the user input is not in
   * the correct format, the method will throw an InputMismatchException. If the user input is
   * empty, the method will throw a NullPointerException.
   *
   * @since 3.0.0
   */
  private void validateInteger() {
    if (!response.matches("\\d+")) {
      throw new InputMismatchException(ErrorResponse.INVALID_INTEGER.getResponse());
    } else if (response.isEmpty()) {
      throw new NullPointerException(ErrorResponse.EMPTY_STRING.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a positive integer. If the user input
   * is not in the correct format, the method will throw an InputMismatchException. If the user
   * input is empty, the method will throw a NullPointerException.
   *
   * @since 3.0.0
   */
  private void validatePositiveInteger() {
    validateInteger();
    if (Integer.parseInt(response) < 0) {
      throw new InputMismatchException(ErrorResponse.INVALID_POSITIVE_INTEGER.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid train number. If the user input
   * is not in the correct format, the method will throw an InputMismatchException. If the user
   * input is empty, the method will throw a NullPointerException. If the train number is not is
   * already in use, the method will throw an InputMismatchException.
   *
   * @param station An object of the TrainStation class.
   * @since 3.0.0
   */
  private void validateTrainNumber(TrainStation station) {
    validateInteger();
    if (Integer.parseInt(response) <= 0) {
      throw new InputMismatchException(ErrorResponse.INVALID_NATURAL_NUMBER.getResponse());
    } else if (!station.isTrainNumberUnique(Integer.parseInt(response))) {
      throw new InputMismatchException(ErrorResponse.INVALID_TRAIN_NUMBER.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid delay. If the user input is not
   * in the correct format, the method will throw an InputMismatchException. If the user input is
   * empty, the method will throw a NullPointerException. If the delay is greater than or equal to
   * 1440 minutes (24 hours), the method will throw an InputMismatchException.
   *
   * @since 3.0.0
   */
  private void validateDelay() {
    validatePositiveInteger();
    if (Integer.parseInt(response) >= 1440) {
      throw new InputMismatchException(ErrorResponse.INVALID_DELAY.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid train line. If the user input
   * is not in the correct format, the method will throw an InputMismatchException. If the user
   * input is empty, the method will throw a NullPointerException. If the input is longer than 4
   * characters, the method will throw an InputMismatchException.
   *
   * @since 3.0.0
   */
  private void validateTrainLine() {
    if (!response.matches("[a-zA-Z0-9æøåÆØÅ]+")) {
      throw new InputMismatchException(ErrorResponse.CHARACTERS_AND_INTEGERS.getResponse());
    } else if (response.isEmpty()) {
      throw new NullPointerException(ErrorResponse.EMPTY_STRING.getResponse());
    } else if (response.length() >= 5) {
      throw new InputMismatchException(ErrorResponse.INVALID_TRAIN_LINE.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid destination. If the user input
   * is not in the correct format, the method will throw an InputMismatchException. If the user
   * input is empty, the method will throw a NullPointerException. If the input is longer than 15
   * characters, the input will be shortened to 15 characters.
   *
   * @param print An object of the Print class.
   * @return The validated destination.
   * @since 3.0.0
   */
  private String validateDestination(Print print) {
    inputString(print);
    if (response.length() > 15) {
      response = response.substring(0, 14) + ".";
    }
    return response;
  }

  /**
   * Validates the user input to make sure that the input is a valid time. If the user input is not
   * in the correct format, the method will throw an InputMismatchException. If the user input is
   * empty, the method will throw a NullPointerException.
   *
   * @since 3.0.0
   */
  private void validateTime() {
    if (!response.matches("\\d{2}:\\d{2}")) {
      throw new InputMismatchException(ErrorResponse.INVALID_TIME.getResponse());
    } else if (response.isEmpty()) {
      throw new NullPointerException(ErrorResponse.EMPTY_STRING.getResponse());
    } else if (Integer.parseInt(response.substring(0, 2)) > 23 || response.length() > 5) {
      throw new InputMismatchException(ErrorResponse.INVALID_TIME.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid station time. The user can quit
   * the program by typing 'q'. If the user input is not in the correct format, the method will
   * throw an InputMismatchException. If the user input is empty, the method will throw a
   * NullPointerException.
   *
   * @since 3.0.0
   */
  private void validateStationTime() {
    if (!response.equalsIgnoreCase("q")) {
      validateTime();
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid departure time. If the user
   * input is not in the correct format, the method will throw an InputMismatchException. If the
   * user input is empty, the method will throw a NullPointerException. If the departure time is
   * before the station time, the method will also throw an InputMismatchException.
   *
   * @param station An object of the TrainStation class.
   * @since 3.0.0
   */
  private void validateDepartureTime(TrainStation station) {
    validateTime();
    if (LocalTime.parse(response).isBefore(station.getStationClock())
        || LocalTime.parse(response).equals(station.getStationClock())) {
      throw new InputMismatchException(ErrorResponse.TIME_ALREADY_PASSED.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid answer. A valid answer is 'y'
   * or 'n'. If the user input in not in the correct format, the method will throw an
   * InputMismatchException. If the user input is empty, the method will throw a
   * NullPointerException.
   *
   * @since 3.0.0
   */
  private void validateAnswer() {
    if (!response.matches("[ynYN]") || response.length() != 1) {
      throw new InputMismatchException(ErrorResponse.INVALID_ANSWER.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the user pressed enter. If the user input is not in
   * the correct format, the method will throw an InputMismatchException.
   *
   * @since 3.0.0
   */
  private void validateEnter() {
    if (!response.isEmpty()) {
      throw new InputMismatchException(ErrorResponse.INVALID_ENTER.getResponse());
    }
  }

  /**
   * Validates the user input to make sure that the input is a valid train number. If the user input
   * is not in the correct format or train number is already in use, the method will throw an
   * InputMismatchException. If the user input is empty, the method will throw a
   * NullPointerException.
   *
   * @param station An object of the TrainStation class.
   * @since 3.0.0
   */
  private void validateSearchTrainNumber(TrainStation station) {
    validateInteger();
    if (station.isTrainNumberUnique(Integer.parseInt(response))) {
      throw new InputMismatchException(ErrorResponse.INVALID_SEARCH.getResponse());
    } else if (response.isEmpty()) {
      throw new NullPointerException(ErrorResponse.EMPTY_STRING.getResponse());
    }
  }

  /**
   * The user is prompted to enter the station time. The input is validated to make sure the format
   * is correct.  The user will be prompted to enter a new time until the input is correct. The user
   * can quit the program by typing 'q'. The method returns the validated station time.
   *
   * @param print An object of the Print class.
   * @return The validated station time.
   * @since 3.0.0
   */
  public String inputStationTime(Print print) {
    print.inputTime();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateStationTime();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      }
    }
    return response;
  }

  /**
   * The user is prompted to enter the departure time. The input is validated to make sure the
   * format is correct. The user will be prompted to enter a new time until the input is correct.
   * The method returns the validated departure time.
   *
   * @param print   An object of the Print class.
   * @param station An object of the TrainStation class.
   * @return The validated departure time.
   * @since 3.0.0
   */
  public String inputDepartureTime(Print print, TrainStation station) {
    print.inputDepartureTime();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateDepartureTime(station);
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      }
    }
    return response;
  }

  /**
   * The user is prompted to enter the train number. The input is validated to make sure the format
   * is correct. The user will be prompted to enter a new train number until the input is correct.
   * The method returns the validated train number.
   *
   * @param print   An object of the Print class.
   * @param station An object of the TrainStation class.
   * @return The validated train number.
   * @since 3.0.0
   */
  public int inputTrainNumber(Print print, TrainStation station) {
    print.inputTrainNumber();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateTrainNumber(station);
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      } catch (NumberFormatException n) {
        print.errorMessage(ErrorResponse.INVALID_NUMBER_FORMAT);
      }
    }
    return Integer.parseInt(response);
  }

  /**
   * The user is prompted to enter the train line. The input is validated to make sure the format is
   * correct. The user will be prompted to enter a new train line until the input is correct. The
   * method returns the validated train line.
   *
   * @param print An object of the Print class.
   * @return The validated train line.
   * @since 3.0.0
   */
  public String inputTrainLine(Print print) {
    print.inputTrainLine();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateTrainLine();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      }
    }
    return response;
  }

  /**
   * The user is prompted to enter a string containing characters form the alphabet. The input is
   * validated to make sure the format is correct. The user will be prompted to enter a new string
   * until the input is correct. The method returns the validated string.
   *
   * @param print An object of the Print class.
   * @since 3.0.0
   */
  public void inputString(Print print) {
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateString();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      }
    }
  }

  /**
   * The user is prompted to enter a destination. The input is validated to make sure the format is
   * correct. The user will be prompted to enter a new string until the input is correct. The method
   * returns the validated string. If the input is greater than 15 characters, the input will be
   * shortened to 15 characters.
   *
   * @param print An object of the Print class.
   * @return The validated destination.
   * @since 3.0.0
   */
  public String inputDestination(Print print) {
    print.inputDestination();
    return validateDestination(print);
  }

  /**
   * The user is prompted to enter a destination to search for. The input is validated to make sure
   * the format is correct. The user will be prompted to enter a new string until the input is
   * correct. The method returns the validated string. If the input is greater than 15 characters,
   * the input will be shortened to 15 characters.
   *
   * @param print An object of the Print class.
   * @return The validated destination.
   * @since 3.0.0
   */
  public String inputSearchDestination(Print print) {
    print.inputSearchDestination();
    return validateDestination(print);
  }

  /**
   * The user is prompted to enter a positive integer. The input is validated to make sure the
   * format is correct. The user will be prompted to enter a new integer until the input is correct.
   * The method returns the validated integer.
   *
   * @param print An object of the Print class.
   * @return The validated integer.
   * @since 3.0.0
   */
  public int inputPositiveInteger(Print print) {
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validatePositiveInteger();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      } catch (NumberFormatException n) {
        print.errorMessage(ErrorResponse.INVALID_NUMBER_FORMAT);
      }
    }
    return Integer.parseInt(response);
  }

  /**
   * The user is prompted to enter the minutes of delay. The input is validated to make sure the
   * format is correct. The user will be prompted to enter a new delay until the input is correct.
   * The method returns the validated delay. The delay is limited to 1440 minutes (24 hours).
   *
   * @param print An object of the Print class.
   * @return The validated minutes of delay.
   * @since 3.0.0
   */
  public int inputDelay(Print print) {
    print.inputDelay();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateDelay();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      } catch (NumberFormatException n) {
        print.errorMessage(ErrorResponse.INVALID_NUMBER_FORMAT);
      }
    }
    return Integer.parseInt(response);
  }

  /**
   * The user is prompted to enter a track number. The input is validated to make sure the format is
   * correct. The user will be prompted to enter a new track number until the input is correct. The
   * method returns the validated track number.
   *
   * @param print An object of the Print class.
   * @return The validated track number.
   * @since 3.0.0
   */
  public int inputTrackNumber(Print print) {
    print.inputTrackNumber();
    return inputPositiveInteger(print);
  }

  /**
   * The user is prompted to enter a track number to search for. The input is validated to make sure
   * the format is correct. The user will be prompted to enter a new track number until the input is
   * correct. The method returns the validated track number.
   *
   * @param print An object of the Print class.
   * @return The validated track number.
   * @since 3.0.0
   */
  public boolean inputAnswer(Print print) {
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateAnswer();
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      }
    }
    return response.equalsIgnoreCase("y");
  }

  /**
   * The user is prompted to press enter. The input is validated to make sure the format is correct.
   * The user will be prompted to press enter until the input is correct.
   *
   * @param print An object of the Print class.
   * @since 3.0.0
   */
  public void inputEnter(Print print) {
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateEnter();
        correctInput = true;
      } catch (InputMismatchException e) {
        print.errorMessage(e.getMessage());
      }
    }
  }

  /**
   * The user is prompted to enter a train number to search for. The input is validated to make sure
   * the format is correct. The user will be prompted to enter a new train number until the input is
   * correct. The method returns the validated train number.
   *
   * @param print   An object of the Print class.
   * @param station An object of the TrainStation class.
   * @return The validated train number.
   * @since 3.0.0
   */
  public int inputSearchTrainNumber(Print print, TrainStation station) {
    print.inputSearchTrainNumber();
    correctInput = false;
    while (!correctInput) {
      response = inputReader.nextLine();
      try {
        validateSearchTrainNumber(station);
        correctInput = true;
      } catch (InputMismatchException | NullPointerException e) {
        print.errorMessage(e.getMessage());
      } catch (NumberFormatException n) {
        print.errorMessage(ErrorResponse.INVALID_NUMBER_FORMAT);
      }
    }
    return Integer.parseInt(response);
  }
}
