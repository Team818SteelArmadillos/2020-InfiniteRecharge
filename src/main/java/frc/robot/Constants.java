package frc.robot;

public final class Constants {
    // Motors
    public static final int elevatorMotorPortOne = 1;
    // public static final int elevatorMotorPortTwo = 2;
    // Piston
    
    public static final int[] actuatorPistonPort = { 6, 7 };
    // Buttons

    public static class oi {
        // Joysticks
        public static final int leftJoystickPort = 0;
        public static final int rightJoystickPort = 1;
        public static final int gamePadPort = 2;
    }

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
    public static class motorPorts{
        public static final int WOF_MOTOR_PORT =  1;
        public static final int elevatorMotorPort = 1;
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
    public static class Pistons{
        public static final int[] actuatorPistonPort = {6, 7};
    }
}
    