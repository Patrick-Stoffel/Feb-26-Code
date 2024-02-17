package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operatorJoystick = new Joystick(1);
   // private final CommandXboxController operatorController = new CommandXboxController(0);

    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final KickerSubsystem kickerSubsystem = new KickerSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();

    

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    
    
    private final SendableChooser<Command> autoChooser;
    

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

       // NamedCommands.registerCommand("marker 1", Commands.print("Passed marker 1"));
      //  NamedCommands.registerCommand("marker 2", Commands.print("Passed marker 2"));
        

        // Configure the button bindings
        configureButtonBindings();

       // autoChooser = AutoBuilder.buildAutoChooser();
      //  SmartDashboard.putData("Auto Mode", autoChooser);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

       // SmartDashboard.putData("Example Auto", new PathPlannerAuto("Example Auto"));

      /*   SmartDashboard.putData("Pathfind to Pickup Pos", AutoBuilder.pathfindToPose(
            new Pose2d(14.0, 6.5, Rotation2d.fromDegrees(0)),
            new PathConstraints(
                4.0, 4.0,
                Units.degreesToRadians(360), Units.degreesToRadians(540)
            ),
            0,
            2.0
        ));
        */

      /*   SmartDashboard.putData("Pathfind to scoring Pos", AutoBuilder.pathfindToPose(
            new Pose2d(2.15, 3.0, Rotation2d.fromDegrees(180)),
            new PathConstraints(
                4.0, 4.0,
                Units.degreesToRadians(360), Units.degreesToRadians(540)
            )
        ));

       /* SmartDashboard.putData("On-the-fly path", Commands.runOnce(() -> {
            Pose2d currentPose = swerve.getPose();

            Pose2d startPose = new Pose2d(currentPose.getTranslation(), new Rotation2d());
            PathPlannerPath endPos = new PathPlannerPath(
                bezierPoints,
                new PathConstraints(
                    4.0, 4.0,
                    Units.degreesToRadians(360), Units.degreesToRadians(540)
                ),  
                new GoalEndState(0.0, currentPose.getRotation())
            );

            path.preventFlipping = true;

            AutoBuilder.followPath(path).schedule();
        }));*/

        

        new JoystickButton(operatorJoystick, 6).whileTrue(new ShooterCommand(shooterSubsystem, Constants.topShooterVoltage , Constants.bottomShooterVoltage));
        new JoystickButton(operatorJoystick, 2).whileTrue(new KickerCommand(kickerSubsystem, Constants.kickerVoltage));
        new JoystickButton(operatorJoystick, 12).whileTrue(new ArmCommand(armSubsystem, Constants.armVoltage));
        new JoystickButton(operatorJoystick, 11).whileTrue(new ArmCommand(armSubsystem, Constants.downArmVoltage));
        new JoystickButton(operatorJoystick, 1).whileTrue(new IntakeCommand(intakeSubsystem, Constants.intakeVoltage));
        new JoystickButton(operatorJoystick, 4).whileTrue(new KickerCommand(kickerSubsystem, Constants.backKickerVoltage));

        
    }
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoChooser.getSelected();
    }
 
}
