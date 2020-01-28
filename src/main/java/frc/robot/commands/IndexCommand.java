package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import 

public class IndexCommand extends CommandBase {

  boolean jogindexUp, buttonReleased;

  public IndexCommand() {
    requires(index);
  }

  protected void initialize() {
    index.setindexMotor(0);
    jogindexUp = false;
    buttonReleased = true;
    timer = new Timer();
  }

  protected void execute() {
    if (oi.getJogindexUp() && buttonReleased) {
      jogindexUp = true;
    }

    if (oi.getIndexButton() && buttonReleased) {
      jogindexUp = false;
    }

    if (oi.getindexButtonIn()) {
      index.setIndexMotor(-1.00);
    } else if (oi.getindexButtonOut()) {
      index.setindexMotor(1.00);
    } else {
      index.setindexMotor(0);
    }
    if (jogBallUp) {
      index.setindexMotor(-0.40);
    }
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
    index.setindexMotor(0);
  }
}