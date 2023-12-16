/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class DBKom {
    private KomModel dt=new KomModel();    
    public KomModel getKomModel(){ return(dt);}
    public void setKomModel(KomModel s){ dt=s;}
    
    public ObservableList<KomModel>  Load() {
        try {
            ObservableList<KomModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idkomp, namakomp, jenis, harga from komputer");

            int i = 1;
            while (rs.next()) {
                KomModel d=new KomModel();
                d.setIdkomp(rs.getString("idkomp"));                
                d.setNamakomp(rs.getString("namakomp"));
                d.setJenis(rs.getString("jenis")); 
                d.setHarga(rs.getDouble("harga"));                 
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from komputer where idkomp = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into komputer (idkomp, namakomp, jenis, harga) values (?,?,?,?)");
            con.preparedStatement.setString(1, getKomModel().getIdkomp());           
            con.preparedStatement.setString(2, getKomModel().getNamakomp());
            con.preparedStatement.setString(3, getKomModel().getJenis());        
            con.preparedStatement.setDouble(4, getKomModel().getHarga());     
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
        
        public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from komputer where idkomp  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update komputer set namakomp = ?, jenis = ?, harga = ? where idkomp = ? ");
            con.preparedStatement.setString(1, getKomModel().getNamakomp());
            con.preparedStatement.setString(2, getKomModel().getJenis());
            con.preparedStatement.setDouble(3, getKomModel().getHarga());
            con.preparedStatement.setString(4, getKomModel().getIdkomp());
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
        
        public ObservableList<KomModel>  CariKom(String kode, String nama) {
        try {    
            ObservableList<KomModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from komputer WHERE idkomp LIKE '" + kode + "%' OR namakomp LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            KomModel d = new KomModel();
            d.setIdkomp(rs.getString("idkomp"));
            d.setNamakomp(rs.getString("namakomp"));
            d.setJenis(rs.getString("jenis"));
            d.setHarga(rs.getDouble("harga"));
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
