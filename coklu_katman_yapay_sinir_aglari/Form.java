package com.mycompany.coklu_katman_yapay_sinir_aglari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Form extends JFrame {
    
    public JButton button;
    public JButton sifirla_butonu;
    public JLabel X_label;
    public JLabel X_Text;
    public JLabel Y_label;
    public JLabel Y_Text;
    public JLabel Z_label;
    public JLabel Z_Text;
    public JLabel Ogrenme_Katsayisi_label;
    public JTextField Ogrenme_Katsayisi_Text;
    public JLabel Error_Esik_label;
    public JTextField Error_Esik_Text;
    public JLabel Aktivasyon_Fonksiyonlari_label;
        
    public JComboBox Aktivasyon_Fonksiyonlari_Combobox;
    public JLabel Sinif_Sayisi_label;
    public JComboBox Sinif_Sayisi_Combobox;
    public JComboBox Ara_Katman_Noron_Sayisi_Combobox;
    public JLabel Ara_Katman_Noron_Sayisi_label;
    public JButton Ayarlari_Setle_Button;
    
    public JLabel Orneklerin_Sinifi_label;
    public JComboBox Orneklerin_Sinifi_Combobox;

    public JLabel Saykıl_Sayisi_label;
    public static JLabel Saykıl_Sayisi_text;
    //public static JTextField Saykıl_Sayisi_text;
    
    public JLabel Error_label;
    public static JLabel Error_text;
    //public static JTextField Error_text;
    
    public ImagePanel imagePanel;

    int sinif_sayisi;
    int ara_katman_noron_sayisi;
              
    public Form() {
        super("ÇOK KATMANLI FULLY CONNECTED NEURAL NETWORK");

        setSize(1366, 768);
        setLayout(null);

        button = new JButton("Eğitim ve Test Yap");
        button.setBounds(880, 350, 180, 40);
        add(button);
        
        sifirla_butonu = new JButton("Sıfırla");
        sifirla_butonu.setBounds(880, 510, 180, 40);
        add(sifirla_butonu);
        
        imagePanel = new ImagePanel();
        
        imagePanel.setBounds(5, 55, 701, 501);
        
        imagePanel.koordinat_ciz();
        add(imagePanel);
        imagePanel.revalidate();
        imagePanel.repaint();
        
        Ogrenme_Katsayisi_label = new JLabel("Öğrenme Katsayısı : ");
        Ogrenme_Katsayisi_label.setBounds(880, 240, 150, 40);
        add(Ogrenme_Katsayisi_label);
        
        Ogrenme_Katsayisi_Text = new JTextField("0.7");
        Ogrenme_Katsayisi_Text.setBounds(1030, 245, 70, 30);
        add(Ogrenme_Katsayisi_Text);
        
        Error_Esik_label = new JLabel("Error Eşik : ");
        Error_Esik_label.setBounds(880, 280, 150, 40);
        add(Error_Esik_label);
        
        Error_Esik_Text = new JTextField("0.001");
        Error_Esik_Text.setBounds(1030, 285, 70, 30);
        add(Error_Esik_Text);
        
        Aktivasyon_Fonksiyonlari_label = new JLabel("Katmanların Aktivasyon Fonksiyonu : ");
        Aktivasyon_Fonksiyonlari_label.setBounds(730, 10, 290, 40);
        add(Aktivasyon_Fonksiyonlari_label);
        
        Aktivasyon_Fonksiyonlari_Combobox = new JComboBox();
        Aktivasyon_Fonksiyonlari_Combobox.setBounds(1020, 10, 120, 40);
        String[] aktivasyon_fonksiyonlari_dizi = new String[4];
        
        aktivasyon_fonksiyonlari_dizi[0] = "sigmoid";
        aktivasyon_fonksiyonlari_dizi[1] = "softplus";
        aktivasyon_fonksiyonlari_dizi[2] = "softsign";
        aktivasyon_fonksiyonlari_dizi[3] = "tanh";
        
        ComboBoxModel aktivasyon_fonksiyonlari_model = new DefaultComboBoxModel(aktivasyon_fonksiyonlari_dizi);
        Aktivasyon_Fonksiyonlari_Combobox.setModel(aktivasyon_fonksiyonlari_model);
        add(Aktivasyon_Fonksiyonlari_Combobox);
        
        Sinif_Sayisi_label = new JLabel("Sınıf Sayısı : ");
        Sinif_Sayisi_label.setBounds(730, 55, 120, 40);
        add(Sinif_Sayisi_label);
        
        Sinif_Sayisi_Combobox = new JComboBox();
        Sinif_Sayisi_Combobox.setBounds(920, 55, 120, 40);
        String[] sinif_sayisi_dizi = new String[5];
        
        sinif_sayisi_dizi[0] = "2";
        sinif_sayisi_dizi[1] = "3";
        sinif_sayisi_dizi[2] = "4";
        sinif_sayisi_dizi[3] = "5";
        sinif_sayisi_dizi[4] = "6";
        ComboBoxModel sinif_sayisi_model = new DefaultComboBoxModel(sinif_sayisi_dizi);
        Sinif_Sayisi_Combobox.setModel(sinif_sayisi_model);
        add(Sinif_Sayisi_Combobox);
        
        Ara_Katman_Noron_Sayisi_label = new JLabel("Ara Katman Nöron Sayısı : ");
        Ara_Katman_Noron_Sayisi_label.setBounds(730, 100, 220, 40);
        add(Ara_Katman_Noron_Sayisi_label);
        
        Ara_Katman_Noron_Sayisi_Combobox = new JComboBox();
        Ara_Katman_Noron_Sayisi_Combobox.setBounds(920, 100, 120, 40);
        String[] ara_katman_noron_sayisi_dizi = new String[15];
        
        for(int a=1; a<= 15;a++)
        {
            ara_katman_noron_sayisi_dizi[a - 1] = String.valueOf(a);
        }
        
        ComboBoxModel ara_katman_noron_sayisi_model = new DefaultComboBoxModel(ara_katman_noron_sayisi_dizi);
        Ara_Katman_Noron_Sayisi_Combobox.setModel(ara_katman_noron_sayisi_model);
        add(Ara_Katman_Noron_Sayisi_Combobox);
        
        Ayarlari_Setle_Button = new JButton("Ayarları Setle");
        Ayarlari_Setle_Button.setBounds(1080, 70, 140, 40);
        add(Ayarlari_Setle_Button);
        
        Orneklerin_Sinifi_label = new JLabel("Örneklerin Sınıfı : ");
        Orneklerin_Sinifi_label.setBounds(730, 170, 220, 40);
        add(Orneklerin_Sinifi_label);
        
        Orneklerin_Sinifi_Combobox = new JComboBox();
        Orneklerin_Sinifi_Combobox.setBounds(920, 170, 120, 40);
        
        
        add(Orneklerin_Sinifi_Combobox);
        
        
        Saykıl_Sayisi_label = new JLabel("Saykıl Sayısı : ");
        Saykıl_Sayisi_label.setBounds(800, 400, 120, 40);
        add(Saykıl_Sayisi_label);
        
        Saykıl_Sayisi_text = new JLabel("-------");
        //Saykıl_Sayisi_text = new JTextField("-------");
        Saykıl_Sayisi_text.setBounds(920, 400, 120, 40);
        add(Saykıl_Sayisi_text);
        
        Error_label = new JLabel("Error : ");
        Error_label.setBounds(800, 450, 120, 40);
        add(Error_label);
        
        Error_text = new JLabel("-------");
        //Error_text = new JTextField("-------");
        Error_text.setBounds(920, 450, 120, 40);
        add(Error_text);
        
        
        X_label = new JLabel("X : ");
        X_label.setBounds(730, 240, 120, 40);
        add(X_label);
        
        X_Text = new JLabel("  - ");
        X_Text.setBounds(760, 240, 120, 40);
        add(X_Text);
        
        Y_label = new JLabel("Y : ");
        Y_label.setBounds(730, 260, 120, 40);
        add(Y_label);
        
        Y_Text = new JLabel("  - ");
        Y_Text.setBounds(760, 260, 120, 40);
        add(Y_Text);
        
        Z_label = new JLabel("Z : ");
        Z_label.setBounds(730, 280, 120, 40);
        add(Z_label);
        
        Z_Text = new JLabel("  - ");
        Z_Text.setBounds(760, 280, 120, 40);
        add(Z_Text);

        
        ArrayList<ArrayList<Integer> > input = new ArrayList<ArrayList<Integer> >(); 
        
        input.add(new ArrayList<Integer>());
        input.add(new ArrayList<Integer>());
        
        ArrayList<ArrayList<Double> > input_normalize = new ArrayList<ArrayList<Double> >(); 
        
        input_normalize.add(new ArrayList<Double>());
        input_normalize.add(new ArrayList<Double>());
        
        ArrayList<Integer> etiket=new ArrayList<Integer>();
        
        
         imagePanel.addMouseListener(new MouseAdapter() 
         {
             @Override
             public void mouseClicked(MouseEvent evt)
             {
                 if(Orneklerin_Sinifi_Combobox.getSelectedItem() == null)
                 {
                     JOptionPane.showMessageDialog(null, "Lütfen Sınıf Seçimi Yapınız");
                     return;
                 }
                 
                 int x = evt.getX() - (imagePanel.getWidth() / 2);
                 int y = (imagePanel.getHeight() /2) - evt.getY();
                 
                 
                 X_Text.setText(String.valueOf(x));
                 Y_Text.setText(String.valueOf(y));
                 Z_Text.setText(String.valueOf(1));

                 int secilen_sinif = Integer.parseInt(Orneklerin_Sinifi_Combobox.getSelectedItem().toString());
                 secilen_sinif = secilen_sinif - 1;
      
                 input.get(0).add(x);
                 input.get(1).add(y);
                 etiket.add(secilen_sinif);
                 
                 imagePanel.arti_ciz(secilen_sinif,x,y);
             };
      });
 
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
 
                double X_ortalama = 0;
		double Y_ortalama = 0;
		double X_standart_sapma, Y_standart_sapma;

                
                double x_kare = 0;
		double y_kare = 0;

		double X_varyans;
		double Y_varyans;

		
		int nokta_sayisi = 0;
                for(int a=0;a<input.get(0).size();a++)
                {
                    int x = input.get(0).get(a);
		    int y = input.get(1).get(a);

		    X_ortalama = X_ortalama + x;
		    Y_ortalama = Y_ortalama + y;

		    x_kare =x_kare + (x * x);
	            y_kare =y_kare + (y * y);

		    nokta_sayisi++;
                }
		
		X_ortalama = X_ortalama / nokta_sayisi;
		Y_ortalama = Y_ortalama / nokta_sayisi;

		X_varyans = (x_kare / nokta_sayisi) - (X_ortalama * X_ortalama);
		Y_varyans = (y_kare / nokta_sayisi) - (Y_ortalama * Y_ortalama);
		
		X_standart_sapma = Math.sqrt(X_varyans);
		Y_standart_sapma = Math.sqrt(Y_varyans);
                for(int a=0;a<input.get(0).size();a++)   
		{
                    double x = (input.get(0).get(a) - X_ortalama) / X_standart_sapma;
                    double y = (input.get(1).get(a) - Y_ortalama) / Y_standart_sapma;
                    
                    input_normalize.get(0).add(x);
                    input_normalize.get(1).add(y);
		}
                
                System.out.println("Boyut:   "+input_normalize.get(0).size());
                System.out.println("Etiket Boyut:   "+etiket.size());
         
                
         double learning_rate = Double.parseDouble(Ogrenme_Katsayisi_Text.getText());
         System.out.println("learning rate :  "+learning_rate);
         double error_esik = Double.parseDouble(Error_Esik_Text.getText());
         String aktivasyon = Aktivasyon_Fonksiyonlari_Combobox.getSelectedItem().toString();
         System.out.println("aktivasyon :  "+aktivasyon);                
                
         Fully_Connected_Layer fully_connected = new Fully_Connected_Layer();
         
         Layer input = new Layer();
         input.input_shape(3);
         fully_connected.Katman_Ekle(input);
         
         Layer hidden1 = new Layer();
         hidden1.dense(ara_katman_noron_sayisi,aktivasyon);
         fully_connected.Katman_Ekle(hidden1);
         
         Layer output = new Layer();
         output.dense(sinif_sayisi,aktivasyon);
         fully_connected.Katman_Ekle(output);
         
         fully_connected.parametreleri_setle();
         
         fully_connected.learning_rate = learning_rate;
         fully_connected.error_esik = error_esik;
      
         fully_connected.Ozet();
         
         
         fully_connected.Train(Saykıl_Sayisi_text, Error_text, input_normalize, etiket);
         
                 
               
               System.out.println("X_ortalama  :   "+X_ortalama);
               System.out.println("X_standart_sapma  :   "+X_standart_sapma);
               
               System.out.println("Y_ortalama  :   "+Y_ortalama);
               System.out.println("Y_standart_sapma  :   "+Y_standart_sapma);
                
                for (int row =-(imagePanel.getHeight()/2); row < (imagePanel.getHeight()/2); row=row+3)
                {
                    for (int column = (imagePanel.getWidth()/2); column > -(imagePanel.getWidth()/2); column=column-3)
                    {
                        
                        double[] z = new double[3];

                        z[0] = ( column - X_ortalama) / X_standart_sapma;
                        z[1] = (row - Y_ortalama) / Y_standart_sapma;
                        z[2] = 1;
                   
                        Layer sonuc = fully_connected.Test(z);
                        double en_buyuk = sonuc.input[0];
                        int hangisi = 0;
                        for (int i = 0; i < sonuc.noron_sayisi; i++)
                        {
                                if (sonuc.input[i] > en_buyuk)
                                {
                                        en_buyuk = sonuc.input[i];
                                        
                                        hangisi = i;
                                }
                        }
                        imagePanel.aralikli_boya(hangisi,(column + (imagePanel.getWidth()/2)),((imagePanel.getHeight()/2) - row));   
                    }
                    
                }
            }
        });
        
        Ayarlari_Setle_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
              sinif_sayisi = Integer.parseInt(Sinif_Sayisi_Combobox.getSelectedItem().toString());
              ara_katman_noron_sayisi = Integer.parseInt(Ara_Katman_Noron_Sayisi_Combobox.getSelectedItem().toString());
              
              JOptionPane.showMessageDialog(null, sinif_sayisi);
              JOptionPane.showMessageDialog(null, ara_katman_noron_sayisi);
              
              String[] orneklerin_sinifi_dizi = new String[sinif_sayisi];

              for(int a=1; a<=sinif_sayisi ;a++)
              {
                  orneklerin_sinifi_dizi[a - 1] = String.valueOf(a);
              }
        
              ComboBoxModel Orneklerin_Sinifi_model = new DefaultComboBoxModel(orneklerin_sinifi_dizi);
              Orneklerin_Sinifi_Combobox.setModel(Orneklerin_Sinifi_model);
              
              
              
              Sinif_Sayisi_Combobox.setEnabled(false);
              Ara_Katman_Noron_Sayisi_Combobox.setEnabled(false);
              
            }
        });

        sifirla_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                
              sinif_sayisi = 0;//Integer.parseInt(Sinif_Sayisi_Combobox.getSelectedItem().toString());
              ara_katman_noron_sayisi = 0;//Integer.parseInt(Ara_Katman_Noron_Sayisi_Combobox.getSelectedItem().toString());
              
              input_normalize.clear();
              input.clear();
              etiket.clear();
              
              imagePanel.koordinat_ciz();
              //JOptionPane.showMessageDialog(null, sinif_sayisi);
              //JOptionPane.showMessageDialog(null, ara_katman_noron_sayisi);
              
//              String[] orneklerin_sinifi_dizi = new String[sinif_sayisi];
//
//              for(int a=1; a<=sinif_sayisi ;a++)
//              {
//                  orneklerin_sinifi_dizi[a - 1] = String.valueOf(a);
//              }
//        
              //ComboBoxModel Orneklerin_Sinifi_model = null;
              //Orneklerin_Sinifi_Combobox.setModel(Orneklerin_Sinifi_model);
              Orneklerin_Sinifi_Combobox = new JComboBox();
              
              
              Sinif_Sayisi_Combobox.setEnabled(true);
              Ara_Katman_Noron_Sayisi_Combobox.setEnabled(true);
              
            }
        });
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 

    }
    public static void main(String args[]) {
        new Form().setVisible(true);
    }
}