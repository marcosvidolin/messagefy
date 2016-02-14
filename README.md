#Messagefy#

This is a very small helper java library to easily build a JavaMail MIME object (javax.mail.internet.MimeMessage).


# Getting started

Example:

```java
 Messagefy messagefy = new Messagefy.Builder()
       .sender("sender@sender.com")
       .to("vidolima@vidolima.com")
       .subject("Messagefy")
       .content("Hello Messagefy!")
       .build();
       
javaMailSender.send(messagefy.getMailMessage());
```

# More Options

| Attribute           | Type                 | Description |
| :-------------------| :------------------- | :---------- |
| attachment          | byte[]               | The file to be attached to the message. |  
| attachmentMimeType  | String               | Attachment MIME type. |
| attachmentName      | String               | Attachment name. Default name is "Untitled". |
| ccList              | Collection<String>   | List of "Cc" (carbon copy) recipients. |
| content             | String               | The Message's content to a Multipart object. |
| contentType         | String               | The Message's Content Type. Default content type is "text/html". |
| sender              | String               | The sender e-mail. |
| senderName          | String               | The sender name. |
| subject             | String               | The Message's subject. |
| toList              | Collection<String>   | List of "To" (primary) recipients. |


# TODO
* Multiples attachments
* Tests

# License
Messagefy is released under the terms of the [MIT License](http://opensource.org/licenses/MIT).