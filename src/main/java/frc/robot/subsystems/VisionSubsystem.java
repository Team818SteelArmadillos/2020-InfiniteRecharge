/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    NetworkTableEntry ty = table.getEntry("Vertical Angle");
    double y = ty.getDouble(0.0);
    SmartDashboard.putNumber("VisionY", y);
  }

  public void getArea() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
    NetworkTableEntry ta = table.getEntry("Has Target");
    double area = ta.getDouble(0.0);
    SmartDashboard.putNumber("VisionArea", area);
  }

  public void logData() {
    //Logging Data
    getX();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
