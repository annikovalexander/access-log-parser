public class UserAgent {
    private final String osType;
    private final String browserType;

    public UserAgent(String userAgentString) {
        this.osType = extractOsType(userAgentString);
        this.browserType = extractBrowserType(userAgentString);
    }

    public String getOsType() {
        return osType;
    }

    public String getBrowserType() {
        return browserType;
    }

    private String extractOsType(String userAgentString) {
        if (userAgentString.contains("Windows")) {
            return "Windows";
        } else if (userAgentString.contains("Mac OS X")) {
            return "macOS";
        } else if (userAgentString.contains("Linux")) {
            return "Linux";
        } else {
            return "Other";
        }
    }

    private String extractBrowserType(String userAgentString) {
        if (userAgentString.contains("Edg/")) {
            return "Edge";
        } else if (userAgentString.contains("Firefox")) {
            return "Firefox";
        } else if (userAgentString.contains("Chrome")) {
            return "Chrome";
        } else if (userAgentString.contains("Opera")) {
            return "Opera";
        } else {
            return "Other";
        }
    }
}