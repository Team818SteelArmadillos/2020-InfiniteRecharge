package frc.robot.commands;

import frc.robot.Robot;

import org.ejml.equation.ManagerFunctions.Input1;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {

  private Timer indexTimer;
  boolean jogindexUp, buttonReleased;

  public IndexCommand() {
    indexTimer = new Timer();
    addRequirements(Robot.m_indexSubsystem);
    addRequirements(Robot.m_intakeSubsystem);
  }

  @Override
  public void initialize() {
    indexTimer.start();
    Robot.m_indexSubsystem.setIndexMotor(0);
    jogindexUp = false;
  }

  @Override
  public void execute() {

    // Robot.m_indexSubsystem.setIndexMotor(Robot.m_oi.getIndex());
    Robot.m_indexSubsystem.logData();
    Robot.wof.logData();

    /*if (Robot.m_oi.getSpoolShootButton()) {
      if (indexTimer.get() < 0.5) {
        Robot.m_indexSubsystem.doIndex(-0.3);
      } else {
        Robot.m_indexSubsystem.doIndex(0.6);
      }
    } else */
    if (Robot.m_indexSubsystem.indexSensor()) {
      Robot.m_indexSubsystem.setIndexMotor(0.6);
      indexTimer.reset();
    } else {
      Robot.m_indexSubsystem.setIndexMotor(0);
      indexTimer.reset();
    }
    if (Robot.m_oi.getIntake() && !Robot.m_indexSubsystem.index1Sensor()) {
      Robot.m_intakeSubsystem.setIntakePistons(1);
      Robot.m_intakeSubsystem.setIntakeMotor(0.75);
    } else {
      Robot.m_intakeSubsystem.setIntakePistons(0.5);
      Robot.m_intakeSubsystem.setIntakeMotor(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_indexSubsystem.setIndexMotor(0);
  }

  @Override
  public boolean isFinished() {
    jogindexUp = false;
    // return Robot.m_indexSubsystem.indexSensor();
    return false;
  }
}