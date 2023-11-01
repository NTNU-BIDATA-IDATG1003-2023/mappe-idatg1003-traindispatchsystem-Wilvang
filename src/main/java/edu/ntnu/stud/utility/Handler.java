package edu.ntnu.stud.utility;

import java.time.LocalTime;
import java.util.Scanner;

public class Handler {

  /**
   * Method to input a string from the user. The method checks if the input is
   * a string. If the input is not a string, the method will ask the user to
   * input a string.
   *
   * @param typeOfInput the type of input the user is asked to input.
   * @return the string input from the user.
   */
  public String inputString(String typeOfInput){
    Scanner inputReader = new Scanner(System.in);
    String inputString = "";
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if(!inputReader.hasNextInt()) {
        inputString = inputReader.nextLine();
        flag = true;
      }
      else
      {
        System.out.println("Please enter a string!");
        inputReader.next();
      }
    }
    return inputString;
  }

  /**
   * Method to input an integer from the user. The method checks if the input is
   * an integer. If the input is not an integer, the method will ask the user to
   * input an integer.
   *
   * @param typeOfInput the type of input the user is asked to input.
   * @return the integer input from the user.
   */
  public int inputInteger(String typeOfInput){
    Scanner inputReader = new Scanner(System.in);
    int inputInteger = 0;
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if (inputReader.hasNextInt()){
        inputInteger = inputReader.nextInt();
        flag = true;
      }
      else {
        System.out.println("Please enter an integer!");
        inputReader.next();
      }
    }
    return inputInteger;
  }

  /**
   * Method to check if the input time is in the format "hh:mm". The method
   * checks if the input is a string with length 5, and if the first two
   * characters are integers between 0 and 23, and if the last two characters
   * are integers between 0 and 59. If the input is not in the format "hh:mm",
   * the method will return false.
   *
   * @param time the input time.
   * @return true if the input time is in the format "hh:mm", false otherwise.
   */

  public boolean clockFormat(String time) {
    if (time != null && time.length() == 5) {
      // Try expression to check if the format is "hh:mm"
      try {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        return hour < 24 && hour >= 0 && minute < 60 && minute >= 0
            && time.charAt(2) == ':';
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false; // Return false for any other cases.
  }

  /**
   * Method to input a time from the user. The method checks if the input is
   * in the format "hh:mm". If the input is not in the format "hh:mm", the
   * method will ask the user to input a time in the format "hh:mm".
   * The method returns time as a LocalTime object.
   *
   * @return the time as a LocalTime object.
   */
  public LocalTime inputTime(){
    Scanner inputReader = new Scanner(System.in);
    boolean flag = false;
    String clockTime = null;
    System.out.println("Please enter the current time (hh:mm) ::");
    while(!flag) {
      clockTime = inputReader.nextLine();
      if (clockFormat(clockTime)) {
        flag = true;
      } else {
        System.out.println("Please enter a valid time format! (hh:mm)");
      }
    }
    return LocalTime.parse(clockTime);
  }



}
