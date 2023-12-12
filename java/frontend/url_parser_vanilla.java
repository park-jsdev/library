// This implementation does not use the url object, and reads the string, but may not handle all edge cases.

public class URLParser {

    /**
     * Extracts the value of a query parameter from a URL string.
     *
     * @param urlString     The URL string.
     * @param parameterName The name of the query parameter to extract.
     * @return The value of the query parameter or null if not found.
     */
    public static String getQueryParamValue(String urlString, String parameterName) {
        // Check if the URL contains '?', indicating the start of query parameters
        int queryStartIndex = urlString.indexOf('?');
        if (queryStartIndex == -1) {
            return null; // No query parameters
        }

        // Extract the query string
        String queryString = urlString.substring(queryStartIndex + 1);

        // Split the query string into individual parameters
        String[] params = queryString.split("&");

        // Loop through the parameters to find the desired one
        for (String param : params) {
            if (param.startsWith(parameterName + "=")) {
                // Extract and return the parameter value
                return param.substring(parameterName.length() + 1);
            }
        }

        return null; // Parameter not found
    }

    public static void main(String[] args) {
        String url = "http://example.com/path/?token=myToken&otherParam=value";
        String token = getQueryParamValue(url, "token");
        System.out.println("Token: " + token); // Outputs: myToken
    }
}
