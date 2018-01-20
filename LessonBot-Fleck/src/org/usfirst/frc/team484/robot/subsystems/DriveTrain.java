package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
    public void driveWithJoystick() {
    	Robot.io.drive.arcadeDrive(-Robot.io.driveStick.getY(), Robot.io.driveStick.getX(), true);
    }
    
   
    public void driveWithValue(double move, double rot) {
    	Robot.io.drive.arcadeDrive(move, rot);
    }
}

