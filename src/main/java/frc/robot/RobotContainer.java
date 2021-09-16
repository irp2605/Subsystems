/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveHatch;
import frc.robot.commands.MoveIntake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private SpeedController leftOne, leftTwo, rightOne, rightTwo;
  private SpeedControllerGroup left, right;
  private DifferentialDrive drive;
  private static DriveTrain driveTrain;

  private SpeedController intakeLeft, intakeRight;
  private Intake intake;

  private static Joystick joy; 
  private Button intakeIn, intakeOut;
  private Button armUp, armDown;
  private Button wristUp, wristDown;
  private Button hatchButton;

  private SpeedController armMotor;
  private AnalogPotentiometer armPot;
  private Arm arm;

  private SpeedController wristMotor;
  private AnalogPotentiometer wristPot;
  private Wrist wrist;

 
  private Solenoid sol;
  private Hatch hatch;

   /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    leftOne = new SteelTalonsController(Constants.LEFT_ONE_PORT, false, Constants.LEFT_ONE_BIAS);
    leftTwo = new SteelTalonsController(Constants.LEFT_TWO_PORT, false, Constants.LEFT_TWO_BIAS);
    rightOne = new SteelTalonsController(Constants.RIGHT_ONE_PORT, false, Constants.RIGHT_ONE_BIAS);
    rightTwo = new SteelTalonsController(Constants.RIGHT_TWO_PORT, false, Constants.RIGHT_TWO_BIAS);

    left = new SpeedControllerGroup(leftOne, leftTwo);
    right = new SpeedControllerGroup(rightOne, rightTwo);

    drive = new DifferentialDrive(left, right);

    driveTrain = new DriveTrain(left, right, drive);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    intakeLeft = new SteelTalonsController(Constants.INTAKE_LEFT_PORT, false, 1);
    intakeRight = new SteelTalonsController(Constants.INTAKE_RIGHT_PORT, false, 1);
    intake = new Intake(intakeLeft, intakeRight);

    armMotor = new SteelTalonsController(Constants.ARM_MOTOR_PORT, false, 1);
    armPot = new AnalogPotentiometer(Constants.ARM_POT_PORT, 118);
    arm = new Arm(armMotor, armPot);

    wristMotor = new SteelTalonsController(Constants.WRIST_MOTOR_PORT, false, 1);
    wristPot = new AnalogPotentiometer(Constants.WRIST_POT_PORT, 118);
    wrist = new Wrist(wristMotor, wristPot);

    sol = new Solenoid(Constants.SOL);
    hatch = new Hatch(sol);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    joy = new Joystick(0);


    intakeIn = new JoystickButton(joy, Constants.INTAKE_IN_BUTTON);
    intakeOut = new JoystickButton(joy, Constants.INTAKE_OUT_BUTTON);

    intakeIn.whileHeld(new MoveIntake(Constants.INTAKE_IN_SPEED));
    intakeOut.whileHeld(new MoveIntake(Constants.INTAKE_OUT_SPEED));


    armUp = new JoystickButton(joy, Constants.ARM_UP_BUTTON);
    armDown = new JoystickButton(joy, Constants.ARM_DOWN_BUTTON);

    wristUp = new JoystickButton(joy, Constants.WRIST_UP_BUTTON);
    wristDown = new JoystickButton(joy, Constants.WRIST_DOWN_BUTTON);

    hatchButton = new JoystickButton(joy, Constants.HATCH_BUTTON);
    hatchButton.whileHeld(new MoveHatch(hatch, Constants.SOL_BOOL));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  
  public DriveTrain getDriveTrain()
  {
    return driveTrain;
  }

  public Intake getIntake()
  {
    return intake;
  }

  public Joystick getJoystick()
  {
    return joy;
  }

  public Arm getArm()
  {
    return arm;
  }

  public Wrist getWrist()
  {
    return wrist;
  }

  public Hatch getHatch()
  {
    return hatch;
  }
}