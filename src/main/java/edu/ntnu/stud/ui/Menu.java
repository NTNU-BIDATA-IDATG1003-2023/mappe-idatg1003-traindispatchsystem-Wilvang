package edu.ntnu.stud.ui;

import edu.ntnu.stud.transport.train.TrainDispatch;
import edu.ntnu.stud.utility.Handler;
import java.time.LocalTime;


public class Menu {

  private boolean start;
  private Handler handler;
  private Print message;

  private LocalTime currentTime;

  /**
   * Constructor for objects of class Menu. The constructor initializes the
   * boolean start to false and creates an object of the Handler class. The
   * constructor also creates an object of the TrainDispatch class. The
   * constructor also initializes the current time to 00:00.
   */
  public Menu() {
    this.start = false;
    this.handler = new Handler();
    this.message = new Print();

    currentTime = LocalTime.of(0,0);
  }


  public void init() {
    this.message.welcomeMessage();
    this.currentTime = this.handler.inputTime();
  }

  public void start(TrainDispatch trainDispatch) {
    while (!this.start) {
      message.clockMessage(currentTime);
      message.overviewMessage();
      mainMenu(handler.inputInteger("number associated with the option"));
    }
  }


  public void mainMenu(int select) {
    switch (select) {
      case 1:
        // print overview
        this.message.clockMessage(this.currentTime);
        break;
      case 2:
        // add new train departure
        this.message.clockMessage(this.currentTime);
        break;
      case 3:
        // assign track
        this.message.clockMessage(this.currentTime);
        break;
      case 4:
        // add delay
        this.message.clockMessage(this.currentTime);
        break;
      case 5:
        // search for train number
        this.message.clockMessage(this.currentTime);
        break;
      case 6:
        // search for destination
        this.message.clockMessage(this.currentTime);
        break;
      case 7:
        // update clock
        this.message.clockMessage(this.currentTime);
        this.currentTime = this.handler.inputTime();
        break;
      case 8:
        // exit
        this.message.goodByeMessage();
        this.start = true;
        break;
      default:
        // invalid input
        break;
    }
  }


}

