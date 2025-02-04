package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutonBucketFarParkOnly extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();
        johnny7.bucketInit();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed = 0.5;
        int rest = 100;


        waitForStart();
        runtime.reset(); //bucket faces forward
        johnny7.moveBackwardInches(72, speed); //move back to submersible
        sleep(rest);
        johnny7.moveLeftInches(5, speed); //move in front of first sample
        sleep(rest);
        johnny7.moveForwardInches(36, speed); //push first sample
        sleep(rest);
        johnny7.moveBackwardInches(36, speed);
        sleep(rest);
        johnny7.moveLeftInches(5, speed); //move in front of second sample
        sleep(rest);
        johnny7.moveForwardInches(36, speed); //push second sample
        sleep(rest);
        johnny7.moveBackwardInches(36, speed);
        sleep(rest);
        johnny7.moveLeftInches(5, speed); //move in front of third sample
        sleep(rest);
        johnny7.moveForwardInches(36, speed); //push third sample
        sleep(rest);
        johnny7.moveBackwardInches(12, speed);
        sleep(rest);
    }
}
