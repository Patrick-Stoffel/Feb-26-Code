// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterKickerSCG extends SequentialCommandGroup {
  /** Creates a new ShooterAndKickerCommandGroup. */
  
  public ShooterKickerSCG(ShooterSubsystem shooterSubsystem, KickerSubsystem kickerSubsystem ) {
    
    addCommands(//
      new ShooterCommand(shooterSubsystem, Constants.topShooterVoltage, Constants.bottomShooterVoltage),//
      new WaitCommand(2),//
      new KickerCommand(kickerSubsystem, Constants.kickerVoltage),//
      new WaitCommand(4),//
      new ShooterCommand(shooterSubsystem, 0, 0),//
      new KickerCommand(kickerSubsystem, 0)//

      
      
      
      

    
      
     
      
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand()
    
  }
}
