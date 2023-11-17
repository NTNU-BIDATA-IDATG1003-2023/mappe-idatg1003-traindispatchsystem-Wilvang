package edu.ntnu.stud.transport.train;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {

  private TrainDeparture trainDepartureTest;

  @BeforeEach
  void setUp() {
    trainDepartureTest = new TrainDeparture("12:00", 13,
        "L2", "Oslo");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void setDelayPositiveTest() {
    trainDepartureTest.setDelay(120);
    assertEquals(120, trainDepartureTest.getMinutesDelay(),
        "The departure time was not changed");
  }
}