
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoBallTrackCommand extends CommandBase {

  private boolean hasTarget;
  private boolean reachedTarget;

  public AutoBallTrackCommand() {

    addRequirements(Robot.m_driveSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    addRequirements(Robot.m_drivevision); //rename the drivevison to drivevisionsubsystem
    addRequirements(Robot.m_shooterSubsystem);

  }

  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_shooterSubsystem.shooterSpeed(0);
  }

  @Override
  public void execute() {//remeber to reset the tolerance to make it so the robot can actually agnle tot he ball.
    if(!Robot.m_drivevision.getTarget()){
      Robot.m_driveSubsystem.setRightMotors(0.1);
      Robot.m_driveSubsystem.setLeftMotors(-0.1);
    } else if(Robot.m_drivevision.getTarget()) {
      hasTarget = true;
      if (Robot.m_drivevision.getX() - Robot.m_driveSubsystem.getAngle() < 0) {
        Robot.m_driveSubsystem.setRightMotors(-0.1);
        Robot.m_driveSubsystem.setLeftMotors(0.1);
      }
      else if (Robot.m_drivevision.getX() - Robot.m_driveSubsystem.getAngle() > 0) {
        Robot.m_driveSubsystem.setRightMotors(0.1);
        Robot.m_driveSubsystem.setLeftMotors(-0.1);
    } else reachedTarget = true;
    
  } else hasTarget = false;


  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public boolean isFinished() {
    return reachedTarget;
  }
}
