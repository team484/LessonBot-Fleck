package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotIO {

	public Talon leftClimber;
	public Talon rightClimber;
	public Talon leftMotor;
	public Talon rightMotor;
	
	
	public AnalogGyro bottom_gyro;
	public AnalogGyro top_gyro;
	public Joystick driveStick;
	public Joystick operatorStick;
	public Encoder leftEncoder;
	public Encoder rightEncoder;

	public DifferentialDrive drive;
	
	public RobotIO() {
		
		leftMotor = new Talon(RobotMap.leftWheel);
		rightMotor = new Talon(RobotMap.rightWheel);
		
		leftClimber = new Talon(RobotMap.leftClimber);
		rightClimber = new Talon(RobotMap.rightClimber);
		bottom_gyro = new AnalogGyro(RobotMap.bottonGyro);
		top_gyro = new AnalogGyro(RobotMap.topGyro);
		driveStick = new Joystick(RobotMap.driverJoystick);
		operatorStick = new Joystick(RobotMap.operatorJoystick);
		drive = new DifferentialDrive(leftMotor, rightMotor);
		leftEncoder = new Encoder(RobotMap.leftWheelEncA, RobotMap.leftWheelEncB);
		rightEncoder = new Encoder(RobotMap.rightWheelEncA, RobotMap.rightWheelEncB);

		
		//encoder.setDistancePerPulse();
	}
	
	public double getGyro() {
		return (top_gyro.getAngle() - bottom_gyro.getAngle()) / 2.0;
	}
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
	}
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public void resetGyros() {
		top_gyro.reset();
		bottom_gyro.reset();
	}
}
