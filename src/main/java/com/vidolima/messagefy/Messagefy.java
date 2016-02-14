package com.vidolima.messagefy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * This is a helper class to easily build a {@link MimeMessage}. <br>
 * How to:
 * 
 * <pre>
 * <code>
 * Messagefy messagefy = new Messagefy.Builder()
 * 		.sender("sender@sender.com").to("vidolima@vidolima.com")
 * 		.subject("Messagefy")
 * 		.content("Hello Messagefy!")
 * 		.build();
 * 
 * javaMailSender.send(messagefy.getMailMessage());
 * </code>
 * </pre>
 * 
 * @since Jun 16, 2015
 * @author <a href="mailto:marcosvidolin@gmail.com">Marcos Alexandre Vidolin de
 *         Lima</a>
 */
public class Messagefy {

	// TODO: permitir multiplos anexos

	private final String DEFAULT_CONTENT_TYPE = "text/html";
	private final String DEFAULT_ATTACHMENT_NAME = "Untitled";

	private final String sender;
	private final String senderName;
	private final Collection<String> toList;
	private final Collection<String> ccList;
	private final String content;
	private final String contentType;
	private final String subject;
	private final String subjectCharset;
	private final byte[] attachment;
	private final String attachmentName;
	private final String attachmentMimeType;

	public Collection<String> getToList() {
		return this.toList;
	}

	public Address[] getToAddress() throws AddressException {
		if (this.toList == null)
			return new Address[0];

		// just converts the object type of the List to avoid
		// ArrayStoreException
		List<Address> address = new ArrayList<Address>();
		for (String to : this.toList) {
			address.add(new InternetAddress(to));
		}

		return address.toArray(new Address[this.toList.size()]);
	}

	public Collection<String> getCcList() {
		return this.ccList;
	}

	public Address[] getCcAddress() throws AddressException {
		if (this.ccList == null)
			return new Address[0];

		// just converts the object type of the List to avoid
		// ArrayStoreException
		Collection<Address> address = new ArrayList<Address>();
		for (String cc : this.ccList) {
			address.add(new InternetAddress(cc));
		}

		return address.toArray(new Address[this.ccList.size()]);
	}

	public String getSender() {
		return sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getContent() {
		return this.content;
	}

	public String getContentType() {
		if (this.contentType == null || this.contentType.isEmpty())
			return DEFAULT_CONTENT_TYPE;
		return this.contentType;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getSubjectCharset() {
		return this.subjectCharset;
	}

	public byte[] getAttachment() {
		return this.attachment;
	}

	public String getAttachmentName() {
		if (this.attachmentName == null || this.attachmentName.isEmpty())
			return DEFAULT_ATTACHMENT_NAME;
		return this.attachmentName;
	}

	public String getAttachmentMimeType() {
		return this.attachmentMimeType;
	}

	public boolean hasAttachment() {
		return this.getAttachment() != null;
	}

	/**
	 * Returns the text as {@link MimeBodyPart} for attachment.
	 * 
	 * @return {@link MimeBodyPart}
	 * @throws MessagingException
	 */
	private MimeBodyPart getTextMimeBodyPart() throws MessagingException {
		final MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent("", "text/plain");
		return mimeBodyPart;
	}

	/**
	 * Returns the attachment as {@link MimeBodyPart}.
	 * 
	 * @return {@link MimeBodyPart}
	 * @throws MessagingException
	 */
	private MimeBodyPart getAttachmentMimeBodyPart() throws MessagingException {
		DataSource dataSource = new ByteArrayDataSource(this.getAttachment(), this.getAttachmentMimeType());
		final MimeBodyPart pdfBodyPart = new MimeBodyPart();
		pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		pdfBodyPart.setFileName(this.getAttachmentName());
		return pdfBodyPart;
	}

	/**
	 * Returns a generic mime type part.
	 * 
	 * @return {@link MimeBodyPart}
	 * @throws MessagingException
	 */
	private MimeBodyPart getGenericMimeBodyPart() throws MessagingException {
		final MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setContent(this.getContent(), this.getContentType());
		return textBodyPart;
	}

	/**
	 * Build the message.
	 * 
	 * @return {@link MimeMessage}
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private MimeMessage getMimeMessage() throws MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		// construct the mime multi part
		Multipart mimeMultipart = new MimeMultipart("alternative");
		mimeMultipart.addBodyPart(this.getGenericMimeBodyPart());
		if (this.hasAttachment()) {
			mimeMultipart.addBodyPart(this.getTextMimeBodyPart());
			mimeMultipart.addBodyPart(this.getAttachmentMimeBodyPart());
		}

		// the message
		MimeMessage message = new MimeMessage(session);
		message.setSender(new InternetAddress(this.getSender(), this.getSenderName()));
		message.setSubject(this.getSubject());
		message.addRecipients(Message.RecipientType.TO, this.getToAddress());
		message.addRecipients(Message.RecipientType.CC, this.getCcAddress());
		message.setSentDate(new Date());
		message.setContent(mimeMultipart);

		return message;
	}

	/**
	 * Sends the e-mail.
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public MimeMessage getMailMessage() throws UnsupportedEncodingException, MessagingException {
		return this.getMimeMessage();
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private Messagefy(Builder builder) {
		this.sender = builder.sender;
		this.senderName = builder.senderName;
		this.toList = builder.toList;
		this.ccList = builder.ccList;
		this.content = builder.content;
		this.contentType = builder.contentType;
		this.subject = builder.subject;
		this.subjectCharset = builder.subjectCharset;
		this.attachment = builder.attachment;
		this.attachmentName = builder.attachmentName;
		this.attachmentMimeType = builder.attachmentMimeType;
	}

	/**
	 * Inner class. EmailSender's builder.
	 */
	public static class Builder {

		private String sender;
		private String senderName;
		private Collection<String> toList;
		private Collection<String> ccList;
		private String content;
		private String contentType;
		private String subject;
		private String subjectCharset;
		private byte[] attachment;
		private String attachmentName;
		private String attachmentMimeType;

		public Builder from(final String sender) {
			this.sender = sender;
			return this;
		}

		public Builder senderName(final String senderName) {
			this.senderName = senderName;
			return this;
		}

		public Builder to(final String to) {
			if (toList == null)
				this.toList = new ArrayList<String>();
			this.toList.add(to);
			return this;
		}

		public Builder cc(final String cc) {
			if (ccList == null)
				this.ccList = new ArrayList<String>();
			this.ccList.add(cc);
			return this;
		}

		public Builder toList(final Collection<String> toList) {
			this.toList = toList;
			return this;
		}

		public Builder ccList(final Collection<String> ccList) {
			this.ccList = ccList;
			return this;
		}

		public Builder content(final String content) {
			this.content = content;
			return this;
		}

		/**
		 * Obs.: The default content is "text/html".
		 * 
		 * @param contentType
		 * @return
		 */
		public Builder contentType(final String contentType) {
			this.contentType = contentType;
			return this;
		}

		public Builder subject(final String subject) {
			this.subject = subject;
			return this;
		}

		public Builder subjectCharset(final String subjectCharset) {
			this.subjectCharset = subjectCharset;
			return this;
		}

		public Builder attachment(final byte[] attachment) {
			this.attachment = attachment;
			return this;
		}

		public Builder attachmentName(final String attachmentName) {
			this.attachmentName = attachmentName;
			return this;
		}

		public Builder attachmentMimeType(final String attachmentMimetype) {
			this.attachmentMimeType = attachmentMimetype;
			return this;
		}

		/**
		 * Return an instance of #{EmailSender}
		 */
		public Messagefy build() {
			return new Messagefy(this);
		}
	}
}