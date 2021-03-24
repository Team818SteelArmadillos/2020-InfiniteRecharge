
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Robot;

public class TeleopAutoShoot extends CommandBase {
  PIDController AnglePID;
  double motorpower;
  double err;

  public TeleopAutoShoot() {
    addRequirements(Robot.m_driveSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    addRequirements(Robot.m_shootervisionSubsystem);
    AnglePID = new PIDController(1.5, 0, 0);
    motorpower = 0.0;
    err = 100.0;
  }

  @Override
  public void initialize() {
    Robot.m_shootervisionSubsystem.LightOn();
  }

  @Override
  public void execute() {
    if(!Robot.m_drivevision.getTarget()){
      Robot.m_driveSubsystem.setRightMotors(0.15);
      Robot.m_driveSubsystem.setLeftMotors(-0.15);
    } else {
      err = Robot.m_drivevision.getX() - (Math.PI/2);
      motorpower = MathUtil.clamp(AnglePID.calculate(err), -0.075, 0.075);
      Robot.m_driveSubsystem.setRightMotors(-motorpower);
      Robot.m_driveSubsystem.setLeftMotors(motorpower);
    } 
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_shootervisionSubsystem.LightOff();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
