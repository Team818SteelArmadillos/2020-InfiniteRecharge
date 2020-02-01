package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Constants.oi.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.ElevatorCommand;

public class OI {

  // Joysticks
  Joystick leftJoyStick, rightJoyStick;
  Joystick gamePad;
  // Elevator Buttons
  JoystickButton elevatorButton;
  // Drive Straight Button
  JoystickButton driveStraightButton;
  JoystickButton dynamicBraking;

  JoystickButton indexButton;
  Trigger jogindexUp;

  public OI() {
    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new Joystick(gamePadPort);

    // Elevator Buttons
    elevatorButton = new JoystickButton(gamePad, 7);

    // Manual Motor Overide Button
    dynamicBraking = new JoystickButton(leftJoyStick, 1);
    driveStraightButton = new JoystickButton(rightJoyStick, 1);

    jogindexUp = new Trigger();
  }

  public boolean get() {
    return (gamePad.getPOV() == 0);
  }

  public boolean getIndexUp() {
    return (gamePad.getPOV() == 0);
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

  public boolean getElevatorDown() {
    return (gamePad.getPOV() == 180);
  }

  public double getleftYAxis() {
    // return Math.pow(-leftJoyStick.getY(), 3.0);
    return -leftJoyStick.getY() * Math.abs(leftJoyStick.getY());
  }

  public double getrightXAxis() {
    // return Math.pow(-rightJoyStick.getX(), 3.0);
    return -rightJoyStick.getX() * Math.abs(rightJoyStick.getX());
  }

}
