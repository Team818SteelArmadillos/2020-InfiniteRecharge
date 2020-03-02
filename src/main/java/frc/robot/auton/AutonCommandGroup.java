package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotLog;
import frc.robot.commands.SpoolShooterCommand;
import frc.robot.commands.driveDistance;
import frc.robot.commands.turnAngle;

public class AutonCommandGroup extends SequentialCommandGroup {

  public AutonCommandGroup(double autonPosition) {
    // Auton 1 = Do nothing
    // Auton 2 = Drive a few feet off of the line and then do nothing
    // Auton 3 = Shoot three balls and drive off of the line
    // Auton 4 = Start facing the trench run. Deploy intake and drive forward to get first two balls. Turn around and drive in front of the Power Port.
    // Shoot all five balls.

    if(autonPosition == 1) {
    RobotLog.putMessage("Running Auton Position 1");

  } else if(autonPosition == 2) {
    new driveDistance(3);
    RobotLog.putMessage("Running Auton Position 2");

  } else if(autonPosition == 3) {
    new SpoolShooterCommand();
    new driveDistance(3);
    RobotLog.putMessage("Running Auton Position 3");

  } else if (autonPosition == 4){
    new driveDistance(3);
    new turnAngle(180);
    new driveDistance(3);
    new SpoolShooterCommand();
  }

}
}
