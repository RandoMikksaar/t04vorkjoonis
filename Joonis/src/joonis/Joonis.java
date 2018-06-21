package joonis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
        
public class Joonis {
    public static void main (String[] arg) throws Exception {
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
        
        String aadress = "http://www.tlu.ee/~jaagup/veeb1/loomad.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(aadress).openStream()));
        
        String rida = br.readLine();
        rida = br.readLine();
            
        int MassOfCats = 0;
        int HeightOfCats = 0;
        int CountOfCats = 0;
        int MassOfDogs = 0;
        int HeightOfDogs = 0;
        int CountOfDogs = 0;
        int MassOfCatsAverage = 0;
        int MassOfDogsAverage = 0;
        
        while (rida != null) {           
            //loendab kasside massid ja kõrgused
            String[] m = rida.split(",");
            if (m[0].equals("kass")) {
                MassOfCats += Integer.parseInt(m[1]);
                HeightOfCats += Integer.parseInt(m[2]);
                CountOfCats++;
                g.setColor(Color.cyan);
                g.fillOval(200, 60, 10, 10);
                g.drawString("kasside kõrguste ja masside suhe", 215, 70);
            } 
            
            //loendab koerte massid ja kõrgused
            else if (m[0].equals("koer")) {
                MassOfDogs += Integer.parseInt(m[1]);
                HeightOfDogs += Integer.parseInt(m[2]);
                CountOfDogs++;
                g.setColor(Color.pink);
                g.fillOval(200, 80, 10, 10);
                g.drawString("koerte kõrguste ja masside suhe", 215, 90);
            }
            
            //arvutab kassiga kõrguste ja masside suhte
            g.fillOval(Integer.parseInt(m[1])/30+30,400-Integer.parseInt(m[2])*2-30,8,8);
            rida = br.readLine();
        }
        br.close();
        
        //cats average mass
        g.setColor(Color.blue);
        g.fillOval((MassOfCats/CountOfCats)/30+30,400-(HeightOfCats/CountOfCats)*2-30,10,10);
        g.fillOval(50, 60, 10, 10);
        g.drawString("Kasside mass", 65, 69);
        
        //dogs average mass
        g.setColor(Color.red);
        g.fillOval((MassOfDogs/CountOfDogs)/30 + 30, 400-(HeightOfDogs/CountOfDogs)*2-30,10,10);
        g.fillOval(50, 80, 10, 10);
        g.drawString("Koerte mass", 65, 89);
        
        
        //x ja y teljed
        g.setColor(Color.black);
        g.drawLine(50, 380, 460, 380);
        g.drawLine(50, 80, 50, 380);
        g.drawString("Kõrgus", 1, 250);
        g.drawString("Mass", 250, 400);
        
        ImageIO.write(image, "png", new File("joonis.png"));
    }
}