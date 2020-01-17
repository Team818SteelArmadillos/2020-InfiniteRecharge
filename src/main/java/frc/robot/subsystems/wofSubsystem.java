/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import frc.robot.Constants;
import static frc.robot.Constants.motorPorts.*;
import frc.robot.Constants.sensorPorts;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;


public class wofSubsystem extends SubsystemBase {
  private TalonSRX wofSRX; 
  private final ColorSensorV3 wofColor;
  private final I2C.Port i2cPort;
  public wofSubsystem() {
    i2cPort = I2C.Port.kOnboard;
    wofSRX = new TalonSRX(WOF_MOTOR_PORT);
    wofColor = new ColorSensorV3(i2cPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
