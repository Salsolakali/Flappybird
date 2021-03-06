/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author xp
 */
public class Columna {
    Ellipse2D circuloInferior, circuloSuperior;
    Rectangle2D capitel, base;
    int hueco = 150;
    int altura_columna = 400;
    int ancho_columna = 79;
    private int ancho_pantalla;
    Image col_abajo, col_arriba;
        
    public Columna (int _ancho, int _anchoPantalla){
        Random aleatorio = new Random();
        int desplazamiento = aleatorio.nextInt(200);
        ancho_pantalla = _anchoPantalla;
        
        capitel = new Rectangle2D.Double(_ancho, -desplazamiento, ancho_columna, altura_columna);
        
        base = new Rectangle2D.Double(_ancho,altura_columna + hueco - desplazamiento + ancho_columna/2,ancho_columna,altura_columna);
        
        circuloInferior = new Ellipse2D.Double(_ancho,altura_columna + hueco - desplazamiento,ancho_columna, ancho_columna);
        
        circuloSuperior = new Ellipse2D.Double(_ancho,altura_columna - desplazamiento - ancho_columna/2,ancho_columna, ancho_columna);
        precargaImagen();
    }
    
    private void precargaImagen(){
         
        col_abajo = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/pipe_bottom.png")).
               getImage().getScaledInstance(79, 400, Image.SCALE_DEFAULT))).getImage();
        
        col_arriba = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/pipe_top.png")).
               getImage().getScaledInstance(79, 400, Image.SCALE_DEFAULT))).getImage();
    }
    
    public void mueve(Graphics2D g2){
        mueveColumna();
        
        
        //mueveColumna(base);

//        g2.setColor(Color.green);
        
        
        g2.drawImage(col_abajo, (int)base.getX(), (int)base.getY()-ancho_columna/2, null);
        g2.drawImage(col_arriba, (int)capitel.getX(), (int)capitel.getY()+ancho_columna/2, null);
        
//        g2.fill(circuloInferior);
//        g2.fill(circuloSuperior);
//        g2.fill(capitel);
//        
//        g2.fill(base);
        

    }
    
    private void mueveColumna(){
        if (capitel.getX() + ancho_columna < 0){
            Random aleatorio = new Random();
            int desplazamiento = aleatorio.nextInt(200);
            capitel.setFrame(ancho_pantalla, 
                            -desplazamiento,
                            capitel.getWidth(), 
                            capitel.getHeight());
            base.setFrame(ancho_pantalla, 
                            altura_columna + hueco - desplazamiento + ancho_columna/2,
                            base.getWidth(), 
                            base.getHeight());
            circuloInferior.setFrame(ancho_pantalla, 
                                        altura_columna + hueco - desplazamiento,
                                        ancho_columna,
                                        ancho_columna
                                            );
            circuloSuperior.setFrame(ancho_pantalla,
                                     altura_columna -desplazamiento - ancho_columna/2,
                                     ancho_columna,
                                     ancho_columna);
        }
        else{
            capitel.setFrame(capitel.getX()-1, capitel.getY(),capitel.getWidth(), capitel.getHeight());
            base.setFrame(base.getX()-1, base.getY(),base.getWidth(), base.getHeight());
            circuloInferior.setFrame(circuloInferior.getX()-1, circuloInferior.getY(),circuloInferior.getWidth(), circuloInferior.getHeight());
            circuloSuperior.setFrame(circuloSuperior.getX()-1, circuloSuperior.getY(), circuloSuperior.getWidth(), circuloSuperior.getHeight());
        }
    }
    
//    public int contadorColumnas(Ellipse2D p, int c){
//        if(capitel.getX() + ancho_columna <150){
//            c++;
//        }
//        if(base.getX() + ancho_columna <150){
//            c++;
//        }
//        return c;
//    }
}
