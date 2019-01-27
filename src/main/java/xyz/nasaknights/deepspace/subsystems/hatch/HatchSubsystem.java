package xyz.nasaknights.deepspace.subsystems.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchSubsystem extends Subsystem
{
    private DoubleSolenoid s1;
    private DoubleSolenoid s2;

    public HatchSubsystem(DoubleSolenoid s1, DoubleSolenoid s2)
    {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public void setState(boolean eject)
    {
        s1.set(eject ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        s2.set(eject ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }
}
