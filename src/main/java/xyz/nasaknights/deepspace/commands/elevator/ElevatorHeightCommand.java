package xyz.nasaknights.deepspace.commands.elevator;

import edu.wpi.first.wpilibj.command.PIDCommand;
import xyz.nasaknights.deepspace.Robot;
import xyz.nasaknights.deepspace.subsystems.elevator.ElevatorSubsystem;

public class ElevatorHeightCommand extends PIDCommand
{
    private ElevatorSubsystem.ElevatorHeight height;

    public ElevatorHeightCommand(ElevatorSubsystem.ElevatorHeight height)
    {
        // TODO Tune PID to correct values
        super("AutomaticElevatorCommand", 0, 0, 0);
        this.height = height;

        requires(Robot.getElevator());

        this.getPIDController().setAbsoluteTolerance(50);
        this.getPIDController().setSetpoint(height.getEncoderTicks());
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
