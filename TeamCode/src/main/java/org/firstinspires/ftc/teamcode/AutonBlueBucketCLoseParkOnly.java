package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutonBlueBucketCLoseParkOnly extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();
        johnny7.bucketPrimed();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed = 0.5;
        int rest = 100;


        waitForStart();
        runtime.reset(); //start with slide bucket facing game buckets
        johnny7.moveBackwardInches(3, speed); //move closer to the buckets
        sleep(rest);
        johnny7.slideHigh(); //raise slide to high bucket
        sleep(rest);
        johnny7.waitForSlideMotors();
        sleep(rest);
        johnny7.bucketDump(); //dump sample into high bucket
        sleep(1000);
        johnny7.bucketPrimed(); //reset bucket
        sleep(rest);
        johnny7.moveForwardInches(3, speed); //move backwards away from buckets
        sleep(rest);
        johnny7.slideLow(); //reset slide
        sleep(rest);
        johnny7.moveLeftInches(24, speed); //move close to the submersible
        sleep(rest);
        johnny7.turnRightDegrees(90, speed); //turn to face samples
        sleep(rest);
        johnny7.moveLeftInches(3, speed); //park
        sleep(rest);
    }
}
