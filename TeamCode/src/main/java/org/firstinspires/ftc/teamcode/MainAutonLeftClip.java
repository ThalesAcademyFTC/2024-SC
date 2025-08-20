package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class MainAutonLeftClip extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();
        //johnny7.initClip();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed = 0.5;
        int rest = 100;


        waitForStart();
        runtime.reset();
        johnny7.moveLeftInches(5, speed); //move away from field wall
        sleep(rest);
        johnny7.turnRightDegrees(90, speed); //turn to face the rungs
        sleep(rest);
        johnny7.readyToClip();//ready to get the specimen in the right position for clipping
        sleep(rest);
        johnny7.moveForwardInches(18, speed); //move forward towards submersible zone
        sleep(rest);
        //johnny7.waitForClawMotor();
        //sleep(rest);
        johnny7.actuallyClip();//arm raises upwards
        sleep(rest);
        johnny7.moveBackwardInches(5, speed);//move back to score specimen
        sleep(rest);
        //johnny7.clawOpen(); //open claw so that we can move backward
        //sleep(rest);
        johnny7.moveBackwardInches(14, speed); //back away from rungs
        sleep(rest);
        johnny7.moveLeftInches(26, speed);
        sleep(rest);


    }
}
