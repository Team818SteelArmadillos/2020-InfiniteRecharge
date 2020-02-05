package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Constants.oi.*;
import frc.robot.commands.ElevatorCommand;

public class OI {

  // Joysticks
  Joystick leftJoyStick, rightJoyStick;
  Joystick gamePad;
  // Elevator Buttons
  JoystickButton elevatorButton;
  // Intake Buttons
  JoystickButton intakeButton;
  // Drive Straight Button
  JoystickButton driveStraightButton;
  JoystickButton dynamicBraking;
  JoystickButton shiftGear;

  public OI() {
    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new Joystick(gamePadPort);

    // Elevator Buttons
    elevatorButton = new JoystickButton(gamePad, 7);

    // Intake Buttons
    intakeButton = new JoystickButton(gamePad, 6);

    // Manual Motor Overide Button
    dynamicBraking = new JoystickButton(leftJoyStick, 1);
    driveStraightButton = new JoystickButton(rightJoyStick, 1);
    shiftGear = new JoystickButton(gamePad, 8);

  };

  /*
   * manualOverrideButton.whileHeld(new ManualCommand());
   * zeroEncoderTrigger.whenActive(new ZeroEncoderCommand());
   * driveStraightButton.whileHeld(new DriveStraight());
   * dynamicBraking.whileHeld(new DynamicBrakingCommand());
   */

  public boolean shiftGear(){

    return (gamePad.getRawButtonPressed(8));

  }

  public boolean getElevatorUp() {
    return (gamePad.getPOV() == 0);
  }
// set a tolerance for above and below?
  public boolean getElevatorDown() {
    return (gamePad.getPOV() == 180);
  }

  public boolean getIntake(){
    return (gamePad.getRawButtonPressed(6));
  }

  public double getleftYAxis() {
    // return Math.pow(-leftJoyStick.getY(), 3.0);
    return -leftJoyStick.getY() * Math.abs(leftJoyStick.getY());
  }

  public double getrightYAxis() {
    // return -Math.pow(-rightJoyStick.getY(), 3.0);
    return -rightJoyStick.getY() * Math.abs(rightJoyStick.getY());
  }

  public double getleftXAxis() {
    // return Math.pow(-leftJoyStick.getX(), 3.0);
    return -leftJoyStick.getX() * Math.abs(leftJoyStick.getX());
  }

 public double getrightXAxis() {
    // return Math.pow(-rightJoyStick.getX(), 3.0);
    return -rightJoyStick.getX() * Math.abs(rightJoyStick.getX());
  }

}