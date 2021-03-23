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

  private boolean hasTarget;
  private boolean reachedTarget;
  PIDController AnglePID;
  double motorpower;
  double err;
  Timer timer;
  boolean timerStarted;
  
  public DrivetoBallCommand() {
    addRequirements(Robot.m_driveSubsystem);
    addRequirements(Robot.m_drivevision); //rename the drivevison to drivevisionsubsystem
    AnglePID = new PIDController(0.5, 0, 0);
    motorpower = 0.0;
    err = 100.0;
    timer = new Timer();
    timerStarted = false;
  }

  // Command Includes, you start with a target, 
  //keep going until the target is reached, this is determind if the first sensor on the intake has a ball
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetEncoders();
    timer.reset();
    //System.out.println("hey");
  }

  // Called every time the scheduler runs while the command is scheduled.
   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
    Robot.m_driveSubsystem.shift(false);

      err = Robot.m_drivevision.getX() - (Math.PI/2);
      motorpower = MathUtil.clamp(AnglePID.calculate(err), -0.05, 0.05);
      
      if(!Robot.m_drivevision.getTarget()){
        if(!timerStarted) {
          timer.start();
          timerStarted = true;
        }
        Robot.m_driveSubsystem.setRightMotors(0);
        Robot.m_driveSubsystem.setLeftMotors(0);
      } else {
        Robot.m_driveSubsystem.setRightMotors(-0.4 - motorpower);
        Robot.m_driveSubsystem.setLeftMotors(-0.4 + motorpower);
      }

  }
     
   
 
   // Called once the command ends or is interrupted.
   @Override
   public void end(boolean interrupted) {
     Robot.m_driveSubsystem.setBothMotors(0);
     //timer.stop();
   }
 
   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
     return !Robot.m_drivevision.getTarget() && timer.get() > 2;
   
   }
 }
 