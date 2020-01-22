package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem.*;

public class ShooterCommand extends CommandBase{    

    public void setShooterMotor(double speed){
    shooterMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setShooter(double speed){
    setShooterMotor(speed);
    }

}