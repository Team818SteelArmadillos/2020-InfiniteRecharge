package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class commandGroupAuton extends SequentialCommandGroup {

  public commandGroupAuton() {
    if (autonPosition == 1) {
      addSequential();
      addSequential();
      addSequential();
      RobotLog.putMessage("Running Auton Position 1");
    } else if (autonPosition == 2) {
      addSequential();
      addSequential();
      addSequential();
      RobotLog.putMessage("Running Auton Position 2");
    } else if (autonPosition == 3) {
      addSequential();
      addSequential();
      addSequential();
      RobotLog.putMessage("Running Auton Position 3");
    }

  }
}
