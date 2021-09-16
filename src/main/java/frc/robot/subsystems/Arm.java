package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private SpeedController armMotor;
    private AnalogPotentiometer armPot;
    public Arm(SpeedController armMotor, AnalogPotentiometer armPot)
    {
        this.armMotor = armMotor;
        this.armPot = armPot;
    }
    public void moveArm(double speed)
    {
        if((speed > 0 && armPot.get() >= Constants.ARM_TOP_LIMIT) || (speed < 0 && armPot.get() <= Constants.ARM_BOTTOM_LIMIT))
            armMotor.set(speed);
        else 
            armMotor.set(0);
    }

    public void stopArm(){
        armMotor.stopMotor();
    }

    public AnalogPotentiometer getArmPot()
    {
        return armPot;
    }

    public SpeedController getArmMotor() {
        return armMotor;
    }
    
}