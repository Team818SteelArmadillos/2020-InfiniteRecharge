package frc.robot;

public final class Constants {
    
    // Piston
    
    public static final int[] actuatorPistonPort = { 6, 7 };
    // Buttons

 public static class oi{
    // Joysticks
  public static final int leftJoystickPort = 0;
  public static final int rightJoystickPort = 1;
  public static final int gamePadPort = 2;
  public static final int shiftGear = 3;
    
    public static final int JOYSTICK_PORT_LEFT = 0;
    public static final int JOYSTICK_PORT_RIGHT = 1;
    public static final double JOYSTICK_LEFT_DEADZONE_Y = 0.02;
    public static final double JOYSTICK_RIGHT_DEADZONE_Y = 0.02;

    }

    public static class DriveConstants{
        
    //Drive Motors
    public static final int[] MOTOR_PORTS_LEFT = {1, 2};
    public static final int[] MOTOR_PORTS_RIGHT = {3, 4};
    public static final boolean LEFT_INVERTED = false;
    public static final double RAMP_RATE = 0;
    public static final double wheelCircumference = 6*Math.PI;
    public static final int[] COOLING_PORTS = {5, 6};
    
    //Chassis constants
    public static final double WHEEL_DIAMETER = 6;
    public static final double ENCODER_GEAR_RATIO = 1;
    public static final int ENCODER_PULSES_PER_REVOLUTION = 4096;
    public static final int VELOCITY_CALCULATION_PER_SECOND = 10;
    public static final double distancePerPulse = wheelCircumference/(double) ENCODER_PULSES_PER_REVOLUTION;
    }



    
    public static final int JOYSTICK_PORT_LEFT = 0;
    public static final int JOYSTICK_PORT_RIGHT = 1;
    public static final double JOYSTICK_LEFT_DEADZONE_Y = 0.02;
    public static final double JOYSTICK_RIGHT_DEADZONE_Y = 0.02;

    public static final double VELOCITY_CALCULATIONS_PER_SECOND = 10.0;

    public static class motorPorts {
        public static final int WOF_MOTOR_PORT = 1;
        public static final int elevatorMotorPortOne = 1;
        // public static final int elevatorMotorPortTwo = 2;
        public static final int intakeMotorPort = 4;
        public static final int indexMotorPort = 3;
    }

    public static class sensorPorts {
        public static final int WOF_LIGHT_SENSOR = 1;
        public static final int indexSensor1 = 2;
        public static final int indexSensor2 = 3;
        public static final int indexSensor3 = 4;
        public static final int DRIVE_GYRO = 2;
    }
    public static class Numbers{
        public static final boolean inverseIndex = false;
        public static final int SPIN_TIMER = 10;
        public static final double[][] RED = {{0.472,0.362,0.107},{0.525,0.383,0.125}};
        public static final double[][] GREEN = {{0.260,0.480,0.210},{0.289,0.500,0.225}};
        public static final double[][] CYAN = {{0.222,0.426,0.316},{0.245,0.435,0.348}};
        public static final double[][] YELLOW = {{0.408,0.467,0.096},{0.415,0.490,0.114}};
    
    }

    public static class Pistons{
        public static final int[] actuatorPistonPort = {6, 7};
        public static final int[] intakePistonPortOne = {3, 4};
        public static final int[] intakePistonPortTwo = {1, 2};
        public static final int[] shiftPistonPorts = {8, 9};

    }

    public static class ShooterConstants {
        //PID loop values
        public static final int[] ShooterMotorArray = {0,1,2,3};
        //Shooter speed PID variables
        public static final double kP = .5;
    
        public static final double kI = .01;
    
        public static final double kD = .25;

        public static final double kShooterToleranceRPM = .003;

        //ShooterConversionFactors
        public static final double encoderPulsesPerRevolution = 4096;
    
        public static final double velocityCalculationsPerSecond = 10;
        //ShooterSpecs
        public static final double targetHeight = 8.1875;

        public static final double shooterHeight = 1.4167;

        public static final double cameraHeight = 2.9167;

        public static final double relativeTargetHeight = targetHeight - (shooterHeight - cameraHeight);

        public static final double maxShooterDistance = 3740;

        public static final double maxShooterMotorSpeed = 2400;

        }

        public static final int[] shiftPistonPort = {8, 9, 10, 11};

    //Gear Ratios

    double low = 21.67;
    double high = 8.41;
    }
    



