/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.debugger.Log;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author alec
 */
public class LinearShooter implements Constants, IShooter {
    
    public BTMotor motShoot1;
    public BTMotor motShoot2;
    public BTMotor pitchMotor;
    public Piston shootPiston;
    public DigitalInput lowSensor;
    public DigitalInput highSensor;
    private ShooterInfo shootInfo;
    private BTMotor collectorMotor;
    boolean isVoltage = true;
    
    public LinearShooter(boolean isCan)
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, isCan, isVoltage);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, isCan, isVoltage);
        pitchMotor = new BTMotor(SHOOTER_PITCH_MOTOR_PORT, isCan, false);
        shootPiston = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
        lowSensor = new DigitalInput(SHOOTER_PITCH_LOW_PORT);
        highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        collectorMotor = new BTMotor(COLLECTOR_MOTOR_PORT, isCan, isVoltage);
    }
    public void update(ControlBoard cb)
    {
        shootInfo = cb.getShooter();
        
        setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
        shoot(shootInfo.canShoot);
        pitch(highSensor.get(), lowSensor.get(), shootInfo.pitchMotor);
        reload(shootInfo.reloadMotor);
        
        shootInfo.cycles--;
        cb.setShooter(shootInfo);
    }
    public void reload(double motorspeed)
    {
        collectorMotor.setX(motorspeed);
    }
    public void shoot(boolean canShoot)
    {
        if (canShoot) {            
           shootPiston.setPistonState(true);
        }
        else
        {
            shootPiston.setPistonState(false);
        }
    }
    
    public void killShot(){
        motShoot1.setX(0);
        motShoot2.setX(0);
    }
    
    public void setSpeed(boolean setOff, boolean setOn, double speed) {
        if (setOn) {
            motShoot1.setX(speed);
            motShoot2.setX(speed);
        }
        else if (setOff) {
            killShot();
        }
    }
    public void pitch(boolean limitHigh, boolean limitLow, double pitchSpeed)
    {
        if(limitHigh)
        {
            pitchMotor.setX(0);
        }
        else 
        {
            pitchMotor.setX(pitchSpeed);
        }
        if(limitLow)
        {
            pitchMotor.setX(0);
        }
        else 
        {
            pitchMotor.setX(pitchSpeed);
        }
    }
}
