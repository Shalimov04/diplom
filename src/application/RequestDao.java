package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.jdbc.MysqlDataSource;

public class RequestDao {
    
	MysqlDataSource dataSource;
	JdbcTemplate jdbc;

    public RequestDao(MysqlDataSource data) {
		super();
		jdbc = new JdbcTemplate(data);
    }
    
    /* 
	private Integer requestID;
	private String startDate;
    private String computerTechType;
    private String problemDescryption;
    private String requestStatus;
    private String completionDate;
    private String repairParts;
    
    private Integer masterID;
    private Integer clientID;
    */

    public void save(Request application) {
        jdbc.update(
            "insert into `Request`(`requestID`, `startDate`, `computerTechType`, `problemDescryption`, `requestStatus`, `completionDate`, `repairParts`, `masterID`, `clientID`) values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
            application.getRequestID(),
            application.getStartDate(),
            application.getComputerTechType(),
            application.getProblemDescryption(),
            application.getRequestStatus(),
            application.getCompletionDate(),
            application.getRepairParts(),
            application.getMasterID(),
            application.getClientID()
        );
    }
    
    public void update(Request application) {
    	jdbc.update(
            "update `Request` set `startDate` = ?, `computerTechType` = ?, `problemDescryption` = ?, `requestStatus` = ?, `completionDate` = ?, `repairParts` = ?, `masterID` = ?, `clientID` = ? where `requestID` = ?",
            application.getStartDate(),
            application.getComputerTechType(),
            application.getProblemDescryption(),
            application.getRequestStatus(),
            application.getCompletionDate(),
            application.getRepairParts(),
            application.getMasterID(),
            application.getClientID(),
            application.getRequestID()
        );
    }

    public List<Request> getAll(){
        List<Request> request = jdbc.query("select * from `Request`", (resultSet, rowNum) -> {
          Request req = new Request();
          req.setRequestID(resultSet.getInt("requestID"));
          req.setStartDate(resultSet.getString("startDate"));
          req.setComputerTechType(resultSet.getString("ComputerTechType"));
          req.setProblemDescryption(resultSet.getString("problemDescryption"));
          req.setRequestStatus(resultSet.getString("requestStatus"));
          req.setCompletionDate(resultSet.getString("completionDate"));
          req.setRepairParts(resultSet.getString("repairParts"));
          req.setMasterID(resultSet.getInt("masterID"));
          req.setClientID(resultSet.getInt("clientID"));
          return req;
        });
        return request;
      }
}