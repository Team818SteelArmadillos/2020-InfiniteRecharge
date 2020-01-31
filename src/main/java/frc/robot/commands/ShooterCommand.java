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

public class ShooterCommand extends CommandBase {
  
  Timer timer;

  public ShooterCommand() {
    addRequirements(Robot.m_shooterSubsystem);
    timer = new Timer();
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // Setting automatic or manual shooter; automatic = true, manual = false
    if(Robot.m_shooterSubsystem.shooterModeImput = true){
      //automatic mode
     
  }else{
      //manual mode
      Robot.m_shooterSubsystem.shooterSpeed(1600);

  if(Robot.m_oi.shooterManualFire()){

    timer.reset();
    timer.start();
    Robot.index.setindexMotor(1);

  }  
  if(timer.hasPeriodPassed(2)){
    Robot.index.setindexMotor(0);
    timer.stop();

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
