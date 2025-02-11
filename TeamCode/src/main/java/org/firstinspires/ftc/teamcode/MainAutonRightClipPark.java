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
        double speed = 0.5;
        int rest = 100;


        waitForStart();
        runtime.reset();
        johnny7.moveLeftInches(5, speed);
        sleep(rest);
        johnny7.moveBackwardInches(9,speed); //move forward towards submersible zone
        sleep(rest);
        johnny7.readyToClip();//ready to get the specimen in the right position for clipping
        sleep(rest);
        //johnny7.waitForClawMotor();
        //sleep(rest);
        johnny7.turnRightDegrees(180,speed);
        sleep(rest);
        johnny7.moveForwardInches(14,speed);
        sleep(rest);
        johnny7.actuallyClip();// actually clip onto the high bar because of methane
        sleep(rest);
        johnny7.moveBackwardInches(7,speed);// move back a little bit so that we can move left
        sleep(50);
        johnny7.clawOpen();// open claw so that we can move backward
        sleep(rest);
        johnny7.turnRightDegrees(5,speed);
        johnny7.moveBackwardInches(17,speed);
        sleep(rest);
        johnny7.moveLeftInches(48,speed);
        sleep(rest);
    }
}
