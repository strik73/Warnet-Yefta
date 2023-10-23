/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLDisplayKomController implements Initializable {

    @FXML
    private TableView<KomModel> tbvkom;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnkurang;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    @FXML
    private TextField txtcari;
    @FXML
    private Button btnubah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }   
    
     public void showdata(){
        ObservableList<KomModel> data=FXMLDocumentController.dtkom.Load();
        if(data!=null){            
            tbvkom.getColumns().clear();            
            tbvkom.getItems().clear();

            TableColumn col=new TableColumn("ID Komputer");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("idkomp"));
            tbvkom.getColumns().addAll(col);
            col=new TableColumn("Nama Komputer");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("namakomp"));
            tbvkom.getColumns().addAll(col);
            col=new TableColumn("Jenis");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("jenis"));
            tbvkom.getColumns().addAll(col);
            
            tbvkom.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data is empty",ButtonType.OK);
            a.showAndWait();
            tbvkom.getScene().getWindow().hide();
        }                
     }

    @FXML
    private void tambahklik(ActionEvent event) {
      try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputKom.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        //awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        KomModel s= new KomModel();       
        s=tbvkom.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtkom.delete(s.getIdkomp())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           //awalklik(event);       
        }    
    }

    @FXML
    private void akhirklik(ActionEvent event) {
         tbvkom.getSelectionModel().selectLast();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
         tbvkom.getSelectionModel().selectNext();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
         tbvkom.getSelectionModel().selectPrevious();
    }

    @FXML
    private void awalklik(ActionEvent event) {
         tbvkom.getSelectionModel().selectFirst();
    }

    @FXML
    private void cariData(javafx.scene.input.KeyEvent event) {
        KomModel s = new KomModel();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<KomModel> data=FXMLDocumentController.dtkom.CariKom(key,key);
        if(data!=null){            
            tbvkom.getColumns().clear();
            tbvkom.getItems().clear();
            
            TableColumn col=new TableColumn("ID Komputer");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("idkomp"));
            tbvkom.getColumns().addAll(col);
            col=new TableColumn("Nama Komputer");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("namakomp"));
            tbvkom.getColumns().addAll(col);
            col=new TableColumn("Jenis");
            col.setCellValueFactory(new PropertyValueFactory<KomModel, String>("jenis"));
            tbvkom.getColumns().addAll(col);
            tbvkom.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvkom.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        KomModel s= new KomModel();
        s=tbvkom.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputKom.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputKomController isidt=(FXMLInputKomController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);

    }
}
