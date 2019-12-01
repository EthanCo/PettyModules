package com.heiko.ssdpservertest;

import android.text.TextUtils;

import static com.heiko.ssdpservertest.SSDP.HEADER_SEARCH;

/**
 * SSDP Parser
 *
 * @author EthanCo
 * @since 2018/3/13
 */
public class SsdpParser {
    public static SSDPBean parser(String data) {
        SSDPBean ssdpBean = new SSDPBean();
        if (data.startsWith(HEADER_SEARCH)) {
            data = data.trim();
            String[] dataArray = data.split(SSDPConstants.NEWLINE);
            for (String line : dataArray) {
                if (TextUtils.isEmpty(line)) {
                    continue;
                }
                String lineUpper = line.toUpperCase();
                if (lineUpper.startsWith(HEADER_SEARCH)) {
                    ssdpBean.setHead(line.replace(SSDPConstants.NEWLINE, ""));

                }else if (lineUpper.startsWith("HOST:")) {
                    String host = substringOptimize(line, 5);
                    ssdpBean.setHost(host);
                    String[] arr = host.split(":");
                    if (arr.length >= 2) {
                        ssdpBean.setIp(arr[0]);
                        ssdpBean.setPort(Integer.parseInt(arr[1]));
                    }

                } else if (lineUpper.startsWith("ST:".replace(SSDPConstants.NEWLINE, ""))) {
                    String st = substringOptimize(line, 3);
                    ssdpBean.setST(st);

                } else if (lineUpper.startsWith("MAN:")) {
                    String man = substringOptimize(line, 4).replace("\"", "");
                    ssdpBean.setMan(man);

                } else if (lineUpper.startsWith("MX:")) {
                    int mx = Integer.parseInt(substringOptimize(line, 3).replace(" ", ""));
                    ssdpBean.setMX(mx);
                }
            }
        }
        return ssdpBean;
    }

    private static String substringOptimize(String line, int beginIndex) {
        return line.substring(beginIndex).replace(SSDPConstants.NEWLINE, "").trim();
    }
}
