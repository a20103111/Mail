<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	int width = 62, height = 22;

	if (request.getParameter("width") != null) {
		try {
			width = Integer.parseInt(request.getParameter("width"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	BufferedImage buffImg = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);//定义一个可访问图像数据缓冲区的 Image（画布）

	Graphics2D g = buffImg.createGraphics();//实例化画笔，拿到这块画布的画笔

	g.setColor(Color.WHITE);//设置画笔的背景为白色
	g.fillRect(0, 0, width, height);//设置画布为一个矩形并设置长宽，填充指定的矩形

	//设置字体样式
	Font font = new Font("Times New Roman", Font.PLAIN, 18);
	g.setFont(font);

	g.setColor(Color.BLACK);//设置画笔的颜色为黑色
	g.drawRect(0, 0, width - 1, height - 1);//在画布上画一个黑色的矩形
	g.setColor(Color.GRAY);

	Random random = new Random();

	//设置40条干扰线
	for (int i = 0; i < 10; i++) {
		int x1 = random.nextInt(width);//设置一个0-width的随机数，做干扰线的起始x坐标
		int y1 = random.nextInt(height);//设置一个0-height的随机，做干扰线的起始y坐标
		int x2 = random.nextInt(10);//设置0-10的随机数，用于长度
		int y2 = random.nextInt(10);

		g.drawLine(x1, y1, x1 + x2, y1 + y2);//用画笔画出一条灰色的线
	}

	int length = 4; //设置默认生成4个长度的验证码，如果有请求参数就按参数的值
	if (request.getParameter("length") != null) {
		try {
			length = Integer.parseInt(request.getParameter("length"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	//待选的字母
	String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	String[] fontNames = { "宋体", "华文繁体", "黑体", "微软雅黑", "楷体_GB2312" };//待选的样式
	StringBuffer randomCode = new StringBuffer();
	for (int i = 0; i < length; i++) {//开始创作验证码
		int index = random.nextInt(codes.length());
		//String strRand = String.valueOf(random.nextInt(10));//随机出验证码
		String strRand = codes.charAt(index) + "";//改进版:随机出验证码
		//g.setColor(Color.RED);//取得固定的红色
		g.setColor(new Color(random.nextInt(150), random.nextInt(150),
				random.nextInt(150)));//改进版，获得随机颜色
/* 		g.setFont(new Font(fontNames[random.nextInt(fontNames.length)],
				random.nextInt(4), random.nextInt(5) + 24));//改进版：每个验证码随机字体样式，随机大小(fontname,style,size) */
		g.drawString(strRand, i * 1.0F * width / 4, 16);//画出该字体内容与坐标（横坐标改进）
		randomCode.append(strRand);//把字的内容加入到容器中，后面再传回前端，用作判断
	}

	buffImg.flush();//把画笔画在流里的内容刷新
	g.dispose();//刷新一次画布

	session.setAttribute("randomCode", randomCode.toString());//把画板验证码的内容传回前端，用于判断
	System.out.println(randomCode.toString());

	response.setContentType("image/jpeg");

	//设置这三条响应头：禁止浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	ServletOutputStream outputStream = response.getOutputStream();
	ImageIO.write(buffImg, "jpeg", outputStream);//把画布的内容写到浏览器

	outputStream.close();

	out.clear();
	out = pageContext.pushBody();
%>

