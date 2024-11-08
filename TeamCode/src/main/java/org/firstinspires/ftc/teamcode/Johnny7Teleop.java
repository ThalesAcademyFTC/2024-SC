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
    public void init(){johnny7=new Johnny6(this,Johnny6.Drivetrain.JOHNNY6); johnny7.bucketPrimed();}

    @Override
    public void loop(){
        double y=gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;
        boolean isUp=false;
        boolean isDown;
        int isPrimed=0;

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

                if(gamepad2.x){
                   isPrimed+=1;
                   if(isPrimed==1){
                       johnny7.bucketDump();
                   }
                   else if(isPrimed<1){
                       johnny7.bucketPrimed();
                       isPrimed=0;
                   }
                }
                else{
                    johnny7.bucketPrimed();
                }


            if(gamepad2.dpad_up) {
                johnny7.slideUp();
                isUp=true;
                isDown=false;
            } else if(gamepad2.dpad_down) {
                if(johnny7.isBottomSensorPressed()) {
                    johnny7.rest();
                }
                else {
                    johnny7.slideDown();
                }
            }
            else {

                johnny7.rest();

            }




    }
}
