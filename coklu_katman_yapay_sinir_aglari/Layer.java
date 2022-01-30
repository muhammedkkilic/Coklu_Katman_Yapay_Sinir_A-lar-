package com.mycompany.coklu_katman_yapay_sinir_aglari;

import java.util.Random;

public class Layer {
    
    public boolean train;
    
    public int noron_sayisi;
    public int parametre_sayisi;
    
    public String aktivasyon;
    
    public double[][] kernel;
    public double[] bias;
    
    public double[] input_activation;
    public double[] output_activation;
    public double[] input;
    
    public double[] Delta_Layer;
    public double[][] Weight_Delta;
    public double[][] Momentum_Weight_Delta;
    public double[] SmallDelta;
    public double[] Momentum_SmallDelta;
    
    public Layer()
    {
        
    }
    
    public void input_shape(int input_boyutu)
    {
        this.noron_sayisi = input_boyutu;
        this.parametre_sayisi = 1;
        this.train = false;
    }
    public void dense(int noron_sayisi, String aktivasyon)
    {
        this.noron_sayisi = noron_sayisi;
        this.aktivasyon = aktivasyon;
        
        this.train = true;
        
    }
    public void dinamik_dizi_olustur()
    {
        kernel = new double[noron_sayisi][parametre_sayisi];
        bias = new double[noron_sayisi];
        SmallDelta = new double[noron_sayisi];
        Momentum_SmallDelta = new double[noron_sayisi];
        Delta_Layer = new double[noron_sayisi];
        Weight_Delta = new double[noron_sayisi][parametre_sayisi];
        Momentum_Weight_Delta = new double[noron_sayisi][parametre_sayisi];
        input_activation = new double[noron_sayisi];
        output_activation = new double[noron_sayisi];
        input = new double[noron_sayisi];
        
        Random rand = new Random();
        
        for (int a = 0; a < this.noron_sayisi; a++)
        {
            for (int b = 0; b < this.parametre_sayisi; b++)
            {
                    kernel[a][b] = rand.nextDouble();
            }
            bias[a] = rand.nextDouble();
        }
        
    }
    
    
}

