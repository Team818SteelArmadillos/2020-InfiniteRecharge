package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ElevatorCommand extends CommandBase {
  private final ElevatorSubsystem m_subsystem;

  
  public ElevatorCommand(ElevatorSubsystem subsystem) {
    
    addRequirements(subsystem);
  }

  public void initialize() {
  }

  public void execute() {
  }

  public void end(boolean interrupted) {
  }

  public boolean isFinished() {
    return false;
  }
}
