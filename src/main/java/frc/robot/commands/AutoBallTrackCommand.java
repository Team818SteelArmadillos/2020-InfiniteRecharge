
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Robot;

public class AutoBallTrackCommand extends CommandBase {

  private boolean hasTarget;
  private boolean reachedTarget;
  PIDController AnglePID;
  double motorpower;
  double err;//how far off the robot is from the target
  

  public AutoBallTrackCommand() {

    addRequirements(Robot.m_driveSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    addRequirements(Robot.m_drivevision); //rename the drivevison to drivevisionsubsystem
    addRequirements(Robot.m_shooterSubsystem);
    AnglePID = new PIDController(1.5, 0, 0);
    motorpower = 0.0;
    err = 100.0;

  }

  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_shooterSubsystem.shooterSpeed(0);

  }

  @Override
  public void execute() {//remeber to reset the tolerance to make it so the robot can actually agnle tot he ball.
    Robot.m_driveSubsystem.shift(false);
    
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
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(err) < 0.01 && Math.abs(Robot.m_driveSubsystem.getRightVelocity()) < 0.1 ;
  }
}
