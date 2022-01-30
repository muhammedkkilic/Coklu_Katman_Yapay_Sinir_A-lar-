package com.mycompany.coklu_katman_yapay_sinir_aglari;


import java.util.ArrayList;
import javax.swing.JLabel;

public class Fully_Connected_Layer {
    
    public double learning_rate;
    public double momentum;
    public double error_esik;
    
    
    
    public ArrayList<Layer> katmanlar=new ArrayList<Layer>();
    
    public void Katman_Ekle(Layer layer)
    {
        katmanlar.add(layer);
    }
    public void parametreleri_setle()
    {
        for(int a=1;a<katmanlar.size();a++)
        {
            int onceki_boyut = 0;
            onceki_boyut = katmanlar.get(a - 1).noron_sayisi;
            katmanlar.get(a).parametre_sayisi = onceki_boyut;
            
            
            katmanlar.get(a).dinamik_dizi_olustur();
        }
    }
    public void Ozet()
    {
        for(int a=0;a<katmanlar.size();a++)
        {
            System.out.println(katmanlar.get(a).noron_sayisi+"   "+katmanlar.get(a).parametre_sayisi);
            
        }
    }
    public Fully_Connected_Layer()
    {
        
    }
    public Layer ileri_yayılım(double[] z, double[] label)
    {
        katmanlar.get(0).input = z;
        
        for(int a=1;a<katmanlar.size();a++)
        {
            for(int b=0;b<katmanlar.get(a).noron_sayisi;b++)
            {
                double toplam = 0;
                for(int c=0;c<katmanlar.get(a).parametre_sayisi;c++)
                {
                    toplam = toplam + (katmanlar.get(a - 1).input[c] * katmanlar.get(a).kernel[b][c]);
                }
                toplam = toplam + katmanlar.get(a).bias[b];
                katmanlar.get(a).input_activation[b] = toplam;

                toplam = activasyon_fonksiyonu(katmanlar.get(a).aktivasyon,toplam);

                katmanlar.get(a).output_activation[b] = toplam;
                katmanlar.get(a).input[b] = toplam;
            }
        }
        return katmanlar.get(katmanlar.size() - 1);
    }
    public void geri_yayilim_small_delta_hesapla(double[] z, double[] label, double[] error_derivate)
    {
        double toplam = 0;
        int katman_indisi = (katmanlar.size() - 1);
        for(int a=0;a<katmanlar.get(katman_indisi).noron_sayisi;a++)
        {
            toplam = error_derivate[a] * activasyon_fonksiyonu_turev(katmanlar.get(katman_indisi).aktivasyon,katmanlar.get(katman_indisi).input_activation[a]) ;
            katmanlar.get(katman_indisi).Delta_Layer[a] = toplam;
        }
        
        for(int a=(katmanlar.size() - 2);a>0;a--)
        {
            for(int b=0;b<katmanlar.get(a + 1).parametre_sayisi;b++)
            {
                toplam = 0;
                for(int c=0;c<katmanlar.get(a + 1).noron_sayisi;c++)
                {
                    toplam = toplam + (katmanlar.get(a + 1).kernel[c][b] * katmanlar.get(a + 1).Delta_Layer[c]);
                }
                toplam = activasyon_fonksiyonu_turev(katmanlar.get(a).aktivasyon,katmanlar.get(a).input_activation[a]) * toplam;
		katmanlar.get(a).Delta_Layer[a] = toplam;
            }
        }
        katmanlar.get(0).input = z;
        for(int a=1;a<katmanlar.size();a++)
        {
            for(int b=0;b<katmanlar.get(a).noron_sayisi;b++)
            {
                for(int c=0;c<katmanlar.get(a).parametre_sayisi;c++)
                {
                    toplam = katmanlar.get(a - 1).input[c] * katmanlar.get(a).Delta_Layer[b];

		    katmanlar.get(a).Weight_Delta[b][c] = toplam;
                }
                toplam = 1.0 * katmanlar.get(a).Delta_Layer[b];
		katmanlar.get(a).SmallDelta[b] = toplam;
				
            }
        }
        
    }
    public void geri_yayilim_katsayilari_hesapla()
    {
        for(int a=1;a<katmanlar.size();a++)
        {
            for(int b=0;b<katmanlar.get(a).noron_sayisi;b++)
            {
                for(int c=0;c<katmanlar.get(a).parametre_sayisi;c++)
                {
                                     
                    katmanlar.get(a).kernel[b][c] = katmanlar.get(a).kernel[b][c] + (this.learning_rate * katmanlar.get(a).Weight_Delta[b][c]);
                   
                }
                katmanlar.get(a).bias[b] = katmanlar.get(a).bias[b] + (this.learning_rate * katmanlar.get(a).SmallDelta[b]);
            }
        }
    }
    double softplus(double a)
    {
	double sonuc = Math.log(Math.exp(a) + 1);
	return sonuc;
    }
    double softplus_turev(double a)
    {
	double sonuc = (  1 / (1 + Math.exp(-a))  );
	return sonuc;
    }

