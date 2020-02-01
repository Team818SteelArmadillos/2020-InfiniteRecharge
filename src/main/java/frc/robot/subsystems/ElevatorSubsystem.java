package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
//import frc.robot.RobotLog;

public class ElevatorSubsystem extends SubsystemBase {
  static TalonSRX elevatorMotor;
  static DoubleSolenoid actuatorPiston;

  public ElevatorSubsystem(int elevatorMotorPort, int[] actuatorPistonPort) {
    elevatorMotor = new TalonSRX(elevatorMotorPort);
    actuatorPiston = new DoubleSolenoid(actuatorPistonPort[0], actuatorPistonPort[1]);

    // RobotLog.putMessage("Running ElevatorSubsystem");
  }

  public void setElevatorMotor(double Speed) {
    elevatorMotor.set(ControlMode.PercentOutput, Speed);
  }

}