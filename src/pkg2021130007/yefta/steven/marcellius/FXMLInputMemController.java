/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021130007.yefta.steven.marcellius;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLInputMemController implements Initializable {

     boolean editdata=false;
    
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtuser;
    @FXML
    private TextField txtpass;
    private DatePicker datedaftar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void execute(MemModel d){
        if(!d.getIdmember().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdmember());
          txtnama.setText(d.getNama());          
          txtuser.setText(d.getUserid());
          txtpass.setText(d.getPassword());
          txtid.setEditable(false);          
          txtnama.requestFocus();         
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        MemModel n=new MemModel(); 
       
        n.setIdmember(txtid.getText());
        n.setNama(txtnama.getText());     
        n.setUserid(txtuser.getText());   
        n.setPassword(txtpass.getText());
        FXMLDocumentController.dtmem.setMemModel(n);
        if(editdata){
            if(FXMLDocumentController.dtmem.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               hapusklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
         }else if(FXMLDocumentController.dtmem.validasi(n.getIdmember())<=0){
            if(FXMLDocumentController.dtmem.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               hapusklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtid.requestFocus();
        }    
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        txtid.setText("");        
        txtnama.setText("");
        txtuser.setText("");       
        txtpass.setText("");  
        txtid.requestFocus();
    }
    
}

