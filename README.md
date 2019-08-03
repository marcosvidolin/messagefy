# Messagefy

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/79d4d0cf623e4d34ae92b39becfd231c)](https://www.codacy.com/app/marcosvidolin/messagefy?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosvidolin/messagefy&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/marcosvidolin/messagefy.svg?branch=master)](https://travis-ci.org/marcosvidolin/messagefy)

This is a very small helper java library to easily build a JavaMail MIME object (javax.mail.internet.MimeMessage).

To add a dependency on Messagefy using Maven, use the following:

```xml
<dependency>
  <groupId>com.vidolima</groupId>
  <artifactId>messagefy</artifactId>
  <version>1.0.0</version>
  <type>jar</type>
</dependency>
```

To add a dependency using Gradle:
```
dependencies {
  compile 'com.vidolima:messagefy:1.0.0'
}
```


## Getting started

Example:

```java
 Messagefy messagefy = new Messagefy.Builder()
       .from("sender@sender.com")
       .to("vidolima@vidolima.com")
       .subject("Messagefy")
       .content("Hello Messagefy!")
       .build();
       
javaMailSender.send(messagefy.getMailMessage());
```

## More Options

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


## License
Messagefy is released under the terms of the [MIT License](http://opensource.org/licenses/MIT).
