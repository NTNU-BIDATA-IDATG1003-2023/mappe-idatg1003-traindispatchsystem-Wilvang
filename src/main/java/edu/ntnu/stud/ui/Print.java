package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;


public class Print {
  public void printMessage(String message) {
    System.out.println(message);
  }
  public void welcomeMessage() {
    printMessage("Welcome to the train dispatch application!");
  }

  public void goodByeMessage(){
    printMessage("Thank you for using the train dispatch application!");
  }

  public void informationTable(LocalTime time, ArrayList<TrainDeparture> trainDeparturesList){
    printMessage("Train Departures                          Track           " + time);
    Iterator<TrainDeparture> iterateTrain = trainDeparturesList.iterator();
    int i = 0;
    while (i < 10){
      TrainDeparture train = iterateTrain.next();
      if (train.getDelay().equals(LocalTime.of(0,0))){
        printMessage(train.getTrainNumber() + "  " + train.getDepartureTime() + "  " + train.getTrainLine()
            + " " +train.getDestination() + "                " + train.getTrackNumber());
      } else {
        printMessage(train.getTrainNumber() + "  " + train.getDepartureTime() + "  " + train.getTrainLine()
            + " " +train.getDestination() + "      " + train.getDelay() + "      " + train.getTrackNumber());
      }
      i++;
    }
  }
  public void departureOverview(){

  }
  public void overviewMessage() {
    printMessage("Please select an option: ");
    printMessage("[1] View information table");
    printMessage("[2] Add new train departure");
    printMessage("[3] Assign track");
    printMessage("[4] Set delay");
    printMessage("[5] Search for train number");
    printMessage("[6] Search for destination");
    printMessage("[7] Update clock");
    printMessage("[8] Exit");
  }

  public void clockMessage(LocalTime time) {
    printMessage("Clock: " + time);
  }
}
