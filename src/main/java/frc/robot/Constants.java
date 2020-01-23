/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
 public static class oi{
    // Joysticks
  public static final int leftJoystickPort = 0;
  public static final int rightJoystickPort = 1;
  public static final int gamePadPort = 2;


 }

    public static class DriveConstants{
        
    //Drive Motors
    public static final int[] MOTOR_PORTS_LEFT = {1, 2};
    public static final int[] MOTOR_PORTS_RIGHT = {3, 4};
    public static final boolean LEFT_INVERTED = false;
    public static final double RAMP_RATE = 0;

    }

    //Chassis constants
    public static final double WHEEL_DIAMETER = 8;
    public static final double ENCODER_GEAR_RATIO = 1;
    public static final int ENCODER_PULSES_PER_REVOLUTION = 4096;
    public static final int VELOCITY_CALCULATION_PER_SECOND = 10;
    
    public static final int JOYSTICK_PORT_LEFT = 0;
    public static final int JOYSTICK_PORT_RIGHT = 1;
    public static final double JOYSTICK_LEFT_DEADZONE_Y = 0.02;
    public static final double JOYSTICK_RIGHT_DEADZONE_Y = 0.02;
    
    
    public static final double VELOCITY_CALCULATIONS_PER_SECOND = 10.0;
}
    