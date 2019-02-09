package xyz.nasaknights.deepspace.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;

public class HatchCommand extends Command
{
    private boolean extend;
    private boolean finished = false;

    public HatchCommand(boolean extend)
    {
        requires(Robot.getHatch());
        this.extend = extend;
    }

    @Override
    protected boolean isFinished()
    {
        return finished;
    }

    @Override
    protected void execute()
    {
        Robot.getHatch().setState(extend);
    }
}
