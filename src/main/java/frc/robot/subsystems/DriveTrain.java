/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.DriveConstants.*;
/**
 * Add your docs here.
 */

 //As it stands, this *should* drive. 

public class DriveTrain extends SubsystemBase{
//private TalonSRX talonLeft, talonRight;
private TalonFX[] talonsLeft, talonsRight;
private TalonFX talonLeft, talonRight;

private int leftOffset = 0;
private int rightOffset = 0;

private final double distancePerPulse = Math.PI * Constants.WHEEL_DIAMETER * Constants.ENCODER_PULSES_PER_REVOLUTION;
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public DriveTrain() {
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
    talonsLeft[i-1] = new TalonFX(MOTOR_PORTS_LEFT[i]);
    talonsLeft[i-1].configFactoryDefault();
    talonsLeft[i-1].follow(talonLeft);
    talonsLeft[i-1].setInverted(LEFT_INVERTED);
  }
  talonsRight = new TalonFX[MOTOR_PORTS_RIGHT.length - 1];
  for (int i = 1; i < MOTOR_PORTS_RIGHT.length; i++) {
    talonsRight[i-1] = new TalonFX(MOTOR_PORTS_RIGHT[i]);
    talonsRight[i-1].configFactoryDefault();
    talonsRight[i-1].follow(talonRight);
    talonsRight[i-1].setInverted(!LEFT_INVERTED);
    // Should the inverted be Left
  }
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

public double getLeftDistance() {
  return (talonLeft.getSelectedSensorPosition() - leftOffset) * distancePerPulse;
}

public double getRightDistance() {
  return (talonRight.getSelectedSensorPosition() - rightOffset) * distancePerPulse;
}

public double getDistance() {
  return (getRightDistance() + getLeftDistance()) * 0.5;
}

public double getLeftVelocity() {
  return talonLeft.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND / 12;
}

public double getRightVelocity() {
  return talonRight.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND / 12;
}

public double getVelocity() {
  return(getLeftVelocity() + getRightVelocity()) * 0.5;
}
}