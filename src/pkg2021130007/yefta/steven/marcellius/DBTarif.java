/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class DBTarif {
    private TarifModel dt = new TarifModel();
    private HashMap<String, DetilModel> dt2 = new HashMap<String, DetilModel>();

    public TarifModel getTarifModel() {
        return (dt);
    }

    public void setTarifModel(TarifModel s) {
        dt = s;
    }

    public HashMap<String, DetilModel> getJualdetilModel() {
        return (dt2);
    }

    public void setJualdetilModel(DetilModel d) {
        dt2.put(d.getIdkomp(), d);
    }

    public ObservableList<TarifModel> Load() {
        try {
            ObservableList<TarifModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idmember, kodetarif, tanggal from tarif");

            int i = 1;
            while (rs.next()) {
                TarifModel d = new TarifModel();
                d.setIdmember(rs.getString("idmember"));
                d.setKodetarif(rs.getString("kodetarif"));
                d.setTanggal(rs.getDate("tanggal"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from tarif where kodetarif = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into tarif (kodetarif, tanggal,idmember) values (?,?,?)");
            con.preparedStatement.setString(1, getTarifModel().getKodetarif());
            con.preparedStatement.setDate(2, getTarifModel().getTanggal());
            con.preparedStatement.setString(3, getTarifModel().getIdmember());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from tarif where kodetarif  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual set tanggal = ?, idmember = ?  where  kodetarif= ? ");
            con.preparedStatement.setDate(1, getTarifModel().getTanggal());
            con.preparedStatement.setString(2, getTarifModel().getIdmember());
            con.preparedStatement.setString(3, getTarifModel().getKodetarif());
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

//    public void CetakReportMaster() {
//        Koneksi con = new Koneksi();
//        String is = "./src/penjualan/yefta/ReportJualMaster.jasper";
//        Map map = new HashMap();
//        map.put("judul", "Report Master");
//        con.bukaKoneksi();
//        try {
//            JasperPrint jasperPrint = JasperFillManager.fillReport(is, map, con.dbKoneksi);
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            con.tutupKoneksi();
//        }
    }
