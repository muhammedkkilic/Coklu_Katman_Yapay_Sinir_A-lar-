package com.mycompany.coklu_katman_yapay_sinir_aglari;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

        BufferedImage buffer;
        
        int x;
        int y;
        int sinif;
        
        public ImagePanel() {
            setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }

        public void koordinat_ciz() 
        {
            System.out.println(this.getWidth()+"         " + this.getHeight());
            buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D ig2 = buffer.createGraphics();

            ig2.setBackground(Color.WHITE);
            ig2.clearRect(0, 0, this.getWidth(), this.getHeight());
            Graphics g = buffer.getGraphics();

            this.setBackground(Color.white);
                
            
            int Height_ortasi = (this.getHeight() / 2) + 1;
            int Width_ortasi = (this.getWidth() / 2) + 1;
            
            //System.out.println(this.getHeight()+"    "+Height_ortasi);
            //System.out.println(this.getWidth()+"    "+Width_ortasi);
            
            
            g.setColor(Color.BLACK);
            g.drawLine(0, Height_ortasi, this.getWidth(), Height_ortasi);
            g.drawLine(Width_ortasi, 0, Width_ortasi, this.getHeight());
            
            
        }
        
        @Override
        protected void paintComponent(Graphics g) 
        {
            g.drawImage(buffer, 0, 0, this);
            this.revalidate();
            this.repaint();
        }
        public Color Renk_Belirle(int sinif)
        {
            Color renk = null;
            if(sinif == 0)
            {
                renk = Color.RED;
            }
            else if(sinif == 1)
            {
                renk = Color.BLUE;
            }
            else if(sinif == 2)
            {
                renk = Color.GREEN;
            }
            else if(sinif == 3)
            {
                renk = Color.PINK;
            }
            else if(sinif == 4)
            {
                renk = Color.ORANGE;
            }
            else if(sinif == 5)
            {
                renk = Color.yellow;
            }
            
            
            return renk;
        }
        public void arti_ciz(int secilen_sinif, int x,int y)
        {
            this.x = x;
            this.y = y;
            this.sinif = secilen_sinif;
            
            Graphics g = buffer.getGraphics();
                
            g.setColor(Renk_Belirle(this.sinif));
            int Width = this.getWidth();
	    int Height = this.getHeight();
	    int Height_yarim = Height / 2;
	    int Width_yarim = Width / 2;

	    int row, column, row_limit, column_limit;
	    row = (Height_yarim - y) - 5;
	    row_limit = (Height_yarim - y) + 5;
	    column = (x + Width_yarim) - 5;
	    column_limit = (x + Width_yarim) + 5;
                
            
            g.drawLine(x + (int)Width_yarim, row, x + (int)Width_yarim, row_limit);
            g.drawLine(column, Height_yarim - y, column_limit, Height_yarim - y);
        }
        public void aralikli_boya(int sinif, int x, int y)
        {
            this.x = x;
            this.y = y;
            this.sinif = sinif;

            Graphics g = buffer.getGraphics();
                
            g.setColor(Renk_Belirle(this.sinif));

            g.drawLine(x, y, x + 1, y + 1);
            
        }
    }
