
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {
  
  public RobotContainer() {
    
    configureButtonBindings();
  }

 
  private void configureButtonBindings() {
  }


  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
