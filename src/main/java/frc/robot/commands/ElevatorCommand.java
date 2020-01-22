package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ElevatorCommand extends CommandBase {

  
  public ElevatorCommand() {
    addRequirements(Robot.m_elevatorSubsystem);
  }

  public void initialize() {

    //RobotLog.putMessage("Running ElevatorCommand");
  }

  public void execute() {
    if (Robot.m_oi.getElevatorUp()) {
      subsystem.m_elevatorSubsystem.setElevatorMotor(---);
    } else if (Robot.m_oi.getElevatorDown()) {
      subsystem.m_elevatorSubsystem.setElevatorMotor(---);
    } else {
      subsystem.m_elevatorSubsystem.setElevatorMotor(0);
    }
  }
  
  public void end(boolean interrupted) {
  }

  public boolean isFinished() {
    return false;
  }
}
