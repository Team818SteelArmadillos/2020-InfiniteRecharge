package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotLog;

public class AutonOne extends CommandGroup {
  // Auton 1 = Do nothing
  public AutonOne() {
    RobotLog.putMessage("Running Auton Position 1");
  }
}
