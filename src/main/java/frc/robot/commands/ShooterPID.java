package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterPID extends CommandBase {
  PIDController ShootPID;

  private double p;
  private double i;
  private double d;

  private double rpm;
  
  public ShooterPID() {
    addRequirements(Robot.m_shooterSubsystem);
    addRequirements(Robot.m_newintakesubsystem);

    p = 0.0009;
    i = 0.00537;
    d = 0.00003657;
    rpm = 3000;

    ShootPID = new PIDController(p, i, d);

    ShootPID.setTolerance(10);


  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("speed", 3000);
    Robot.m_shooterSubsystem.setPower(0);
  }

  @Override
  public void execute() {

    rpm = SmartDashboard.getNumber("speed", 0);

    Robot.m_shooterSubsystem.setPower(ShootPID.calculate(rpm - Robot.m_shooterSubsystem.getCurrentShooterSpeed()));
    SmartDashboard.putNumber("Shooter Speed", Robot.m_shooterSubsystem.getCurrentShooterSpeed());

    if(ShootPID.atSetpoint()){
      Robot.m_newintakesubsystem.setIndexMotor(0.5);
    }
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
