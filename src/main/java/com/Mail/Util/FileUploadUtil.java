package com.Mail.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class FileUploadUtil {
	
	/*把表单的附件都保存到一个用户的专属文件夹并返回对应的信息用于发送信息*/
	public static Map<String,Object> saveAttachments(String realWebBase,HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		
		Map<String, Object> information = new HashMap<String, Object>();
		ArrayList<String> attachments = new ArrayList<String>();
		
		
		DiskFileItemFactory dfif = new DiskFileItemFactory(); //硬盘文件工厂,对临时保存进行保存
		dfif.setSizeThreshold(4096);
		
		
		//设置存放临时文件的目录
		File temp_file = new File(realWebBase+"/upload/UploadTemp");
		if(!temp_file.exists()){
			temp_file.mkdirs();
		}
		dfif.setRepository(temp_file);
				
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setHeaderEncoding("UTF-8");
		
		List  fileList = null;
		try{
			fileList = sfu.parseRequest(request);//从request得到所有上传域的列表
			System.out.println(fileList.size());

		}catch (Exception e) {

			e.printStackTrace();
			
		}
		
		Iterator fileItr = fileList.iterator();
		
		while(fileItr.hasNext()){//上传域
			FileItem fileItem = (FileItem)fileItr.next(); //得到当前文件
			
			if(fileItem.isFormField()){//如果item是普通文本，就获取其name与value
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				System.out.println("表单域名为:"+name+"表单域值为:"+value);

				if("email_subject".equals(name)){
					information.put("email_subject", value);
				}else if("email_recipientTo".equals(name)){
					information.put("email_recipientTo", value);
				}else if("email_content".equals(name)){
					information.put("email_content", value);
				}
				else if("email_from".equals(name)){

					information.put("email_from", value);


				}

				
			}else{
				
				String path = fileItem.getName();
				
				if(path==null||"".equals(path)){
					System.out.println("不保存空文件");
					continue;
				}
				
				System.out.println("文件路径:"+path);
				
				//attachments.add(path);
				
				long size = fileItem.getSize();
				
				//得到取出路径的文件名
				String t_name = path.substring(path.lastIndexOf("\\")+1);
				System.out.println("文件名:"+t_name);
				//得到文件的扩展名(无扩展名则得到全名)
				String t_ext = t_name.substring(t_name.lastIndexOf(".")+1).toLowerCase();
				System.out.println("扩展名:"+t_ext);

				//业务名:日期，类型(附件)，用户
				String date = new SimpleDateFormat("YYYY.MM.dd").format(new Date()); //日期
				String  type = "Attachments"; 
				

				HttpSession session = request.getSession();
				String user = (String)session.getAttribute("username");
				
				//保存的最终文件完整路径(服务器端)
				String u_path = realWebBase+"upload\\"+type+"\\"+user+"\\"+date;
				String u_name = realWebBase+"upload\\"+type+"\\"+user+"\\"+date+"\\"+t_name;
				System.out.println("文件保存路径:"+u_name);
				
				attachments.add(u_name);
				
				try{//保存文件
					File today_file = new File(u_path);//构建文件目录
					if(!today_file.exists()){
						today_file.mkdirs();
					}
					fileItem.write(new File(u_name));
					
					System.out.println("保存成功");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		information.put("attachments", attachments); 
		
		return information;
		
	}

}
