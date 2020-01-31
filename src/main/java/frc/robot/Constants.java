package frc.robot;

public final class Constants {
    // Motors
    
    // Piston
    
    public static final int[] actuatorPistonPort = { 6, 7 };
    // Buttons

    public static class oi {
        // Joysticks
        public static final int leftJoystickPort = 0;
        public static final int rightJoystickPort = 1;
        public static final int gamePadPort = 2;
    }

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

    public static class ShooterConstants {
        //PID loop values
        public static final int[] ShooterMotorArray = {0,1,2,3};
    
        public static final double kP = .5;
    
        public static final double kI = .01;
    
        public static final double kD = .25;
    
        public static final double kShooterToleranceRPS = .003;
        //ShooterConversionFactors
        public static final double encoderPulsesPerRevolution = 4096;
    
        public static final double velocityCalculationsPerSecond = 10;
    
        }

}


