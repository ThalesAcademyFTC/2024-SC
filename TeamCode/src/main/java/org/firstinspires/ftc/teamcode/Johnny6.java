package org.firstinspires.ftc.teamcode;

import static java.nio.file.Files.move;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorTouch;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.TouchSensor;
public class Johnny6 {

    private HardwareMap hwMap;

    LinearOpMode auton;

    public enum Drivetrain {
        MECHANUM,
        JOHNNY6,
        TEST,
        TREBUCHET6
    }

    public enum Team {
        RED,
        BLUE
    }

    private Drivetrain drive;

    private Telemetry telem;

    //Definitions for global variables

    public DcMotor motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight, slideMotor1, slideMotor2;
    //[] means array
    public DcMotor[] allDriveMotors;
    public DcMotor[] allSlideMotors;

    //Outreach robot servos
    public Servo lockServo;

    public TouchSensor lockSensor;

    //public CRServo //future necessary robot functions using servos
    private IMU imu;

    private IMU.Parameters parameters;

    public WebcamName webcamName;

    //Put any CONSTANTS here

    static final double Y_INCH_TICKS = 40;

    static final double X_INCH_TICKS = 40;

    public Johnny6(OpMode opmode, Drivetrain drivetrain) {

        this.hwMap = opmode.hardwareMap;

        this.drive = drivetrain;

        this.telem = opmode.telemetry;

        //setUpHardware maps variables to their hardware object
        setupHardware();
    }

    public Johnny6(LinearOpMode opmode, Drivetrain type) {

        this.auton = opmode;

        hwMap = opmode.hardwareMap;

        telem = opmode.telemetry;

        drive = type;

        setupHardware();

        telem.addLine("init motor test");
    }

    public Johnny6(HardwareMap hardwareMap, Drivetrain drivetrain) {

        this.hwMap = hardwareMap;

        this.drive = drivetrain;

        setupHardware();
    }

    private void setupHardware() {

        //This switch statement is used to choose which drivetrain
        //depending on the drive variable
        switch (drive) {

            case JOHNNY6:

                motorFrontLeft = hwMap.dcMotor.get("motorFrontLeft");
                motorFrontRight = hwMap.dcMotor.get("motorFrontRight");
                motorBackLeft = hwMap.dcMotor.get("motorBackLeft");
                motorBackRight = hwMap.dcMotor.get("motorBackRight");
                slideMotor1 = hwMap.dcMotor.get("slideMotor1");
                slideMotor2 = hwMap.dcMotor.get("slideMotor2");

                motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                slideMotor2.setDirection(DcMotorSimple.Direction.REVERSE);


                imu = hwMap.get(IMU.class, "imu");


                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT));


                imu.initialize(parameters);

                allDriveMotors = new DcMotor[]{motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight};
                allSlideMotors = new DcMotor[]{slideMotor1, slideMotor2};

                //swebcamName = hwMap.get(WebcamName.class, "eyeofjohnny6");

                //Add arm mechanism hardware devices here


                break;

            case TEST:

                //setup motors for drivetrain
                motorFrontLeft = hwMap.dcMotor.get("motorFrontLeft");
                motorFrontRight = hwMap.dcMotor.get("motorFrontRight");
                motorBackLeft = hwMap.dcMotor.get("motorBackLeft");
                motorBackRight = hwMap.dcMotor.get("motorBackRight");

                //Reverse motors
                motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
                motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

                //Here would go any additional hardware devices for the robot

                imu = hwMap.get(IMU.class, "imu");

                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

                imu.initialize(parameters);


                //camera setup!
                webcamName = hwMap.get(WebcamName.class, "eyeofjohnny6");
                allDriveMotors = new DcMotor[]{motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight};

                break;

            case TREBUCHET6:

                motorFrontLeft = hwMap.dcMotor.get("motorFrontLeft");
                motorFrontRight = hwMap.dcMotor.get("motorFrontRight");

                motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);


                imu = hwMap.get(IMU.class, "imu");

                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT));


                imu.initialize(parameters);
                //these are for Trebuchet
                lockServo = hwMap.servo.get("lockServo");
                lockSensor = hwMap.touchSensor.get("lockSensor");

                break;

            default:

                telem.addLine("Invalid type " + drive + " passed to Johnny6's init function. Nothing has been set up ");
                break;
        }
    }

    //Set motor power for all drivetrain motors on robot to 0

    //set powers for motors and positions for servos
    public void rest() {
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        slideMotor1.setPower(0);
        slideMotor2.setPower(0);

    }

    /*
    This function controls movement for the robot.
    @param x the x speed value
    @param y the y speed value
    @param turn the turn speed value
     */

    public void move(double x, double y, double turn) {

        switch (drive) {

            case JOHNNY6:
                //Denominator is the larget motor power (absolute value) or 1
                //This ensures all the powers maintain the same ratio, but only when
                //at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                //Compute values for the power of each motor
                double frontLeftPower = (y + x + turn) / denominator;
                double backLeftPower = (y - x + turn) / denominator;
                double frontRightPower = (y - x - turn) / denominator;
                double backRightPower = (y + x - turn) / denominator;

                telem.addLine("frontLeft: " + frontLeftPower);
                telem.addLine("frontRight: " + frontRightPower);
                telem.addLine("backLeft: " + backLeftPower);
                telem.addLine("backRight: " + backRightPower);

                //Assign that motor power to each motor
                motorFrontLeft.setPower(frontLeftPower);
                motorBackLeft.setPower(backLeftPower);
                motorFrontRight.setPower(frontRightPower);
                motorBackRight.setPower(backRightPower);

                break;

            case MECHANUM:

                //Denominator is the larget motor power (absolute value) or 1
                //This ensures all the powers maintain the same ratio, but only when
                //at least one is out of the range [-1, 1]
                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                //Compute values for the power of each motor
                frontLeftPower = (y + x + turn) / denominator;
                backLeftPower = (y - x + turn) / denominator;
                frontRightPower = (y - x - turn) / denominator;
                backRightPower = (y + x - turn) / denominator;

                //Assign that motor power to each motor
                motorFrontLeft.setPower(frontLeftPower);
                motorBackLeft.setPower(backLeftPower);
                motorFrontRight.setPower(frontRightPower);
                motorBackRight.setPower(backRightPower);

                break;


            case TEST:

                //Denominator is the larget motor power (absolute value) or 1
                //This ensures all the powers maintain the same ratio, but only when
                //at least one is out of the range [-1, 1]

                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                //Compute values for the power of each motor
                frontLeftPower = (y + x + turn) / denominator;
                backLeftPower = (y - x + turn) / denominator;
                frontRightPower = (y - x - turn) / denominator;
                backRightPower = (y + x - turn) / denominator;

                //Assign that motor power to each motor
                motorFrontLeft.setPower(frontLeftPower);
                motorBackLeft.setPower(backLeftPower);
                motorFrontRight.setPower(frontRightPower);
                motorBackRight.setPower(backRightPower);

                break;
            case TREBUCHET6:
                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                //compute the values for power of each motor
                // make -y because if it was on front, it would be backwards

                backLeftPower = (-y - x) / denominator;
                backRightPower = (-y + x) / denominator;

                //assign power to motor
                motorFrontLeft.setPower(backLeftPower);
                motorFrontRight.setPower(backRightPower);

                break;
        }
    }

    public void moveLeft(double speed) {
        move(-speed, 0, 0);
    }

    public void moveRight(double speed) {
        move(speed, 0, 0);
    }

    public void moveForward(double speed) {
        move(speed, 0, 0);
    }

    public void moveBackward(double speed) {
        move(0, speed, 0);
    }

    public void turnLeft(double speed) {
        move(0, 0, -speed);
    }

    public void turnRight(double speed) {
        move(0, 0, speed);
    }


    public void resetYaw() {
        imu.resetYaw();
    }

    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }


    //Trebuchet servos and other thingies
    public void unLock() {
        lockServo.setPosition(0.5);
    }

    public void Locked() {
        lockServo.setPosition(1);
    }

    public boolean isLockSensorPressed() {
        return lockSensor.isPressed();
    }

    //Competetion teleop movement for motors and servos
    public void slideUp() {slideMotor1.setPower(1); slideMotor2.setPower(1);}
    public void slideDown() {slideMotor1.setPower(-1); slideMotor2.setPower(-1);}


    public void moveForwardInches(double inches, double speed) {

        //Converts to integer by rounding. CASTS to int after rounding.
        int tickTarget = (int) Math.round(inches * Y_INCH_TICKS);

        resetDriveEncoders();

        for (DcMotor x : allDriveMotors) {

            x.setTargetPosition(tickTarget);
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

        move(0, speed, 0);

        waitForMotors();

        resetDriveEncoders();

    }


    public void moveBackwardInches(double inches, double speed) {

        moveForwardInches(-inches, -speed);

    }

    public void moveRightInches(double inches, double speed) {

        int tickTarget = (int) Math.round(inches * X_INCH_TICKS);

        resetDriveEncoders();
        //
        motorFrontLeft.setTargetPosition(tickTarget);
        motorFrontRight.setTargetPosition(-tickTarget);
        motorBackLeft.setTargetPosition(-tickTarget);
        motorBackRight.setTargetPosition(tickTarget);


        for (DcMotor x : allDriveMotors) {

            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

        move(speed, 0, 0);

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveLeftInches(double inches, double speed) {

        moveRightInches(-inches, -speed);

    }

    public void waitForMotors() {
        boolean finished = false;
        while (auton.opModeIsActive() && !finished && !auton.isStopRequested()) {
                if (motorFrontLeft.isBusy() || motorBackLeft.isBusy() || motorFrontRight.isBusy() || motorBackRight.isBusy()) {
                    telem.addData("front left encoder:", motorFrontLeft.getCurrentPosition());
                    telem.addData("front right encoder:", motorFrontRight.getCurrentPosition());
                    telem.addData("back left encoder:", motorBackLeft.getCurrentPosition());
                    telem.addData("back right encoder:", motorBackRight.getCurrentPosition());
                    telem.update();
                } else {
                    finished = true;
                }
        }
    }


    private void resetDriveEncoders() {
        for (DcMotor x: allDriveMotors) {
            x.setPower(0);
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            x.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}