/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageHelper;

/**
 *
 * @author Yusuf Aji Wibowo
 */
public class ItemHistogram {
    public int rgb, jumlah, jumlahKumulatif;
    private int hasilEkualisasi;
    public ItemHistogram(int rgb, int jumlah){
        this.rgb = rgb;
        this.jumlah = jumlah;
        this.jumlahKumulatif = 0;
        this.hasilEkualisasi = 0;
    }
    
    public int getRgb(){
        return this.rgb;
    }
    
    public int getJumlah(){
        return this.jumlah;
    }
    
    public int getJumlahKumulatif(){
        return this.jumlahKumulatif;
    }
    
    public int getHasilEkualisasi(){
        return this.hasilEkualisasi;
    }
    
    public void setRgb(int rgb){
        this.rgb = rgb;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    
    public void setJumlahKumulatif(int jumlah){
        this.jumlahKumulatif = jumlah;
    }
    
    public void setHasilEkualisasi(int nilai){
        this.hasilEkualisasi = nilai;
    }
}