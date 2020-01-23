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
import static frc.robot.Constants.Numbers.*;
import frc.robot.Constants.sensorPorts;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.DriverStation;


public class wofSubsystem extends SubsystemBase {
  private TalonSRX wofSRX; 
  private final ColorSensorV3 wofColor;
  private final I2C.Port i2cPort;

  public wofSubsystem() {
    i2cPort = I2C.Port.kOnboard;
    wofSRX = new TalonSRX(WOF_MOTOR_PORT);
    wofColor = new ColorSensorV3(i2cPort);
  }

  public void doSpin(){
    wofSRX.set(ControlMode.PercentOutput, 0.75);
  }

  public void notSpin(){
    wofSRX.set(ControlMode.PercentOutput, 0);
  }
  
  public char getColor(){
    Color currentColor = wofColor.getColor();
    if(currentColor.red < RED[0][0] && currentColor.red > RED[1][0] && currentColor.green < RED[0][1]
      && currentColor.green > RED[1][1] && currentColor.blue < RED[0][2] && currentColor.blue > RED[1][2]){
      return 'r';
    }
    else if(currentColor.red < GREEN[0][0] && currentColor.red > GREEN[1][0] && currentColor.green < GREEN[0][1]
      && currentColor.green > GREEN[1][1] && currentColor.blue < GREEN[0][2] && currentColor.blue > GREEN[1][2]){
      return 'g';
    }
    else if(currentColor.red < CYAN[0][0] && currentColor.red > CYAN[1][0] && currentColor.green < CYAN[0][1]
      && currentColor.green > CYAN[1][1] && currentColor.blue < CYAN[0][2] && currentColor.blue > CYAN[1][2]){
      return 'c';
    }
    else if(currentColor.red < YELLOW[0][0] && currentColor.red > YELLOW[1][0] && currentColor.green < YELLOW[0][1]
      && currentColor.green > YELLOW[1][1] && currentColor.blue < YELLOW[0][2] && currentColor.blue > YELLOW[1][2]){
      return 'y';
    }
    else{
      return 'n';
    }
  }

  public char getGameData(){
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
  if(gameData.length() > 0)
  {
  switch (gameData.charAt(0))
  {
    case 'B' :
      return 'c';

    case 'G' :
      return 'g';
      
    case 'R' :
      return 'r';
      
    case 'Y' :
      return 'y';
      
    default :
      return 'q';
      
      }
    } 
    else {
      return 'w';
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
