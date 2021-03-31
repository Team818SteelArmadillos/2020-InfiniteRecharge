package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BouncePath extends SequentialCommandGroup {

  public BouncePath() {

    addCommands(
      new driveDistance(30),//first marker
      new TurnDrive(-90),
      new AutoBallTrackCommand(),
      new DrivetoBallCommand(),
      new TapMarker(5),
      new TurnDrive(160),//beginning of movement to second marker
      new driveDistance(140),
      new TurnDrive(-70),
      new driveDistance(30),
      new TurnDrive(-90),
      new driveDistance(30), //just far enough so marker is in range of camera
      new AutoBallTrackCommand(),
      new DrivetoBallCommand(),
      new TapMarker(5),
      new driveDistance(-150),//beginning of movement to third marker
      new TurnDrive(90),
      new driveDistance(70),
      new TurnDrive(-90),
      new driveDistance(30), //just far enough so marker is in range of camera
      new AutoBallTrackCommand(),
      new DrivetoBallCommand(),
      new driveDistance(-60),//beginning of movement to end zone
      new TurnDrive(90),
      new driveDistance(45)
    );
  }
}
