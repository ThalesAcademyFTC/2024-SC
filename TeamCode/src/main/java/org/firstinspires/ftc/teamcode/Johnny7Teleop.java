package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        y*=y;
        if(gamepad1.left_stick_y>0){
            y=-y;
        }
        x*=x;
        if(gamepad1.left_stick_x<0){
            x=-x;
        }
        double turn=gamepad1.right_stick_x/2;

        johnny7.move(x,y,turn);
    }
}
