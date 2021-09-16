package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Hatch;

public class MoveHatch extends CommandBase
{

    private Hatch hatch;
    private boolean hatchBool;

    public MoveHatch(Hatch hatch, boolean hatchBool)
    {
        this.hatch = hatch;
        this.hatchBool = hatchBool;
    }

    @Override
    public void initialize()
    {
        hatch.setHatch(hatchBool);
    }

    @Override
    public void execute()
    {
        hatch.setHatch(hatchBool);
    }

    @Override
    public boolean isFinished()
    {
        return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.HATCH_BUTTON);
    }

    @Override
    public void end(boolean interrupted)
    {
        hatch.setHatch(!hatchBool);
    }
    

}