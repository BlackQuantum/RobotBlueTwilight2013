/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Dlock
 */
import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends BTController {

    Joystick xboxController;
    public XboxController()
    {
        xboxController = new Joystick(1);
    }
    public boolean getShiftButton() {
        return xboxController.getRawButton(6);
    }

    public double getLMotorSpeed() {
        return xboxController.getRawAxis(2);
    }

    public double getRMotorSpeed() {
        return xboxController.getRawAxis(5);
    }
    
    
}