    double softsign(double a)
    {
	double sonuc = a / (Math.abs(a) + 1);
	return sonuc;
    }
    double softsign_turev(double a)
    {
	double sonuc = 1 / (1 + Math.pow(Math.abs(a), 2));
	return sonuc;
    }
    double tanh_turev(double a)
    {
	double sonuc = 1 - Math.pow(Math.tanh(a), 2);
	return sonuc;
    }
    double sigmoid(double a)
    {
	double lamda = 1;
	double sonuc = (2 / (1 + Math.exp(-lamda * a))) - 1;
	return sonuc;
    }
    double sigmoid_turev(double a)
    {
	double sonuc = (1 - (sigmoid(a) * sigmoid(a))) / 2;
	return sonuc;
    }
    double activasyon_fonksiyonu_turev(String activasyon_fonksiyonu, double parametre)
    {
	double sonuc = 0;
	if (activasyon_fonksiyonu == "sigmoid")
	{
		sonuc = sigmoid_turev(parametre);
	}
	if (activasyon_fonksiyonu == "softplus")
	{
		sonuc = softplus_turev(parametre);
	}
	if (activasyon_fonksiyonu == "softsign")
	{
		sonuc = softsign_turev(parametre);
	}
	if (activasyon_fonksiyonu == "tanh")
	{
		sonuc = tanh_turev(parametre);
	}
	
	return sonuc;
    }

    double activasyon_fonksiyonu(String activasyon_fonksiyonu,double parametre)
    {
	double sonuc = 0;
	if (activasyon_fonksiyonu == "sigmoid")
	{
		sonuc = sigmoid(parametre);
	}
	if (activasyon_fonksiyonu == "softplus")
	{
		sonuc = softplus(parametre);
	}
	if (activasyon_fonksiyonu == "softsign")
	{
		sonuc = softsign(parametre);
	}
	if (activasyon_fonksiyonu == "tanh")
	{
		sonuc = Math.tanh(parametre);
	}
	return sonuc;
    }
    public Layer Test(double[] test_input)
    {
        katmanlar.get(0).input = test_input;
        
        for(int a=1;a<katmanlar.size();a++)
        {
            for(int b=0;b<katmanlar.get(a).noron_sayisi;b++)
            {
                double toplam = 0;
                for(int c=0;c<katmanlar.get(a).parametre_sayisi;c++)
                {
                    toplam = toplam + (katmanlar.get(a - 1).input[c] * katmanlar.get(a).kernel[b][c]);
                }
                toplam = toplam + katmanlar.get(a).bias[b];
                toplam = activasyon_fonksiyonu(katmanlar.get(a).aktivasyon,toplam);
                katmanlar.get(a).input[b] = toplam;
            }
        }
        return katmanlar.get(katmanlar.size() - 1);
        
    }
    public void Train(JLabel Saykıl_Sayisi_text,JLabel Error_text,ArrayList<ArrayList<Double> > input_normalize, ArrayList<Integer> etiket)
    {
        int saykil = 0;
        double E = 0;
        
        while(true)
        {
            E = 0;
            for(int a=0;a<input_normalize.get(0).size();a++)
            {
                double[] z = new double[3];

                z[0] = input_normalize.get(0).get(a);
                z[1] = input_normalize.get(1).get(a);
                z[2] = 1;
            
                double[] label = new double[(etiket.size() - 1)];
                for (int i = 0; i < (etiket.size() - 1); i++) {
                    if (etiket.get(a) == i) {
                        label[i] = 1;
                    }
                    else {
                        label[i] = -1;
                    }
                }
                Layer sonuc = ileri_yayılım(z, label);
                double[] error_derivate = new double[sonuc.noron_sayisi];
                
                for(int b=0;b<sonuc.noron_sayisi;b++)
                {
                    double fark = label[b] - sonuc.input[b];
                    error_derivate[b] = fark;
                    E = E + (0.5 * (fark * fark));
                }
                
                
                
                geri_yayilim_small_delta_hesapla(z, label,error_derivate);
                geri_yayilim_katsayilari_hesapla();
            }
            
            Saykıl_Sayisi_text.revalidate();
            Error_text.revalidate();
            
            Saykıl_Sayisi_text.setText(String.valueOf(saykil));
            Error_text.setText(String.valueOf(E));
            
            Saykıl_Sayisi_text.repaint();
            Error_text.repaint();
            

            System.out.println("Saykıl  :   "+saykil+"   E  :  "+E);
            
            //Saykıl_Sayisi_text.repaint();
            //Error_text.repaint();
            
            
            if (E < this.error_esik)
            {
                break;
            }
            saykil = saykil + 1;
            
        }
        Saykıl_Sayisi_text.revalidate();
        Error_text.revalidate();
        
        Saykıl_Sayisi_text.setText(String.valueOf(saykil));
        Error_text.setText(String.format("%.12f", E));
        
        Saykıl_Sayisi_text.repaint();
        Error_text.repaint();
    }
    
        

    
    

   

   
}