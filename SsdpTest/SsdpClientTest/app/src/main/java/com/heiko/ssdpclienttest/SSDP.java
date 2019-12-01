package com.heiko.ssdpclienttest;

import java.net.DatagramPacket;
import java.util.Scanner;

/**
 * SSDP
 *
 * @author EthanCo
 * @since 2018/3/12
 */
public class SSDP {
    /* New line definition */
    public static final String NEWLINE = "\r\n";
    public static final String ADDRESS = "239.255.255.250";
    public static final int PORT = 1900;
    public static final String ST = "ST";
    public static final String LOCATION = "LOCATION";
    public static final String NT = "NT";
    public static final String NTS = "NTS";
    /* Definitions of start line */
    public static final String SL_NOTIFY = "NOTIFY * HTTP/1.1";
    public static final String SL_MSEARCH = "M-SEARCH * HTTP/1.1";
    public static final String SL_OK = "HTTP/1.1 200 OK";

    @SuppressWarnings("resource")
    public static String parseHeaderValue(String content, String headerName) {
        Scanner s = new Scanner(content);
        s.nextLine(); // Skip the start line
        while (s.hasNextLine()) {
            String line = s.nextLine();
            int index = line.indexOf(':');
            String header = line.substring(0, index);
            if (headerName.equalsIgnoreCase(header.trim())) {
                return line.substring(index + 1).trim();
            }
        }
        return null;
    }

    public static String parseHeaderValue(DatagramPacket dp, String headerName) {
        return parseHeaderValue(new String(dp.getData()), headerName);
    }

    @SuppressWarnings("resource")
    public static String parseStartLine(String content) {
        Scanner s = new Scanner(content);
        return s.nextLine();
    }

    public static String parseStartLine(DatagramPacket dp) {
        return parseStartLine(new String(dp.getData()));
    }
}
