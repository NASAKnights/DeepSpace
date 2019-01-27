package xyz.nasaknights.deepspace.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAssistCommand extends Command
{
    private boolean finished = false;

    public DriveAssistCommand()
    {
        super("DriveAssistance");
    }

    @Override
    protected boolean isFinished()
    {
        return finished;
    }
}
