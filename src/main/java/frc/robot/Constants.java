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
<<<<<<< Updated upstream
=======
    
    public static final int[] actuatorPistonPort = { 6, 7 };
    // Buttons

    public static class oi {
    // Joysticks
        public static final int leftJoystickPort = 0;
        public static final int rightJoystickPort = 1;
        public static final int gamePadPort = 2;
    }

>>>>>>> Stashed changes
    public static class motorPorts{
        public static final int WOF_MOTOR_PORT =  1;
    }
    public static class sensorPorts{
        public static final int WOF_LIGHT_SENSOR = 1;
    }
    public static class Numbers{
        public static final int SPIN_TIMER = 10;
        public static final int[][] RED = {{12,13,15},{18,73,856}};
        public static final int[][] GREEN = {{12,13,15},{18,73,856}};
        public static final int[][] CYAN = {{12,13,15},{18,73,856}};
        public static final int[][] YELLOW = {{12,13,15},{18,73,856}};
    }
}
