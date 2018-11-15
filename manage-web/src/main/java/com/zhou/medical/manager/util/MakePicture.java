package com.zhou.medical.manager.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MakePicture  //产生识别验证图像
{   
	private char charTable[]={
		'a','A','b','B','c','C','d','D' ,'e','E' ,
		'f','F','g','G','h','H','i','I','j','J' ,
		'0','1','2','3','4','5','6','7','8','9'
	}; 
	public String drawPicture(int width, int height, HttpServletRequest req, HttpServletResponse res)
	{
		if(width<=0)
			width=100 ;
		if(height<=0) 
			height=60 ;
			
		BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB) ;
		Graphics g=image.getGraphics()  ;
//		g.setColor(Color.LIGHT_GRAY) ;
		g.fillRect(0, 0, width, height)  ;
//		g.setColor(new Color(0x5265fd)) ;
//		g.drawRect(0, 0, width-1, height-1)  ;
		String str ="" ;
		for(int x=0;x<4;x++)
		{
		  str+=charTable[(int) (Math.random()*charTable.length)];
		}
		g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		g.setColor(new Color(0x9E2A30)) ;
		g.drawString(str.substring(0, 1), 5, 15);
		g.setColor(new Color(0xE40E18)) ;
		g.drawString(str.substring(1, 2), 20, 17);
		g.setColor(new Color(0x071187)) ;
		g.drawString(str.substring(2, 3), 35, 13);
		g.setColor(new Color(0x068513)) ;
		g.drawString(str.substring(3, 4), 50, 16);
		Random rand=new Random() ;
		g.setColor(Color.LIGHT_GRAY) ;
		for(int i=0;i<20;i++)
		{
		  int x=rand.nextInt(width)  ;
		  int y=rand.nextInt(height)  ;	
		  g.drawOval(x, y, 1, 1) ;
 		}
		g.dispose()  ;
		res.setDateHeader("experice", System.currentTimeMillis()+1000*180);
		req.getSession().removeAttribute("imgVcode");
		req.getSession().setAttribute("imgVcode", str) ;
		try {
			ImageIO.write(image, "JPEG",res.getOutputStream()) ;
		} catch (IOException e) {
			
			e.printStackTrace();
			return "" ;
		}
		
	   return str ;
	}
}
