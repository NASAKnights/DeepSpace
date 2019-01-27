package xyz.nasaknights.deepspace.subsystems.cam;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CAMSubsystem extends Subsystem
{
    private static final double ROTATION_ENCODER_TICKS = 5000;
    private CANSparkMax m1;
    private CANSparkMax m2;

    public CAMSubsystem(CANSparkMax m1, CANSparkMax m2)
    {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public double getEncoderRotations()
    {
        return m1.getEncoder().getPosition();
    }

    public double getFullRotationOfCAM()
    {
        return ROTATION_ENCODER_TICKS;
    }

    public void setPower(double power)
    {
        m1.set(power);
        m2.set(power * -1); // Assuming this motor is going counterclockwise when CAM is going forward
    }
}
