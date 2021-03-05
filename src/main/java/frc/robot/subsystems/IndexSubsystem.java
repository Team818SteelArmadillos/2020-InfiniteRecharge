package frc.robot.subsystems;

import static frc.robot.Constants.motorPorts.indexMotorPort;
import static frc.robot.Constants.sensorPorts.*;
import static frc.robot.Constants.Numbers.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.motorPorts.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
  private DigitalInput input1, input2, input3;
  private TalonFX indexMotor;

  public IndexSubsystem() {
    indexMotor = new TalonFX(indexMotorPort);
    indexMotor.configFactoryDefault();
    indexMotor.setNeutralMode(NeutralMode.Brake);
    indexMotor.setInverted(inverseIndex);
   input1 = new DigitalInput(indexSensor1);
   input2 = new DigitalInput(indexSensor2);
    input3 = new DigitalInput(indexSensor3);
  }

  public void setIndexMotor(double Speed) {
    indexMotor.set(TalonFXControlMode.PercentOutput, -Speed);
  }

  public boolean indexSensor() {
    return !((input2.get()) || !input3.get());
  }

  public boolean index1Sensor() {
    return !input1.get();
  }

  public void doIndex(double Speed) {
    indexMotor.set(ControlMode.PercentOutput, -Speed);
  }

  public void notIndex() {
    indexMotor.set(ControlMode.PercentOutput, 0);
  }

  public void logData(){
    SmartDashboard.putBoolean("SensorA", input1.get());
    SmartDashboard.putBoolean("SensorB", input2.get());
    SmartDashboard.putBoolean("SensorC", input3.get());
  }
}