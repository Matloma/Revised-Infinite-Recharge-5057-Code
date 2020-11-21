/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  WPI_TalonSRX FrontLeft;
  WPI_TalonSRX FrontRight;
  WPI_TalonSRX BackLeft;
  WPI_TalonSRX BackRight;

  MecanumDrive DriveController;

  public DriveTrain() {
    FrontLeft = new WPI_TalonSRX(Constants.FrontLeftMotorPort);
    FrontRight = new WPI_TalonSRX(Constants.FrontRightMotorPort);
    BackLeft = new WPI_TalonSRX(Constants.BackLeftMotorPort);
    BackRight = new WPI_TalonSRX(Constants.BackRightMotorPort);

    DriveController = new MecanumDrive(FrontLeft, BackLeft, FrontRight, BackRight);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveXbox(XboxController xbox){
    DriveController.driveCartesian(-1*xbox.getY(Hand.kLeft), xbox.getX(Hand.kLeft), xbox.getX(Hand.kRight));
  }

  public void drive(double y, double x, double r){
    DriveController.driveCartesian(y, x, r);
  }

  public void stop(){
    DriveController.driveCartesian(0, 0, 0);
  }
}
