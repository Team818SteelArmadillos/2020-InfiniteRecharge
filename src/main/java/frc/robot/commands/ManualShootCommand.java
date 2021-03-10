/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.subsystems.ShooterSubsystem.*;
import frc.robot.subsystems.VisionSubsystem.*;

public class ManualShootCommand extends CommandBase {

  public ManualShootCommand() {
    addRequirements(Robot.m_shooterSubsystem);
    addRequirements(Robot.m_visionSubsystem);
    addRequirements(Robot.m_newintakesubsystem);
    
  }

  @Override
  public void initialize() {
    Robot.m_newintakesubsystem.setIndexMotor(0);
    Robot.m_shooterSubsystem.shooterSpeed(1);
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("shooter speed", Robot.m_shooterSubsystem.getCurrentShooterSpeed());
    //double verticalAngle = Robot.m_visionSubsystem.getY();
    boolean isAtSpeed = Robot.m_shooterSubsystem.shooterAtSpeed();
    Robot.m_shooterSubsystem.shootingType();
    // Setting automatic or manual shooter; automatic = true, manual = false
    //if(Robot.m_shooterSubsystem.shooterModeImput = true){
      //Get vertical angle
      
      //automatic mode
     
    // Robot.m_shooterSubsystem.shooterSpeed(Robot.m_shooterSubsystem.autoShooterPower());

     //auto fire if conditions met
     //if(isAtSpeed = true && horizontalPIDAdjustFutureThing < .5 && Robot.m_oi.shooterFire()){

     // timer.reset();
     // timer.start();
     // Robot.m_indexSubsystem.setIndexMotor(1.0);

    // }

    // if(timer.hasPeriodPassed(1)){
      
    //  Robot.m_indexSubsystem.setIndexMotor(0);
    //  timer.stop();
     // timer.reset();
  
    //}

  //}else{

    Robot.m_shooterSubsystem.logData();

    if(Robot.m_shooterSubsystem.getCurrentShooterSpeed() > 4000){

    
    Robot.m_newintakesubsystem.setIndexMotor(0.5);

    }else{

    Robot.m_newintakesubsystem.setIndexMotor(0);
    
  }
  
  }

  //}

  @Override
  public void end(boolean interrupted) {
    Robot.m_newintakesubsystem.setIndexMotor(0);
    Robot.m_shooterSubsystem.shooterSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
