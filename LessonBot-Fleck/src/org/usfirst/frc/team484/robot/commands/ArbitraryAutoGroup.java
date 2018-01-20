package org.usfirst.frc.team484.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArbitraryAutoGroup extends CommandGroup {

    public ArbitraryAutoGroup() {
    	addSequential(new AutoDriveDistance(120), 10);
    	addSequential(new AutoTurn(180), 8);
    	addSequential(new AutoDriveDistance(120));
    	
    }
}
