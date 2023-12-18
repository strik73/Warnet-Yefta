/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLInputKomController implements Initializable {

    @FXML
    private TextField txtidkom;
    @FXML
    private TextField txtnamakom;
    @FXML
    private TextField txtjenis;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnhapus;

    boolean editdata = false;
    @FXML
    private TextField txtharga;
    @FXML
    private ComboBox<String> combojenis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 combojenis.setItems(FXCollections.observableArrayList("Normal", "Premium", "Esport"));

        // Add a listener to the ComboBox to handle the selection change
        combojenis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // newValue contains the selected item from the ComboBox
            // You can use it to set the "jenis" field or perform any other actions
            // For example, if "jenis" is a TextField, you can set its text like this:
            txtjenis.setText(newValue);
        });
    }

    public void execute(KomModel d) {
        if (!d.getIdkomp().isEmpty()) {
            editdata = true;
            txtidkom.setText(d.getIdkomp());
            txtnamakom.setText(d.getNamakomp());
//            txtjenis.setText(d.getJenis());
            txtharga.setText(String.valueOf(d.getHarga()));
            txtidkom.setEditable(false);
            txtnamakom.requestFocus();
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        KomModel n = new KomModel();

        n.setIdkomp(txtidkom.getText());
        n.setNamakomp(txtnamakom.getText());
        n.setJenis(txtjenis.getText());
        n.setHarga(Double.parseDouble(txtharga.getText()));
        FXMLDocumentController.dtkom.setKomModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtkom.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtidkom.setEditable(true);
                hapusklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtkom.validasi(n.getIdkomp()) <= 0) {
            if (FXMLDocumentController.dtkom.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                hapusklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtidkom.requestFocus();
        }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        txtidkom.setText("");
        txtnamakom.setText("");
        txtjenis.setText("");
        txtharga.setText("");
        txtidkom.requestFocus();
    }

}
