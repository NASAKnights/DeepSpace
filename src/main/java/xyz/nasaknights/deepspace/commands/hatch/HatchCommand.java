package xyz.nasaknights.deepspace.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;

public class HatchCommand extends Command
{
    private boolean eject;
    private boolean finished = false;

    public HatchCommand(boolean eject)
    {
        this.eject = eject;
    }

    @Override
    protected boolean isFinished()
    {
        return finished;
    }

    @Override
    protected void execute()
    {
        Robot.getHatch().setState(eject);
    }
}
