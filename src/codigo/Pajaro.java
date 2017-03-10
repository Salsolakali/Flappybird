/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

/**
 *
 * @author jorgecisneros
 */
public class Pajaro extends Ellipse2D.Double{
    Color colorPajaro;
    int yVelocidad = -2;
    Image pajaro1, pajaro2;
    
    public Pajaro(int _radio, Color _color){
        super(150, 150, _radio*2, _radio*1.5);
        colorPajaro = _color;
        precargaPajaro();
    }
    
    public void mueve(Graphics2D g2, Integer c){
        this.y = this.y - yVelocidad;
        //pongo un tope para que no se salga por el techo
        if (this.y < 0) {
            this.y = 0;
            yVelocidad = -2;
        }
//        g2.setColor(colorPajaro);
//        g2.fill(this);

        //Si la velocidad es positiva, aleteo (cambio entre imagenes)
        if(yVelocidad>0){
            if(c%10<5){
                g2.drawImage(pajaro1, (int)this.getX(), (int)this.getY(),null);
            }
            else{
                g2.drawImage(pajaro2, (int)this.getX(), (int)this.getY(),null);
                
            }
        }
        //si no, planeo
        else{
                g2.drawImage(pajaro2, (int)this.getX(), (int)this.getY(),null);
        }
        yVelocidad -= 2;
        if (yVelocidad < -3){
            yVelocidad = -2;
        }
    }
    
    public void precargaPajaro(){
       
       pajaro1 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fly2.png")).getImage().getScaledInstance(66, 46, Image.SCALE_DEFAULT))).getImage();
        
       pajaro2 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fly1.png")).getImage().getScaledInstance(66, 46, Image.SCALE_DEFAULT))).getImage();
        
    }
    
    public boolean chequeaColision(Columna c){
        Area areaPajaro = new Area(this);
        Area areaPajaro1 = new Area(this);
        Area areaPajaro2 = new Area(this);
        Area areaCirculoI = new Area(c.circuloInferior);
        Area areaCirculoS = new Area (c.circuloSuperior);
        boolean choca1 = true;
        boolean choca2 = true;
        areaPajaro1.intersect(areaCirculoI);
        areaPajaro2.intersect(areaCirculoS);
       
        if (areaPajaro1.isEmpty()){
            choca1 = false;
        }
        if (areaPajaro2.isEmpty()){
            choca2 = false;
        }
        
        return (areaPajaro.intersects(c.capitel) || 
                areaPajaro.intersects(c.base) ||
                choca1 || choca2
                );
    }
    

}
