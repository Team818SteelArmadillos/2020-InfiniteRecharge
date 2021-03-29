// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BigLoopCommand extends SequentialCommandGroup {
  /** Creates a new BigLoopCommand. */
  public BigLoopCommand() {
    addCommands( new driveDistance(216), //new driveDistance(175)
    new TurnDrive(90),

    new driveDistance(216),
    new TurnDrive(-90),
    new driveDistance(42),
    new TurnDrive(-90),
    new driveDistance(42),
    new TurnDrive(-90),
    new driveDistance(216),
    new TurnDrive(90),
    new driveDistance(216)
    );
  }
}
