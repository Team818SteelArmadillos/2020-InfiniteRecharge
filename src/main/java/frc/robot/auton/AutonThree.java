package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotLog;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.driveDistance;

public class AutonThree extends CommandGroup {
  // Auton 3 = Shoot three balls and drive off of the line
  public AutonThree() {
    new ShooterCommand();
    new driveDistance(3);
    RobotLog.putMessage("Running Auton Position 3");
  }
}
