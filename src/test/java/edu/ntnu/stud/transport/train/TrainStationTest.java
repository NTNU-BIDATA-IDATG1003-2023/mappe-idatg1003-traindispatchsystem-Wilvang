package edu.ntnu.stud.transport.train;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainStationTest {

  private TrainStation trainStationTest;

  @BeforeEach
  void setUp() {
    trainStationTest = new TrainStation();
    trainStationTest.addTrainDeparture("11:00", 13,
        "L2", "Oslo");
    trainStationTest.addTrainDeparture("12:30", 424,
        "F3", "Trondheim");
    trainStationTest.addTrainDeparture("14:00", 65,
        "RE11", "Bergen");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void searchByTrainNumberPositiveTest() {
    assertEquals("Oslo", trainStationTest.searchByTrainNumber(13).next().getDestination(),
        "The destination of the train with train number 13 was wrong");
    assertFalse(trainStationTest.searchByTrainNumber(122).hasNext(),
        "The search by train number method returned with a non existing train departure");
  }

  @Test
  void setNewTrainNumberPositiveTest() {
    trainStationTest.setNewTrainNumber(13, 40);
    assertEquals(40, trainStationTest.searchByTrainNumber(40).next().getTrainNumber(),
        "The train number was not changed");
  }

  @Test
  void setNewTrainNumberNegativeTest() {
    trainStationTest.setStationClock("13:00");
    assertEquals(65, trainStationTest.hideDepartedTrains().next().getTrainNumber());
  }
}