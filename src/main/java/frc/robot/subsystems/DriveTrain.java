/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
private TalonSRX talonLeft, talonRight;
private VictorSPX[] victorsLeft, victorsRight;

private int leftOffset = 0;
private int rightOffset = 0;

private final double distancePerPulse = Math.PI * Constants.WHEEL_DIAMETER * Constants.ENCODER_PULSES_PER_REVOLUTION;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

public DriveTrain() {
  talonLeft = new TalonSRX(Constants.MOTOR_PORTS_LEFT[0]);
  talonRight = new TalonSRX(Constants.MOTOR_PORTS_RIGHT[0]);

  talonLeft.configFactoryDefault();
  talonRight.configFactoryDefault();

  talonLeft.setInverted(Constants.LEFT_INVERTED);
  talonRight.setInverted(!Constants.LEFT_INVERTED);

  talonLeft.configOpenloopRamp(Constants.RAMP_RATE);
  talonRight.configOpenloopRamp(Constants.RAMP_RATE);

  victorsLeft = new VictorSPX[Constants.MOTOR_PORTS_LEFT.length - 1];
  for (int i = 1; i < Constants.MOTOR_PORTS_LEFT.length; i++) {
    victorsLeft[i-1] = new VictorSPX(Constants.MOTOR_PORTS_LEFT[i]);
    victorsLeft[i-1].configFactoryDefault();
    victorsLeft[i-1].follow(talonLeft);
    victorsLeft[i-1].setInverted(Constants.LEFT_INVERTED);
  }
  victorsRight = new VictorSPX[Constants.MOTOR_PORTS_RIGHT.length - 1];
  for (int i = 1; i < Constants.MOTOR_PORTS_RIGHT.length; i++) {
    victorsRight[i-1] = new VictorSPX(Constants.MOTOR_PORTS_RIGHT[i]);
    victorsRight[i-1].configFactoryDefault();
    victorsRight[i-1].follow(talonRight);
    victorsRight[i-1].setInverted(!Constants.LEFT_INVERTED);
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