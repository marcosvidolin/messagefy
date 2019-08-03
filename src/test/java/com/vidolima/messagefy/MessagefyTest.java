package com.vidolima.messagefy;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MessagefyTest {

  @Test
  public void test_build_withValidAttributes_mustPass() {
    byte[] fakeAttachment = "fakeAttachmentFile".getBytes();
    Messagefy messagefy = new Messagefy.Builder()
        .from("messagefy@vidolima.com")
        .senderName("Messagefy Library")
        .to("vidolin@vidolima.com")
        .ccList(Arrays.asList("test2@vidolima.com", "test3@vidolima.com"))
        .subject("Messagefy!!")
        .subjectCharset("utf-8")
        .content("Hello Messagefy!")
        .contentType("text/plain")
        .attachmentName("AttFileName")
        .attachment(fakeAttachment)
        .attachmentMimeType("multipart/byteranges")
        .build();

    Assert.assertEquals(messagefy.getSender(), "messagefy@vidolima.com");
    Assert.assertEquals(messagefy.getSenderName(), "Messagefy Library");
    Assert.assertEquals(messagefy.getCcList().size(), 2);
    Assert.assertEquals(messagefy.getToList().size(), 1);
    Assert.assertEquals(messagefy.getToList().toArray()[0], "vidolin@vidolima.com");
    Assert.assertEquals(messagefy.getSubject(), "Messagefy!!");
    Assert.assertEquals(messagefy.getSubjectCharset(), "utf-8");
    Assert.assertEquals(messagefy.getContent(), "Hello Messagefy!");
    Assert.assertEquals(messagefy.getContentType(), "text/plain");
    Assert.assertEquals(messagefy.getAttachmentName(), "AttFileName");
    Assert.assertEquals(messagefy.getAttachment(), fakeAttachment);
    Assert.assertEquals(messagefy.getAttachmentMimeType(), "multipart/byteranges");

  }

  @Test(expected = IllegalArgumentException.class)
  public void test_build_withoutFrom_mustThrowException() {
    new Messagefy.Builder()
        .to("vidolima@vidolima.com")
        .subject("Messagefy")
        .content("Hello Messagefy!")
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_build_withoutTo_mustThrowException() {
    new Messagefy.Builder()
        .from("vidolima@vidolima.com")
        .subject("Messagefy")
        .content("Hello Messagefy!")
        .build();
  }
}
