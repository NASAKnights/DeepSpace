package xyz.nasaknights.deepspace.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;
import xyz.nasaknights.deepspace.controllers.ControllerMappings;

public class ArcadeDriveCommand extends Command
{
    public ArcadeDriveCommand()
    {
        requires(Robot.getDrivetrain());
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void execute()
    {
        Joystick driver = Robot.getOI().getDriver();

        Robot.getDrivetrain().getDrivetrain().arcadeDrive(driver.getRawAxis(ControllerMappings.PS4Controller.LEFT_Y_AXIS.getID()), driver.getRawAxis(ControllerMappings.PS4Controller.RIGHT_X_AXIS.getID()));
        Robot.getDrivetrain().getMiddle().set(driver.getRawAxis(ControllerMappings.PS4Controller.LEFT_X_AXIS.getID()));
    }
}
