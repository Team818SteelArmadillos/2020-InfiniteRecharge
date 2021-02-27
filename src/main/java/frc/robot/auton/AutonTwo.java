package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotLog;
import frc.robot.commands.driveDistance;

public class AutonTwo extends CommandGroup {
  // Auton 2 = Drive a few feet off of the line and then do nothing
  public AutonTwo() {
    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    new driveDistance(60);
    RobotLog.putMessage("Running Auton Position 2");
  }
}
