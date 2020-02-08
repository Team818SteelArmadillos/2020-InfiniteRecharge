package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonInput extends CommandGroup {
    private static double autonPosition;

    public AutonInput() {
        autonPosition = SmartDashboard.getNumber( "Auton Position" , 1);
    }
}
