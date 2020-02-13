package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.mail.MailMessage;
import org.springframework.mail.MailParseException;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;

@SuppressWarnings("serial")
@Entity
public class CorpoEmail extends EntidadeBase<Long> implements MailMessage {

	@Override
	public void setFrom(String from) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReplyTo(String replyTo) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTo(String to) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTo(String... to) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCc(String cc) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCc(String... cc) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBcc(String bcc) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBcc(String... bcc) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSentDate(Date sentDate) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSubject(String subject) throws MailParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setText(String text) throws MailParseException {
		// TODO Auto-generated method stub

	}

}
