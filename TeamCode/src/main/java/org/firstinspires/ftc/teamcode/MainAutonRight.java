package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class MainAutonRight extends LinearOpMode {
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
        runtime.reset();// start on right side with preloaded clipped sample
        johnny7.readyToClip();//ready to get the specimen in the right position for clipping
        sleep(rest);
        johnny7.waitForClawMotor();
        sleep(rest);
        johnny7.moveForwardInches(24,speed); //move forward towards submersible zone
        sleep(rest);
        johnny7.actuallyClip();// actually clip onto the high bar because of methane
        sleep(rest);
        johnny7.clawOpen();// open claw so that we can move backward
        sleep(rest);
        johnny7.moveBackwardInches(10,speed);// move back a little bit so that we can move left
        sleep(rest);
        johnny7.moveLeftInches(24,speed);//actually move left so that we can get closer to the 3 alliance specific sample
        sleep(rest);
        johnny7.moveForwardInches(34,speed);// get closer to the samples
        sleep(rest);
        johnny7.moveLeftInches(10,speed); // move a little bit to the left so that we can be in the optimal position for moving one into the human player zone
        sleep(rest);
        johnny7.moveForwardInches(48,speed);// move the sample
        sleep(rest);
        johnny7.moveBackwardInches(48,speed);//move back so that move next sample
        sleep(rest);
        johnny7.moveLeftInches(10,speed);// move a bit to the left so that we can move the next sample
        sleep(rest);
        johnny7.moveForwardInches(48,speed);// move the sample
        sleep(rest);
        johnny7.moveBackwardInches(48,speed);//move back so that move next sample
        sleep(rest);
        johnny7.moveLeftInches(10,speed);// move a little bit to the left so that we can be in the optimal position for moving one into the human player zone
        sleep(rest);
        johnny7.moveForwardInches(48,speed);// move the sample then park there
        sleep(rest);


    }
}