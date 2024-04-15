package ch11;
import java.sql.Timestamp;

public class WifiData {
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

    public WifiData(String managerNumber, String borough, String wifiName, String roadAddress, String detailAddress,
                    String installationFloor, String installationType, String installationAgency, String serviceType,
                    String networkType, int installationYear, String indoorOutdoorType, String wifiConnectionEnvironment,
                    double latitude, double longitude, Timestamp workDate) {
        this.managerNumber = managerNumber;
        this.borough = borough;
        this.wifiName = wifiName;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.installationFloor = installationFloor;
        this.installationType = installationType;
        this.installationAgency = installationAgency;
        this.serviceType = serviceType;
        this.networkType = networkType;
        this.installationYear = installationYear;
        this.indoorOutdoorType = indoorOutdoorType;
        this.wifiConnectionEnvironment = wifiConnectionEnvironment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workDate = workDate;
    }

    // Getter 및 Setter 메서드
    public String getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(String managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getInstallationFloor() {
        return installationFloor;
    }

    public void setInstallationFloor(String installationFloor) {
        this.installationFloor = installationFloor;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getInstallationAgency() {
        return installationAgency;
    }

    public void setInstallationAgency(String installationAgency) {
        this.installationAgency = installationAgency;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public int getInstallationYear() {
        return installationYear;
    }

    public void setInstallationYear(int installationYear) {
        this.installationYear = installationYear;
    }

    public String getIndoorOutdoorType() {
        return indoorOutdoorType;
    }

    public void setIndoorOutdoorType(String indoorOutdoorType) {
        this.indoorOutdoorType = indoorOutdoorType;
    }

    public String getWifiConnectionEnvironment() {
        return wifiConnectionEnvironment;
    }

    public void setWifiConnectionEnvironment(String wifiConnectionEnvironment) {
        this.wifiConnectionEnvironment = wifiConnectionEnvironment;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Timestamp getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }
}
