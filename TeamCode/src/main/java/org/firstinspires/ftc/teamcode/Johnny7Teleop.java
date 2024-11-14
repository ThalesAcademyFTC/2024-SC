package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Johnny7Teleop")
public class Johnny7Teleop extends OpMode {
    Johnny6 johnny7;

    //allows driver customization
    static final double STRAFE_FACTOR=1.1;

    //initialize code :)
    @Override
    public void init(){johnny7=new Johnny6(this,Johnny6.Drivetrain.JOHNNY6); johnny7.bucketPrimed();}

    @Override
    public void loop(){
        double y=gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;
        boolean isUp=false;
        boolean isDown;
        boolean isBumperPressed=false;
        boolean isBumperProcessed = false;
        boolean isClawOpen=false;
        int isPrimed=0;
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
            //this is for slowing down the silly robot
            if(gamepad1.left_bumper){
                x = x / 3;
                y = y / 3;
                turn = turn / 3;
            }


        johnny7.move(x,y,turn);

                if(gamepad2.right_trigger>0){
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
                //This is the simmilar code to the Trebuchet Teleop

                if(gamepad2.right_bumper){
                 isBumperPressed=true;
                }
                else{
                 isBumperPressed=false;
                 isBumperProcessed=false;
                }
                if(isBumperPressed&&!isBumperProcessed){
                    if(isClawOpen){
                        johnny7.clawOpen();
                        isClawOpen=false;
                    }
                    else{
                        johnny7.clawClose();
                        isClawOpen=true;

                    }
                    isBumperProcessed=true;

                }
            //This is the code for making the viper slides go up and down
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


//yeah