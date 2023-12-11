package edu.ntnu.stud.transport.train;

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

  @AfterEach
  void tearDown() {
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
   * Positive test for the setDepartureTime method. Checks handling of invalid parameters.
   */
  @Test
  void setDepartureTimePositiveTest() {
    trainDepartureTest.setDepartureTime("13:60");
    assertEquals(LocalTime.parse("00:00"), trainDepartureTest.getDepartureTime(),
        "The departure time did not reset to 00:00");
  }
}