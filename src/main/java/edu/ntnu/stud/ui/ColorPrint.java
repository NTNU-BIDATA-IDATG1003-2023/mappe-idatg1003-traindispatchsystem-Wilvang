package edu.ntnu.stud.ui;


/**
 * The ColorPrint class is used to display messages in different colors to the console. The class
 * contains methods to print messages in different colors, such as red, green, yellow, blue, purple
 * and cyan. The messages can also be displayed with background colors and bold characters.
 *
 *
 * @author Johan Fredrik Wilvang
 * @version 1.9.0
 * @since 1.8.0
 */

public class ColorPrint {
  // Ansi colour codes for terminal output:
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\033[0;90m";
  public static final String ANSI_WHITE = "\033[0;97m";
  public static final String ANSI_RED = "\033[0;91m";
  public static final String ANSI_GREEN = "\033[0;92m";
  public static final String ANSI_YELLOW = "\033[0;93m";
  public static final String ANSI_BLUE = "\033[0;94m";
  public static final String ANSI_PURPLE  = "\033[0;95m";
  public static final String ANSI_CYAN = "\033[0;96m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String BLACK_BOLD = "\033[1;30m";
  public static final String WHITE_BOLD = "\033[1;97m";

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printWhite(String message) {
    System.out.println(ANSI_WHITE + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printBlack(String message) {
    System.out.println(ANSI_BLACK + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printRed(String message) {
    System.out.println(ANSI_RED + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printGreen(String message) {
    System.out.println(ANSI_GREEN + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printYellow(String message) {
    System.out.println(ANSI_YELLOW + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printBlue(String message) {
    System.out.println(ANSI_BLUE + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printPurple(String message) {
    System.out.println(ANSI_PURPLE + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.9.0
   */
  public void printCyan(String message) {
    System.out.println(ANSI_CYAN + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printRedBackground(String message) {
    System.out.println(ANSI_RED_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printGreenBackground(String message) {
    System.out.println(ANSI_GREEN_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printYellowBackground(String message) {
    System.out.println(ANSI_YELLOW_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printBlueBackground(String message) {
    System.out.println(ANSI_BLUE_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printPurpleBackground(String message) {
    System.out.println(ANSI_PURPLE_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printCyanBackground(String message) {
    System.out.println(ANSI_CYAN_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printWhiteBackground(String message) {
    System.out.println(ANSI_WHITE_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   */
  public void printBlackBackground(String message) {
    System.out.println(ANSI_BLACK_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   */
  public String printBlackBold(String message) {
    return BLACK_BOLD + message + ANSI_RESET;
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   */
  public String printWhiteBold(String message) {
    return WHITE_BOLD + message + ANSI_RESET;
  }
}
