package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous()
public class Johnny7AutonTest extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime=new ElapsedTime();

    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        johnny7.bucketPrimed();
        johnny7.clawClose();
        double speed=0.5;
        int rest=200;

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
        johnny7.waitForSlideMotors();
        sleep(rest);
        johnny7.bucketDump(); //dump specimen
        sleep(1000);
        johnny7.bucketPrimed();
        sleep(rest);
        johnny7.moveForwardInches(3,speed);
        sleep(rest);
        johnny7.slideLow();
        sleep(rest);

    }
}
