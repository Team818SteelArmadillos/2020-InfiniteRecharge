package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.RobotLog;

public class ElevatorSubsystem extends SubsystemBase {
  static TalonFX elevatorMotorOne;
  // static TalonFX elevatorMotorTwo;
  static DoubleSolenoid actuatorPiston;
  double pistonVal;

  public ElevatorSubsystem() {
    elevatorMotorOne = new TalonFX(Constants.elevatorMotorPortOne);
    // elevatorMotorTwo = new TalonFX(elevatorMotorPortTwo);
    actuatorPiston = new DoubleSolenoid(Constants.actuatorPistonPort[0], Constants.actuatorPistonPort[1]);

    elevatorMotorOne.configFactoryDefault();
    elevatorMotorOne.setNeutralMode(NeutralMode.Brake);
    elevatorMotorOne.configOpenloopRamp(0.2, 30);

    RobotLog.putMessage("Running ElevatorSubsystem");
  }

  public void setElevatorMotor(double Speed) {
    elevatorMotorOne.set(ControlMode.PercentOutput, Speed);
    // elevatorMotorTwo.set(ControlMode.PercentOutput, -Speed);
  }

  public void setPiston(double pistonVal) {
    if (pistonVal == 0) {
      actuatorPiston.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == 0.5) {
      actuatorPiston.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == 1) {
      actuatorPiston.set(DoubleSolenoid.Value.kForward);
    }

  }

  public void logDataElevator() {
    SmartDashboard.putNumber("Elevator Power:", elevatorMotorOne.getMotorOutputPercent());
    SmartDashboard.putNumber("Elevator Current:", elevatorMotorOne.getSupplyCurrent());
    SmartDashboard.putNumber("Elevator Temperature:", elevatorMotorOne.getTemperature());

    if (pistonVal == 0) {
      SmartDashboard.putString("Elev Piston Position:", "Off");
    } else if (pistonVal == 0.5) {
      SmartDashboard.putString("Elev Piston Position:", "Going in");
    } else if (pistonVal == 1) {
      SmartDashboard.putString("Elev Piston Position:", "Going out");
    }

  }

}
