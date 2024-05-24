package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;

public class MainWinController implements Initializable{
	
	MysqlDataSource dataSource = new MysqlDataSource();
	JdbcTemplate jdbc;
	RequestDao requestDao = new RequestDao(dataSource);
	ObservableList<Request> requestList = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Request> RequestTable;
	@FXML
	private TableColumn<Request, Integer> RequestIDCol;
	@FXML
	private TableColumn<Request, String> StartDateCol;
	@FXML
	private TableColumn<Request, String> ComputerTechTypeCol;
	@FXML
	private TableColumn<Request, String> ProblemDescriptionCol;
	@FXML
	private TableColumn<Request, String> RequestStatusCol;
	@FXML
	private TableColumn<Request, String> CompletionDateCol;
	@FXML
	private TableColumn<Request, String> RepairPartsCol;
	@FXML
	private TableColumn<Request, Integer> MasterIDCol;
	@FXML
	private TableColumn<Request, Integer> ClientIDCol;
	@FXML
	private TextField ComputerTechTypeField;
	@FXML
	private Button AddBtn;
	@FXML
	private Button EditBtn;
    @FXML
    private Button CalcRequestsBtn;
	@FXML
	private TextField RequestStatusField;
	@FXML
	private TextField ProblemDescryptionField;
	@FXML
	private TextField RequestIdField;
	@FXML
	private DatePicker StartDateField;
	@FXML
	private TextField RepairPartsField;
	@FXML
	private TextField MasterIDField;
	@FXML
	private TextField ClientIDField;
	@FXML
	private DatePicker CompletionDateField;
	
    /* 
	private TextField RequestStatusField;
	@FXML
	private TextField ProblemDescryptionField;
	@FXML
	private TextField RequestIdField;
	@FXML
	private DatePicker StartDateField;
	@FXML
	private TextField RepairPartsField;
	@FXML
	private TextField MasterIDField;
	@FXML
	private TextField ClientIDField;
	@FXML
	private DatePicker CompletionDateField;
    */

	// Event Listener on Button[#AddBtn].onAction
	@FXML
	public void AddRequest(ActionEvent event) {
		
		try {
			requestDao.save(
					new Request(
							Integer.parseInt(RequestIdField.getText()),
							StartDateField.getValue().toString(),
							ComputerTechTypeField.getText(),
							ProblemDescryptionField.getText(),
							RequestStatusField.getText(),
							CompletionDateField.getValue().toString(),
							RepairPartsField.getText(),
							Integer.parseInt(MasterIDField.getText()),
							Integer.parseInt(ClientIDField.getText())
							)
					);
		} catch(Exception e){
			Alert alert = new Alert(AlertType.INFORMATION);     
			alert.setContentText("Ошибка добавления!");
			alert.showAndWait();
		} finally {
			initialize(null, null);
		}
		
	}
	// Event Listener on Button[#EditBtn].onAction
	@FXML
	public void EditRequest(ActionEvent event) throws Exception {
		Request req = RequestTable.getSelectionModel().getSelectedItem();
		if(req != null) {
			try {
				requestDao.update(
						new Request(
								Integer.parseInt(RequestIdField.getText()),
								StartDateField.getValue().toString(),
								ComputerTechTypeField.getText(),
								ProblemDescryptionField.getText(),
								RequestStatusField.getText(),
								CompletionDateField.getValue().toString(),
								RepairPartsField.getText(),
								Integer.parseInt(MasterIDField.getText()),
								Integer.parseInt(ClientIDField.getText())
								)
						);
			} catch(Exception e){
				Alert alert = new Alert(AlertType.INFORMATION);    
				alert.setTitle("Ошибка");
				alert.setContentText("Ошибка редактирования!");
				alert.showAndWait();
			} finally {
				initialize(null, null);
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);    
	        alert.setTitle("Ошибка");
			alert.setContentText("Вы ничего не выбрали!");
			alert.showAndWait();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		RequestTable.getItems().clear();
	    dataSource.setServerName("localhost");
	    dataSource.setPort(3306);
	    dataSource.setDatabaseName("TechsRepair");
	    dataSource.setUser("root");
	    dataSource.setPassword("");
	    
	    jdbc = new JdbcTemplate(dataSource);
	    
		
	    RequestIDCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("requestID"));
	    StartDateCol.setCellValueFactory(new PropertyValueFactory<Request, String>("startDate"));
	    ComputerTechTypeCol.setCellValueFactory(new PropertyValueFactory<Request, String>("computerTechType"));
	    ProblemDescriptionCol.setCellValueFactory(new PropertyValueFactory<Request, String>("problemDescryption"));
	    RequestStatusCol.setCellValueFactory(new PropertyValueFactory<Request, String>("requestStatus"));
	    CompletionDateCol.setCellValueFactory(new PropertyValueFactory<Request, String>("completionDate"));
	    RepairPartsCol.setCellValueFactory(new PropertyValueFactory<Request, String>("repairParts"));
	    MasterIDCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("masterID"));
	    ClientIDCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("clientID"));

		
		RequestDao requestDao = new RequestDao(dataSource);
		requestList.addAll(requestDao.getAll());
	    RequestTable.setItems(requestList);
	}
	
    @FXML
    void CalcRequests(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);     
        int cnt = jdbc.query(
                "SELECT COUNT(*) AS cnt FROM `Request` WHERE `requestStatus`='Готова к выдаче'", 
                (ResultSet, RowNum) -> {
                    return ResultSet.getInt("cnt");
                }
            ).getFirst();
        alert.setTitle("Расчет");
		alert.setContentText("Заявок выполнено: " + Integer.toString(cnt));
		alert.showAndWait();
    }
}
