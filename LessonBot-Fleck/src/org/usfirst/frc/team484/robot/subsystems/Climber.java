package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.ClimberDoNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberDoNothing());
    }
    private void setClimbSpeed(double speed) {
    	Robot.io.leftClimber.set(speed);
    	Robot.io.rightClimber.set(speed);

    }
    
    public void climbWithJoystick() {
    	setClimbSpeed(Math.abs(Robot.io.operatorStick.getY()));
    }
    
    public void climbDoNothing() {
    	setClimbSpeed(0.0);
    }
    
}

