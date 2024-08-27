package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
@TeleOp(name="OutreachTeleop")
public class TrebuchetTeleop extends OpMode {
    Johnny6 trebuchet6;
    boolean unLocked=false;
    boolean rBumperPressed=false;
    boolean rBumperProcessed=false;
    boolean sensorPressed=false;
    boolean sensorNotPressed=false;
    boolean sensorProcessed=false;

    @Override
    public void init() { trebuchet6=new Johnny6( this, Johnny6.Drivetrain.TREBUCHET6);
    trebuchet6.unLock();}

    public void loop(){

        //Movement code
        double y=-gamepad1.left_stick_y;
        double x=gamepad1.left_stick_x;

        y*=y;
        x*=x;
        if(gamepad1.left_stick_y>0){
            y=-y;
        }
        if(gamepad1.left_stick_x<0){
            x=-x;
        }

        double turn=gamepad1.left_stick_x/2;
        trebuchet6.move(-x,y,0);

        //Code for trebuchet launch
        if(gamepad1.right_bumper){
            rBumperPressed=true;
        }else{
            rBumperPressed=false;
            rBumperProcessed=false;
        }
        if(rBumperPressed&&!rBumperProcessed){
            if(unLocked){
                trebuchet6.Locked();
                unLocked=false;
            }
            else{
                trebuchet6.unLock();
                unLocked=true;
                sensorPressed=false;
            }
            rBumperProcessed=true;
        }

        //codings for the stupid sensor we cant figure out
        if(trebuchet6.isLockSensorPressed() && !sensorPressed) {
            sensorPressed=true;
            if (sensorPressed){
                trebuchet6.Locked();
            }

        }

    }
}

