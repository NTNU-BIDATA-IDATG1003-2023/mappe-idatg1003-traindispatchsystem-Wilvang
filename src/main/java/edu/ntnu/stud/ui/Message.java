package edu.ntnu.stud.ui;

import edu.ntnu.stud.vehicle.train.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Message {
  public void welcomeMessage() {
    System.out.println("Welcome to the train dispatch application!");
  }

  public void goodByeMessage(){
    System.out.println("Thank you for using the train dispatch application!");
  }

  public void informationTable(LocalTime time, ArrayList<TrainDeparture> trainDeparturesList){
    System.out.println("Train Departures                          Track           " + time);
    Iterator<TrainDeparture> iterateTrain = trainDeparturesList.iterator();
    int i = 0;
    while (i < 10){
      TrainDeparture train = iterateTrain.next();
      if (train.getDelay().equals(LocalTime.of(0,0))){
        System.out.println(train.getTrainNumber() + "  " + train.getDepartureTime() + "  " + train.getTrainLine()
            + " " +train.getDestination() + "                " + train.getTrackNumber());
      } else {
        System.out.println(train.getTrainNumber() + "  " + train.getDepartureTime() + "  " + train.getTrainLine()
            + " " +train.getDestination() + "      " + train.getDelay() + "      " + train.getTrackNumber());
      }
      i++;
    }
  }
  public void departureOverview(){

  }
  public void overviewMessage() {
    System.out.println("Please select an option: ");
    System.out.println("1. View information table");
    System.out.println("2. Add new train departure");
    System.out.println("3. Assign track");
    System.out.println("4. Set delay");
    System.out.println("5. Search for train number");
    System.out.println("6. Search for destination");
    System.out.println("7. Update clock");
    System.out.println("8. Exit");
  }

  public void clockMessage(LocalTime time) {
    System.out.println("Clock: " + time);
  }
}
