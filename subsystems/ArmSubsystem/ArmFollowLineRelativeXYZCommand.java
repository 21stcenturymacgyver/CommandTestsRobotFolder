// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.ArmSubsystem; //it is in this package so it can control the arm 
//without lowering the restrictions on the private and protected classes within the ArmSubsystem 
//If it is not in the same package, then it leaves the Subsystem Vulnerable to access outside 
//the command structure

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ArmFollowLineRelativeXYZCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private ArmSubsystem m_armSubsystem;
  private final double  m_dX,m_dY,m_dZ, m_speedIPS;
  private double m_x2,m_y2, m_z2, m_x1,m_y1,  m_z1;
  private int numOfCycles,m_cycleNumber;
  
  
  public ArmFollowLineRelativeXYZCommand(ArmSubsystem subsystem, double l_deltaX, double l_deltaY, double l_deltaZ, double speedIPS) {
    m_armSubsystem = subsystem;
    m_dX=l_deltaX;
    m_dY=l_deltaY;
    m_dZ=l_deltaZ;
    m_speedIPS=speedIPS;
    
    addRequirements(m_armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double[] l_XYZStart=m_armSubsystem.getArmPositionXYZ();
    m_x1=l_XYZStart[0];
    m_y1=l_XYZStart[1];//this all must be in initialize() because if in the constructor it happens on robot init
    m_z1=l_XYZStart[2];

    //determine new final positions
    m_x2=m_x1+m_dX;
    m_y2=m_y1+m_dY;
    m_z2=m_z1+m_dZ;
    //calculate number of cycles given speed
    numOfCycles= Math.max((int)(Math.hypot(Math.hypot(m_x2-m_x1,m_y2-m_y1), m_z2-m_z1)/m_speedIPS*50),1);//set minimum cycles to 1 since the execute runs before it checks if it is finished
    //it goes wild if it tries do 0*1/0 (zero times infinity!), which would happen if point 1 is where point 2 is;
    m_cycleNumber=1;//not zero because we do want it to move on the first execution
    System.out.println("linear XYZ Relative Interpolating");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //this version of java seems to have a hard time if I use a double array to return both the x and y values from any method.
    double[] l_xyzTarget={interpolate(m_x1, m_x2,m_cycleNumber, numOfCycles),
      interpolate(m_y1, m_y2,m_cycleNumber, numOfCycles),
      interpolate( m_z1, m_z2,m_cycleNumber, numOfCycles)};

    m_armSubsystem.RunJointsToXYZ( l_xyzTarget[0], l_xyzTarget[1], l_xyzTarget[2]);
    
    if (m_cycleNumber==1){
      System.out.print("xyz target start: "+l_xyzTarget[0]+"; ");
      System.out.print(l_xyzTarget[1]+"; ");
      System.out.println(l_xyzTarget[2]+"; ");

    }else if(m_cycleNumber==numOfCycles){
      System.out.print("xyz target end: "+l_xyzTarget[0]+"; ");
      System.out.print(l_xyzTarget[1]+"; ");
      System.out.println(l_xyzTarget[2]+"; ");

    }
    m_cycleNumber++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("done XYZ interpolating");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_cycleNumber>numOfCycles);
  }
  private static double interpolate(double x1, double x2,  int step, int totalSteps){
    double output=x1 + (x2 - x1) * step/totalSteps;
    return output;
  }
  
}
