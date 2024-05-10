// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.Constants.ArmMoveConstants;
import frc.robot.subsystems.ArmSubsystem.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class MultiLine {
  /** Example static factory for an autonomous command. */
  public static Command MultiLineToShelf(ArmSubsystem m_ArmSubsystem) {
    return Commands.sequence(new ArmFollowLineCommand(m_ArmSubsystem, 15, 35, 35), 
    new ArmFollowLineCommand(m_ArmSubsystem, ArmMoveConstants.SHELF_POSITION_RZ[0], ArmMoveConstants.SHELF_POSITION_RZ[1], 15));
    
  }
  public static Command MultiLineToMedium(ArmSubsystem m_ArmSubsystem) {
    return Commands.sequence(new ArmFollowLineCommand(m_ArmSubsystem, 20, 35, 35), 
    new ArmFollowLineCommand(m_ArmSubsystem, ArmMoveConstants.MEDIUM_SCORING_POSITION_RZ[0], ArmMoveConstants.MEDIUM_SCORING_POSITION_RZ[1], 15));
    
  }
  public static Command MultiLineToHigh(ArmSubsystem m_ArmSubsystem) {
    return Commands.sequence(new ArmFollowLineCommand(m_ArmSubsystem, 30, 46, 35), 
    new ArmFollowLineCommand(m_ArmSubsystem, ArmMoveConstants.HIGH_SCORING_POSITION_RZ[0], ArmMoveConstants.HIGH_SCORING_POSITION_RZ[1], 15));
    
  }

  public static Command MultiLineDiamondTest(ArmSubsystem m_ArmSubsystem) {
    return Commands.sequence(new ArmFollowLineCommand(m_ArmSubsystem, 30, 4, 15), 
    new ArmFollowLineCommand(m_ArmSubsystem, 30, 30, 25),
    
    new ArmFollowLineCommand(m_ArmSubsystem, 30, 40, 10),
    new ArmFollowLineCommand(m_ArmSubsystem, 30, 60, 35));
  }

  public static Command MultiLineHHTest(ArmSubsystem m_ArmSubsystem) {
    return Commands.sequence(
       
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 0,8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 3, 0,-8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 3, 0,8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 0,-8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 2, 0,0, 5),

      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 2, 0,8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 2, 0,-8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, -1, 0,4, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, -2, 0,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 5, 0,-4, 5),

      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 4, 0,8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, -4, 0,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 2,0, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 4, 0,-8, 5),
      new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -8,0, 5)); 
      
  }
  // public static Command MultiLineHHTest(ArmSubsystem m_ArmSubsystem) {
  //   return Commands.sequence(
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 2,0, 1), 
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 0,8, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -2,0, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 4, 0,0, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 2,0, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 0,-8, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, 0,4, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, -4, 0,0, 5),
  //     new ArmFollowLineRelativeXYZCommand(m_ArmSubsystem, 0, -2,0, 5)); 
      
  // }
  // private MultiLine() {
  //   throw new UnsupportedOperationException("This is a utility class!");
  // }
}
