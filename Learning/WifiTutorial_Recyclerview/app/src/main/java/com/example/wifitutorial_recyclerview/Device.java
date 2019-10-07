package com.example.wifitutorial_recyclerview;

public class Device {

    private String SSID;
    private String capabilities;
    private String title;


    public Device(String SSID, String capabilities) {
        this.SSID = SSID;
        this.capabilities = capabilities;

        this.title = SSID + " - " + capabilities;

    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getTitle() {
        return title;
    }
}
