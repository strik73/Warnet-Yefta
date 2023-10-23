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
public class FXMLDisplayMemController implements Initializable {

    @FXML
    private TableView<MemModel> tbvmem;
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
    private Button btnubah;
    @FXML
    private TextField txtcari;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata(){
        ObservableList<MemModel> data=FXMLDocumentController.dtmem.Load();
        if(data!=null){            
            tbvmem.getColumns().clear();            
            tbvmem.getItems().clear();
            String status;

            TableColumn col=new TableColumn("ID Member");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("idmember"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("Nama Member");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("nama"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("User ID");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("userid"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("Password");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("password"));
            tbvmem.getColumns().addAll(col);            
            
            tbvmem.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data is empty",ButtonType.OK);
            a.showAndWait();
            tbvmem.getScene().getWindow().hide();
        }                
    }

    @FXML
    private void tambahklik(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMem.fxml"));    
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
    private void kurangklik(ActionEvent event) {
        MemModel s= new MemModel();       
        s=tbvmem.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtmem.delete(s.getIdmember())){
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
        tbvmem.getSelectionModel().selectLast();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvmem.getSelectionModel().selectNext();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvmem.getSelectionModel().selectPrevious();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvmem.getSelectionModel().selectFirst();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
     MemModel s= new MemModel();
        s=tbvmem.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMem.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputMemController isidt=(FXMLInputMemController)loader.getController();
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

    @FXML
    private void cariData(javafx.scene.input.KeyEvent event) {
         MemModel s = new MemModel();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<MemModel> data=FXMLDocumentController.dtmem.CariMem(key,key);
        if(data!=null){            
            tbvmem.getColumns().clear();
            tbvmem.getItems().clear();
            
            TableColumn col=new TableColumn("ID Member");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("idmember"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("Nama Member");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("nama"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("User ID");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("userid"));
            tbvmem.getColumns().addAll(col);
            col=new TableColumn("Password");
            col.setCellValueFactory(new PropertyValueFactory<MemModel, String>("password"));
            tbvmem.getColumns().addAll(col);
            
            tbvmem.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmem.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }        
    }
    
}
