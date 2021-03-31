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
  private double dist[][];

  private double rpm;
  
  public ShooterPID() {
    addRequirements(Robot.m_shooterSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    addRequirements(Robot.m_shootervisionSubsystem);

    p = 0.0009;
    i = 0.00537;
    d = 0.00003657;
    rpm = 3000;
    dist = new double[8][2];

    dist[0][0] = 110;
    dist[0][1] = 2000;
    dist[1][0] = 125;
    dist[1][1] = 2000;
    dist[2][0] = 140;
    dist[2][1] = 2200;
    dist[3][0] = 150;
    dist[3][1] = 2400;
    dist[4][0] = 155;
    dist[4][1] = 2550;
    dist[5][0] = 163;
    dist[5][1] = 2800;
    dist[6][0] = 169;
    dist[6][1] = 3000;
    dist[7][0] = 175;
    dist[7][1] = 5000;
    

    ShootPID = new PIDController(p, i, d);

    ShootPID.setTolerance(10);

    SmartDashboard.putNumber("speed", 3000);
  }

  @Override
  public void initialize() {
    Robot.m_shooterSubsystem.setPower(0);
    Robot.m_shootervisionSubsystem.LightOn();
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
  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0] || distance > dist[7][0]) {
      return 5000;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }
}
