package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class driveDistance extends CommandBase{
  /**
   * Creates a n
public cla drew driveDistance.
   */
  public driveDistance(double distance){
    ////dRequirementhere to declare subsystem dependencies.
    addRequirements(Robot.m_driveSubsystem);
    Robot.m_driveSubsystem.resetEncoders();
    Robot.m_driveSubsystem.setDistanceSetPoint(distance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_driveSubsystem.setBothMotors(Robot.m_driveSubsystem.getDistancePIDOutput(Robot.m_driveSubsystem.getLeftPosition()), Robot.m_driveSubsystem.getDistancePIDOutput(Robot.m_driveSubsystem.getRightPosition()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.m_driveSubsystem.distanceOnSetpoint();
  }
}
