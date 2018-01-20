package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {

	PIDController pid;
	double distance;
	
    public AutoDriveDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	pid = new PIDController(Robot.drivekP, Robot.drivekI, Robot.drivekD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return Robot.io.getDistance();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				Robot.driveTrain.driveWithValue(output, -Robot.io.getGyro() * .2);
			}
		});
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.io.resetGyros();
    	pid.setOutputRange(-.6, .6);
    	Robot.io.resetEncoders();
    	pid.setAbsoluteTolerance(Robot.driveErrorMargin);
    	pid.setSetpoint(distance);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		System.out.println(pid.getError());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.driveTrain.driveWithValue(0.0, 0.0);
    	pid.disable();
    }
}
