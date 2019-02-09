package xyz.nasaknights.deepspace;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import xyz.nasaknights.deepspace.commands.drive.ArcadeDriveCommand;
import xyz.nasaknights.deepspace.subsystems.cam.CAMSubsystem;
import xyz.nasaknights.deepspace.subsystems.cargo.CargoSubsystem;
import xyz.nasaknights.deepspace.subsystems.drivetrain.DrivetrainSubsystem;
import xyz.nasaknights.deepspace.subsystems.elevator.ElevatorSubsystem;
import xyz.nasaknights.deepspace.subsystems.hatch.HatchSubsystem;
import xyz.nasaknights.deepspace.subsystems.vision.VisionSubsystem;
import xyz.nasaknights.deepspace.subsystems.vision.VisionTCPClient;

import static xyz.nasaknights.deepspace.RobotMap.*;

public class Robot extends TimedRobot
{
    private static DrivetrainSubsystem drivetrain;
    private static ElevatorSubsystem elevator;
    private static CAMSubsystem cam;
    private static HatchSubsystem hatch;
    private static VisionSubsystem vision;
    private static CargoSubsystem cargo;

    private static OI oi;

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    public static void main(String[] args)
    {
        RobotBase.startRobot(Robot::new);
    }

    public static OI getOI()
    {
        return oi;
    }

    public static DrivetrainSubsystem getDrivetrain()
    {
        return drivetrain;
    }

    public static ElevatorSubsystem getElevator()
    {
        return elevator;
    }

    public static CAMSubsystem getCAM()
    {
        return cam;
    }

    public static HatchSubsystem getHatch()
    {
        return hatch;
    }

    public static VisionSubsystem getVision() {
        return vision;
    }

    public static CargoSubsystem getCargo()
    {
        return cargo;
    }

    @Override
    public void robotInit()
    {
        oi = new OI(new Joystick(DRIVER_CONTROLLER_ID), new Joystick(OPERATOR_CONTROLLER_ID));

        SpeedControllerGroup left = new SpeedControllerGroup(new WPI_TalonSRX(LEFT_FRONT_TALON_ID), new WPI_VictorSPX(LEFT_REAR_VICTOR_ID));
        SpeedControllerGroup right = new SpeedControllerGroup(new WPI_TalonSRX(RIGHT_FRONT_TALON_ID), new WPI_VictorSPX(RIGHT_REAR_VICTOR_ID));
        SpeedController middle = new WPI_TalonSRX(MIDDLE_TALON_ID);

        drivetrain = new DrivetrainSubsystem(new DifferentialDrive(left, right), middle);
        vision = new VisionSubsystem(new Spark(VISION_LIGHT_PWM_ID));
        elevator = new ElevatorSubsystem(new WPI_TalonSRX(ELEVATOR_TALON_ID), new WPI_VictorSPX(ELEVATOR_VICTOR_ID));
        cam = new CAMSubsystem(new CANSparkMax(CAM_SPARK_LEFT_ID, CANSparkMaxLowLevel.MotorType.kBrushless), new CANSparkMax(CAM_SPARK_RIGHT_ID, CANSparkMaxLowLevel.MotorType.kBrushless)); // TODO Set proper CAN values
        hatch = new HatchSubsystem(new WPI_VictorSPX(HATCH_MOTOR_ID), new DoubleSolenoid(0, 1), new DoubleSolenoid(2, 3));
        cargo = new CargoSubsystem(new WPI_VictorSPX(CARGO_LEFT_ID), new WPI_VictorSPX(CARGO_RIGHT_ID));
    }

    @Override
    public void autonomousInit()
    {
        autonomousCommand = chooser.getSelected();

        if (autonomousCommand != null)
        {
            autonomousCommand.start();
        }
    }

    @Override
    public void teleopInit()
    {
        if (autonomousCommand != null)
        {
            autonomousCommand.cancel();
        }

        new ArcadeDriveCommand().start();
    }

    @Override
    public void testInit() {
        VisionTCPClient.startVisionClient();
    }

    @Override
    public void robotPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
