// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;





public class GroupDrive extends SequentialCommandGroup {
  
  /** Creates a new GroupDrive. */
  public GroupDrive() {
    Robot.m_driveSubsystem.resetEncoders();
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new driveDistance(2));
    //addCommands(new );

  }
}
