package step.step2;
import java.sql.Timestamp;

public class WifiInfo {
    private String managerNumber;
    private String borough;
    private String wifiName;
    private String roadAddress;
    private String detailAddress;
    private String installationFloor;
    private String installationType;
    private String installationAgency;
    private String serviceType;
    private String networkType;
    private int installationYear;
    private String indoorOutdoorType;
    private String wifiConnectionEnvironment;
    private double latitude;
    private double longitude;
    private Timestamp workDate;
    
	public String getManagerNumber() {
		return managerNumber;
	}
	public String getBorough() {
		return borough;
	}
	public String getWifiName() {
		return wifiName;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public String getInstallationFloor() {
		return installationFloor;
	}
	public String getInstallationType() {
		return installationType;
	}
	public String getInstallationAgency() {
		return installationAgency;
	}
	public String getServiceType() {
		return serviceType;
	}
	public String getNetworkType() {
		return networkType;
	}
	public int getInstallationYear() {
		return installationYear;
	}
	public String getIndoorOutdoorType() {
		return indoorOutdoorType;
	}
	public String getWifiConnectionEnvironment() {
		return wifiConnectionEnvironment;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public Timestamp getWorkDate() {
		return workDate;
	}
	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}
	public void setBorough(String borough) {
		this.borough = borough;
	}
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public void setInstallationFloor(String installationFloor) {
		this.installationFloor = installationFloor;
	}
	public void setInstallationType(String installationType) {
		this.installationType = installationType;
	}
	public void setInstallationAgency(String installationAgency) {
		this.installationAgency = installationAgency;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public void setInstallationYear(int installationYear) {
		this.installationYear = installationYear;
	}
	public void setIndoorOutdoorType(String indoorOutdoorType) {
		this.indoorOutdoorType = indoorOutdoorType;
	}
	public void setWifiConnectionEnvironment(String wifiConnectionEnvironment) {
		this.wifiConnectionEnvironment = wifiConnectionEnvironment;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setWorkDate(Timestamp workDate) {
		this.workDate = workDate;
	}
}