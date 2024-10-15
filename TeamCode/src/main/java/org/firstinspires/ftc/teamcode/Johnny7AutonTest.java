package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;


Autonomous()
public class Johnny7AutonTest {
    private Johnny6 johnny7;

    private ElapsedTime runtime=new ElapsedTime();

    @Override
    public void runOpMode() {
        johnny7 = new Johnny6(this, Johnny6.DriveTrain.JOHNNY6);

        waitForStart();

        runtime.reset();
        
        johnny7.moveForwardInches(10,.5);
    }
}
