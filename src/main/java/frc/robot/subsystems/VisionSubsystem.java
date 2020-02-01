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
  NetworkTableEntry tx = table.getEntry("Vertical Angle");
  NetworkTableEntry ty = table.getEntry("Horizontal Angle");
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
