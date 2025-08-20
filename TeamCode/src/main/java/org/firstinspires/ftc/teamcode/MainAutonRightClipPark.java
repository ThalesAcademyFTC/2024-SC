package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class MainAutonRightClipPark extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();
        //johnny7.initClip();
        johnny7.clawClose();
        johnny7.slideLow();
        double speedSlow = 0.5;
        double speedFast = 1;
        int rest = 100;


        waitForStart();
        runtime.reset();
        johnny7.moveLeftInches(5, speedSlow); //move away from field wall
        sleep(rest);
        johnny7.turnRightDegrees(90,speedSlow); //turn to face the rungs
        sleep(rest);
        johnny7.readyToClip();//ready to get the specimen in the right position for clipping
        sleep(rest);
        johnny7.moveForwardInches(18,speedSlow); //move forward towards submersible zone
        sleep(rest);
        //johnny7.waitForClawMotor();
        //sleep(rest);
        johnny7.actuallyClip();//actually clip onto the high bar
        sleep(rest);
        johnny7.moveBackwardInches(5,speedSlow);//move back a little bit so that we can move left
        sleep(rest);
        //johnny7.clawOpen();//open claw so that we can move backward
        //sleep(rest);
        johnny7.moveBackwardInches(14, speedSlow); //back away from rungs
        sleep(rest);
        johnny7.moveLeftInches(32, speedSlow); //move right facing the samples
        sleep(rest);
        johnny7.moveForwardInches(48, speedSlow); //move forward past the samples
        sleep(rest);
        johnny7.moveLeftInches(11, speedSlow); //move into position to push samples
        sleep(rest);
        johnny7.moveBackwardInches(44, speedSlow); //push first sample into zone
        sleep(rest);
        johnny7.moveForwardInches(44, speedSlow);
        sleep(rest);
        johnny7.moveLeftInches(13, speedSlow);
        sleep(rest);
        johnny7.moveBackwardInches(44, speedSlow);
        sleep(rest);

    }
}
