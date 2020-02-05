package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {

  boolean jogindexUp, buttonReleased;

  public IndexCommand() {
    addRequirements(Robot.m_indexSubsystem);
  }

  protected void execute() {
    if(oi.getindexUp() && buttonReleased) {
      jogindexHatchUp = true;
    } 

    if(oi.getIndexButton() && buttonReleased) {
      shootindexBall = true;
      ballTimer.start();
      buttonReleased = false;
    }

    if (oi.getindexButtonIn()) {
      index.setindexMotor(-1.00);
    } else if (oi.getindexButtonOut()) {
      index.setindexMotor(1.00);
    } else {
      index.setindexMotor(0);
    }
    if(jogBallUp) {
      index.setindexMotor(-0.40);
    
    }
  @Override
  public void initialize() {
    Robot.m_indexSubsystem.setIndexMotor(1);
    jogindexUp = false;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_indexSubsystem.setIndexMotor(0);
  }

  @Override
  public boolean isFinished() {
    return Robot.m_indexSubsystem.indexSensor();
  }
}