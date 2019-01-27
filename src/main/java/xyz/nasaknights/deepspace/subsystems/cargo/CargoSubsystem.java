package xyz.nasaknights.deepspace.subsystems.cargo;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoSubsystem extends Subsystem
{
    private WPI_VictorSPX left;
    private WPI_VictorSPX right;

    public CargoSubsystem(WPI_VictorSPX left, WPI_VictorSPX right)
    {
        this.left = left;
        this.right = right;
    }

    public void setPower(double power)
    {
        left.set(power);
        right.set(power);
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
