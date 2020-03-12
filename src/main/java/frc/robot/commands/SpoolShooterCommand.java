package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SpoolShooterCommand extends CommandBase {

  public SpoolShooterCommand() {
    addRequirements(Robot.m_shooterSubsystem);
  }

  @Override
  public void initialize() {
    Robot.m_shooterSubsystem.shooterSpeed(0);

  }

  @Override
  public void execute() {
    Robot.m_shooterSubsystem.shootingType();

    if (Robot.m_oi.getSpoolShootButton()) {
      Robot.m_shooterSubsystem.shooterSpeed(3000);

    } else {
      Robot.m_shooterSubsystem.shooterSpeed(0);
      Robot.m_shooterSubsystem.logData();
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_shooterSubsystem.shooterSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
