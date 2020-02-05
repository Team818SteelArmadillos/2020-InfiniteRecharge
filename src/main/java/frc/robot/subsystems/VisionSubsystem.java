package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {
  /**
   * Creates a new VisionSubsystem.
   */
  public VisionSubsystem() {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
  NetworkTableEntry tx = table.getEntry("Horizontal Angle");
  NetworkTableEntry ty = table.getEntry("Vertical Angle");
  NetworkTableEntry ta = table.getEntry("Has Target");

  //read values periodically
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  boolean target =  ta.getBoolean(false);

  //post to smart dashboard periodically
  SmartDashboard.putNumber("VisionX", x);
  SmartDashboard.putNumber("VisionY", y);
  SmartDashboard.putNumber("VisionArea", area);
  }

  public void getX() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    NetworkTableEntry tx = table.getEntry("Horizontal Angle");
    double x = tx.getDouble(0.0);
    SmartDashboard.putNumber("VisionX", x);
  }

  public void getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    SmartDashboard.putNumber("VisionY", y);
  }

  public void getTarget() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    SmartDashboard.putBoolean("Target", target);
  }

  public void setPowerCell() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    NetworkTableEntry powerCell = table.getEntry("Camera Mode");
    
    
  }

  public void logData() {
    //Logging Data
    getX();
    getY();
    getTarget();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
