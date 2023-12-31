package frc.robot.subsystems.ArmSubsystem;

public class Joint extends SparkmaxMotor {

  // Define all of the motors
  //SparkmaxMotor jointMotor; //= new SparkmaxMotor(ArmConstants.AZIMUTH_MOTOR_ID, ArmConstants.AZIMUTH_LIMIT_SWITH_DIRECTION,0.3, 0.3);

  private double m_homingSpeed, m_degreesPerRev;
  public double m_thetaCurrentSetting;

  private int m_maxAngle,m_minAngle,m_homePositionAngle,m_startingAngle;
  
  //private int i=0;
  private boolean JointZeroed=false;

  public Joint(int deviceID,Boolean limitSwitchDirection, 
  double l_max_output, double l_min_output, 
  double l_homingSpeed, int l_startingAngle,
  int l_maxAngle, int l_minAngle,
  int l_homePositionAngle, double l_degreesPerRev,
  boolean l_invert){

    super(deviceID, limitSwitchDirection,l_max_output,l_min_output, l_invert);
    m_thetaCurrentSetting=l_startingAngle;
    m_startingAngle=l_startingAngle;
    m_homingSpeed = l_homingSpeed;
    m_maxAngle=l_maxAngle;
    m_minAngle=l_minAngle;
    m_degreesPerRev=l_degreesPerRev;
    m_homePositionAngle=l_homePositionAngle;
    if (deviceID==ArmConstants.CLAW_MOTOR_ID){
      m_motor.setSmartCurrentLimit( 10,  60,  2);
    }else{
      m_motor.setSmartCurrentLimit( 10,  5,  20000);
    }
  }

  public double getTrueJointAngle(){
    double l_trueJointAngle;
    if (JointZeroed){
      l_trueJointAngle=m_homePositionAngle+(m_degreesPerRev*m_encoder.getPosition());
    }else{//note: obviously not guaranteed if not zeroed, but it will hold that position
      l_trueJointAngle=m_startingAngle+(m_degreesPerRev*m_encoder.getPosition());
    }
    return l_trueJointAngle;
  }
  //shorter method constructor for default max/min angles
  private void RunJointToPosition(double l_thetaDesiredPos){
    RunJointToPosition(l_thetaDesiredPos,m_maxAngle,m_minAngle);
  }

  //longer constructor to allow changed max and min angles when other joints are limiting the range
  private void RunJointToPosition(double l_thetaDesiredPos,int l_maxAngle, int l_minAngle) {
    //checks for software limits with min and max angles
    double l_theta;
    if (JointZeroed){
      l_theta = Math.min(Math.max(l_minAngle, l_thetaDesiredPos),l_maxAngle);
    }else{
      l_theta = l_thetaDesiredPos;
    }
    //adjustments for offsets and conversions
    if (JointZeroed){
      runToposition((l_theta-m_homePositionAngle)/m_degreesPerRev);
    }else{
      runToposition((l_theta-m_startingAngle)/m_degreesPerRev);
    }
  }

  protected void homeJoint(){    
    if (!JointZeroed && !limitSwitch.isPressed()) {
      adjustMaxPID(-m_homingSpeed, m_homingSpeed);
      if (!(super.m_limitSwitchDirection^(m_degreesPerRev>0))) {//fancy way to use !XOR to correct for direction of limit switch and motor vs axis in one line
        //set velocity forward
        m_thetaCurrentSetting+=0.5;
        RunJointToPosition(m_thetaCurrentSetting);
        //m_pidControllerVel.setReference(ArmConstants.RESETTING_SPEED, CANSparkMax.ControlType.kVelocity);
      }else {
        //set velocity reverse
        m_thetaCurrentSetting-=0.5;
        RunJointToPosition(m_thetaCurrentSetting);
  
      }
    }else{
      if(limitSwitch.isPressed()){
        zeroEncoder(); 
        resetMaxPID();
        JointZeroed=true;
        
      }
    }
  }
  
  protected void RunJointToAngle(double angle) {
    m_thetaCurrentSetting =angle;    
    RunJointToPosition(m_thetaCurrentSetting);
  }
  protected void RunJointToAngle(double angle, int l_max, int l_min) {
    m_thetaCurrentSetting =angle;    
    RunJointToPosition(m_thetaCurrentSetting,l_max,l_min);
  }

  protected boolean homedCondition() {
    // Query some boolean state, such as a digital sensor.    
    return JointZeroed;
  }

  protected void unhome() {
    // Query some boolean state, such as a digital sensor.    
     JointZeroed=false;
  }

  protected void forceHomed() {
    // Query some boolean state, such as a digital sensor.    
     JointZeroed=true;
  }
}