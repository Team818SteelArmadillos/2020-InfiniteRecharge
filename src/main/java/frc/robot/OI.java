package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Constants.oi.*;

public class OI {

  // Joysticks
  Joystick leftJoyStick, rightJoyStick;
  Joystick gamePad;

  //Drive Straight Button
  JoystickButton driveStraightButton;
  JoystickButton dynamicBraking;


  public OI() {
    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new Joystick(gamePadPort);
    


    // Manual Motor Overide Button

    dynamicBraking = new JoystickButton(leftJoyStick, 1);
    driveStraightButton = new JoystickButton(rightJoyStick, 1);

    };

    /*
    manualOverrideButton.whileHeld(new ManualCommand());
    zeroEncoderTrigger.whenActive(new ZeroEncoderCommand());
    driveStraightButton.whileHeld(new DriveStraight());
    dynamicBraking.whileHeld(new DynamicBrakingCommand());
*/

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
