package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class MoveArm extends CommandBase
{
    public double speed;
    public MoveArm(double speed)
    {
        this.speed = speed;
    }
    @Override
    public void initialize()
    {
        if(Robot.m_robotContainer.getArm().getArmPot().get()<Constants.ARM_TOP_LIMIT && Robot.m_robotContainer.getArm().getArmPot().get()>Constants.ARM_BOTTOM_LIMIT)
         Robot.m_robotContainer.getArm().moveArm(speed);
        else
            Robot.m_robotContainer.getArm().stopArm();
    }

    @Override
    public void execute()
    {
        if(Robot.m_robotContainer.getArm().getArmPot().get()<Constants.ARM_TOP_LIMIT && Robot.m_robotContainer.getArm().getArmPot().get()>Constants.ARM_BOTTOM_LIMIT)
         Robot.m_robotContainer.getArm().moveArm(speed);
        else
            Robot.m_robotContainer.getArm().stopArm();
    }

    @Override
    public boolean isFinished()
    {
        if(speed>0)
         return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.ARM_UP_BUTTON);
        else if(speed<0) 
            return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.ARM_DOWN_BUTTON);
        else
            return false;
    }

    @Override
    public void end(boolean interrupted)
    {
		Robot.m_robotContainer.getArm().stopArm ();
    }
}