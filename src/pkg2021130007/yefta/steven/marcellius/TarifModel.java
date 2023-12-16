/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class TarifModel {
    private String kodetarif, idmember;
    private Date tanggal;

    public String getKodetarif() {
        return kodetarif;
    }

    public void setKodetarif(String kodetarif) {
        this.kodetarif = kodetarif;
    }

    public String getIdmember() {
        return idmember;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    
}
