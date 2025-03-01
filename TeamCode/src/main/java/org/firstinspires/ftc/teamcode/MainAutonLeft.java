package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
public class MainAutonLeft extends LinearOpMode{
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();

        // johnny7.initClip();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed = 0.5;
        int rest = 100;


        waitForStart();
        runtime.reset();// start on left side the block closest to the high bucket with preloaded sample
        johnny7.turnLeftDegrees(90,speed);
        sleep(rest);
        johnny7.slideHigh();
        johnny7.moveLeftInches(4,speed);



    }
}

