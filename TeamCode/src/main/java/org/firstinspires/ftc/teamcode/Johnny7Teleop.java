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
    public void init(){johnny7=new Johnny6(this,Johnny6.Drivetrain.JOHNNY6);}

    @Override
    public void loop(){
        double y=gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;
        boolean bottomSensorPressed = false;
        boolean dpadDownPressed=false;
        boolean dpadDownProcessed=false;
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



            if(gamepad2.dpad_up) {
                johnny7.slideUp();
            } else if(gamepad2.dpad_down) {

                johnny7.slideDown();
                if(johnny7.isBottomSensorPressed()&&!bottomSensorPressed){
                    bottomSensorPressed=true;
                    if(bottomSensorPressed){
                        johnny7.stopBottomSlide();
                    }
                }
            }
            else {

                johnny7.rest();
            }

    }
}
