/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  //private static int cam = 0;
  private final SpeedControllerGroup m_leftMotor = new SpeedControllerGroup(new Spark(0), new Spark(1));
  private final SpeedControllerGroup m_rightMotor = new SpeedControllerGroup(new Spark(2), new Spark(3));
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController m_stick = new XboxController(0);
  //private final CameraServer server;

  UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
  UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(1);
  
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  


  
  @Override
  public void teleopPeriodic() { 
     Color detectedColor = m_colorSensor.getColor();

    double IR = m_colorSensor.getIR();
  
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.curvatureDrive(m_stick.getY(Hand.kLeft), m_stick.getX(Hand.kRight), false);
    //server = CameraServer.getInstance();
    //server.startAutomaticCapture();
    //CameraServer.getInstance().startAutomaticCapture(cam);
  }



  public static int getCamera() {
    // return cam;
    return 0;
  }
}
