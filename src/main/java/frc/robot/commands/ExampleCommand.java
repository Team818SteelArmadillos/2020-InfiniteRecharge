package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExampleCommand extends CommandBase {
  
  private final ExampleSubsystem m_subsystem;

  public ExampleCommand(ExampleSubsystem subsystem) {
    m_subsystem = subsystem;
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
