package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Constants.oi.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.SpoolShooterCommand.*;
import frc.robot.subsystems.ShooterSubsystem.*;

public class OI {

  // Joysticks
  Joystick leftJoyStick, rightJoyStick;
  XboxController gamePad;
  // Elevator Buttons
  JoystickButton elevatorButton;
  // Intake Buttons
  JoystickButton intakeButton;
  // Drive Straight Button
  JoystickButton driveStraightButton;
  JoystickButton dynamicBraking;
  // Shooter Buttons
  JoystickButton shooterControlStateSwitch;
  JoystickButton shooterFireButton;
  JoystickButton spoolShooterManual;

  JoystickButton shiftGear;
  JoystickButton indexButton;
  Trigger jogindexUp;
  //StateButtons
  JoystickButton pushButton;
  JoystickButton autoPickupButton;

  public OI() {
    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new XboxController(gamePadPort);

    // Elevator Buttons
    elevatorButton = new JoystickButton(gamePad, 7);

    // Intake Buttons
    intakeButton = new JoystickButton(gamePad, 6);

    // Manual Motor Overide Button
    dynamicBraking = new JoystickButton(leftJoyStick, 1);
    driveStraightButton = new JoystickButton(rightJoyStick, 1);
    shiftGear = new JoystickButton(gamePad, 8);

    // Shooter Method Set and shoot ball
    // State Buttons
    pushButton = new JoystickButton(rightJoyStick, 1);
    autoPickupButton = new JoystickButton(leftJoyStick, 1);

    // Shooter Method Set and Manual Fire Mode
    shooterControlStateSwitch = new JoystickButton(gamePad, 6);
    shooterFireButton = new JoystickButton(gamePad, 8);
    jogindexUp = new Trigger();
  }

  public boolean get() {
    return (gamePad.getPOV() == 0);
  }

  public boolean getIndexUp() {
    return (gamePad.getPOV() == 0);
  }

  public double getIndex() {
    return gamePad.getRawAxis(0) ;
  }


  /*
   * manualOverrideButton.whileHeld(new ManualCommand());
   * zeroEncoderTrigger.whenActive(new ZeroEncoderCommand());
   * driveStraightButton.whileHeld(new DriveStraight());
   * dynamicBraking.whileHeld(new DynamicBrakingCommand());
   */



  public boolean getElevatorUp() {
    return (gamePad.getPOV() == 0);
  }
// set a tolerance for above and below?
  public boolean getElevatorDown() {
    return (gamePad.getPOV() == 180);
  }
    //I don't know what number I'm meant to use
  public boolean shiftGears(){
    return (rightJoyStick.getRawButtonPressed(2));
  }

  public boolean getIntake(){
    return (gamePad.getXButton());
  }

  public double getleftYAxis() {
    // return Math.pow(-leftJoyStick.getY(), 3.0);
    return leftJoyStick.getY() ;
  }

  public double getrightYAxis() {
    // return Math.pow(rightJoyStick.getY(), 3.0);
    return rightJoyStick.getY() ;
  }

  public double getleftXAxis() {
    // return Math.pow(-rightJoyStick.getX(), 3.0);
    return leftJoyStick.getX();
  }
  
  public double getrightXAxis() {
    // return Math.pow(-rightJoyStick.getX(), 3.0);
    return rightJoyStick.getX();
  }


  public boolean shooterFire(){
    return gamePad.getRawButtonPressed(8);
  }
  public boolean getPushButton() {
    return (rightJoyStick.getRawButtonPressed(1));
  }

  public boolean getAutoPickupButton() {
    return (leftJoyStick.getRawButtonPressed(1));
  }

  public boolean getAutoShootButton() {
    return gamePad.getAButton();
  }

  public boolean getManualShootButton() {
    return gamePad.getTriggerAxis(Hand.kRight) > 0.6;
  }

  public boolean getSpoolShootButton() {
    return gamePad.getTriggerAxis(Hand.kLeft) > 0.6;
  }

  public boolean getSpinWofButton() {
    return gamePad.getBButton();
  }

  public boolean getPositionWofButton() {
    return gamePad.getYButton();
  }

}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

