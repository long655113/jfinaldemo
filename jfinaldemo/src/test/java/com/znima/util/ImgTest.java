/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

//import com.madgag.gif.fmsware.GifDecoder;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import javax.imageio.ImageIO;
//import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class ImgTest {

//    private static void splitImage() throws IOException {
//
//        String originalImg = "C:\\Users\\Administrator\\Desktop/2423a.jpg";
//
//        // 读入大图  
//        File file = new File(originalImg);
//        FileInputStream fis = new FileInputStream(file);
//        BufferedImage image = ImageIO.read(fis);
//
//        // 分割成4*4(16)个小图  
//        int rows = 4;
//        int cols = 4;
//        int chunks = rows * cols;
//
//        // 计算每个小图的宽度和高度  
//        int chunkWidth = image.getWidth() / cols;
//        int chunkHeight = image.getHeight() / rows;
//
//        int count = 0;
//        BufferedImage imgs[] = new BufferedImage[chunks];
//        for (int x = 0; x < rows; x++) {
//            for (int y = 0; y < cols; y++) {
//                //设置小图的大小和类型  
//                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
//
//                //写入图像内容  
//                Graphics2D gr = imgs[count++].createGraphics();
//                gr.drawImage(image, 0, 0,
//                        chunkWidth, chunkHeight,
//                        chunkWidth * y, chunkHeight * x,
//                        chunkWidth * y + chunkWidth,
//                        chunkHeight * x + chunkHeight, null);
//                gr.dispose();
//            }
//        }
//
//        // 输出小图  
//        for (int i = 0; i < imgs.length; i++) {
//            ImageIO.write(imgs[i], "jpg", new File("C:\\e\\test\\img" + i + ".jpg"));
//        }
//
//        System.out.println("完成分割！");
//    }
//
//    @Test
//    public void testSegmeng() throws Exception {
////        Icon icon = Segmentation("C:\\Users\\Administrator\\Pictures/597034.gif", 700, 3580, 595, 842);
//        splitImage();
//    }
//
//    /**
//     * 把gif图片按帧拆分成jpg图片
//     *
//     * @param gifName String 小gif图片(路径+名称)
//     * @param path String 生成小jpg图片的路径
//     * @return String[] 返回生成小jpg图片的名称
//     */
//    private synchronized String[] splitGif(String gifName, String path) {
//        try {
//            GifDecoder decoder = new GifDecoder();
//            decoder.read(gifName);
//            int n = decoder.getFrameCount(); //得到frame的个数  
//            String[] subPic = new String[n];
//            String tag = "mytab";
//            for (int i = 0; i < n; i++) {
//                BufferedImage frame = decoder.getFrame(i); //得到帧  
//                //int delay = decoder.getDelay(i); //得到延迟时间  
//                //生成小的JPG文件  
//                subPic[i] = path + i + ".jpg";
//                FileOutputStream out = new FileOutputStream(subPic[i]);
//                ImageIO.write(frame, "jpeg", out);
//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//                encoder.encode(frame); //存盘   
//                out.flush();
//                out.close();
//            }
//            return subPic;
//        } catch (Exception e) {
//            System.out.println("splitGif Failed!");
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Test
//    public void testSplitGif() {
//        String[] splitGif = splitGif("http://www.d8qu.com/files/article/attachment/125/125869/19404731/597175.gif", "C:\\e\\test\\img/");
//        for (String p : splitGif) {
//            System.out.println(p);
//        }
//    }
//
//    /*
//     * 将GIF转JPG
//     * */
//    @Test
//    public void gifTojpg() throws IOException {
//        GifDecoder decoder = new GifDecoder();
//        InputStream is = new FileInputStream("C:\\Users\\Administrator\\Pictures/597034.gif");
//        if (decoder.read(is) != 0) {
//            System.out.println("读取有错误");
//            return;
//        }
//        is.close();
//        System.out.println(" 帧是数量: " + decoder.getFrameCount());
//        for (int i = 0; i < decoder.getFrameCount(); i++) {
//            BufferedImage frame = decoder.getFrame(i);
//            int delay = decoder.getDelay(i);
//            System.out.println("延迟时间: " + delay);
//            OutputStream out = new FileOutputStream("C:\\e\\test\\img/" + i + "bb.jpg");
//            ImageIO.write(frame, "jpeg", out);//将frame 按jpeg格式  写入out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            out.flush();
//            out.close();
//        }
//        System.out.println("gif to jpg");
//    }

}
