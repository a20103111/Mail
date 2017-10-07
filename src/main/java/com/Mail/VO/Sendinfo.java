package com.Mail.VO;

import java.util.ArrayList;

//发送邮件的bean
public class Sendinfo {
	
	private String email_subject; //邮件主题
	private String email_from;//邮件发信人
	private String email_recipientTo;//邮件收件人，可多个
	private String email_recipientCC;//邮件抄送人，可多个
	private String email_recipientBCC;//邮件暗送人，可多个
	private String email_repaly;//邮件回复人，可多个
	private String email_content="";//邮件本体内容
	private ArrayList<String> email_attachments; //附件路径
	
	public Sendinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sendinfo(String email_subject, String email_from,
					String email_recipientTo, String email_recipientCC,
					String email_recipientBCC, String email_repaly, String email_content) {
		super();
		this.email_subject = email_subject;
		this.email_from = email_from;
		this.email_recipientTo = email_recipientTo;
		this.email_recipientCC = email_recipientCC;
		this.email_recipientBCC = email_recipientBCC;
		this.email_repaly = email_repaly;
		this.email_content = email_content;
	}
	
	

	public String getEmail_subject() {
		return email_subject;
	}

	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}

	public String getEmail_from() {
		return email_from;
	}

	public void setEmail_from(String email_from) {
		this.email_from = email_from;
	}

	public String getEmail_recipientTo() {
		return email_recipientTo;
	}

	public void setEmail_recipientTo(String email_recipientTo) {
		this.email_recipientTo = email_recipientTo;
	}

	public String getEmail_recipientCC() {
		return email_recipientCC;
	}

	public void setEmail_recipientCC(String email_recipientCC) {
		this.email_recipientCC = email_recipientCC;
	}

	public String getEmail_recipientBCC() {
		return email_recipientBCC;
	}

	public void setEmail_recipientBCC(String email_recipientBCC) {
		this.email_recipientBCC = email_recipientBCC;
	}

	public String getEmail_repaly() {
		return email_repaly;
	}

	public void setEmail_repaly(String email_repaly) {
		this.email_repaly = email_repaly;
	}

	public String getEmail_content() {
		return email_content;
	}

	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}

	public ArrayList<String> getEmail_attachments() {
		return email_attachments;
	}

	public void setEmail_attachments(ArrayList<String> email_attachments) {
		this.email_attachments = email_attachments;
	}
	
	
	
}
