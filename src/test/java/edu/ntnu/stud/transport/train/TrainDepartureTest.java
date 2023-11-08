package edu.ntnu.stud.transport.train;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.transport.train.TrainDeparture.TrainBuilder;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {
  private TrainDeparture trainDepartureTest;
  private TrainBuilder trainBuilderTest;

  /**
   * Set up an object of class TrainDeparture to test methods.
   */
  @BeforeEach
  void setUp() {
    trainBuilderTest= new TrainBuilder(12,00,390)
        .setDestination("Oslo").setTrackNumber(12,12);
    trainDepartureTest = trainBuilderTest.build();
  }

  @AfterEach
  void tearDown() {
  }

  /**
   * Positive test to check delay values in the area of validity.
   */
  @Test
  void testSetDelayPositive() {
    trainDepartureTest = this.trainBuilderTest.setDelay(120).build();
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
    this.trainDepartureTest = this.trainBuilderTest.setDelay(-10).build();
    assertEquals(LocalTime.of(0,0),
        this.trainDepartureTest.getDelay(),
        "Test for delay not passed!");

    assertEquals(LocalTime.of(12,0),
        this.trainDepartureTest.getDepartureTime(),
        "Test for departure time delay not passed!");

  }
}