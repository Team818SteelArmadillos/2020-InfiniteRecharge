
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoAllign extends CommandBase {

  private boolean hasTarget;
  private boolean reachedTarget;

  public AutoAllign() {

    addRequirements(Robot.m_driveSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    addRequirements(Robot.m_shootervisionSubsystem);
    addRequirements(Robot.m_shooterSubsystem);

  }

  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_shooterSubsystem.shooterSpeed(0);
  }

  @Override
  public void execute() {
    if (Robot.m_shootervisionSubsystem.getTarget()) {
      hasTarget = true;
      if (Robot.m_shootervisionSubsystem.getX() - Robot.m_driveSubsystem.getAngle() < 0) {
        Robot.m_driveSubsystem.setRightMotors(0.3);
        Robot.m_driveSubsystem.setLeftMotors(-0.3);
      }
      else if (Robot.m_shootervisionSubsystem.getX() - Robot.m_driveSubsystem.getAngle() > 0) {
        Robot.m_driveSubsystem.setRightMotors(-0.3);
        Robot.m_driveSubsystem.setLeftMotors(0.3);
    } else reachedTarget = true;


    
  } else hasTarget = false;


  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public boolean isFinished() {
    return !hasTarget || reachedTarget;
  }
}
