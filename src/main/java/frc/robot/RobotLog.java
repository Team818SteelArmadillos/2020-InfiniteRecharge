package frc.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Matthew P. Team 818 The Steel Armadillos
 * 
 * This class is used to display messages on the SmartDashboard for quick
 * debugging and has practical applications in testing. Integers, booleans, and
 * other data can be written to the SmartDashboard to identify whether or not
 * the program is behaving in the intended manner.
 * 
 * 
 * Updated for efficiency on 2019-03-06 -- Matthew Polgar was here
 */

public abstract class RobotLog {

  private static String log;
  private static SimpleDateFormat timeFormat = new SimpleDateFormat("[HH:mm:ss]\t");

  private static final int NUM_LINES = 512;

  public static void init() {

    log = "";
    for (int i = 0; i < NUM_LINES - 1; i++) {
      log += '\n';
    }

    updateLog();

  }

  public static void putMessage(String message) {

    Date date = new Date();
    //log = log.substring(0, log.lastIndexOf('\n'));
    log = timeFormat.format(date) + message + "\n" + log;

    updateLog();

  }

  private static void updateLog() {
    try {
      SmartDashboard.putString("RobotLog", log);
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
  }

}
