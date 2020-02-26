package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {

  boolean jogindexUp, buttonReleased;

  public IndexCommand() {
    addRequirements(Robot.m_indexSubsystem);
  }

  @Override
  public void initialize() {
    Robot.m_indexSubsystem.setIndexMotor(0);
    jogindexUp = false;
  }

  @Override
  public void execute() {
    if (Robot.m_indexSubsystem.indexSensor()) {
      Robot.m_indexSubsystem.setIndexMotor(1.00);
    } else {
      Robot.m_indexSubsystem.setIndexMotor(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_indexSubsystem.setIndexMotor(0);
  }

  @Override
  public boolean isFinished() {
    jogindexUp = false;
    return Robot.m_indexSubsystem.indexSensor();
  }
}