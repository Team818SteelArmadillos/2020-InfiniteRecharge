package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import frc.robot.Constants;
import static frc.robot.Constants.subsystems.*;

public class IndexSubsystem extends SubsystemBase {
  static Victor indexMotor;

  public IndexSubsystem(int indexMotorPort) {
    if (Constants.subsystems.indexEnabled) {
      indexMotor = new Victor(indexMotorPort);
      indexMotor.setInverted(false);
    }
  }

  public void setIndexMotor(double Speed) {
    if (Constants.subsystems.indexEnabled) {
      indexMotor.set(ControlMode.PercentOutput, Speed);
    }
  }

  public void initDefaultCommand() {
    if (Constants.subsystems.indexEnabled) {
      setDefaultCommand(new indexCommand());
    }
  }
}