package frc.robot.commands;

import frc.robot.Robot;
// import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.RobotLog;

public class ElevatorCommand extends CommandBase {

  public ElevatorCommand(int pistonVal) {
    addRequirements(Robot.m_elevatorSubsystem);
  }

  public void initialize() {
    Robot.m_elevatorSubsystem.setPiston(0);
    // RobotLog.putMessage("Running ElevatorCommand");
  }

  public void execute() {
    if (Robot.m_oi.getElevatorUp()) {
      Robot.m_elevatorSubsystem.setElevatorMotors(.7);
      Robot.m_elevatorSubsystem.setPiston(0.5);
    } else if (Robot.m_oi.getElevatorDown()) {
      Robot.m_elevatorSubsystem.setElevatorMotors(.7);
      Robot.m_elevatorSubsystem.setPiston(0.5);
    } else {
      Robot.m_elevatorSubsystem.setElevatorMotors(1);
    }
  }

  public boolean isFinished() {
    return false;
  }

  public void end(boolean interrupted) {
    Robot.m_elevatorSubsystem.setElevatorMotors(0);
    Robot.m_elevatorSubsystem.setPiston(1);
  }

}
