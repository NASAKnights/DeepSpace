package xyz.nasaknights.deepspace.subsystems.hatch;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchSubsystem extends Subsystem
{
    private DoubleSolenoid s1;
    private DoubleSolenoid s2;
    private WPI_VictorSPX victor;

    public HatchSubsystem(WPI_VictorSPX victor, DoubleSolenoid s1, DoubleSolenoid s2)
    {
        this.victor = victor;
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public void setState(boolean extend)
    {
        s1.set(extend ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        s2.set(extend ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public boolean isExtended()
    {
        return s1.get() == DoubleSolenoid.Value.kForward;
    }
}
