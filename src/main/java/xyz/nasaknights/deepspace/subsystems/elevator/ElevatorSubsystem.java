package xyz.nasaknights.deepspace.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorSubsystem extends Subsystem
{
    private TalonSRX m1;
    private VictorSPX m2;

    public ElevatorSubsystem(TalonSRX m1, VictorSPX m2)
    {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public int getEncoderRotations()
    {
        return m1.getSensorCollection().getQuadraturePosition();
    }

    public void setPower(double power)
    {
        m1.set(ControlMode.PercentOutput, power);
        m2.set(ControlMode.PercentOutput, power);
    }

    public enum ElevatorHeight
    {
        // TODO Set actual encoder values for elevator height
        BOTTOM(0),
        MIDDLE(10000),
        TOP(20000);

        private long encoderTicks;

        ElevatorHeight(long encoderTicks)
        {
            this.encoderTicks = encoderTicks;
        }

        public long getEncoderTicks()
        {
            return encoderTicks;
        }
    }
}
