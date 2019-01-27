package xyz.nasaknights.deepspace;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import xyz.nasaknights.deepspace.commands.drive.ArcadeDriveCommand;
import xyz.nasaknights.deepspace.subsystems.cam.CAMSubsystem;
import xyz.nasaknights.deepspace.subsystems.drivetrain.DrivetrainSubsystem;
import xyz.nasaknights.deepspace.subsystems.elevator.ElevatorSubsystem;
import xyz.nasaknights.deepspace.subsystems.hatch.HatchSubsystem;

public class Robot extends TimedRobot
{
    private static DrivetrainSubsystem drivetrain;
    private static ElevatorSubsystem elevator;
    private static CAMSubsystem cam;
    private static HatchSubsystem hatch;

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

    @Override
    public void robotInit()
    {
        oi = new OI(new Joystick(0), new Joystick(1));

        SpeedControllerGroup left = new SpeedControllerGroup(new WPI_TalonSRX(2), new WPI_TalonSRX(3));
        SpeedControllerGroup right = new SpeedControllerGroup(new WPI_TalonSRX(0), new WPI_TalonSRX(1));
        SpeedController middle = new WPI_TalonSRX(4);

        drivetrain = new DrivetrainSubsystem(new DifferentialDrive(left, right), middle);
        /*elevator = new ElevatorSubsystem(new WPI_TalonSRX(4), new WPI_VictorSPX(5)); // TODO Set proper CAN values
        cam = new CAMSubsystem(new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless), new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless)); // TODO Set proper CAN values
        hatch = new HatchSubsystem(new DoubleSolenoid(0, 1), new DoubleSolenoid(2, 3)); */
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
    public void robotPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
