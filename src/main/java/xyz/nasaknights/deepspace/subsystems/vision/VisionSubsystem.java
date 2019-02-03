package xyz.nasaknights.deepspace.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionSubsystem extends Subsystem
{
    private NetworkTable visionTable;
    private NetworkTableEntry rotation;
    private NetworkTableEntry move;

    private Spark spark;

    public VisionSubsystem(Spark spark)
    {
        visionTable = NetworkTableInstance.getDefault().getTable("vision");

        rotation = visionTable.getEntry("rotation");
        move = visionTable.getEntry("move");

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
