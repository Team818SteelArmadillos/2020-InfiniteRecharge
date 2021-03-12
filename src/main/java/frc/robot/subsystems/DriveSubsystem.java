/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.Constants.Pistons.*;
//import static frc.robot.Constants.sensorPorts.*;

/**
 * Add your docs here.
 */



public class DriveSubsystem extends SubsystemBase {
  // private FalconFX talonLeft, talonRight1;
  private TalonFX talonLeft2, talonRight2;
  private TalonFX talonLeft1, talonRight1;
  private DoubleSolenoid shiftPistonLeft;

  private int leftOffset = 0;
  private int rightOffset = 0;

boolean brake = false;
boolean highGear = false;
boolean isHighGear = false;

double high =8.41;
double low = 21.67;

  PIDController controllerDistance;



  private AnalogGyro gyro;

  public DriveSubsystem() {
    gyro = new AnalogGyro(new AnalogInput(GYRO_PORT));
    talonLeft1 = new TalonFX(MOTOR_PORTS_LEFT[0]);
    talonRight1 = new TalonFX(MOTOR_PORTS_RIGHT[0]);
    talonLeft2 = new TalonFX(MOTOR_PORTS_LEFT[1]);
    talonRight2 = new TalonFX(MOTOR_PORTS_RIGHT[1]);

    talonLeft1.configFactoryDefault();
    talonLeft1.setInverted(LEFT_INVERTED);
    talonLeft1.configOpenloopRamp(RAMP_RATE);

    talonRight1.configFactoryDefault();
    talonRight1.setInverted(!LEFT_INVERTED);
    talonRight1.configOpenloopRamp(RAMP_RATE);

    talonLeft2.configFactoryDefault();
    talonLeft2.setInverted(LEFT_INVERTED);
    talonLeft2.follow(talonLeft1);
    talonLeft2.setInverted(LEFT_INVERTED);
      
    talonRight2.configFactoryDefault();
    talonRight2.setInverted(!LEFT_INVERTED);
    talonRight2.follow(talonRight1);
    talonRight2.setInverted(!LEFT_INVERTED);
    
    shiftPistonLeft = new DoubleSolenoid(13, shiftPistonPorts[0], shiftPistonPorts[1]);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shift(boolean highGear){
    isHighGear = highGear;
    if(highGear){
      shiftPistonLeft.set(DoubleSolenoid.Value.kReverse);
    }else{
      shiftPistonLeft.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean currentGear(){
    return isHighGear;
  }

  public void setBothMotors(double speedLeft, double speedRight) {
    setLeftMotors(speedLeft);
    setRightMotors(speedRight);
  }

  public double getDistancePIDOutput(double disVal) {
    return controllerDistance.calculate(disVal);
  }

  public void setDistanceSetPoint(double setpoint) {
    controllerDistance.setSetpoint(setpoint);
  }

  public void setLeftMotors(double speed) {
    talonLeft1.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotors(double speed) {
    talonRight1.set(ControlMode.PercentOutput, speed);
  }

  public void setBothMotors(double speed) {
    setLeftMotors(speed);
    setRightMotors(speed);
  }

  public void resetEncoders() {
    talonLeft1.setSelectedSensorPosition(0);
    talonRight1.setSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    if (isHighGear){
      return (talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / high;
    } else {
      return (talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / low;
    }

  }

  public double getRightPosition() {
    if (isHighGear){
      return (talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / high;
    } else {
      return (talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / low;
    }
  
  }

  public double getPosition() {
    return (getRightPosition() + getLeftPosition()) * 0.5;
  }

  public double getLeftVelocity() {
    if(isHighGear){
    return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * high);
    } else {
      return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * low);
    }
  }

  public double getRightVelocity() {
    if(isHighGear){
    return talonRight1.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * high);
    } else {
    return talonRight1.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * low);
  }
}

  public double getVelocity() {
    return (getLeftVelocity() + getRightVelocity()) * 0.5;
  }

  public double getRightTemp() {
    return talonRight1.getTemperature();
  }

  public boolean distanceOnSetpoint() {
    return controllerDistance.atSetpoint();
  }
  
  public double getLeftTemp(int leftTemp) {
    if (leftTemp == 0) {
      return talonLeft1.getTemperature();
    } else if (leftTemp > 0 && MOTOR_PORTS_LEFT.length > leftTemp) {
      return talonLeft2.getTemperature();
    } else {
      return 0;
    }

  }
  public double getMaxLeftTemp() {
    double maxLeftTemp = talonLeft1.getTemperature();
  
      if(talonLeft2.getTemperature()>maxLeftTemp) {
        maxLeftTemp = talonLeft2.getTemperature();
      }
    
    return maxLeftTemp;
  }

  public double getRightTemp(int rightTemp) {
    if (rightTemp == 0) {
      return talonRight1.getTemperature();
    } else if (rightTemp > 0 && MOTOR_PORTS_RIGHT.length > rightTemp) {
      return talonRight2.getTemperature();
    } else {
      return 0;
    }
  }

  public double getMaxRightTemp() {
    double maxRightTemp = talonRight1.getTemperature();
    
      if(talonRight2.getTemperature()>maxRightTemp) {
        maxRightTemp = talonRight2.getTemperature();
  
      }
    return maxRightTemp;
  }
  public double getAngle(){
    return gyro.getAngle();
  }

  public void resetGyro(){
    gyro.reset();
  }

  public void logData() {
    // Logging Data
    SmartDashboard.putNumber("Left Wheel Position(rev)", getLeftPosition());
    SmartDashboard.putNumber("Right Wheel Position(rev)", getRightPosition());

    SmartDashboard.putNumber("Left Wheel Velocity(rpm)", getLeftVelocity());
    SmartDashboard.putNumber("Right Wheel Velocity(rpm)", getRightVelocity());
    SmartDashboard.putNumber("Angle", getAngle());
    SmartDashboard.putBoolean("Brake Mode", brake);

    for (int i = 0; i < MOTOR_PORTS_LEFT.length; i++) {

      SmartDashboard.putNumber("Left Motor " + (i) + " Temp(C)", getLeftTemp(i));

    }

    for (int i = 0; i < MOTOR_PORTS_RIGHT.length; i++) {

      SmartDashboard.putNumber("Right Motor " + (i) + " Temp(C)", getRightTemp(i));

    }

    SmartDashboard.putNumber("Left Power", talonLeft1.getMotorOutputPercent());
    SmartDashboard.putNumber("Right Power", talonRight1.getMotorOutputPercent());

    SmartDashboard.putNumber("Gyro Angle", getAngle());
    

  }

  public void setBrakeMode(Boolean brakeMode) {

    brake = brakeMode;

    if (brakeMode) {

      talonRight1.setNeutralMode(NeutralMode.Brake);
      talonLeft1.setNeutralMode(NeutralMode.Brake);

      
        talonLeft2.setNeutralMode(NeutralMode.Brake);
      

     
        talonRight2.setNeutralMode(NeutralMode.Brake);
      

    } else {

      talonRight1.setNeutralMode(NeutralMode.Coast);
      talonLeft1.setNeutralMode(NeutralMode.Coast);

      
        talonLeft2.setNeutralMode(NeutralMode.Coast);
      

     
        talonRight2.setNeutralMode(NeutralMode.Coast);
      
    }
  }
}
