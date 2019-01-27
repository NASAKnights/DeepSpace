package xyz.nasaknights.deepspace;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    private Joystick driver;
    private Joystick operator;

    OI(Joystick driver, Joystick operator)
    {
        this.driver = driver;
        this.operator = operator;

        //new JoystickButton(driver, ControllerMappings.PS4Controller.X.getID()).whenPressed(new GearShiftCommand());

        //new JoystickButton(operator, ControllerMappings.PS4Controller.CIRCLE.getID()).whileHeld(new ElevatorCommand(false));
//        new JoystickButton(operator, ControllerMappings.PS4Controller.CIRCLE.getID()).whileHeld(new ElevatorCommand(true));
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
