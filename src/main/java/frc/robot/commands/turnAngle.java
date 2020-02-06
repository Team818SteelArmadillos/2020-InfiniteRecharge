/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class turnAngle extends CommandBase {
  /**
   * Creates a new turnAngle.
   */
  public turnAngle(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(Robot.drive);
    Robot.drive.resetGyro();
    Robot.drive.setDistanceSetPoint(angle);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = Robot.drive.getDistancePIDOutput(Robot.drive.getAngle());
    Robot.drive.setBothMotors(-speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drive.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.drive.distanceOnSetpoint();
  }
}
