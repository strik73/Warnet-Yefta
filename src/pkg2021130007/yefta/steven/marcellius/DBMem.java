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
public class DBMem {
    private MemModel dt=new MemModel();    
    public MemModel getMemModel(){ return(dt);}
    public void setMemModel(MemModel s){ dt=s;}
    
    public ObservableList<MemModel>  Load() {
        try {
            ObservableList<MemModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idmember, nama, userid, password from member");

            int i = 1;
            while (rs.next()) {
                MemModel d=new MemModel();
                d.setIdmember(rs.getString("idmember"));                
                d.setNama(rs.getString("nama"));
                d.setUserid(rs.getString("userid"));                
                d.setPassword(rs.getString("password"));           
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from member where idmember = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into member (idmember,nama, userid, password) values (?,?,?,?)");
            con.preparedStatement.setString(1, getMemModel().getIdmember());           
            con.preparedStatement.setString(2, getMemModel().getNama());
            con.preparedStatement.setString(3, getMemModel().getUserid());           
            con.preparedStatement.setString(4, getMemModel().getPassword());        
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from member where idmember  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update member set nama = ?, userid = ?, password = ?  where  idmember = ?");
            con.preparedStatement.setString(1, getMemModel().getNama());
            con.preparedStatement.setString(2, getMemModel().getUserid());
            con.preparedStatement.setString(3, getMemModel().getPassword());
            con.preparedStatement.setString(4, getMemModel().getIdmember());
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
        
        public ObservableList<MemModel>  CariMem(String kode, String nama) {
        try {    
            ObservableList<MemModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from member WHERE idmember LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            MemModel d = new MemModel();
            d.setIdmember(rs.getString("idmember"));
            d.setNama(rs.getString("nama"));
            d.setUserid(rs.getString("userid"));
            d.setPassword(rs.getString("password"));
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

