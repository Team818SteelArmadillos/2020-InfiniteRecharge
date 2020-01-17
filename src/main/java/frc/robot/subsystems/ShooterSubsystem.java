/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.cab.TalonSRX;

public class ShooterSubsystem extends SubsystemBase {
  
  private TalonSRX shooterMotor;

   public ShooterSubsystem() {

    shooterMotor = new TalonSRX(Constants.ShooterMotorType);

    shooterMotor.configureFactoryDefault();

  }

}
