package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotLog;
import frc.robot.commands.driveDistance;

public class AutonCommandGroup extends SequentialCommandGroup {

  public AutonCommandGroup() {
    // Auton 1 = Do nothing
    // Auton 2 = 
    // Auton 3 = 
    // Auton 4 = 

    if(autonPosition==1) {
    
    RobotLog.putMessage("Running Auton Position 1");
  } else if(autonPosition==2) {
    addSequential(driveDistance(3));
    RobotLog.putMessage("Running Auton Position 2");
  } else if(autonPosition==3) {
    addSequential();
    addSequential();
    addSequential();
    RobotLog.putMessage("Running Auton Position 3");
  }

}}
