package xyz.nasaknights.deepspace.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Subsystem
{
    private DifferentialDrive drivetrain;
    private SpeedController middle;
    private DoubleSolenoid left;
    private DoubleSolenoid right;

    private Command activeCommand = null;

    private boolean highGear = false;

    public DrivetrainSubsystem(DifferentialDrive drivetrain, SpeedController middle)
    {
        super("Drivetrain");
        this.drivetrain = drivetrain;
        this.drivetrain.setSafetyEnabled(false);
        this.middle = middle;

        // TODO Set proper solenoid channel IDs
        this.left = new DoubleSolenoid(0, 1);
        this.right = new DoubleSolenoid(2, 3);
    }

    @Override
    protected void initDefaultCommand()
    {
    }

    public DifferentialDrive getDrivetrain()
    {
        return drivetrain;
    }

    public SpeedController getMiddle()
    {
        return this.middle;
    }

    public void shiftGears(boolean high)
    {
        if (this.highGear == high) return;

        left.set(high ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        right.set(high ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public boolean isInHighGear()
    {
        return this.highGear;
    }

    public Command getActiveCommand() {
        return activeCommand;
    }

    public void setActiveCommand(Command cmd) {
        activeCommand.cancel();
        activeCommand = cmd;
    }
}
