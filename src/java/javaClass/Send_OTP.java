package javaClass;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Send_OTP {
 
    private static final String BOT_TOKEN = "7577436350:AAGgubVKSMspuszb4gn3nfPVEWxaoJ7QEqU"; // Replace with your bot token
    private static final String CHAT_ID = "273700910"; // Replace with recipient chat ID
    public static boolean sendTelegramMessage(String message) throws URISyntaxException 
    {
        System.out.println("Inside SendOTP ");
        HttpURLConnection conn = null; // Declare connection outside try block
        try {
            // Build the Telegram API URL safely using URI
            URI uri = new URI("https", "api.telegram.org", "/bot" + BOT_TOKEN + "/sendMessage", null);
            URL url = uri.toURL();
            System.out.println("Build the Telegram API URL safely using URI ");
            // Open HTTP connection
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // JSON Payload
            String jsonPayload = String.format("{\"chat_id\": \"%s\", \"text\": \"%s\"}", CHAT_ID, message);

            // Send the payload
             System.out.println("Send the payload ");
            try (OutputStream os = conn.getOutputStream())
            {
                System.out.println("inside Try 1");
                os.write(jsonPayload.getBytes("utf-8"));
            }

            // Check response
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode); // Debugging output
            System.out.println("Check response");
            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (IOException | IllegalArgumentException e)
        { // Print the exception for better debugging
            // Print the exception for better debugging
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect(); // Ensure connection is closed
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        // Example usage
        String message = "Hello, this is your OTP: 123456";
        boolean result = sendTelegramMessage(message);

        if (result) {
            System.out.println("OTP sent successfully!");
        } else {
            System.out.println("Failed to send OTP. Please try again.");
        }
    }
}
