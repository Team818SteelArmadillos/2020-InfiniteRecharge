package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import frc.robot.RobotLog;

public class ElevatorSubsystem extends SubsystemBase {
  static TalonFX elevatorMotorOne;
  static TalonFX elevatorMotorTwo;
  static DoubleSolenoid actuatorPiston;
  double pistonVal;

  public ElevatorSubsystem(int elevatorMotorPortOne, int elevatorMotorPortTwo, int[] actuatorPistonPort) {
    elevatorMotorOne = new TalonFX(elevatorMotorPortOne);
    elevatorMotorTwo = new TalonFX(elevatorMotorPortTwo);
    actuatorPiston = new DoubleSolenoid(actuatorPistonPort[0], actuatorPistonPort[1]);

    // RobotLog.putMessage("Running ElevatorSubsystem");
  }

  public void setElevatorMotors(double Speed) {
    elevatorMotorOne.set(ControlMode.PercentOutput, Speed);
    elevatorMotorTwo.set(ControlMode.PercentOutput, -Speed);
  }

  public void setPiston(double pistonVal) {
    if (pistonVal == 0) {
      actuatorPiston.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == 0.5){
      actuatorPiston.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == 1){
      actuatorPiston.set(DoubleSolenoid.Value.kForward);
    }
  }

}
