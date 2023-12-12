// This implementation uses the Java URL object: https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
// It assumes well-formed urls.

public class URLParser {

    /**
     * Extracts the value of a query parameter from a URL string.
     *
     * @param url           The URL string.
     * @param parameterName The name of the query parameter to extract.
     * @return The value of the query parameter or null if not found.
     */
    public static String getQueryParamValue(String url, String parameterName) {
        int queryStartIndex = url.indexOf('?');

        if (queryStartIndex == -1) {
            return null; // No query string in URL
        }

        // Extract query string and split into parameters
        String queryString = url.substring(queryStartIndex + 1);
        String[] params = queryString.split("&");

        // Search for the specified parameter
        for (String param : params) {
            if (param.startsWith(parameterName + "=")) {
                return param.substring(parameterName.length() + 1);
            }
        }

        return null; // Parameter not found
    }

    public static void main(String[] args) {
        String url = "http://example.com/path/?token=myToken&otherParam=value";
        String token = getQueryParamValue(url, "token");
        System.out.println("Token: " + token);
    }
}
