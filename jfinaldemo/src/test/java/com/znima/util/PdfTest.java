/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class PdfTest {
    
    @Test
    public void testWritePdf() throws DocumentException, FileNotFoundException, IOException {
        
        String FILE_DIR = "C:\\e\\test/";
        
        //Step 1—Create a Document.  
        Document document = new Document();  
        //Step 2—Get a PdfWriter instance.  
        PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));  
        //Step 3—Open the Document.  
        document.open();  
        //Step 4—Add content.  
        document.add(new Paragraph("Hello World"));  
        //Step 5—Close the Document.  
        
        //生成图片输入流
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Documents\\test\\img.txt")));
//        
//        StringBuilder sb = new StringBuilder();
//        
//        String readLine;
//        
//        while ((readLine = br.readLine()) != null) {
//            sb.append(readLine).append("\n");
//        }
//        
//        org.jsoup.nodes.Document inEle = Jsoup.parse(sb.toString());
//        
//        InputStream in = JsoupUtil.downLoadImg(inEle);
        
        //输出图片到PDF
        Image img = Image.getInstance("http://www.d8qu.com/files/article/attachment/125/125869/19404603/597048.gif");  
//        img.setAlignment(Image.LEFT | Image.TEXTWRAP);
//        img.setBorder(Image.BOX);
//        img.setBorderWidth(10);
//        img.setBorderColor(BaseColor.WHITE);
        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
        System.out.println("page->width:" + width + ", height:" + height);
        
        img.scaleToFit(595,842);//大小  
        width = img.getWidth();
        height = img.getHeight();
        System.out.println("image->width:" + width + ", height:" + height);
        
//        img.setRotationDegrees(-30);//旋转  
//        img.setWidthPercentage(100.0f);
        document.add(img);
        
        document.close(); 
    }
    
    @Test
    public void writePdf() throws FileNotFoundException, DocumentException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\e\\soft\\document/武侠世界大穿越.txt")));
        
        String FILE_DIR = "C:\\e\\test/";
        
        //Step 1—Create a Document.  
        Document doc = new Document();  
        //Step 2—Get a PdfWriter instance.  
        PdfWriter.getInstance(doc, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));  
        //Step 3—Open the Document.  
        doc.setMargins(0, 0, 0, 0);
//        doc.
        doc.open();  
        
        String str;
        
        
        
        while ((str = br.readLine()) != null) {
//            doc.
        }
    }
}
