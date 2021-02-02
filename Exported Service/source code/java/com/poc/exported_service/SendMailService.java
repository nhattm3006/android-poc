package com.poc.exported_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailService extends IntentService {

    public static final String SENDMAIL = "send gmail";
    public static final String RESULT = "result";

    public SendMailService() {
        super("SendMail");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String gmail = intent.getStringExtra("gmail");
        String subject = intent.getStringExtra("subject");
        String content = intent.getStringExtra("content");
        sendMail(gmail, subject, content);
    }

    protected void sendMail(String mail, String subject, String content) {
        final String username = "temp300699@gmail.com";
        final String password = "nhat3006";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail));
            message.setSubject("Sent from MobileApp: " + subject);
            message.setText("Message:"
                    + "\n\n"+ content);

            new SendMailService.SendMailTask().execute(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private class SendMailTask extends AsyncTask<Message,String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch(SendFailedException ee) {
                return "SendFailedException";
            } catch (MessagingException e) {
                return "MessagingException";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Success")) {
                super.onPostExecute(result);
                Log.d("SendMailService", "Mail sent successfully");
            }
            else if (result.equals("SendFailedException"))
                Log.d("SendMailService", "Email sent failure");
            else if (result.equals("MessagingException"))
                Log.d("SendMailService", "Email sent error");
        }
    }
}
