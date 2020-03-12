package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDVisionLightSubsystem extends SubsystemBase {
  static Solenoid LEDLight;

  public LEDVisionLightSubsystem() {
    LEDLight = new Solenoid(13, Constants.LEDLight);
    LEDLight.set(true);
  }

}
