package edu.ntnu.stud.transport;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/*
 * This class is used to test the TrainDeparture class.
 */

class TrainDepartureTest {

  private TrainDeparture trainDepartureTest;

  /*
   * This method is used to set up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    trainDepartureTest =
        new TrainDeparture("12:00", 13, "L2", "Oslo");
  }

  /*
   * Positive test for the delay method. Checks handling of valid parameters and if the real
   * departure time is updated with the delay.
   */
  @Test
  void setDelayPositiveTest() {
    trainDepartureTest.setDelay(120);
    assertEquals(120, trainDepartureTest.getMinutesDelay(),
        "The departure time was not changed");
    assertEquals(LocalTime.parse("14:00"), trainDepartureTest.getRealDepartureTime());
  }

  /*
   * Negative test for the delay method. Checks handling of negative delay values.
   */
  @Test
  void setDelayNegativeTest() {
    trainDepartureTest.setDelay(-13);
    assertNotEquals(-13, trainDepartureTest.getMinutesDelay(),
        "Negative delay should not update the departure time");
  }

  /*
   * Positive test for the setDepartureTime method. Checks handling of invalid time formats.
   */
  @Test
  void setDepartureTimePositiveTest() {
    trainDepartureTest.setDepartureTime("13:60");
    assertEquals(LocalTime.parse("00:00"), trainDepartureTest.getDepartureTime(),
        "The departure time did not reset to 00:00");
  }

  /*
   * Negative test for the setDepartureTime method. Checks handling of invalid integers.
   */
  @Test
  void setTrackNegativeTest() {
    trainDepartureTest.setTrackNumber(-10);
    assertEquals(-1, trainDepartureTest.getTrackNumber(),
        "The track number should not be set to -10");
  }

  /*
   * Positive test for the setTrackNumber method. Checks handling of valid integers.
   */
  @Test
  void setTrainNumberPositiveTest() {
    trainDepartureTest.setTrainNumber(40);
    assertEquals(40, trainDepartureTest.getTrainNumber(),
        "The train number was not changed to 40");
  }

  /*
   * Positive test for the setTrainNumber method. Checks if the characters are converted to upper
   * case and if the correct train line is set.
   */
  @Test
  void setTrainLinePositiveTest() {
    trainDepartureTest.setTrainLine("l1");
    assertEquals("L1", trainDepartureTest.getTrainLine(),
        "The train line was not changed to L1");
  }

  /*
   * Positive test for the setDestination method. Checks if the first character is converted to
   * upper case and the rest to lower case, and if the correct destination is set.
   */
  @Test
  void setDestinationPositiveTest() {
    trainDepartureTest.setDestination("oslo");
    assertEquals("Oslo", trainDepartureTest.getDestination(),
        "The destination was not changed to Oslo");
  }
}