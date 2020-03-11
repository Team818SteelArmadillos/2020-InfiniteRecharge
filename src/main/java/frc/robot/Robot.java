package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.wofSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.wofSubsystem;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.LEDVisionLightSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  enum RobotState {
    DEFAULT, PUSH, AUTOSHOOT, MANUALSHOOT, SPINWOF, POSITIONWOF, AUTOPICKUP;
  }


  //private RobotContainer m_robotContainer;
  static public OI m_oi;
  public static wofSubsystem wof;
  static public ElevatorSubsystem m_elevatorSubsystem;
  static public IntakeSubsystem m_intakeSubsystem;
  static public ShooterSubsystem m_shooterSubsystem;
  static public VisionSubsystem m_visionSubsystem;
  static public DriveSubsystem m_driveSubsystem;
  static public IndexSubsystem m_indexSubsystem;
  static public LEDVisionLightSubsystem m_ledvisionlightSubsystem;
  // private Command m_autonomousCommand;
  private Command m_elevatorCommand;
  private Command m_IndexCommand;
  private Command m_WOFCommandPosition;
  private Command m_WOFCommandSpin;
  private Command m_TankDrive;
  private Command m_IntakeCommand;
  private Command m_SpoolShooterCommand;
  //private Command m_PushCommand;
  //private Command m_AutoShootCommand;
  //private Command m_ManualShootCommand;
  //private Command m_DriveCommand;
  
  static RobotState Rstate = RobotState.DEFAULT;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.


  //private RobotContainer m_robotContainer;
  m_oi = new OI();
  wof = new wofSubsystem();
  m_elevatorSubsystem = new ElevatorSubsystem();
  m_intakeSubsystem = new IntakeSubsystem();
  m_shooterSubsystem = new ShooterSubsystem();
  m_visionSubsystem = new VisionSubsystem();
  m_driveSubsystem = new DriveSubsystem();
  m_indexSubsystem = new IndexSubsystem();
  m_ledvisionlightSubsystem = new LEDVisionLightSubsystem();
  m_elevatorCommand = new ElevatorCommand();
  m_IndexCommand = new IndexCommand();
  m_TankDrive = new TankDriveCommand();
  m_WOFCommandPosition = new WOFCommandPosition();
  m_WOFCommandSpin = new WOFCommandSpin();
  m_IntakeCommand = new IntakeCommand();
  m_SpoolShooterCommand = new SpoolShooterCommand();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    Rstate = RobotState.DEFAULT;
    startDefault();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    switch (Rstate) {
      case DEFAULT:
        if(m_oi.getPushButton()) {
          endDefault();
          startPush();
          Rstate = RobotState.PUSH;
        }

        if(m_oi.getAutoShootButton()) {
          endDefault();
          startAutoShoot();
          Rstate = RobotState.AUTOSHOOT;
        }

        if(m_oi.getManualShootButton()) {
          endDefault();
          startManualShoot();
          Rstate = RobotState.MANUALSHOOT;
        }

        if(m_oi.getSpinWofButton()) {
          endDefault();
          startSpinWof();
          Rstate = RobotState.SPINWOF;
        }

        if(m_oi.getPositionWofButton()) {
          endDefault();
          startPositionWof();
          Rstate = RobotState.POSITIONWOF;
        }

        if(m_oi.getAutoPickupButton()) {
          endDefault();
          startAutoShoot();
          Rstate = RobotState.AUTOPICKUP;
        }
        break;

      case PUSH:
        if(!m_oi.getPushButton()) {
          endPush();
          startDefault();
          Rstate = RobotState.DEFAULT;
       }
        break;

      case AUTOSHOOT:
        if(!m_oi.getAutoShootButton()) {
          endAutoShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      case MANUALSHOOT:
        if(!m_oi.getManualShootButton()) {
          endManualShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      case SPINWOF:
        if(!m_oi.getSpinWofButton()) {
          endSpinWof();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      case POSITIONWOF:
        if(!m_oi.getPositionWofButton()) {
          endPositionWof();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      case AUTOPICKUP:
        if(!m_oi.getAutoPickupButton()) {
          endAutoPickup();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;
    }
  }

  private void startDefault() {
    //TankDriveCommand
    //IntakeCommand
    m_SpoolShooterCommand.schedule();
    m_TankDrive.schedule();
    m_IntakeCommand.schedule();
    m_IndexCommand.schedule();
    // m_elevatorCommand.schedule();
  }

  private void endDefault() {
    //TankDriveCommand
    //IntakeCommand
    m_SpoolShooterCommand.cancel();
    m_TankDrive.cancel();
    m_IntakeCommand.cancel();
    m_IndexCommand.cancel();
    // m_elevatorCommand.cancel();
  }

  private void startPush() {
    //PushCommand
  }

  private void endPush() {
    //PushCommand
  }

  private void startAutoShoot() {
    //AutoShootCommand
  }

  private void endAutoShoot() {
    //AutoShootCommand
  }

  private void startManualShoot() {
    //ManualShootCommand
    //TankDriveCommand
  }

  private void endManualShoot() {
    //ManualShootCommand
    //TankDriveCommand
  }

  private void startPositionWof() {
    m_WOFCommandPosition.schedule();
  }

  private void endPositionWof() {
    m_WOFCommandPosition.cancel();
  }

  private void startSpinWof() {
    m_WOFCommandSpin.schedule();
  }

  private void endSpinWof() {
    m_WOFCommandPosition.cancel();
  }

  private void startAutoPickup() {
    //AutoPickupCommand
  }

  private void endAutoPickup() {
    //AutoPickupCommand
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
