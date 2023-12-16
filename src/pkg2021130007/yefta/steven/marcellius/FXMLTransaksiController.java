/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLTransaksiController implements Initializable {

    @FXML
    private TextField txtkodetarif;
    @FXML
    private DatePicker dtptanggal;
    @FXML
    private TableView<MemModel> tbvmem;
    @FXML
    private TableView<KomModel> tbvkom;
    @FXML
    private TextField txtidkom;
    @FXML
    private TextField txtdurasi;
    @FXML
    private TableView<DetilModel> tbvdetil;
    @FXML
    private Button btncancel;
    @FXML
    private Button btnsave;
    @FXML
    private TextField txtid;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdatamem();
        showdatakomp();
        tbvmem.getSelectionModel().selectFirst();
    }

    public void showdatamem() {
        ObservableList<MemModel> data = FXMLDocumentController.dtmem.Load();
        if (data != null) {
            tbvmem.getColumns().clear();
            tbvmem.getItems().clear();

            TableColumn col = new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("idmember"));
            tbvmem.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("nama"));
            tbvmem.getColumns().addAll(col);

            tbvmem.setItems(data);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvmem.getScene().getWindow().hide();
        }
    }

    public void showdatakomp() {
        ObservableList<KomModel> data = FXMLDocumentController.dtkom.Load();
        if (data != null) {
            tbvkom.getColumns().clear();
            tbvkom.getItems().clear();

            TableColumn col = new TableColumn("idkomp");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("idkomp"));
            tbvkom.getColumns().addAll(col);
            col = new TableColumn("namakomp");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("namakomp"));
            tbvkom.getColumns().addAll(col);
            col = new TableColumn("jenis");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("jenis"));
            tbvkom.getColumns().addAll(col);

            tbvkom.setItems(data);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvkom.getScene().getWindow().hide();
        }
    }

    public void showdatadetil() {
        ObservableList<DetilModel> data = FXMLDocumentController.dtdetil.Load(txtkodetarif.getText());
        if (data != null) {
            tbvdetil.getColumns().clear();
            tbvdetil.getItems().clear();

            TableColumn col = new TableColumn("Kode Tarif");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("kodetarif"));
            tbvdetil.getColumns().addAll(col);
            col = new TableColumn("ID Komputer");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("idkomp"));
            tbvdetil.getColumns().addAll(col);
            col = new TableColumn("Jenis");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("jenis"));
            tbvdetil.getColumns().addAll(col);
            col = new TableColumn("Durasi (jam)");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("durasi"));
            tbvdetil.getColumns().addAll(col);
            col = new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("harga"));
            tbvdetil.getColumns().addAll(col);
            col = new TableColumn("Total");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("total"));
            tbvdetil.getColumns().addAll(col);

            tbvdetil.setItems(data);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is emprty", ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();
        }
    }

    @FXML
    private void tbvmemklik(MouseEvent event) {
        txtid.setText(tbvmem.getSelectionModel().getSelectedItem().getIdmember());
    }

    @FXML
    private void tbvkomklik(MouseEvent event) {
        txtidkom.setText(tbvkom.getSelectionModel().getSelectedItem().getIdkomp());
    }

    @FXML
    private void cancelklik(ActionEvent event) {

    }

    @FXML
    private void saveklik(ActionEvent event) {
        TarifModel n = new TarifModel();
        n.setKodetarif(txtkodetarif.getText());
        n.setTanggal(Date.valueOf(dtptanggal.getValue()));
        n.setIdmember(txtid.getText());

        FXMLDocumentController.dttarif.setTarifModel(n);
        if (FXMLDocumentController.dttarif.insert()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
            a.showAndWait();
            cancelklik(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
            a.showAndWait();
        }
        tbvkom.disableProperty().setValue(Boolean.FALSE);
        txtidkom.editableProperty().setValue(Boolean.TRUE);
        txtdurasi.editableProperty().setValue(Boolean.TRUE);
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        DetilModel n = new DetilModel();
        n.setKodetarif(txtkodetarif.getText());
        n.setIdkomp(txtidkom.getText());
        n.setDurasi(Integer.parseInt(txtdurasi.getText()));

        FXMLDocumentController.dtdetil.setDetilModel(n);
        if (FXMLDocumentController.dtdetil.insert()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Barang berhasil disimpan", ButtonType.OK);
            a.showAndWait();
            cancelklik(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Barang gagal disimpan", ButtonType.OK);
            a.showAndWait();
        }

        showdatadetil();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        DetilModel n = new DetilModel();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Barang akan dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();

        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdetil.delete(txtkodetarif.getText(), txtidkom.getText())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Barang berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Barang gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }

        }
        showdatadetil();
    }

}
