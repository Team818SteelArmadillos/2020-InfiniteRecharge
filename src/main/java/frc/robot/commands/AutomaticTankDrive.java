package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TankDriveCommand extends CommandBase {
  /**
   * Creates a new TankDriveCommand.
   */
  public TankDriveCommand() {
    addRequirements(Robot.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_driveSubsystem.setBothMotors(Robot.m_oi.getleftYAxis(), Robot.m_oi.getrightYAxis());
    if(Robot.m_driveSubsystem.getVelocity() > 5.5){
      Robot.m_driveSubsystem.shift(true);
    } else if (Robot.m_driveSubsystem.getVelocity() < 4){
      Robot.m_driveSubsystem.shift(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
