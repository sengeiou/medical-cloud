package com.zhou.medical.manager.web;

import com.zhou.medical.manager.util.MakePicture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/imgVcode")
public class ImageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        MakePicture mp = new MakePicture();
        String str = mp.drawPicture(60, 20, req, res);
        res.getOutputStream().print(str);
    }
}
