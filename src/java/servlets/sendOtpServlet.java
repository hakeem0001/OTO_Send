
package servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClass.Send_OTP;

@WebServlet(name = "sendOtpServlet", urlPatterns = {"/sendOtpServlet"})
public class sendOtpServlet extends HttpServlet
{
private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

   
//@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otp = generateOTP(); // Generate OTP
        String message = "Your OTP is: Please Dont Share it " + otp;

        // Send OTP via Telegram
        boolean sent = false;
    try {
        sent = Send_OTP.sendTelegramMessage(message);
           System.out.println("After Get Code ="+sent);
        } 
    catch (URISyntaxException ex) 
        {
        Logger.getLogger(Send_OTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (sent) 
        {
            request.setAttribute("status", "OTP sent successfully to Telegram.");
        } 
        else 
        {
            request.setAttribute("status", "Failed to send OTP. Please try again.");
        }

        // Forward response to JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
   
}
