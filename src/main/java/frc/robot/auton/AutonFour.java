package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.driveDistance;
import frc.robot.commands.turnAngle;

public class AutonFour extends CommandGroup {
 // Auton 4 = Start facing the trench run. Deploy intake and drive forward to get first two balls. Turn around and drive in front of the Power Port.
 // Shoot all five balls.
  public AutonFour() {
    new driveDistance(3);
    new turnAngle(180);
    new driveDistance(3);
    new ShooterCommand();
  }
}
