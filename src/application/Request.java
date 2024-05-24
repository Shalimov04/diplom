package application;

public class Request {
    /* 
    requestID integer primary key auto_increment,
    startDate date,
    computerTechType varchar(100),
    computerTechModel varchar(200),
    problemDescryption varchar(100),
    requestStatus varchar(100),
    completionDate date,
    repairParts varchar(100),
    masterID integer,
    foreign key (masterID) references `Master`(masterID) ON DELETE SET NULL,
    clientID integer,
    foreign key (clientID) references `Client`(clientID) ON DELETE SET NULL
    */
	
	
	private Integer requestID;
	private String startDate;
    private String computerTechType;
    private String problemDescryption;
    private String requestStatus;
    private String completionDate;
    private String repairParts;
    
    private Integer masterID;
    private Integer clientID;
    
	public Request() {
		super();
	}
	
	public Request(Integer requestID, String startDate, String computerTechType, String problemDescryption,
			String requestStatus, String completionDate, String repairParts, Integer masterID, Integer clientID) {
		super();
		this.requestID = requestID;
		this.startDate = startDate;
		this.computerTechType = computerTechType;
		this.problemDescryption = problemDescryption;
		this.requestStatus = requestStatus;
		this.completionDate = completionDate;
		this.repairParts = repairParts;
		this.masterID = masterID;
		this.clientID = clientID;
	}
    
    public Integer getRequestID() {
		return requestID;
	}
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getComputerTechType() {
		return computerTechType;
	}
	public void setComputerTechType(String computerTechType) {
		this.computerTechType = computerTechType;
	}
	public String getProblemDescryption() {
		return problemDescryption;
	}
	public void setProblemDescryption(String problemDescryption) {
		this.problemDescryption = problemDescryption;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getRepairParts() {
		return repairParts;
	}
	public void setRepairParts(String repairParts) {
		this.repairParts = repairParts;
	}
	public Integer getMasterID() {
		return masterID;
	}
	public void setMasterID(Integer masterID) {
		this.masterID = masterID;
	}
	public Integer getClientID() {
		return clientID;
	}
	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}
    
}
