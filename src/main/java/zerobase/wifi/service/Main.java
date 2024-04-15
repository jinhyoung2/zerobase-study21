package zerobase.wifi.service;

public class Main {
    public static void main(String[] args) {
        WifiApiComponent wifiApiComponent = new WifiApiComponent();
        int pageIndex = 1;
        int wifiInfoCount = wifiApiComponent.getWifiInfoCount(pageIndex);
        System.out.println("저장된 와이파이 정보 개수: " + wifiInfoCount);
    }
}
