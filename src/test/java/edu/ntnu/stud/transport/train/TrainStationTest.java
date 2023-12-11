package edu.ntnu.stud.transport.train;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/*
 * This class is used to test the TrainStation class.
 */

class TrainStationTest {

  private TrainStation trainStationTest;

  /*
   * This method is used to set up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    trainStationTest = new TrainStation();
    trainStationTest.setStationClock("10:00");
    trainStationTest.addTrainDeparture(
        "11:00", 13, "L2", "Oslo");
    trainStationTest.addTrainDeparture(
        "12:30", 424, "F3", "Trondheim");
    trainStationTest.addTrainDeparture(
        "14:00", 65, "RE11", "Bergen");
    trainStationTest.addTrainDeparture(
        "11:00", 65, "RE11", "Bergen");

  }

  @AfterEach
  void tearDown() {
  }

  /*
   * Positive test for the searchByDestination method. Checks if the methods returns train departures
   * containing the dest destination searched for.
   */
  @Test
  void searchByDestinationPositiveTest() {
    assertEquals("Bergen", trainStationTest.searchByDestination("Bergen").next().getDestination(),
        "The search by destination did not work");
  }

  /*
   * Positive test for the setNewTrainNumber method. Checks if the train number of the train
   * departure is changed.
   */
  @Test
  void setNewTrainNumberPositiveTest() {
    trainStationTest.setNewTrainNumber(13, 40);
    assertEquals(40, trainStationTest.searchByTrainNumber(40).next().getTrainNumber(),
        "The train number was not changed to 40");
  }

  /*
   * Negative test for the setNewTrainNumber method. Checks if the train number of the train
   * departure is changed to an invalid value.
   */
  @Test
  void setNewTrainNumberNegativeTest() {
    trainStationTest.setNewTrainNumber(13, 0);
    assertFalse( trainStationTest.searchByTrainNumber(-1).hasNext(),
        "The train number was set to the invalid value 0");
  }

  /*
   * Negative test for the setNewDepartureTime method. Checks if the departure time of the train
   * departure is changed to a time before the current time at the station.
   */
  @Test
  void setNewDepartureTimeNegativeTest() {
    trainStationTest.setNewDepartureTime(13, "09:00");
    assertNotEquals(LocalTime.parse("09:00"),
        trainStationTest.searchByTrainNumber(13).next().getDepartureTime(),
        "The departure time was changed to before the current time");
  }

  /*
   * Positive test for the hideDepartedTrains method. Checks if the trains that have departed are
   * hidden.
   */
  @Test
  void hideDepartedTrainsPositiveTest() {
    trainStationTest.setStationClock("13:00");
    assertEquals(65, trainStationTest.hideDepartedTrains().next().getTrainNumber(),
        "The trains was not hidden properly");
    assertEquals(1, trainStationTest.getNumberOfTrains(),
        "The number of trains was not changed");
  }

  /*
   * Positive test for the isTrainNumberUnique method. Checks if the train number is unique.
   */
  @Test
  void isTrainNumberUniquePositiveTest() {
    assertTrue(trainStationTest.isTrainNumberUnique(1),
        "The train number 1 was not unique");
  }

  /*
   * Negative test for the isTrainNumberUnique method. Checks if the train number is not unique.
   */
  @Test
  void isTrainNumberUniqueNegativeTest() {
    assertFalse(trainStationTest.isTrainNumberUnique(13),
        "The train number 13 was unique");
  }
}