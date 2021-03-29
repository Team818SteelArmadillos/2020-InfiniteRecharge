// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BarrelRaceCommand extends SequentialCommandGroup {
  /** Creates a new BarrelRaceCommand. */
  public BarrelRaceCommand() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TurnDrive(-30),
      new driveDistance(54),
      new TurnDrive(80),
      new driveDistance(65),
      new TurnDrive(100),
      new driveDistance(55),
      new TurnDrive(95),
      new driveDistance(162),
      new TurnDrive(-90),
      new driveDistance(45),
      new TurnDrive(-110),
      new driveDistance(50),
      new TurnDrive(-100),
      new driveDistance(103),
      new TurnDrive(-100),
      new driveDistance(35),
      new TurnDrive(-90),
      new driveDistance(300)
    );
  }
}
