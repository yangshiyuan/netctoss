package com.netctoss.action.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.netctoss.action.BaseAction;
import com.netctoss.util.ImageUtil;

public class CreateImageAction
        extends BaseAction {

    //ouput
    private InputStream imageStream;

    public String execute() {
        //生成验证码图片
        Map<String, BufferedImage> map =
                ImageUtil.createImage();
        //通过遍历得到唯一生成的验证码
        String imageCode =
                map.keySet().iterator().next();
        //将验证码记录到session，在登录验证时使用
        session.put("imageCode", imageCode);
        //根据验证码，得到图片
        BufferedImage image = map.get(imageCode);
        //将图片转换为输入流，由result作输出
        try {
            imageStream =
                    ImageUtil.getInputStream(image);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

}
