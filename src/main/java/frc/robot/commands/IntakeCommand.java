package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotLog;

public class IntakeCommand extends CommandBase {
  Timer intakePistonTimer;
  private double intakePistonVal;
  boolean sampleBool;

  public IntakeCommand() {
    addRequirements(Robot.m_intakeSubsystem);

    intakePistonTimer = new Timer();
    sampleBool = false;
  }

  @Override
  public void initialize() {
    Robot.m_intakeSubsystem.setIntakePistons(0);
    RobotLog.putMessage("Running IntakeCommand");
  }

  @Override
  public void execute() {
    if (Robot.m_oi.getIntake()) {
      Robot.m_intakeSubsystem.setIntakePistons(0.5);
      intakePistonTimer.start();
      if (intakePistonTimer.hasPeriodPassed(0.5)) {
        Robot.m_intakeSubsystem.setIntakeMotor(1);
        intakePistonTimer.stop();
        intakePistonTimer.reset();
    }

    } else if (!Robot.m_oi.getIntake()) {
        Robot.m_intakeSubsystem.setIntakeMotor(0);
        intakePistonTimer.start();
        if (intakePistonTimer.hasPeriodPassed(0.5)) {
          Robot.m_intakeSubsystem.setIntakeMotor(1);
          intakePistonTimer.stop();
          intakePistonTimer.reset();
          sampleBool = true;
        }

      } else if (!Robot.m_oi.getIntake()) {

        if (intakePistonVal == 0) {
          // Nothing
        } else {
          Robot.m_intakeSubsystem.setIntakeMotor(0);
          intakePistonTimer.start();
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    RobotLog.putMessage("Interrupted IntakeCommand");
    Robot.m_intakeSubsystem.setIntakeMotor(0);

  }
}
