package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.Pistons.*;
import static frc.robot.Constants.motorPorts.*;
import frc.robot.RobotLog;

public class IntakeSubsystem extends SubsystemBase {
  static VictorSPX intakeMotor;
  static DoubleSolenoid intakePistonOne;
  static DoubleSolenoid intakePistonTwo;
  double intakePistonVal;

public IntakeSubsystem() {
  intakeMotor = new VictorSPX(intakeMotorPort);
  intakePistonOne = new DoubleSolenoid(intakePistonPortOne[3], intakePistonPortOne[4]);
  intakePistonTwo = new DoubleSolenoid(intakePistonPortTwo[1], intakePistonPortTwo[2]);

  RobotLog.putMessage("Running IntakeSubsystem");

} 

public void setIntakeMotor(double Speed){
  intakeMotor.set(ControlMode.PercentOutput, Speed);
}

public void setIntakePistons(double intakePistonVal) {
  if (intakePistonVal == 0.5) {
    intakePistonOne.set(DoubleSolenoid.Value.kReverse);
    intakePistonTwo.set(DoubleSolenoid.Value.kReverse);

  } else if (intakePistonVal == 1) {
    intakePistonOne.set(DoubleSolenoid.Value.kForward);
    intakePistonTwo.set(DoubleSolenoid.Value.kForward);
  }

  }
  
  public void logDataElevator() {
    SmartDashboard.putNumber("Intake Power:", intakeMotor.getMotorOutputPercent());
      if (intakePistonVal == 0.5) {
      SmartDashboard.putString("Intake Piston Position:", "In");
    } else if (intakePistonVal == 1) {
      SmartDashboard.putString("Intake Piston Position:", "Out");
    }

  }


  }