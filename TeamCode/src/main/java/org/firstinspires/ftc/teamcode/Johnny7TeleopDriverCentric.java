package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name="Johnny7Teleop DriverCentric")
public class Johnny7TeleopDriverCentric extends OpMode {
    Johnny6 johnny7;

    //allows driver customization
    static final double STRAFE_FACTOR=1.1;
    boolean bottomSensorPressed = false;
    boolean slidingUp = false;

    public void driveAbsolute(double up, double right, double rotateCW) {
        // figure out direction from up and right
        double v1 = Math.sqrt((up*up) + (right*right));
        double theta = Math.atan2(right, up);
        double beta = johnny7.getHeading();
        double alpha = (Math.PI / 2) - beta - theta;
        double x2 = Math.cos(alpha) * v1;
        double y2 = Math.sin(alpha) * v1;
        johnny7.move(x2, y2, rotateCW);
    }
    @Override
    public void init(){
        johnny7=new Johnny6(this,Johnny6.Drivetrain.JOHNNY6);
        //johnny7.initClip();
        //johnny7.clawClose();
        johnny7.resetSlideEncoders();
        johnny7.resetClawMotor();
    }

    @Override
    public void start(){
        johnny7.clawMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop(){
        double y=gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;

        //telemetry

        telemetry.update();

    //remember, y is the opposite on the axis
            y*=y;
            if (gamepad1.left_stick_y > 0){
                    y = -y;
            }
            x *= x;
            if (gamepad1.left_stick_x < 0) {
                    x = -x;
            }

            double turn = gamepad1.right_stick_x / 2;
            if(gamepad1.right_bumper){
                x = x / 3;
                y = y / 3;
                turn = turn / 3;
            }

            driveAbsolute(y,x,turn);

                //Move claw arm
                if(gamepad2.left_trigger > 0) {
                    johnny7.moveClaw(gamepad2.left_trigger*.5);
                } else if(gamepad2.right_trigger > 0) {
                    johnny7.moveClaw(-gamepad2.right_trigger*.5);
                } else {
                    johnny7.moveClaw(0);
                }


            //Claw code: may need to be changed
            if(gamepad2.x){
                johnny7.clawOpen();
            }
            else if(gamepad2.b){
                johnny7.clawClose();
            }

            //This is the code for making the viper slides go up and down

            if(gamepad2.dpad_up) {
                johnny7.slideHigh();
            }
            else if(gamepad2.dpad_right) {
                johnny7.slideMedium();
            }
            else if(gamepad2.dpad_down) {
                johnny7.slideLow();
            }
            else if(gamepad2.dpad_left) {
                johnny7.slideHang();
            }

            //touch sensor code
            if(johnny7.isBottomSensorPressed()&&!bottomSensorPressed){
                bottomSensorPressed=true;
                johnny7.stopBottomSlide();
                johnny7.resetSlideEncoders();
            }
            if(!johnny7.isBottomSensorPressed()) {
                bottomSensorPressed=false;
            }

    }
}
