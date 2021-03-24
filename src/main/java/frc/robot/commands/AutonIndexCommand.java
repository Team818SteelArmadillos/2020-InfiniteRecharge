package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonIndexCommand extends CommandBase {

  private Timer indexTimer;
  boolean jogindexUp, toggle, buttonIsHeld, runIndex, exitToggle;

  public AutonIndexCommand() {
    indexTimer = new Timer();
    addRequirements(Robot.m_newintakesubsystem);
  }

  @Override
  public void initialize() {
    Robot.m_newintakesubsystem.setIndexMotor(0);
    Robot.m_newintakesubsystem.setIntakePistons(0.5);
    Robot.m_newintakesubsystem.setIntakeMotor(0);
    jogindexUp = false;
    toggle = true;
    buttonIsHeld = false;
    runIndex = true;
    exitToggle = false; 
    
  }

  @Override
  public void execute() {

    // Robot.m_indexSubsystem.setIndexMotor(Robot.m_oi.getIndex());
    Robot.m_newintakesubsystem.logData();

    
     if (toggle) {
      Robot.m_newintakesubsystem.setIntakePistons(1);
      Robot.m_newintakesubsystem.setIntakeMotor(0.85);
  } else { 
    Robot.m_newintakesubsystem.setIntakeMotor(0);
    Robot.m_newintakesubsystem.setIntakePistons(0.5);
  }
      
    

    if (Robot.m_newintakesubsystem.indexSensor1()){
      toggle = false;
    }  

    
    if ( (Robot.m_newintakesubsystem.indexSensor1() && Robot.m_newintakesubsystem.indexSensor2()) || (Robot.m_newintakesubsystem.indexSensor2())) {
      Robot.m_newintakesubsystem.setIndexMotor(0.5);
    } else {
      Robot.m_newintakesubsystem.setIndexMotor(0);
    }

    if(Robot.m_newintakesubsystem.indexSensor2()) {
      exitToggle = true;
      indexTimer.start();
    }


    if (Robot.m_newintakesubsystem.indexSensor3()) {
      runIndex = false;
    }


    //if (!Robot.m_oi.getIntake()){
      //toggle = false;
    //}

  


    /*if (Robot.m_oi.getSpoolShootButton()) {
      if (indexTimer.get() < 0.5) {
        Robot.m_indexSubsystem.doIndex(-0.3);
      } else {
        Robot.m_indexSubsystem.doIndex(0.6);
      }
    } else */

    
    /* if (Robot.m_oi.getIntake() && !Robot.m_newintakesubsystem.indexSensor1()) {
      Robot.m_newintakesubsystem.setIntakePistons(1);
      Robot.m_newintakesubsystem.setIntakeMotor(0.85);
    } else {
       Robot.m_newintakesubsystem.setIntakePistons(0.5);
      Robot.m_newintakesubsystem.setIntakeMotor(0);
    }
    */
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_newintakesubsystem.setIndexMotor(0);
    Robot.m_newintakesubsystem.setIntakeMotor(0);
    Robot.m_newintakesubsystem.setIntakePistons(0.5);
  }
    @Override
  public boolean isFinished() {
    return exitToggle && !Robot.m_newintakesubsystem.indexSensor2() && indexTimer.get() > 0.5;
  }
}