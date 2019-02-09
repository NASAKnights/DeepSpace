package xyz.nasaknights.deepspace.subsystems.vision;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionSubsystem extends Subsystem
{
    private Spark spark;

    public VisionSubsystem(Spark spark)
    {
        this.spark = spark;
    }

    public void setSparkPower(double power) {
        this.spark.set(power);
    }

    public Spark getSpark() {
        return spark;
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
