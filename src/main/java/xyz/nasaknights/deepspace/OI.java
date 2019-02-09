package xyz.nasaknights.deepspace;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import xyz.nasaknights.deepspace.commands.cam.CAMCommand;
import xyz.nasaknights.deepspace.commands.cargo.CargoCommand;
import xyz.nasaknights.deepspace.commands.drive.GearShiftCommand;
import xyz.nasaknights.deepspace.commands.elevator.ElevatorCommand;
import xyz.nasaknights.deepspace.commands.hatch.HatchCommand;
import xyz.nasaknights.deepspace.controllers.ControllerMappings;

public class OI
{
    private Joystick driver;
    private Joystick operator;

    OI(Joystick driver, Joystick operator)
    {
        this.driver = driver;
        this.operator = operator;

        new JoystickButton(driver, ControllerMappings.PS4Controller.X.getID()).whenPressed(new GearShiftCommand());
        new JoystickButton(driver, ControllerMappings.PS4Controller.SHARE.getID()).whenPressed(new CAMCommand());

        new JoystickButton(operator, ControllerMappings.PS4Controller.CIRCLE.getID()).whileHeld(new ElevatorCommand(false));
        new JoystickButton(operator, ControllerMappings.PS4Controller.TRIANGLE.getID()).whileHeld(new ElevatorCommand(true));
        new JoystickButton(operator, ControllerMappings.PS4Controller.X.getID()).whenPressed(new HatchCommand(!Robot.getHatch().isExtended()));
        new JoystickButton(operator, ControllerMappings.PS4Controller.LEFT_BUMPER.getID()).whileHeld(new CargoCommand(true));
        new JoystickButton(operator, ControllerMappings.PS4Controller.LEFT_BUMPER.getID()).whileHeld(new CargoCommand(false));
    }

    public Joystick getDriver()
    {
        return driver;
    }

    public Joystick getOperator()
    {
        return operator;
    }
}
