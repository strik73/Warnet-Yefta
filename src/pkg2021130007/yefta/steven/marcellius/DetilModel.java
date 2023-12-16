/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

/**
 *
 * @author HP
 */
public class DetilModel {
    private String kodetarif, idkomp, jenis;
    private int durasi;
    private double harga, total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getKodetarif() {
        return kodetarif;
    }

    public void setKodetarif(String kodetarif) {
        this.kodetarif = kodetarif;
    }

    public String getIdkomp() {
        return idkomp;
    }

    public void setIdkomp(String idkomp) {
        this.idkomp = idkomp;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    
}
