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
        runtime.reset();
        johnny7.resetClawMotor();
        johnny7.clawClose();
        johnny7.slideLow();
        double speed=0.5;
        int rest=100;

        waitForStart();
        runtime.reset();
        johnny7.readyToClip();
        sleep(rest);
        johnny7.waitForClawMotor();
        johnny7.actuallyClip();
        sleep(rest);
    }
}
