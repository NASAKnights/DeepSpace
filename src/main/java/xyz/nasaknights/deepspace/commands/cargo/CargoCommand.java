package xyz.nasaknights.deepspace.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;

public class CargoCommand extends Command
{
    private boolean intake;

    public CargoCommand(boolean intake)
    {
        this.intake = intake;
    }

    @Override
    protected void execute()
    {
        Robot.getCargo().setPower(intake ? 1 : -1);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
