package br.com.softports.application.infrastructure.service;

import br.com.softports.core.api.common.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceDefault implements EmailService {

    private final JavaMailSender javaMailSender;
    private final MailProperties properties;

    @Override
    public void sendEmail(String destinatario, String comCopia, String assunto, String conteudoHtml) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, properties.getDefaultEncoding().toString());
            helper.setFrom(properties.getUsername());
            helper.setTo(destinatario);
            helper.setCc(comCopia);
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Não foi possível enviar o e-mail da ocorrência");
        }
    }
}