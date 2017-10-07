package com.Mail.Util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMailUtil {
	
	
	/*组装复杂组件,添加附件*/
	public static Multipart addAttachment(Multipart multipart,ArrayList<String> filePaths){
		
		int index=1;
		
		for(String filePath:filePaths){
			
			System.out.println("附件路径:"+filePath);
			
			String fileName = filePath.substring(filePath.lastIndexOf("\\")+1); //在文件路径中获取文件名
			System.out.println("附件文件名:"+fileName);
			
			BodyPart attach = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(filePath);
			DataHandler dataHandler = new DataHandler(dataSource);

			try {
				attach.setDataHandler(dataHandler);
				attach.setFileName(MimeUtility.encodeText(fileName)); //为附件设置文件名
				multipart.addBodyPart(attach);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return multipart;
	}

}
