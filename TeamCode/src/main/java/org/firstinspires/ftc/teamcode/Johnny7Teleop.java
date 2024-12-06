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
        int isPrimed=0;

        //telemetry
        telemetry.addData("sensor state", johnny7.bottomSensor.isPressed());
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


                if(gamepad2.left_trigger > 0) {
                    johnny7.moveClaw(gamepad2.left_trigger*0.5);
                } else if(gamepad2.right_trigger > 0) {
                    johnny7.moveClaw(-gamepad2.right_trigger*0.5);
                } else {
                    johnny7.moveClaw(0);
                }

                //Bucket code
                if(gamepad2.a) {
                    johnny7.bucketDump();
                } else {
                    johnny7.bucketPrimed();
                }

            //manual joystick override for slide
            if(gamepad2.left_stick_y<-0.1){
                johnny7.slideMotor1.setPower(1);
                johnny7.slideMotor2.setPower(1);
                johnny7.slideMotor2.setPower(1);
            }
            else if(gamepad2.left_stick_y>0.1){
                johnny7.slideMotor1.setPower(-1);
                johnny7.slideMotor2.setPower(-1);
            }
            else{
                johnny7.slideMotor1.setPower(0);
                johnny7.slideMotor2.setPower(0);
            }

                //This is the simmilar code to the Trebuchet Teleop
                /*if(gamepad2.right_bumper){
                 isBumperPressed=true;
                }
                else{
                 isBumperPressed=false;
                 isBumperProcessed=false;
                }
                if(isBumperPressed&&!isBumperProcessed){
                    if(isClawOpen){
                        johnny7.clawClose();
                        isClawOpen=false;
                    }
                    else{
                        johnny7.clawOpen();
                        isClawOpen=true;

                    }
                    isBumperProcessed=true;

                }*/

            //test claw code

            if(gamepad2.x){
                johnny7.clawOpen();
            }
            else if(gamepad2.b){
                johnny7.clawClose();
            }

            //This is the code for making the viper slides go up and down
            if(gamepad2.dpad_up) {
                johnny7.slideUpHigh(50);
              //  telemetry.addData("slide motor 1 position", johnny7.slideMotor1.getTargetPosition());
            } else if(gamepad2.dpad_down) {
                johnny7.slideDown();    
            } else if(gamepad2.dpad_right) {
                johnny7.slideUpMed(15);
            } else {
                johnny7.rest();
            }
            if(johnny7.isBottomSensorPressed()&&!bottomSensorPressed){
                bottomSensorPressed=true;
                if(bottomSensorPressed){
                    johnny7.stopBottomSlide();

                }
            }

    }
}
