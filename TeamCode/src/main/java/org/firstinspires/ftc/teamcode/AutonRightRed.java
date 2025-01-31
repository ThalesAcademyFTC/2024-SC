package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous()
public class AutonRightRed extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime=new ElapsedTime();



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
        runtime.reset();
        johnny7.moveForwardInches(24, speed); //move forward one square
        sleep(rest);
        johnny7.moveLeftInches(30, speed); //move left to arrive at the buckets
        sleep(rest);
        johnny7.turnRightDegrees(180, speed); //turn to face the buckets
        sleep(rest);
        johnny7.moveForwardInches(5, speed); //get closer to the buckets
        sleep(rest);
        johnny7.slideHigh(); //raise arm up to the high bucket
        sleep(rest);
        johnny7.bucketDump(); //dump specimen
        sleep(rest);
        johnny7.slideLow(); //lower slide
        sleep(rest);
        johnny7.moveBackwardInches(5, speed); //move away from buckets
        sleep(rest);
        johnny7.moveLeftInches(45, speed); //move to the other side of the field
        sleep(rest);
        johnny7.moveBackwardInches(12, speed); //move backwards
        sleep(rest);
        johnny7.moveLeftInches(5, speed); //move to face the first block
        sleep(rest);
        johnny7.moveForwardInches(10, speed); //push the first block into the zone
        sleep(rest);
        johnny7.moveBackwardInches(10, speed);
        sleep(rest);
        johnny7.moveLeftInches(5, speed);
        sleep(rest);
        johnny7.moveForwardInches(10, speed); //push the second block into the zone
        sleep(rest);
        johnny7.moveBackwardInches(10, speed);
        sleep(rest);
        johnny7.moveLeftInches(5, speed);
        sleep(rest);
        johnny7.moveForwardInches(10, speed); //push the third block into the zone
        sleep(rest);
        johnny7.moveBackwardInches(10, speed);
        sleep(rest);
        johnny7.moveRightInches(18, speed); //parks in the submersible zone
        sleep(rest);


    }
    private void sleep() {
    }
}
