module TechService {
	requires javafx.controls;
	requires javafx.fxml;
	requires spring.jdbc;
	requires mysql.connector.j;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
