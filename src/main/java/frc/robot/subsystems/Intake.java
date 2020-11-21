/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  Victor intakeMotor;
  DoubleSolenoid piston;
  boolean isDown = false;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    intakeMotor = new Victor(Constants.IntakeMotorPort);
    piston = new DoubleSolenoid(Constants.IntakePistonUp, Constants.IntakePistonDown);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intake(double speed){
    intakeMotor.set(speed);
  }

  public void intakeDown(){
    piston.set(Value.kForward);
  }

  public void intakeUp(){
    piston.set(Value.kReverse);
  }

  public void intakeXbox(XboxController xbox){
    if(isDown){
      if(xbox.getTriggerAxis(Hand.kLeft)>.1)
        intake(xbox.getTriggerAxis(Hand.kLeft));
      else if(xbox.getBumper(Hand.kLeft))
        intake(-0.25);
      else
        stop();
    } else stop();

    if(xbox.getBButton()){
      if(isDown)
        intakeUp();
      else 
        intakeDown();
    }
  }

  public void stop(){
    intakeMotor.set(0);
  }
}
