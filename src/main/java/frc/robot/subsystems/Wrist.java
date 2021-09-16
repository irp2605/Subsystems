package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase {
    private SpeedController wristMotor;
    private AnalogPotentiometer wristPot;
    public Wrist(SpeedController wristMotor, AnalogPotentiometer wristPot)
    {
        this.wristMotor = wristMotor;
        this.wristPot = wristPot;
    }
    public void moveWrist(double speed)
    {
        if((speed > 0 && wristPot.get() >= Constants.WRIST_TOP_LIMIT) || (speed < 0 && wristPot.get() <= Constants.WRIST_BOTTOM_LIMIT))
            wristMotor.set(speed);
        else 
            wristMotor.set(0);
    }

    public void stopWrist(){
        wristMotor.stopMotor();
    }

    public AnalogPotentiometer getWristPot()
    {
        return wristPot;
    }

    public SpeedController getWristMotor() {
        return wristMotor;
    }

    
}