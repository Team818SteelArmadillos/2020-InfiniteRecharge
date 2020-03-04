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
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.controller.PIDController;
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



public class DriveTrain extends SubsystemBase {
  // private FalconFX talonLeft, talonRight;
  private TalonFX talonsLeft, talonsRight;
  private TalonFX talonLeft, talonRight;
  private DoubleSolenoid shiftPistonLeft;

  private int leftOffset = 0;
  private int rightOffset = 0;

boolean brake = false;
boolean highGear = false;
boolean isHighGear = false;

double low = 21.67;
double high = 8.41;

  PIDController controllerDistance;



  private AnalogGyro gyro;

  public DriveTrain() {
    talonLeft = new TalonFX(MOTOR_PORTS_LEFT[0]);
    talonRight = new TalonFX(MOTOR_PORTS_RIGHT[0]);

    talonLeft.configFactoryDefault();
    talonRight.configFactoryDefault();

    talonLeft.setInverted(LEFT_INVERTED);
    talonRight.setInverted(!LEFT_INVERTED);

    talonLeft.configOpenloopRamp(RAMP_RATE);
    talonRight.configOpenloopRamp(RAMP_RATE);

  
      talonsLeft = new TalonFX(MOTOR_PORTS_LEFT[1]);
      talonsLeft.configFactoryDefault();
      talonsLeft.follow(talonLeft);
      talonsLeft.setInverted(LEFT_INVERTED);
    
 
      talonsRight = new TalonFX(MOTOR_PORTS_RIGHT[1]);
      talonsRight.configFactoryDefault();
      talonsRight.follow(talonRight);
      talonsRight.setInverted(!LEFT_INVERTED);
      // Should the inverted be Left
    
    
    shiftPistonLeft = new DoubleSolenoid(13, shiftPistonPorts[0], shiftPistonPorts[1]);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shift(boolean highGear){
    isHighGear = highGear;
    if(highGear){
      shiftPistonLeft.set(DoubleSolenoid.Value.kForward);
    }else{
      shiftPistonLeft.set(DoubleSolenoid.Value.kReverse);
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
    talonLeft.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotors(double speed) {
    talonRight.set(ControlMode.PercentOutput, speed);
  }

  public void setBothMotors(double speed) {
    setLeftMotors(speed);
    setRightMotors(speed);
  }

  public void resetEncoders() {
    talonLeft.setSelectedSensorPosition(0);
    talonRight.setSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    return (talonLeft.getSelectedSensorPosition() - leftOffset) * distancePerPulse;
  }

  public double getRightPosition() {
    return (talonRight.getSelectedSensorPosition() - rightOffset) * distancePerPulse;
  }

  public double getPosition() {
    return (getRightPosition() + getLeftPosition()) * 0.5;
  }

  public double getLeftVelocity() {
    if(isHighGear){
    return talonLeft.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * high);
    } else {
      return talonLeft.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * low);
    }
  }

  public double getRightVelocity() {
    if(isHighGear){
    return talonRight.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * high);
    } else {
    return talonRight.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND * Math.PI / (12 * low);
  }
}

  public double getVelocity() {
    return (getLeftVelocity() + getRightVelocity()) * 0.5;
  }

  public double getRightTemp() {
    return talonRight.getTemperature();
  }

  public boolean distanceOnSetpoint() {
    return controllerDistance.atSetpoint();
  }
  
  public double getLeftTemp(int leftTemp) {
    if (leftTemp == 0) {
      return talonLeft.getTemperature();
    } else if (leftTemp > 0 && MOTOR_PORTS_LEFT.length > leftTemp) {
      return talonsLeft.getTemperature();
    } else {
      return 0;
    }

  }
  public double getMaxLeftTemp() {
    double maxLeftTemp = talonLeft.getTemperature();
  
      if(talonsLeft.getTemperature()>maxLeftTemp) {
        maxLeftTemp = talonsLeft.getTemperature();
      }
    
    return maxLeftTemp;
  }

  public double getRightTemp(int rightTemp) {
    if (rightTemp == 0) {
      return talonRight.getTemperature();
    } else if (rightTemp > 0 && MOTOR_PORTS_RIGHT.length > rightTemp) {
      return talonsRight.getTemperature();
    } else {
      return 0;
    }
  }

  public double getMaxRightTemp() {
    double maxRightTemp = talonRight.getTemperature();
    
      if(talonsRight.getTemperature()>maxRightTemp) {
        maxRightTemp = talonsRight.getTemperature();
  
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

    SmartDashboard.putBoolean("Brake Mode", brake);

    for (int i = 0; i < MOTOR_PORTS_LEFT.length; i++) {

      SmartDashboard.putNumber("Left Motor " + (i) + " Temp(C)", getLeftTemp(i));

    }

    for (int i = 0; i < MOTOR_PORTS_RIGHT.length; i++) {

      SmartDashboard.putNumber("Right Motor " + (i) + " Temp(C)", getRightTemp(i));

    }

    SmartDashboard.putNumber("Left Power", talonLeft.getMotorOutputPercent());
    SmartDashboard.putNumber("Right Power", talonRight.getMotorOutputPercent());

    SmartDashboard.putNumber("Gyro Angle", getAngle());
    

  }

  public void setBrakeMode(Boolean brakeMode) {

    brake = brakeMode;

    if (brakeMode) {

      talonRight.setNeutralMode(NeutralMode.Brake);
      talonLeft.setNeutralMode(NeutralMode.Brake);

      
        talonsLeft.setNeutralMode(NeutralMode.Brake);
      

     
        talonsRight.setNeutralMode(NeutralMode.Brake);
      

    } else {

      talonRight.setNeutralMode(NeutralMode.Coast);
      talonLeft.setNeutralMode(NeutralMode.Coast);

      
        talonsLeft.setNeutralMode(NeutralMode.Coast);
      

     
        talonsRight.setNeutralMode(NeutralMode.Coast);
      
    }
  }
}
