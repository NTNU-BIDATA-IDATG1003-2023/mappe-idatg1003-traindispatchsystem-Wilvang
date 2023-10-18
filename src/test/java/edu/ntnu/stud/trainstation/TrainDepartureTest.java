package edu.ntnu.stud.trainstation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {
  private TrainDeparture trainDepartureTest;

  /**
   * Set up an object of class TrainDeparture to test methods.
   */
  @BeforeEach
  void setUp() {
    this.trainDepartureTest = new TrainDeparture(12,0, "re", 13);
  }

  @AfterEach
  void tearDown() {
  }

  /**
   * Positive test to check delay values in the area of validity.
   */
  @Test
  void testSetDelayPositive() {
    this.trainDepartureTest.setDelay(120);
    assertEquals(LocalTime.of(2,0),
        this.trainDepartureTest.getDelay(),
        "Test for delay not passed!");

    assertEquals(LocalTime.of(14,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time delay not passed!");
  }

  /**
   * Negative test to check delay values outside the area of validity.
   */
  @Test
  void testSetDelayNegative() {
    this.trainDepartureTest.setDelay(-1,-10);
    assertEquals(LocalTime.of(0,0),
        this.trainDepartureTest.getDelay(),
        "Test for delay not passed!");

    assertEquals(LocalTime.of(12,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time delay not passed!");

  }

  /**
   * Positive test to check departure time values in the area of validity.
   */
  @Test
  void testSetDepartureTimePositive() {
    this.trainDepartureTest.setDepartureTime(18,0);
    assertEquals(LocalTime.of(18,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time not passed!");
  }

  /**
   * Negative test to check departure time values outside the area of validity.
   */
  @Test
  void testSetDepartureTimeNegative() {
    this.trainDepartureTest.setDepartureTime(50,20);
    assertEquals(LocalTime.of(0,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time not passed!");

    this.trainDepartureTest.setDepartureTime(2,-12);
    assertEquals(LocalTime.of(0,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time not passed!");
  }

  /**
   * Positive test to check for positive output.
   */
  @Test
  void testSetTrainLinePositive() {
    this.trainDepartureTest.setTrainLine("re",21);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("RE21"),
        "Test for positive express train line not passed!");

    this.trainDepartureTest.setTrainLine("fly", 2);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("FLY2"),
        "Test for positive flight train line not passed!");

    this.trainDepartureTest.setTrainLine("l", 13);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("L13"),
        "Test for positive train line not passed!");
  }

  /**
   * Negative test to check invalid train types and route numbers.
   */
  @Test
  void testSetTrainLineNegative() {
    this.trainDepartureTest.setTrainLine("re",211);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("INVALID"),
        "Test for negative express train line not passed!");
    this.trainDepartureTest.setTrainLine("flyy", 2);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("INVALID"),
        "Test for negative flight train line not passed!");

    this.trainDepartureTest.setTrainLine("l", -4);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("INVALID"),
        "Test for negative flight train line not passed!");

    this.trainDepartureTest.setTrainLine("1", 23);
    assertTrue(this.trainDepartureTest.getTrainLine().equals("INVALID"),
        "Test for negative train line not passed!");
  }
}