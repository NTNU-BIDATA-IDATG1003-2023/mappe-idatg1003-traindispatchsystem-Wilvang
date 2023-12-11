package edu.ntnu.stud.ui;


/**
 * The ConsoleColor class is used to display message output in different colors to the console. The
 * class contains methods to print messages in different colors, such as red, green, yellow, blue,
 * purple and cyan. The messages can also be displayed with background colors and bold characters.
 *
 * @author Johan Fredrik Wilvang
 * @version 3.0.2
 * @since 2.3.3
 */

public class ConsoleColor {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_WHITE = "\033[0;97m";
  public static final String ANSI_BLUE = "\033[0;94m";
  public static final String ANSI_PURPLE = "\033[0;95m";
  public static final String ANSI_CYAN = "\033[0;96m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String BLUE_BOLD = "\033[1;94m";
  public static final String PURPLE_BOLD = "\033[1;95m";
  public static final String CYAN_BOLD = "\033[1;96m";
  public static final String WHITE_BOLD = "\033[1;97m";
  public static final String STRIKE_THROUGH = "\u001B[9m";
  public static final String UNDO_STRIKE_THROUGH = "\u001B[29m";

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.6.1
   */
  public void printWhite(String message) {
    System.out.println(ANSI_WHITE + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.6.1
   */
  public void printBlue(String message) {
    System.out.println(ANSI_BLUE + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified color.
   *
   * @param message The message to be printed.
   * @since 1.6.1
   */
  public void printCyan(String message) {
    System.out.println(ANSI_CYAN + message + ANSI_RESET);
  }

  /**
   * Prints a message to the console in the specified background color.
   *
   * @param message The message to be printed.
   * @since 1.6.1
   */
  public void printBlueBackground(String message) {
    System.out.println(ANSI_BLUE_BACKGROUND + message + ANSI_RESET);
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   * @since 1.6.1
   */
  public String printWhiteBold(String message) {
    return WHITE_BOLD + message + ANSI_RESET;
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   * @since 2.1.0
   */
  public String printBlueBold(String message) {
    return BLUE_BOLD + message + ANSI_RESET;
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   * @since 2.1.0
   */
  public String printPurpleBold(String message) {
    return PURPLE_BOLD + message + ANSI_RESET;
  }

  /**
   * Converts the message to a string to bold characters with the specified color.
   *
   * @param message The message to be printed.
   * @return The message to be printed in bold characters with the specified color.
   * @since 2.1.0
   */
  public String printCyanBold(String message) {
    return CYAN_BOLD + message + ANSI_RESET;
  }

  /**
   * Converts the message to strike-through text.
   *
   * @param message The message to be printed.
   * @return The message to be printed in strike-through text.
   * @since 2.3.1
   */
  public String printStrikeThrough(String message) {
    return STRIKE_THROUGH + message + UNDO_STRIKE_THROUGH;
  }
}
