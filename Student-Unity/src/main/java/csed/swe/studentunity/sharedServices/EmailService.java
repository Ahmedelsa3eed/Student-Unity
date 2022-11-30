package csed.swe.studentunity.sharedServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(){}




    public void send(String body, String subject, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("amr1kemon1@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
