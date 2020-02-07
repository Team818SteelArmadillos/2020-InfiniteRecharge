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
  public double setShooterSpeed;
  private PIDController controllerShooterSpeed;
  public boolean shooterModeImput = false;
  public double verticalAngle;

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
    controllerShooterSpeed.setTolerance(kShooterToleranceRPS);
  }

  //Current shooter speed
  public double getCurrentShooterSpeed(){

    return talonShooterMotor.getSelectedSensorVelocity() * velocityCalculationsPerSecond / encoderPulsesPerRevolution / 60;

  }
  //"goal" shooter speed
  public void shooterSpeed(double shooterSpeed) {
    
    setShooterSpeed = shooterSpeed;

  }

  public void shootingType() {

    if (setShooterSpeed == 0){ 
      
    talonShooterMotor.set(ControlMode.PercentOutput, 0);

  }else{

    talonShooterMotor.set(ControlMode.PercentOutput, controllerShooterSpeed.calculate(getCurrentShooterSpeed(), setShooterSpeed));

  }

  }

  public double getVerticalSin(){
    return Math.sin(verticalAngle);
  }
  //finds distance to target
  public double getTargetDistance(){
    return relativeTargetHeight * getVerticalSin();
  }
//divides distance by max possible distance to find the destired shooter power
  public double autoShooterPower(){
    return (getTargetDistance() / maxShooterDistance) * maxShooterMotorSpeed;
  }

  public void logData(){
    SmartDashboard.putNumber("shooter speed", getCurrentShooterSpeed());
    SmartDashboard.putNumber("target shooter speed", setShooterSpeed);
    SmartDashboard.putNumber("shooter power", talonShooterMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("shooter 0 motor current", talonShooterMotor.getSupplyCurrent());
    //for (int i = 1; i < ShooterMotorArray.length; i++){
      //SmartDashboard.putNumber("shooter " + i + " motor current", victorsShooterMotors[i-1].getSupplyCurrent());
   // }
  }

  public void controlShooterModeSet(){
    
    shooterModeImput = !shooterModeImput;

  }

  public boolean isVerticalPIDRight(){

    if(controllerShooterSpeed.atSetpoint()){
      return true;
    }else{
      return false;
    }

    }
  
    public boolean manualISAtSpeed(){
      if(shooterModeImput == false && getCurrentShooterSpeed() == 1600){
        return true;
      }else{
        return false;
      }
    }

}