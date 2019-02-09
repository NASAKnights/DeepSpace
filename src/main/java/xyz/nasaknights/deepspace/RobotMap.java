package xyz.nasaknights.deepspace;

public class RobotMap {
    public static short LEFT_REAR_VICTOR_ID = 4;
    public static short RIGHT_REAR_VICTOR_ID = 5;
    public static short LEFT_FRONT_TALON_ID = 2;
    public static short RIGHT_FRONT_TALON_ID = 3;
    public static short MIDDLE_TALON_ID = 5;

    // TODO Update all CAN IDs

    public static String VISION_SERVER_IP = "10.1.22.20";
    public static short VISION_SERVER_PORT = 8080;

    public static short HATCH_MOTOR_ID = 0;

    public static short VISION_LIGHT_PWM_ID = 0;

    public static short ELEVATOR_TALON_ID = 0;
    public static short ELEVATOR_VICTOR_ID = 0;

    public static short CAM_SPARK_LEFT_ID = 0;
    public static short CAM_SPARK_RIGHT_ID = 1;

    public static short DRIVER_CONTROLLER_ID = 0;
    public static short OPERATOR_CONTROLLER_ID = 1;

    public static short CARGO_LEFT_ID = 0;
    public static short CARGO_RIGHT_ID = 1;
}