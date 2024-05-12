package frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class HomeAllCommand extends Command {

    private final boolean m_DEBUG;

    public HomeAllCommand(boolean debug) {
        m_DEBUG = debug;

        addRequirements(new ArmSubsystem());
    }

    @Override public void initialize() {
        if (m_DEBUG) System.out.println("Initialized homing");
    }

    @Override public void execute() {
        if (m_DEBUG) System.out.println("Executing homing");

        if (!ArmSubsystem.shoulderIsHomed()) {
            ArmSubsystem.homeShoulder();
        } else if (!ArmSubsystem.elbowIsHomed()) {
            ArmSubsystem.homeElbow();
        }
    }

    @Override public void end(boolean interrupted) {
        if (!m_DEBUG) return;

        if (interrupted) {
            System.out.println("Homing interrupted.");
        } else {
            System.out.println("Homing finished.");
        }
    } 

    @Override public boolean isFinished() {
        return ArmSubsystem.shoulderAndElbowHomedCondition();
    }
    
}
