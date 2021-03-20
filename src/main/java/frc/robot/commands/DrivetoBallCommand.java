// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Robot;

public class DrivetoBallCommand extends CommandBase {

  PIDController PIDLeft;
  PIDController PIDRight;
  double PIDLeftOutput;
  double distance;
  private boolean reachedTarget;
  Timer timer;
  
  public DrivetoBallCommand() {
    PIDLeft = new PIDController(0.05, 0, 0);
    PIDRight = new PIDController(0.05, 0, 0);
    PIDLeft.setTolerance(2);
    PIDRight.setTolerance(2);
    
    addRequirements(Robot.m_driveSubsystem);
    
  }

  // Command Includes, you start with a target, 
  //keep going until the target is reached, this is determind if the first sensor on the intake has a ball
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetEncoders();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
     
    if(Robot.m_drivevision.getTarget()){
     
      Robot.m_driveSubsystem.shift(false);
     PIDLeftOutput = MathUtil.clamp(PIDLeft.calculate(distance + Robot.m_driveSubsystem.getLeftPosition()), -0.5, 0.5);
     Robot.m_driveSubsystem.setLeftMotors(PIDLeftOutput);
     Robot.m_driveSubsystem.setRightMotors(MathUtil.clamp(PIDRight.calculate(distance + Robot.m_driveSubsystem.getRightPosition()), -0.5, 0.5));

    }
    
   }
 
   // Called once the command ends or is interrupted.
   @Override
   public void end(boolean interrupted) {
     Robot.m_driveSubsystem.setBothMotors(0);
     timer.stop();
   }
 
   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
     return Robot.m_newintakesubsystem.indexSensor1() || (timer.get() > 5);
   
   }
 }
 