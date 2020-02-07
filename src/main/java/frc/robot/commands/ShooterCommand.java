/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.subsystems.ShooterSubsystem.*;
import frc.robot.subsystems.VisionSubsystem.*;

public class ShooterCommand extends CommandBase {
  
  Timer timer;

  public ShooterCommand() {
    addRequirements(Robot.m_shooterSubsystem);
    addRequirements(Robot.m_visionSubsystem);
    addRequirements(Robot.m_indexSubsystem);
    timer = new Timer();
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double verticalAngle = Robot.m_visionSubsystem.getY();
    boolean isVerticalPIDRight = Robot.m_shooterSubsystem.isVerticalPIDRight();
    boolean isManualShooterSpooled = Robot.m_shooterSubsystem.manualISAtSpeed();
    // Setting automatic or manual shooter; automatic = true, manual = false
    if(Robot.m_shooterSubsystem.shooterModeImput = true){
      //Get vertical angle
      
      //automatic mode
     
     Robot.m_shooterSubsystem.shooterSpeed(Robot.m_shooterSubsystem.autoShooterPower());

     //auto fire if conditions met
     if(isVerticalPIDRight = true && horizontalPIDAdjustFutureThing < .5){

      timer.reset();
      timer.start();
      Robot.m_indexSubsystem.setIndexMotor(1.0);

     }

     if(timer.hasPeriodPassed(1)){

      Robot.m_indexSubsystem.setIndexMotor(0);
      timer.stop();
      timer.reset();
  
    }

  }else{

      //manual mode
     if(Robot.m_oi.spoolShooterMotorManual()){
     Robot.m_shooterSubsystem.shooterSpeed(1600);
     }else if(Robot.m_oi.shooterManualFire()){
      Robot.m_shooterSubsystem.shooterSpeed(1600);
     }else{
      Robot.m_shooterSubsystem.shooterSpeed(0);
     }

    if(Robot.m_oi.shooterManualFire() && isManualShooterSpooled == true){

    timer.reset();
    timer.start();
    Robot.m_indexSubsystem.setIndexMotor(1);

    }else{
      Robot.m_shooterSubsystem.shooterSpeed(1600);
  }

  if(timer.hasPeriodPassed(1)){
    Robot.m_indexSubsystem.setIndexMotor(0);
    timer.stop();
    timer.reset();

  }
  
  }

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
