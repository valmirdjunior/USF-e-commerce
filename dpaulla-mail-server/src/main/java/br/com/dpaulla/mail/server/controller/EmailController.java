package br.com.dpaulla.mail.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.dpaulla.mail.server.model.dto.CadastroResponse;
import br.com.dpaulla.mail.server.model.dto.OrderResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmailController {
	
	@Value("${app.mail.contact.from}")
	private String fromEmail;
	
	@Value("${app.mail.contact.passwd}")
	private String password;
	
	private String subject;
	
	@Value("${app.mail.contact.smtp}")
	private String smtp;
	
	@Value("${app.mail.contact.port}")
	private String port;
	
	@Value("${app.mail.contact.authentication}")
	private String auth;
	
	@Value("${app.mail.contact.ssl-enable}")
	private String sslEnable;
	
	@Value("${app.mail.contact.ssl-version}")
	private String sslVersion;
	
	private String body;
	
	@RequestMapping(value = "/mail/cadastro",  method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<CadastroResponse>> ConectorController(@RequestBody CadastroResponse alerts) 
			throws MessagingException, IOException{ 
		DeferredResult<ResponseEntity<CadastroResponse>> response = new DeferredResult<ResponseEntity<CadastroResponse>>();
		ResponseEntity<CadastroResponse> responseEntityAlert = null;
		responseEntityAlert.status(HttpStatus.ACCEPTED);
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", sslEnable);
		props.setProperty("mail.smtp.ssl.protocols", sslVersion);

		props.put("mail.smtp.from", fromEmail);
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		
		subject = "Seja Bem Vindo!";
		body = "<!DOCTYPE html> <html><meta charset=\\\"UTF-8\\\"> Prezado(a)  "+ alerts.getUser().getNome() + ", seja bem vindo ao DPaulla Cosméticos." + 
				"<br><br>" + 
				"Seu e-mail foi cadastrado com sucesso em nossa plataforma. <br><br>"
				+ "Aproveite todas as nossas promoções, clique aqui e receba diariamente nossas ofertas e cadastros de novos produtos. <br><br>"
				+ "Qualquer dúvida, problema, reclamação ou sugestão, entre em contato conosco através desse e-mail ou por nosso <br><br>"
				+ "telefone (11) 99999-8888, estamos sempre a disposição. <br><br>"
				+ "Atenciosamente,<br><br>"
				+ "Equipe relação com clientes <br><br>"
				+ "DPaulla Cosméticos<br><br><br>" + 
				"";
		body+= "</html>";

		log.info("Montou o HTML : {}");
		
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(body, "text/html; charset=utf-8");
		
		Session session = Session.getInstance(props, auth);
        Message msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress(fromEmail));
        InternetAddress[] toAddresses = { new InternetAddress(alerts.getUser().getUsername())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
		
        msg.setSentDate(new Date());
	    
	    msg.setSubject(subject);
 
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");
 
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        msg.setContent(multipart);
 
        Transport.send(msg);

	    log.info("Email Sent Successfully!!!");
	    response.setResult(responseEntityAlert);
        
		return response;
	}
	
	@RequestMapping(value = "/mail/compra",  method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<OrderResponse>> compraController(@RequestBody OrderResponse alerts) 
			throws MessagingException, IOException{ 
		DeferredResult<ResponseEntity<OrderResponse>> response = new DeferredResult<ResponseEntity<OrderResponse>>();
		ResponseEntity<OrderResponse> responseEntityAlert = null;
		responseEntityAlert.status(HttpStatus.ACCEPTED);
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", sslEnable);
		props.setProperty("mail.smtp.ssl.protocols", sslVersion);

		props.put("mail.smtp.from", fromEmail);
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		
		subject = "Compra realizada com sucesso!";
		body = "<!DOCTYPE html> <html><meta charset=\\\"UTF-8\\\"> Prezado(a) "+ alerts.getUser().getNome() + "," + 
				"<br><br>" + 
				"Agradecemos pela preferência e pela compra. <br><br>"
			  + "Segue informações da compra:  <br><br>"
				+ "<br><br>Código:" + alerts.getOrder().getId()
				+ "<br>Código PagSeguro: " + alerts.getOrder().getOrderId()
				+ "<br>Total da Compra: " + alerts.getOrder().getOrderTotalPrice()
				+ "<br>Tipo de Pagamento: " + alerts.getOrder().getOrderPaymentType()
				+ "<br>Quantidade Parcelas: " + alerts.getOrder().getOrderPaymentAmounts()
				+ "<br>Valor por Parcelas: " + alerts.getOrder().getOrderPaymentPrinceAmount()
				+ "<br>Valor Frete: " + alerts.getOrder().getOrderShippingPrice()
				+ "<br>Status Pagamento: " + alerts.getOrder().getOrderStatus()
				+ "<br><br><br><br><br>Atenciosamente,<br><br>"
				+ "Equipe relação com clientes <br><br>"
				+ "DPaulla Cosméticos<br><br><br>" + 
				"";
		body+= "</html>";

		log.info("Montou o HTML : {}");
		
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(body, "text/html; charset=utf-8");
		
		Session session = Session.getInstance(props, auth);
        Message msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress(fromEmail));
        InternetAddress[] toAddresses = { new InternetAddress(alerts.getUser().getUsername())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
		
        msg.setSentDate(new Date());
	    
	    msg.setSubject(subject);
 
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");
 
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        msg.setContent(multipart);
 
        Transport.send(msg);

	    log.info("Email Sent Successfully!!!");
	    response.setResult(responseEntityAlert);
        
		return response;
	}
	
	@RequestMapping(value = "/mail/transacao",  method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<OrderResponse>> transacaoController(@RequestBody OrderResponse alerts) 
			throws MessagingException, IOException{ 
		DeferredResult<ResponseEntity<OrderResponse>> response = new DeferredResult<ResponseEntity<OrderResponse>>();
		ResponseEntity<OrderResponse> responseEntityAlert = null;
		responseEntityAlert.status(HttpStatus.ACCEPTED);
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", sslEnable);
		props.setProperty("mail.smtp.ssl.protocols", sslVersion);

		props.put("mail.smtp.from", fromEmail);
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		
		subject = "Status de Pedido Alterado!";
		body = "<!DOCTYPE html> <html><meta charset=\\\"UTF-8\\\"> Prezado(a) "+ alerts.getUser().getNome() + "," + 
				"<br><br>" + 
				"Houve alteração de Status no seu Pedido: " + alerts.getOrder().getId()
				+ " <br><br>O status Pagamento: " + alerts.getOrder().getOrderStatus()
			  + "<br><br> Segue informações da compra:  <br><br>"
				+ "<br><br>Código PagSeguro: " + alerts.getOrder().getOrderId()
				+ "<br>Total da Compra: " + alerts.getOrder().getOrderTotalPrice()
				+ "<br>Tipo de Pagamento: " + alerts.getOrder().getOrderPaymentType()
				+ "<br>Quantidade Parcelas: " + alerts.getOrder().getOrderPaymentAmounts()
				+ "<br>Valor por Parcelas: " + alerts.getOrder().getOrderPaymentPrinceAmount()
				+ "<br>Valor Frete: " + alerts.getOrder().getOrderShippingPrice()
				+ "<br><br><br><br><br>Atenciosamente,<br><br>"
				+ "Equipe relação com clientes <br><br>"
				+ "DPaulla Cosméticos<br><br><br>" + 
				"";
		body+= "</html>";

		log.info("Montou o HTML : {}");
		
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(body, "text/html; charset=utf-8");
		
		Session session = Session.getInstance(props, auth);
        Message msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress(fromEmail));
        InternetAddress[] toAddresses = { new InternetAddress(alerts.getUser().getUsername())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
		
        msg.setSentDate(new Date());
	    
	    msg.setSubject(subject);
 
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");
 
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        msg.setContent(multipart);
 
        Transport.send(msg);

	    log.info("Email Sent Successfully!!!");
	    response.setResult(responseEntityAlert);
        
		return response;
	}
	
}
