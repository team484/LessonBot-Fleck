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
public class AutoTurn extends Command {
	private PIDController pid;
	private double setpoint = 0;

    public AutoTurn(double setpoint) {
    	pid = new PIDController(Robot.rotkP, Robot.rotkI, Robot.rotkD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return Robot.io.getGyro();
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
				Robot.driveTrain.driveWithValue(0.0, output);
				
			}
		});
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.io.bottom_gyro.reset();
    	Robot.io.top_gyro.reset();
    	pid.setOutputRange(-.5, .5);
    	pid.setSetpoint(setpoint);
    	pid.setAbsoluteTolerance(Robot.rotErrorMargin);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("foo" + pid.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.rotErrorMargin + setpoint > Robot.io.getGyro() && Robot.io.getGyro() > setpoint - Robot.rotErrorMargin;
    	return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
