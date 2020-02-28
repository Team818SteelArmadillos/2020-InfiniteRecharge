/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ShooterConstants.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ShooterSubsystem extends SubsystemBase {
  
  public TalonSRX talonShooterMotor;
  public VictorSPX[] victorsShooterMotors;
  public double targetShooterSpeed;
  private PIDController controllerShooterSpeed;
  public boolean shooterModeImput = false;

  //Sets motors 
  public ShooterSubsystem() {

    talonShooterMotor = new TalonSRX(ShooterMotorArray[0]);

    talonShooterMotor.configFactoryDefault();

    victorsShooterMotors = new VictorSPX[ShooterMotorArray.length - 1];
    for (int i = 1; i < ShooterMotorArray.length; i++){
      victorsShooterMotors[i - 1] = new VictorSPX(ShooterMotorArray[i]);
      victorsShooterMotors[i - 1].configFactoryDefault();
      victorsShooterMotors[i - 1].follow(talonShooterMotor);
    }
    controllerShooterSpeed = new PIDController(kP, kI, kD);
    controllerShooterSpeed.setTolerance(kShooterToleranceRPM);
  }

  //Current shooter speed
  public double getCurrentShooterSpeed(){

    return talonShooterMotor.getSelectedSensorVelocity() * velocityCalculationsPerSecond / encoderPulsesPerRevolution / 60;

  }
  //"goal" shooter speed
  public void shooterSpeed(double shooterSpeed) {
    
    targetShooterSpeed = shooterSpeed;

  }

  public void shootingType() {

    if (targetShooterSpeed == 0){ 
      
    talonShooterMotor.set(ControlMode.PercentOutput, 0);

  }else{

    talonShooterMotor.set(ControlMode.PercentOutput, controllerShooterSpeed.calculate(getCurrentShooterSpeed(), targetShooterSpeed));

  }

  }

  
//divides distance by max possible distance to find the destired shooter power
  public double autoShooterPower(){
    return (0 / maxShooterDistance) * maxShooterMotorSpeed;
  }

  public void logData(){
    SmartDashboard.putNumber("shooter speed", getCurrentShooterSpeed());
    SmartDashboard.putNumber("target shooter speed", targetShooterSpeed);
    SmartDashboard.putNumber("shooter power", talonShooterMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("shooter 0 motor current", talonShooterMotor.getSupplyCurrent());
    //for (int i = 1; i < ShooterMotorArray.length; i++){
      //SmartDashboard.putNumber("shooter " + i + " motor current", victorsShooterMotors[i-1].getSupplyCurrent());
   // }
  }

  //public void controlShooterModeSet(){
    
   // shooterModeImput = !shooterModeImput;

 // }

  public boolean shooterAtSpeed(){

    if(controllerShooterSpeed.atSetpoint() && targetShooterSpeed > 0){
      return true;
    }else{
      return false;
    }

    }

}