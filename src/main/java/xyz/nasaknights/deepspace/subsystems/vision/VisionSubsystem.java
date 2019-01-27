package xyz.nasaknights.deepspace.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionSubsystem extends Subsystem
{
    private NetworkTable visionTable;
    private NetworkTableEntry rotation;
    private NetworkTableEntry move;

    public VisionSubsystem()
    {
        visionTable = NetworkTableInstance.getDefault().getTable("vision");

        rotation = visionTable.getEntry("rotation");
        move = visionTable.getEntry("move");
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
