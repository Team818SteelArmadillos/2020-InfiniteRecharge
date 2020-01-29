package frc.robot.commands;

import frc.robot.subsystems.BallPickupSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotLog;

public class BallPickupCommand extends CommandBase {

  private final BallPickupSubsystem m_subsystem;

  public BallPickupCommand(BallPickupSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    RobotLog.putMessage("Running BallPickupCommand");
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
