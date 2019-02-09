package xyz.nasaknights.deepspace.commands.cam;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.PIDCommand;
import xyz.nasaknights.deepspace.Robot;

public class CAMCommand extends PIDCommand
{
    public CAMCommand()
    {
        super("CAMCommand", 0, 0, 0); // TODO Set proper PID values

        requires(Robot.getCAM());

        this.getPIDController().setAbsoluteTolerance(50);
        this.getPIDController().setSetpoint(Robot.getCAM().getEncoderRotations() + Robot.getCAM().getFullRotationOfCAM());
    }

    @Override
    protected void initialize()
    {
        if (DriverStation.getInstance().getMatchTime() > 30.0 || DriverStation.getInstance().isAutonomous())
        {
            return;
        }

        this.getPIDController().enable();
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.getCAM().getEncoderRotations();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        Robot.getCAM().setPower(output);
    }

    @Override
    protected boolean isFinished()
    {
        return (DriverStation.getInstance().getMatchTime() > 30.0 || DriverStation.getInstance().isAutonomous()) || this.getPIDController().onTarget();
    }

    @Override
    protected void end()
    {
        this.getPIDController().disable();
        Robot.getCAM().setPower(0);
    }

    @Override
    protected void interrupted()
    {
        this.getPIDController().disable();
        Robot.getCAM().setPower(0);
    }
}
