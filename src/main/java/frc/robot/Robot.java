package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveVisionSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import frc.robot.subsystems.IntakeSubsystem;

import frc.robot.subsystems.IndexSubsystem;

import frc.robot.auton.AutonTwo;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterVisionSubsystem;
import frc.robot.subsystems.NewIntakeSubsystem;

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
  //static public IntakeSubsystem m_intakeSubsystem;
  static public ShooterSubsystem m_shooterSubsystem;
  static public ShooterVisionSubsystem m_shootervisionSubsystem;
  static public DriveSubsystem m_driveSubsystem;
  static public DriveVisionSubsystem m_drivevision;
  //static public IndexSubsystem m_indexSubsystem;
  static public NewIntakeSubsystem m_newintakesubsystem;
  // private Command m_autonomousCommand;
  private Command m_elevatorCommand;
  private Command m_IndexCommand;
  private Command m_WOFCommandPosition;
  private Command m_WOFCommandSpin;
  private Command m_TankDrive;
  private Command m_IntakeCommand;
  private Command m_SpoolShooterCommand;
  private Command m_ManualShootCommand;
  private Command m_AutoShoot;
  
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
 
 // m_intakeSubsystem = new IntakeSubsystem();
  m_shooterSubsystem = new ShooterSubsystem();
  m_shootervisionSubsystem = new ShooterVisionSubsystem();
  m_driveSubsystem = new DriveSubsystem();
 // m_indexSubsystem = new IndexSubsystem();
  m_newintakesubsystem = new NewIntakeSubsystem();
  m_IndexCommand = new IndexCommand();
  m_TankDrive = new TankDriveCommand();
  m_IntakeCommand = new IntakeCommand();
  m_SpoolShooterCommand = new SpoolShooterCommand();
  m_ManualShootCommand = new ManualShootCommand();
  m_AutoShoot = new AutoAllign();
  m_drivevision = new DriveVisionSubsystem();

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
    PathARed auton = new PathARed();
    //AutonIndexCommand auton = new AutonIndexCommand();
    //PathBBlue auton = new PathBBlue();
   //TimeDrive auton = new TimeDrive();
   if (auton != null) {
      auton.schedule();
   }
   
   
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
    SmartDashboard.putNumber("Auton Angle", m_driveSubsystem.getAngle());
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
    SmartDashboard.putNumber("Angle", m_driveSubsystem.getAngle());
  
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
    //m_AutoShiftTankDrive.schedule();
    //m_IntakeCommand.schedule();
    m_IndexCommand.schedule();
  }

  private void endDefault() {
    //TankDriveCommand
    //IntakeCommand
    m_SpoolShooterCommand.cancel();
    m_TankDrive.cancel();
    //m_AutoShiftTankDrive.cancel();
   // m_IntakeCommand.cancel();
    m_IndexCommand.cancel();
  }

  private void startPush() {
    //PushCommand
  }

  private void endPush() {
    //PushCommand
  }

  private void startAutoShoot() {
    m_AutoShoot.schedule();
    //AutoShootCommand
  }

  private void endAutoShoot() {
    m_AutoShoot.cancel();
    //AutoShootCommand
  }

  private void startManualShoot() {
    m_ManualShootCommand.schedule();
    m_TankDrive.schedule();
    //m_AutoShiftTankDrive.schedule();
  }

  private void endManualShoot() {
    m_ManualShootCommand.cancel();
    m_TankDrive.cancel();
    //m_AutoShiftTankDrive.cancel();
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
