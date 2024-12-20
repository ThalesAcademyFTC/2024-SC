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
        double speed = 0.5;
        int sleep = 100;


        waitForStart();
        runtime.reset();
        johnny7.moveForwardInches(24, speed);
        sleep();
        //move arm up to high bar
        sleep();
        //place block with clip
        sleep();
        //move arm down
        sleep();
        johnny7.moveRightInches(12, speed);
        sleep();
        johnny7.moveForwardInches(12, speed);
        sleep();
        johnny7.turnRight(speed); //degrees?
        sleep();
        johnny7.moveLeftInches(4, speed); //move first block
        sleep();
        johnny7.moveForwardInches(20, speed);
        sleep();
        johnny7.moveBackwardInches(20, speed);
        sleep();
        johnny7.moveLeftInches(4, speed); //move second block
        sleep();
        johnny7.moveForwardInches(20, speed);
        sleep();
        johnny7.moveBackwardInches(20, speed);
        sleep();
        johnny7.moveLeftInches(4,speed); //move third block
        sleep();
        johnny7.moveForwardInches(20, speed);
        sleep();
        johnny7.moveBackwardInches(20, speed);
        sleep();
        johnny7.moveRightInches(10, speed); //robot parks
        sleep();
    }
    private void sleep() {
    }
}
