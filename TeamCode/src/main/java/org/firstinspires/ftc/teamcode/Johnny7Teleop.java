package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Johnny7Teleop")
public class Johnny7Teleop extends OpMode {
    Johnny6 johnny7;

    //allows driver customization
    static final double STRAFE_FACTOR=1.1;


    @Override
    public void init(){
        johnny7=new Johnny6(this,Johnny6.Drivetrain.JOHNNY6);
        johnny7.bucketPrimed();
        johnny7.clawClose();
    }

    @Override
    public void loop(){
        double y=gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;
        boolean bottomSensorPressed = false;
        boolean dpadDownPressed=false;
        boolean dpadDownProcessed=false;
        boolean isUp=false;
        boolean isDown;
         boolean motorToggle = false;
         boolean lastButtonState = false;
        boolean isBumperPressed= false;
        boolean isBumperProcessed = false;
        boolean isClawOpen=false;
        boolean slidingUp = false;
        int isPrimed=0;

        //telemetry
        telemetry.addData("sensor state", johnny7.bottomSensor.isPressed());
        telemetry.addData("slide motor 1 encoder:",johnny7.slideMotor1.getCurrentPosition());
        telemetry.addData("slide 1 target", johnny7.slideMotor1.getTargetPosition());
        telemetry.addData("slide motor 2 encoder: ",johnny7.slideMotor2.getCurrentPosition());
        telemetry.addData("slide 2 target", johnny7.slideMotor2.getTargetPosition());
        telemetry.addData("front left encoder:", johnny7.motorFrontLeft.getCurrentPosition());
        telemetry.addData("front right encoder:", johnny7.motorFrontRight.getCurrentPosition());
        telemetry.addData("back left encoder:", johnny7.motorBackLeft.getCurrentPosition());
        telemetry.addData("back right encoder:", johnny7.motorBackRight.getCurrentPosition());
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

        johnny7.move(x,y,turn);

                //Move claw arm
                if(gamepad2.left_trigger > 0) {
                    johnny7.moveClaw(gamepad2.left_trigger*1);
                } else if(gamepad2.right_trigger > 0) {
                    johnny7.moveClaw(-gamepad2.right_trigger*1);
                } else {
                    johnny7.moveClaw(0);
                }

                //Bucket code
                if(gamepad2.right_bumper) {
                    johnny7.bucketDump();
                } else if (gamepad2.left_bumper){
                    johnny7.bucketLoad();
                } else {
                    johnny7.bucketPrimed();
                }

            //Claw code: may need to be changed
            if(gamepad2.x){
                johnny7.clawOpen();
            }
            else if(gamepad2.b){
                johnny7.clawClose();
            }

            //This is the code for making the viper slides go up and down
            if(gamepad2.dpad_up&&!slidingUp) {
                slidingUp = true;
                johnny7.slideHigh();
            }
            else if(gamepad2.dpad_right&&!slidingUp) {
                slidingUp = true;
                johnny7.slideMedium();
            }
            else if(gamepad2.dpad_down) {
                johnny7.slideLow();
            }
            else {
                johnny7.rest();
            }

            //touch sensor code
            if(johnny7.isBottomSensorPressed()&&!bottomSensorPressed){
                bottomSensorPressed=true;
                if(bottomSensorPressed){
                    johnny7.stopBottomSlide();
                }
            }

    }
}
