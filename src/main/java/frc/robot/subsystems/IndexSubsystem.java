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

import edu.wpi.first.wpilibj.command.Subsystem;
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
    //input1 = new DigitalInput(indexSensor1);
    //input2 = new DigitalInput(indexSensor2);
    //input3 = new DigitalInput(indexSensor3);
  }

  public void setindexMotor(double Speed) {
    indexMotor.set(TalonFXControlMode.PercentOutput, Speed);
  }
  public void setIndexMotor(double Speed) {
    indexMotor.set(ControlMode.PercentOutput, Speed);
  }

  public boolean indexSensor() {
    return (!input1.get() && !input2.get()) || input3.get();
  }

  public void doIndex() {
    indexMotor.set(ControlMode.PercentOutput, 1);
  }

  public void notIndex() {
    indexMotor.set(ControlMode.PercentOutput, 0);
  }
}