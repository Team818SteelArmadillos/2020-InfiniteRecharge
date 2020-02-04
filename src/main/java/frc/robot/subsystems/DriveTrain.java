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
import frc.robot.Robot;
import frc.robot.Constants.oi;

import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.Constants.Pistons.*;
import static frc.robot.Constants.sensorPorts.*;

/**
 * Add your docs here.
 */

public class DriveTrain extends SubsystemBase {
  // private FalconFX talonLeft, talonRight;
  private TalonFX[] talonsLeft, talonsRight;
  private TalonFX talonLeft, talonRight;

  private DoubleSolenoid shiftPistonLeft, shiftPistonRight;

  private int leftOffset = 0;
  private int rightOffset = 0;

  boolean brake = false;
  boolean isHighGear = false;

  double low = 21.67;
  double high = 8.41;

  PIDController controllerDistance;

  private final double distancePerPulse = ENCODER_PULSES_PER_REVOLUTION;

  private AnalogGyro gyro;
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public DriveTrain() {
    shiftPistonLeft = new DoubleSolenoid(shiftPistonPort[0], shiftPistonPort[1]);
    shiftPistonRight = new DoubleSolenoid(shiftPistonPort[2], shiftPistonPort[3]);
    gyro = new AnalogGyro(DRIVE_GYRO);

    talonLeft = new TalonFX(MOTOR_PORTS_LEFT[0]);
    talonRight = new TalonFX(MOTOR_PORTS_RIGHT[0]);

    talonLeft.configFactoryDefault();
    talonRight.configFactoryDefault();

    talonLeft.setInverted(LEFT_INVERTED);
    talonRight.setInverted(!LEFT_INVERTED);

    talonLeft.configOpenloopRamp(RAMP_RATE);
    talonRight.configOpenloopRamp(RAMP_RATE);

    talonsLeft = new TalonFX[MOTOR_PORTS_LEFT.length - 1];
    for (int i = 1; i < MOTOR_PORTS_LEFT.length; i++) {
      talonsLeft[i - 1] = new TalonFX(MOTOR_PORTS_LEFT[i]);
      talonsLeft[i - 1].configFactoryDefault();
      talonsLeft[i - 1].follow(talonLeft);
      talonsLeft[i - 1].setInverted(LEFT_INVERTED);
    }
    talonsRight = new TalonFX[MOTOR_PORTS_RIGHT.length - 1];
    for (int i = 1; i < MOTOR_PORTS_RIGHT.length; i++) {
      talonsRight[i - 1] = new TalonFX(MOTOR_PORTS_RIGHT[i]);
      talonsRight[i - 1].configFactoryDefault();
      talonsRight[i - 1].follow(talonRight);
      talonsRight[i - 1].setInverted(!LEFT_INVERTED);
      // Should the inverted be Left
    }

    controllerDistance = new PIDController(Kp, Ki, Kd);
    controllerDistance.setTolerance(distanceTolerance);
  }

  public void shift(boolean highGear){
    isHighGear = highGear;
    if(highGear){
      shiftPistonLeft.set(DoubleSolenoid.Value.kForward);
      shiftPistonRight.set(DoubleSolenoid.Value.kForward);
    }else{
      shiftPistonLeft.set(DoubleSolenoid.Value.kReverse);
      shiftPistonRight.set(DoubleSolenoid.Value.kReverse);
    }
  }

  public boolean currentGear(){
    return isHighGear;
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

  public void setBothMotors(double speedLeft, double speedRight) {
    setLeftMotors(speedLeft);
    setRightMotors(speedRight);
  }

  public void resetEncoders() {
    talonLeft.setSelectedSensorPosition(0);
    talonRight.setSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    return (talonLeft.getSelectedSensorPosition() - leftOffset) * distancePerPulse * Math.PI * wheelCircumference / (low);
  }

  public double getRightPosition() {
    return (talonRight.getSelectedSensorPosition() - rightOffset) * distancePerPulse * Math.PI * wheelCircumference / (low);
  }

  public double getPosition() {
    return (getRightPosition() + getLeftPosition()) * 0.5;
  }

  public double getLeftVelocity() {
    return talonLeft.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND / 60;
  }

  public double getRightVelocity() {
    return talonRight.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND / 60;
  }

  public double getVelocity() {
    return (getLeftVelocity() + getRightVelocity()) * 0.5;
  }

  public double getDistancePIDOutput(double disVal) {
    return controllerDistance.calculate(disVal);
  }

  public void setDistanceSetPoint(double setpoint) {
    controllerDistance.setSetpoint(setpoint);
  }

  public boolean distanceOnSetpoint() {
    return controllerDistance.atSetpoint();
  }
  

  public double getLeftTemp(int leftTemp) {
    if (leftTemp == 0) {
      return talonLeft.getTemperature();
    } else if (leftTemp > 0 && MOTOR_PORTS_LEFT.length > leftTemp) {
      return talonsLeft[leftTemp].getTemperature();
    } else {
      return 0;
    }

  }
  public double getMaxLeftTemp() {
    double maxLeftTemp = talonLeft.getTemperature();
    for (int i = 1; i < MOTOR_PORTS_LEFT.length; i++){
      if(talonsLeft[i].getTemperature()>maxLeftTemp) {
        maxLeftTemp = talonsLeft[i].getTemperature();
      }
    }
    return maxLeftTemp;
  }



  public double getRightTemp(int rightTemp) {
    if (rightTemp == 0) {
      return talonRight.getTemperature();
    } else if (rightTemp > 0 && MOTOR_PORTS_RIGHT.length > rightTemp) {
      return talonsRight[rightTemp].getTemperature();
    } else {
      return 0;
    }
  }

  public double getMaxRightTemp() {
    double maxRightTemp = talonRight.getTemperature();
    for (int i = 1; i < MOTOR_PORTS_RIGHT.length; i++){
      if(talonsRight[i].getTemperature()>maxRightTemp) {
        maxRightTemp = talonsRight[i].getTemperature();
      }
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

    SmartDashboard.putBoolean("High Gear", currentGear());
    

  }

  public void setBrakeMode(Boolean brakeMode) {

    brake = brakeMode;

    if (brakeMode) {

      talonRight.setNeutralMode(NeutralMode.Brake);
      talonLeft.setNeutralMode(NeutralMode.Brake);

      for (int i = 1; i < MOTOR_PORTS_LEFT.length; i++) {
        talonsLeft[i - 1].setNeutralMode(NeutralMode.Brake);
      }

      for (int i = 1; i < MOTOR_PORTS_RIGHT.length; i++) {
        talonsRight[i - 1].setNeutralMode(NeutralMode.Brake);
      }

    } else {

      talonRight.setNeutralMode(NeutralMode.Coast);
      talonLeft.setNeutralMode(NeutralMode.Coast);

      for (int i = 1; i < MOTOR_PORTS_LEFT.length; i++) {
        talonsLeft[i - 1].setNeutralMode(NeutralMode.Coast);
      }

      for (int i = 1; i < MOTOR_PORTS_RIGHT.length; i++) {
        talonsRight[i - 1].setNeutralMode(NeutralMode.Coast);
      }
    }
  }
}
