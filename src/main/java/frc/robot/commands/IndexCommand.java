package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {

  boolean jogindexHatchUp, jogindexHatchDown, buttonReleased, shootindexBall;
  Timer timer, ballTimer;
  double time = 0.05;
  double ballTime = 0.1;
  public IndexCommand() {
    requires(index);
  }

  protected void initialize() {
    index.setindexMotor(0);
    buttonReleased = true;
    timer = new Timer();
  }

  protected void execute() {
    if(oi.getindexUp() && buttonReleased) {
      jogindexHatchUp = true;
    } 

    if(oi.getIndexButton() && buttonReleased) {
      shootindexBall = true;
      ballTimer.start();
      buttonReleased = false;
    }

    if (oi.getindexButtonIn()) {
      index.setindexMotor(-1.00);
    } else if (oi.getindexButtonOut()) {
      index.setindexMotor(1.00);
    } else {
      index.setindexMotor(0);
    }
    if(jogBallUp) {
      index.setindexMotor(-0.40);
    //Sends max power to index, to send a ball into the shooter, to shoot - Joseph
    if()
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