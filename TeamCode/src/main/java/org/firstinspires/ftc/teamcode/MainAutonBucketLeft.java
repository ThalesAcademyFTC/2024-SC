package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
public class MainAutonBucketLeft extends LinearOpMode {
    private Johnny6 johnny7;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.Drivetrain.JOHNNY6);
        runtime.reset();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed = 0.5;
        int rest = 100;
        waitForStart();// start with claw facing the bucket so that it is easier for bucketing
        johnny7.moveForwardInches(48, speed);
        sleep(rest);



    }

}
