package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.subsystems.ShooterSubsystem.*;
import frc.robot.subsystems.VisionSubsystem.*;

public class SpoolShooterCommand extends CommandBase {
  
  Timer timer;

  public SpoolShooterCommand() {
    addRequirements(Robot.m_shooterSubsystem);
    addRequirements(Robot.m_visionSubsystem);
    addRequirements(Robot.m_indexSubsystem);
    timer = new Timer();
  }

  @Override
  public void initialize() {
    Robot.m_indexSubsystem.setIndexMotor(0);
    Robot.m_shooterSubsystem.shooterSpeed(1600);
  }

  @Override
  public void execute() {
    
    Robot.m_shooterSubsystem.shooterSpeed(1600);
  
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_shooterSubsystem.shooterSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
