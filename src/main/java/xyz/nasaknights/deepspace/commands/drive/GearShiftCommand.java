package xyz.nasaknights.deepspace.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;

public class GearShiftCommand extends Command
{
    private boolean finished = false;

    @Override
    protected void execute()
    {
        Robot.getDrivetrain().shiftGears(!Robot.getDrivetrain().isInHighGear());

        finished = true;
    }

    @Override
    protected boolean isFinished()
    {
        return this.finished;
    }
}
