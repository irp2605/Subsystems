package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hatch extends SubsystemBase
{
    private Solenoid sol;
    public Hatch(Solenoid sol)
    {
        this.sol = sol;
    }

    public void setHatch(boolean solBool)
    {
        sol.set(solBool);
    }

    public Solenoid getSolenoid(){
        return sol;
    } 
}