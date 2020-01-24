package frc.robot.commands;

import frc.robot.Robot;
// import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
//import frc.robot.RobotLog;

public class ElevatorCommand extends CommandBase {
  Timer pistonTimer;

  public ElevatorCommand(int pistonVal) {
    addRequirements(Robot.m_elevatorSubsystem);

    pistonTimer = new Timer();
  }

  public void initialize() {
    Robot.m_elevatorSubsystem.setPiston(0);
    // RobotLog.putMessage("Running ElevatorCommand");
  }

  public void execute() {
    if (Robot.m_oi.getElevatorUp()) {
      Robot.m_elevatorSubsystem.setPiston(0.5);
      pistonTimer.start();
      if (pistonTimer.hasPeriodPassed(0.5)) {
        Robot.m_elevatorSubsystem.setElevatorMotors(.7);
        pistonTimer.stop();
        pistonTimer.reset();
      }
    } else if (Robot.m_oi.getElevatorDown()) {
      Robot.m_elevatorSubsystem.setPiston(0.5);
      pistonTimer.start();
      if (pistonTimer.hasPeriodPassed(0.5)) {
        Robot.m_elevatorSubsystem.setElevatorMotors(.7);
        pistonTimer.stop();
        pistonTimer.reset();
      }

    } else {
      Robot.m_elevatorSubsystem.setElevatorMotors(0);
      pistonTimer.start();
      if (pistonTimer.hasPeriodPassed(0.5))
        Robot.m_elevatorSubsystem.setElevatorMotors(1);
      pistonTimer.stop();
      pistonTimer.reset();
    }
  }

  // idk if it goes in finished or not but we need to get timer in feild so it
  // shuts off motors, half a
  // sec passes, and piston is put in to be safe. Also the timer from the field goes DOWNWARDS

  public boolean isFinished() {
    return false;
  }

  public void end(boolean interrupted) {
    Robot.m_elevatorSubsystem.setElevatorMotors(0);
    Robot.m_elevatorSubsystem.setPiston(1);
  }

}
