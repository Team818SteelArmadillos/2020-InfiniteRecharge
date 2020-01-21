package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotLog;

public class ElevatorSubsystem extends SubsystemBase {
  static TalonSRX elevatorMotor;
// change below later in season
  static int tolerance = 1000;
  static int ifOn = 0;

  public ElevatorSubsystem(int elevatorMotor) {
    elevator = new DoubleSolenoid(elevatorMotor);
// talonsrx motor 
// 1 button 
// double solenoid
// anticpate 1 pnumatic actuator - ratcheting system

  RobotLog.putMessage("Running ElevatorSubsystem");
}

public void setElevatorMotor(double Speed){
  elevator.set(Speed);
}

  public void elevatorButton(int ifOn) {
    if (buttonhit){
      ifOn = ifOn + 1;
    } else if (button active & the nhit){
      ifOn = ifOn - 1;
    } else {
      // Nothing
    }
  }
}
