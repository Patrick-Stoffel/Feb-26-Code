// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KickerSubsystem extends SubsystemBase {
  /** Creates a new KickerSubsystem. */
  private static TalonFX kickerMotor;
    final VoltageOut kickerVoltageOutRequest = new VoltageOut(0);
  public KickerSubsystem() {
    kickerMotor = new TalonFX(24, "rio");
    kickerMotor.getConfigurator();
    
    
    kickerMotor.setInverted(false);
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
  public void setKickerSpeed(double kickerVolts) {
    
    kickerMotor.setControl(kickerVoltageOutRequest.withOutput(kickerVolts));
  }


}
