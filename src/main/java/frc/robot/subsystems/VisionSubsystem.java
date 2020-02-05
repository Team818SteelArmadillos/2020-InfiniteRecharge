package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {
  
  NetworkTable table;
  NetworkTableEntry tx, ty, ta, mode;
  
  public VisionSubsystem() {

  table = NetworkTableInstance.getDefault().getTable("Vision");
  tx = table.getEntry("Horizontal Angle");
  ty = table.getEntry("Vertical Angle");
  ta = table.getEntry("Has Target");
  mode = table.getEntry("Camera Mode");
  }
<<<<<<< HEAD

  public double getX() {
    return tx.getDouble(0.0);
  }

  public double getY() {
    return ty.getDouble(0.0);
=======
  
  public double getX() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    NetworkTableEntry tx = table.getEntry("Horizontal Angle");
    double x = tx.getDouble(0.0);
    return x;
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    SmartDashboard.putNumber("VisionY", y);
    NetworkTableEntry ty = table.getEntry("Vertical Angle");
    double y = ty.getDouble(0.0);
    return y;
>>>>>>> 307f89b64a6ccb430e17c3c6d7506d2970f68964
  }

  public Boolean getTarget() {
    return ta.getBoolean(false);

  }

  public void setPowerCell(boolean powerCellMode) {
    mode.setBoolean(powerCellMode);
  }

  public void logData() {
    //Logging Data
    SmartDashboard.putNumber("VisionX", getX());
    SmartDashboard.putNumber("VisionY", getY());
    SmartDashboard.putBoolean("Target", getTarget());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
