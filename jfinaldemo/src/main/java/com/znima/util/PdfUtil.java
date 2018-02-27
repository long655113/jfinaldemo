/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.znima.entity.NovelItem;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PdfUtil {
    
    /**
     * 写小说内容到PDF文件
     * @param os
     * @param novelItems
     * @throws Exception 
     */
    public static void outputPdf(OutputStream os, List<NovelItem> novelItems) throws Exception {
        
        float marginLeft = 2;
        float marginRight = 2;
	float marginTop = 2;
        float marginBottom = 2;
        
        List<Chapter> chapterList = new ArrayList();
        BaseFont bfChinese = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        
        Integer size = 28;
        
        Font font = new Font(bfChinese, size, Font.NORMAL);
        int chapterIndex = 0;
        for (NovelItem item : novelItems) {
            String title = item.get("title") + "";
            String content = MyStringUtil.toContentString("" + item.getContent());
            
            content = content.replace("\n\n", "\n").replace("\n\n", "\n").replace("\n\n", "\n").replace("\n\n", "\n");
            
            Chunk chapTitle = new Chunk(title, font);
            Chapter chapter = new Chapter(new Paragraph(chapTitle), chapterIndex++);
            chapTitle.setLocalDestination(chapter.getTitle().getContent());
            
            Paragraph chapterContent = new Paragraph(content, font);
            chapterContent.setLeading(size * 1.1f); //设置行距
            chapter.add(chapterContent);
            
            chapterList.add(chapter);
        }

        Document document = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
        // add index page.
        PdfWriter writer = PdfWriter.getInstance(document, os);
        IndexEvent indexEvent = new IndexEvent();
        writer.setPageEvent(indexEvent);
        document.open();
        // add content chapter
        for (Chapter c : chapterList) {
            document.add(c);
            indexEvent.setBody(true);
        }
        document.close();
    }
}
