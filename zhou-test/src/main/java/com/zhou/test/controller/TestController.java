package com.zhou.test.controller;

import com.zhou.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/testService/Test1")
    public Map<String, Object> testService1() {
//        testService.test1();
        log.info("testService1testService1testService1");
        Map<String, Object> map = new HashMap<>();
        map.put("data", "罗亦洲1111111111111");
        map.put("code", 200);
        return map;
    }

    @RequestMapping("/testService/Test2")
    public String testService2() {
//        testService.test2();
        return "/testService/Test2/testService/Test2/testService/Test2";
    }


    @RequestMapping("/login")
    public Map<String, Object> login(String user, String password) {
//        String user = userLogin.get("user");
//        String password = userLogin.get("password");

        System.out.println("user:" + user);
        System.out.println("password:" + password);
        Map<String, Object> map = new HashMap<>();

        if ("admin".equals(user) && "666666".equals(password)) {

            map.put("status", true);
            map.put("message", "成功");
        } else {
            map.put("status", false);
            map.put("message", "失败");
        }

        return map;
    }

    @RequestMapping("/upload")
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile files[]) {
        System.out.println("files22222222222222222：" + files);

        try {
            System.out.println("2222222222222files.length：" + files.length);
            if (files.length == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", false);
                map.put("message", "失败");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "失败");
            return map;
        }
//
        for (MultipartFile multipartFile : files) {

            System.out.println("file:" + multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            File targetPath = new File("/Users/zhouzhou/Downloads");
            File targetFile = new File(targetPath.getPath(), fileName);

            if (!targetFile.exists()) {
                targetPath.mkdirs();
                try {
                    targetFile.createNewFile();
                    multipartFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("message", "成功");

        return map;
    }


    @RequestMapping("/uploadBase64")
    public Map<String, Object> uploadBase64(HttpServletRequest request, String base64) {

        System.out.println("base64111111:" + base64);

        GenerateImage(base64.split(",")[1], "zhou.png");

        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("message", "成功");

        return map;
    }


    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr
     * @return
     */
    public static boolean GenerateImage(String imgStr, String name) {
        System.out.println("imgStr:" + imgStr);
        //图像数据为空
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            //新生成的图片
            String imgFilePath = "/Users/zhouzhou/Downloads/" + name;
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
