package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterPID extends CommandBase {
  PIDController ShootPID;
  public ShooterPID() {
    ShootPID = new PIDController(0.01, 0.012, 0);
  }

  @Override
  public void initialize() {
    Robot.m_shooterSubsystem.setPower(0);
  }

  @Override
  public void execute() {
    Robot.m_shooterSubsystem.setPower(ShootPID.calculate(3000 - Robot.m_shooterSubsystem.getCurrentShooterSpeed()));
    SmartDashboard.putNumber("Shooter Speed", Robot.m_shooterSubsystem.getCurrentShooterSpeed());
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_shooterSubsystem.setPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
