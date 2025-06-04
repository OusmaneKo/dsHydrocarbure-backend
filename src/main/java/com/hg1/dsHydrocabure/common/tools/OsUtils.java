package com.hg1.dsHydrocabure.common.tools;

import com.hg1.dsHydrocabure.AppConstants;

public class OsUtils {

    public static final int OS_TYPE_LINUX = 1;
    public static final int OS_TYPE_WINDOWS = 2;
    public static final int OS_TYPE_MAC = 3;
    public static final int OS_TYPE_SCOLARIS = 4;
    public static final int OS_TYPE_OTHERS = 5;


    public static int getOsType() {
        int osType = 0;
        String osName = System.getProperty("os.name");
        String osNameMatch = osName.toLowerCase();
        if (osNameMatch.contains("linux")) {
            osType = OS_TYPE_LINUX;
        } else if (osNameMatch.contains("windows")) {
            osType = OS_TYPE_WINDOWS;
        } else if (osNameMatch.contains("solaris")
                || osNameMatch.contains("sunos")) {
            osType = OS_TYPE_SCOLARIS;
        } else if (osNameMatch.contains("mac os")
                || osNameMatch.contains("macos")
                || osNameMatch.contains("darwin")) {
            osType = OS_TYPE_LINUX;
        } else {
            osType = OS_TYPE_OTHERS;

        }
        return osType;
    }

    public static String getOsPath(String p) {
        p = p.replaceAll("\\\\", "/");
        if (getOsType() == OS_TYPE_WINDOWS) {
            if (!p.endsWith("/")) {
                p += "/";
            }
        } else {
            if (!p.endsWith("/")) {
                p += "/";
            }
            String sch = "";

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '\'') {
                    sch = sch + p.charAt(i);
                }
            }
            p = sch;
        }

        return p;
    }

    public static String getOsHomeDir() {
        String p = "";
        if (getOsType() == OS_TYPE_WINDOWS) {
            p = "C:/dsHydrocarbure/";
        } else {
            p = "/home/dsHydrocarbure/";
        }
        return p;
    }

    public static String getTempDir() {
        //  return getOsHomeDir() + "/" + AppConstants.DIR_TEMP;
        return "";
    }
}
