package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class MoveWrist extends CommandBase
{
    public double speed;
    public MoveWrist(double speed)
    {
        this.speed = speed;
    }
    @Override
    public void initialize()
    {
        if(Robot.m_robotContainer.getWrist().getWristPot().get()<Constants.WRIST_TOP_LIMIT && Robot.m_robotContainer.getWrist().getWristPot().get()>Constants.WRIST_BOTTOM_LIMIT)
         Robot.m_robotContainer.getWrist().moveWrist(speed);
        else
            Robot.m_robotContainer.getWrist().stopWrist();
    }

    @Override
    public void execute()
    {
        if(Robot.m_robotContainer.getWrist().getWristPot().get()<Constants.WRIST_TOP_LIMIT && Robot.m_robotContainer.getWrist().getWristPot().get()>Constants.WRIST_BOTTOM_LIMIT)
         Robot.m_robotContainer.getWrist().moveWrist(speed);
        else
            Robot.m_robotContainer.getWrist().stopWrist();
    }

    @Override
    public boolean isFinished()
    {
        if(speed>0)
         return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.WRIST_UP_BUTTON);
        else if(speed<0) 
            return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.WRIST_DOWN_BUTTON);
        else
            return false;
    }

    @Override
    public void end(boolean interrupted)
    {
		Robot.m_robotContainer.getWrist().stopWrist ();
    }
}