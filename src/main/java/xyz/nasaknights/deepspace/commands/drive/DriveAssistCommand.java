package xyz.nasaknights.deepspace.commands.drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import xyz.nasaknights.deepspace.Robot;
import xyz.nasaknights.deepspace.subsystems.vision.VisionTCPClient;

public class DriveAssistCommand extends Command
{
    private PIDController xController = new PIDController(0, 0, 0, new PIDSource() {
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public double pidGet() {
            return VisionTCPClient.getX();
        }


    }, this::useXOutput);

    private PIDController yController = new PIDController(0, 0, 0, new PIDSource() {
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public double pidGet() {
            return VisionTCPClient.getY();
        }


    }, this::useYOutput);

    public DriveAssistCommand()
    {
        super("DriveAssistance");

        requires(Robot.getDrivetrain());

        xController.setSetpoint(0);
        xController.setAbsoluteTolerance(1);

        yController.setSetpoint(0);
        yController.setAbsoluteTolerance(1);
    }

    @Override
    protected boolean isFinished()
    {
        return xController.onTarget() && yController.onTarget();
    }

    @Override
    protected void end() {
        Robot.getDrivetrain().setActiveCommand(new ArcadeDriveCommand());
    }

    private void useXOutput(double output) {
        if (xController.onTarget()) Robot.getDrivetrain().getMiddle().set(0);

        Robot.getDrivetrain().getMiddle().set(output);
    }

    private void useYOutput(double output) {
        if (yController.onTarget()) Robot.getDrivetrain().getDrivetrain().arcadeDrive(0, 0);

        Robot.getDrivetrain().getDrivetrain().arcadeDrive(output, VisionTCPClient.getRotation());
    }
}
