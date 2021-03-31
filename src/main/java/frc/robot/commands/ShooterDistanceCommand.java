
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterDistanceCommand extends CommandBase {
  /** Creates a new ShooterDistanceCommand. */
  public ShooterDistanceCommand() {
    addRequirements(Robot.m_shootervisionSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_shootervisionSubsystem.LightOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("distance", 47.25/Math.tan(Robot.m_shootervisionSubsystem.getY()+(2*Math.PI/18)));
    SmartDashboard.putNumber("Y Distance", Robot.m_shootervisionSubsystem.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_shootervisionSubsystem.LightOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
