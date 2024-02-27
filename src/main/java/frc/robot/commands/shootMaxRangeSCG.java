// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.VoltageConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class shootMaxRangeSCG extends SequentialCommandGroup {
  // Creates a new shootMaxRangeSCG. 
  public shootMaxRangeSCG(ArmSubsystem armSubsystem, ShooterSubsystem shooterSubsystem, KickerSubsystem kickerSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
     new MoveArmToMaxPOSCMD(armSubsystem, VoltageConstants.vk_ArmUp),//
     new RunShooterTimeBaseCMD(shooterSubsystem, VoltageConstants.vk_TopShooterMax, VoltageConstants.vk_BottomShooterMax),//
      new MoveArmToHomePOSCMD(armSubsystem, VoltageConstants.vk_ArmDown)//
    );
    
  }
}
