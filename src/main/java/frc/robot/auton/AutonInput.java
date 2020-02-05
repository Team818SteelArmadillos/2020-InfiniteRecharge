package frc.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class AutonInput extends CommandGroup {
    private static double autonPosition;

    public AutonInput() {
        autonPosition = SmartDashboard.getNumber();
    }
}
