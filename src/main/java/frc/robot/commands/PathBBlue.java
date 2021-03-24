// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PathBBlue extends SequentialCommandGroup {
  /** Creates a new PathBBlue. */
  public PathBBlue() {

    addCommands(
    new AutoBallPickupCommandGroup(),
    new TurnDrive(-100),
    new AutoBallTrackCommand(),
    new AutoBallPickupCommandGroup(),
    new TurnDrive(80),
    new AutoBallTrackCommand(),
    new AutoBallPickupCommandGroup(),
    new TurnDrive(-30),
    new driveDistance(35)
    );
  }
}
