/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class FXMLJualController implements Initializable {

    @FXML
    private TextField txtkodetarif;
    @FXML
    private TableView<TarifModel> tbvtarif;
    @FXML
    private Button btnhapus;
    @FXML
    private TableView<DetilModel> tbvdetil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvtarif.getSelectionModel().selectFirst();
        txtkodetarif.setText( tbvtarif.getSelectionModel().getSelectedItem().getKodetarif());
        showdata2();
    }    
    
    public void showdata(){
        ObservableList<TarifModel> data=FXMLDocumentController.dttarif.Load();
        if(data!=null){            
            tbvtarif.getColumns().clear();            
            tbvtarif.getItems().clear();
            String status;

            TableColumn col=new TableColumn("kodetarif");
            col.setCellValueFactory(new PropertyValueFactory<TarifModel, String>("kodetarif"));
            tbvtarif.getColumns().addAll(col);
            col=new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<TarifModel, String>("idmember"));
            tbvtarif.getColumns().addAll(col);
            col=new TableColumn("Tanggal");            
            col.setCellValueFactory(new FormattedDateValueFactory<TarifModel>("tanggal", "dd-MM-yyyy"));
            tbvtarif.getColumns().addAll(col);
            
            tbvtarif.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data is emprty",ButtonType.OK);
            a.showAndWait();
            tbvtarif.getScene().getWindow().hide();
        }               
           
    }
    
     public void showdata2(){
         ObservableList<DetilModel> data = FXMLDocumentController.dtdetil.Load(txtkodetarif.getText());
        if(data!=null){            
            tbvdetil.getColumns().clear();            
            tbvdetil.getItems().clear();

            TableColumn col=new TableColumn("Kode Tarif");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("kodetarif"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("ID Komputer");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("idkomp"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("Jenis");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("jenis"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("Durasi (jam)");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("durasi"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("Harga");
            col.setCellValueFactory(new FormattedDouble<DetilModel>("harga","#,###,##0"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("Total");
            col.setCellValueFactory(new FormattedDouble<DetilModel>("total","#,###,##0"));
            tbvdetil.getColumns().addAll(col);
            
            tbvdetil.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data is emprty",ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();
        }           
         
     }    
    
    @FXML
    private void pilihdata(MouseEvent event) {
        txtkodetarif.setText( tbvtarif.getSelectionModel().getSelectedItem().getKodetarif());
        showdata2();
    }
    
    @FXML
    private void hapusklik(ActionEvent event) {

        Alert a= new Alert(Alert.AlertType.CONFIRMATION, "Data jual akan dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
               FXMLDocumentController.dttarif.delete(txtkodetarif.getText());
               FXMLDocumentController.dtdetil.deleteall(txtkodetarif.getText()); 
               
                Alert b= new Alert(Alert.AlertType.INFORMATION, "Data penjualan berhasil dihapus" ,ButtonType.OK);
                b.showAndWait();
        } else {
                Alert b= new Alert(Alert.AlertType.ERROR, "Data penjualan gagal dihapus" ,ButtonType.OK);
                b.showAndWait();
            }
        
        showdata();
        showdata2();
            
        }
     
}
