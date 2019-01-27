package xyz.nasaknights.deepspace.commands.elevator;

import edu.wpi.first.wpilibj.command.PIDCommand;
import xyz.nasaknights.deepspace.Robot;
import xyz.nasaknights.deepspace.subsystems.elevator.ElevatorSubsystem;

public class ElevatorCommand extends PIDCommand
{
    boolean up;

    public ElevatorCommand(boolean up)
    {
        // TODO Tune PID to correct values
        super("ManualElevatorCommand", 0, 0, 0);
        this.up = up;

        requires(Robot.getElevator());

        this.getPIDController().setAbsoluteTolerance(50);
        this.getPIDController().setSetpoint(up ? ElevatorSubsystem.ElevatorHeight.TOP.getEncoderTicks() : ElevatorSubsystem.ElevatorHeight.BOTTOM.getEncoderTicks());
    }

    @Override
    protected void initialize()
    {
        this.getPIDController().enable();
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.getElevator().getEncoderRotations();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        Robot.getElevator().setPower(output);
    }

    @Override
    protected boolean isFinished()
    {
        return this.getPIDController().onTarget();
    }

    @Override
    protected void interrupted()
    {
        this.getPIDController().disable();
        Robot.getElevator().setPower(0);
    }

    @Override
    protected void end()
    {
        this.getPIDController().disable();
        Robot.getElevator().setPower(0);
    }
}
