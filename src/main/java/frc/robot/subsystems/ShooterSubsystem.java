/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ShooterConstants.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class ShooterSubsystem extends SubsystemBase {
  
  private TalonFX talon1, talon2;

  //Sets motors 
  public ShooterSubsystem() {

    talon1 = new TalonFX(ShooterMotorArray[0]);
    talon2 = new TalonFX(ShooterMotorArray[1]);
    
    talon1.configFactoryDefault();
    talon2.configFactoryDefault();
    talon2.follow(talon1);
    
    }

  //Current shooter speed
  public double getCurrentShooterSpeed(){

    return talon1.getSelectedSensorVelocity() * velocityCalculationsPerSecond *-1 * 60 / encoderPulsesPerRevolution;
    //Revolutions per mintue. Negative is to account for the change in direction.

  }

  public void logData(){
    SmartDashboard.putNumber("shooter speed", getCurrentShooterSpeed());
    SmartDashboard.putNumber("shooter power", talon1.getMotorOutputPercent());
    SmartDashboard.putNumber("shooter 0 motor current", talon1.getSupplyCurrent());

  }

public void setPower(double power) {
talon1.set(ControlMode.PercentOutput, power);
}


}