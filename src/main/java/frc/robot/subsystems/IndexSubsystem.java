package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import static frc.robot.Constants.motorPorts.*;
import frc.robot.commands.Command;

public class indexSubsystem extends Subsystem {
  static Talon indexMotor;

  public indexSubsystem(int indexMotorPort) {
    if (Constants.indexEnabled){
    indexMotor = new WPI_TalonSRX(indexMotorPort);
    indexMotor.setInverted(false);
    //RobotLog.putMessage("Running indexSubsystem");
    }
  }

  public void setindexMotor(double Speed) {
    if (indexEnabled){
    indexMotor.set(Speed);
    }
  }

  public void initDefaultCommand() {
    if (Constants.indexEnabled){
    setDefaultCommand(new indexCommand());
    }
  }
}