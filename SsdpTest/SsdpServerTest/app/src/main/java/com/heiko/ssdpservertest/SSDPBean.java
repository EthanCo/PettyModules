package com.heiko.ssdpservertest;

import android.text.TextUtils;

import static com.heiko.ssdpservertest.SSDP.HEADER_NOTIFY;
import static com.heiko.ssdpservertest.SSDP.HEADER_SEARCH;

/**
 * SSDPBean
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class SSDPBean {
    private String head;
    private String host;
    private String ST;
    private String man;
    private int mx;
    private String ip;
    private int port;

    private String nt;
    private String nts;
    private String usn;
    private String location;
    private String cacheControl;


    private  String deviceType;
    private String server;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public int getMX() {
        return mx;
    }

    public void setMX(int MX) {
        this.mx = MX;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMx() {
        return mx;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getNts() {
        return nts;
    }

    public void setNts(String nts) {
        this.nts = nts;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String generateMessage() {
        StringBuilder sb = new StringBuilder();
        if (HEADER_SEARCH.startsWith(head)) {
            sb.append(head).append(SSDPConstants.NEWLINE)
                    .append("HOST:").append(host).append(SSDPConstants.NEWLINE)
                    .append("ST:").append(ST).append(SSDPConstants.NEWLINE)
                    .append("MAN:").append(man).append(SSDPConstants.NEWLINE)
                    .append("MX:").append(mx).append(SSDPConstants.NEWLINE);
        }else if(HEADER_NOTIFY.startsWith(head)){
            sb.append(head).append(SSDPConstants.NEWLINE)
                    .append("HOST:").append(host).append(SSDPConstants.NEWLINE)
                    .append("NT:").append(nt).append(SSDPConstants.NEWLINE)
                    .append("NTS:").append(nts).append(SSDPConstants.NEWLINE)
                    .append("USN:").append(usn).append(SSDPConstants.NEWLINE)
                    .append("LOCATION:").append(location).append(SSDPConstants.NEWLINE)
                    .append("CACHE-CONTROL:").append(cacheControl).append(SSDPConstants.NEWLINE)
                    .append("DEVICE_TYPE:").append(deviceType).append(SSDPConstants.NEWLINE);

            if (!TextUtils.isEmpty(server)) {
                sb.append("SERVER:").append(server).append(SSDPConstants.NEWLINE);
            }
            /*if (!TextUtils.isEmpty()) {
            }*/
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return generateMessage();
    }
}
