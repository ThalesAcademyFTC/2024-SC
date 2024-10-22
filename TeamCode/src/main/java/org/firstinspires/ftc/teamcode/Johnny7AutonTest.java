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


        waitForStart();
        runtime.reset();
        johnny7.moveForwardInches(50,.5);
        johnny7.rest();
        johnny7.moveRightInches(100,.2);
        johnny7.rest();
        johnny7.moveBackwardInches(50, .3);
        johnny7.rest();
        johnny7.moveLeftInches(75,.3);
    }
}
