// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotLog;

import static frc.robot.Constants.Pistons.*;
import static frc.robot.Constants.motorPorts.*;

public class NewIntakeSubsystem extends SubsystemBase {
  static VictorSPX intakemotor;
  static DoubleSolenoid intakepistonOne;
  double intakePistonVal;
  public boolean isPistonOut = false;
  public DigitalInput inputlow, inputmid, inputhigh;
  public TalonFX indexMotor;
  public int sensor1counter = 0;
  public int sensor2counter = 0;
  public int sensor3counter = 0;

  public NewIntakeSubsystem(){
    intakemotor = new VictorSPX(intakeMotorPort);
    intakepistonOne = new DoubleSolenoid(13, intakePistonPortOne[0], intakePistonPortOne[1]);
    indexMotor = new TalonFX(indexMotorPort);
    indexMotor.configFactoryDefault();
    indexMotor.setNeutralMode(NeutralMode.Brake);
    indexMotor.setInverted(Constants.Numbers.inverseIndex);
    inputhigh = new DigitalInput(Constants.sensorPorts.indexSensor3);
    inputmid = new DigitalInput(Constants.sensorPorts.indexSensor1);
    inputlow = new DigitalInput(Constants.sensorPorts.indexSensor2);
  
    RobotLog.putMessage("Running IntakeSubsystem");  
  }



  public void setIntakeMotor(double Speed){
    intakemotor.set(ControlMode.PercentOutput, -Speed);
  }

  public void setIntakePistons(double intakePistonVal) {
    if (intakePistonVal == 0.5) {
      intakepistonOne.set(DoubleSolenoid.Value.kReverse);
      isPistonOut = false;
    } else if (intakePistonVal == 1) {
      intakepistonOne.set(DoubleSolenoid.Value.kForward);
      isPistonOut = true;
    }
  
    }
    
    public void logDataElevator() {
      SmartDashboard.putNumber("Intake Power:", intakemotor.getMotorOutputPercent());
        if (intakePistonVal == 0.5) {
        SmartDashboard.putString("Intake Piston Position:", "In");
      } else if (intakePistonVal == 1) {
        SmartDashboard.putString("Intake Piston Position:", "Out");
      }
  
    }
    public void setIndexMotor(double Speed) {
      indexMotor.set(TalonFXControlMode.PercentOutput, -Speed);
    }
  
    public boolean indexSensor1() {
      if(!inputlow.get() && !(sensor1counter >= 20)){
        sensor1counter++;
      }
      else if(inputlow.get() && !(sensor1counter <= 0)) {
        sensor1counter--;
        
      }

      return sensor1counter == 20;

    }

    public boolean indexSensor2() {
      return !inputmid.get();
    }
  
    public boolean indexSensor3() {
      return !inputhigh.get();
    }

    public void doIndex(double Speed) {
      indexMotor.set(ControlMode.PercentOutput, -Speed);
    }
  
    public void notIndex() {
      indexMotor.set(ControlMode.PercentOutput, 0);
    }
  
    public void logData(){
      SmartDashboard.putBoolean("Sensorhigh", inputhigh.get());
      SmartDashboard.putBoolean("Sensormid", inputmid.get());
      SmartDashboard.putBoolean("Sensorlow", inputlow.get());
    }
  
  
  



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
