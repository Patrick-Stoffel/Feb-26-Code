// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KickerSubsystem extends SubsystemBase {
  /** Creates a new KickerSubsystem. */
  private static CANSparkMax kickerMotor;
  public KickerSubsystem() {
    kickerMotor = new CANSparkMax(16, MotorType.kBrushless);
    kickerMotor.restoreFactoryDefaults();
    kickerMotor.setInverted(false);
    kickerMotor.setSmartCurrentLimit(80);
    kickerMotor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setKickerSpeed(double kickerVolts) {
    kickerMotor.setVoltage(kickerVolts);
  }
}
