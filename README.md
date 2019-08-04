# Messagefy

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/79d4d0cf623e4d34ae92b39becfd231c)](https://www.codacy.com/app/marcosvidolin/messagefy?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosvidolin/messagefy&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/marcosvidolin/messagefy.svg?branch=master)](https://travis-ci.org/marcosvidolin/messagefy)
[![Download](https://api.bintray.com/packages/marcosvidolin/maven/messagefy/images/download.svg?version=v1.1.1) ](https://bintray.com/marcosvidolin/maven/messagefy/v1.1.1/link)

This is a very small helper java library to easily build a JavaMail MIME object (javax.mail.internet.MimeMessage).

## To add Messagefy on your project:

Messagefy is release by publishing in to the JCenter. So add the following configuration to your "build.gradle".

```gradle
repositories {
    ...
    jcenter()
}
```

Maven:

```xml
<dependency>
	<groupId>com.vidolima</groupId>
	<artifactId>messagefy</artifactId>
	<version>1.1.1</version>
	<type>pom</type>
</dependency>
```

Gradle:
```gradle
dependencies {
  compile 'com.vidolima:messagefy:1.1.1'
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
| from                | String               | The sender e-mail. |
| senderName          | String               | The sender name. |
| subject             | String               | The Message's subject. |
| toList              | Collection<String>   | List of "To" (primary) recipients. |

## License
Messagefy is released under the terms of the [MIT License](http://opensource.org/licenses/MIT).

## Contributors
[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/0)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/0)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/1)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/1)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/2)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/2)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/3)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/3)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/4)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/4)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/5)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/5)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/6)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/6)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/images/7)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/messagefy/links/7)
