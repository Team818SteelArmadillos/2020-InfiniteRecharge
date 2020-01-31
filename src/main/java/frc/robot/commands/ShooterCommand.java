/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
 
  public ShooterCommand() {
    addRequirements(Robot.m_shooterSubsystem);
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
