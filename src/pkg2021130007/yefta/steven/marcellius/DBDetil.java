/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class DBDetil {
    private DetilModel dt=new DetilModel();    
    public DetilModel getDetilModel(){ return(dt);}
    public void setDetilModel(DetilModel s){ dt=s;}
    
    public ObservableList<DetilModel>  Load(String kode) {
        try {
            ObservableList<DetilModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select k.idkomp, t.kodetarif, k.harga, t.durasi, k.jenis from detil_tarif t join komputer k on (t.idkomp=k.idkomp) WHERE kodetarif LIKE '" + kode + "'");

            int i = 1;
            while (rs.next()) {
                DetilModel d=new DetilModel();
                d.setKodetarif(rs.getString("kodetarif"));                
                d.setIdkomp(rs.getString("idkomp"));
                d.setJenis(rs.getString("jenis"));
                d.setHarga(rs.getDouble("harga"));
                d.setDurasi(rs.getInt("durasi"));
                
                float total = 0;
                int durasi = rs.getInt("durasi");
                double harga = rs.getDouble("harga");
                total = (float) (durasi*harga);
                d.setTotal(total);
                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        } 
      }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from detil_tarif where kodetarif = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detil_tarif (kodetarif, idkomp, durasi) values (?,?,?)");
            con.preparedStatement.setString(1, getDetilModel().getKodetarif());           
            con.preparedStatement.setString(2, getDetilModel().getIdkomp());
            con.preparedStatement.setInt(3, getDetilModel().getDurasi());  
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
    
    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detil_tarif where kodetarif  = ? and idkomp = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean deleteall(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detil_tarif where kodetarif  = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update detil_tarif set idkomp = ?, durasi = ? where  kodetarif= ? ");
            con.preparedStatement.setString(1, getDetilModel().getIdkomp());
            con.preparedStatement.setInt(2, getDetilModel().getDurasi());
            con.preparedStatement.setString(3, getDetilModel().getKodetarif());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }
}
