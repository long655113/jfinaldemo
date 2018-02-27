/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MANGOCOOL on 2015/10/19.
 */
public class PdfIndex {

    /**
     * EventListner for Content
     */
//    private static class ContentEvent extends PdfPageEventHelper {
//
//        private int page;
//        Map<String, Integer> index = new LinkedHashMap<String, Integer>();
//
//        @Override
//        public void onStartPage(PdfWriter writer, Document document) {
//            page++;
//        }
//
//        @Override
//        public void onChapter(PdfWriter writer, Document document,
//                float paragraphPosition, Paragraph title) {
//            index.put(title.getContent(), page);
//        }
//
//        @Override
//        public void onSection(PdfWriter writer, Document document,
//                float paragraphPosition, int depth, Paragraph title) {
//            onChapter(writer, document, paragraphPosition, title);
//        }
//    }

    /**
     * EventListner for Index
     */
    private static class IndexEvent extends PdfPageEventHelper {

        private int page;
        private boolean body;

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            // set page number on content
            if (body) {
                page++;
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, new Phrase(page + ""),
                        (document.right() + document.left()) / 2, document.bottom() - 18, 0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // make content pdf (calculate page number)
//        Document content = new Document(PageSize.A4, 50, 50, 50, 50);
//        PdfWriter contentWriter = PdfWriter.getInstance(content, new ByteArrayOutputStream());
//        ContentEvent event = new ContentEvent();
//        contentWriter.setPageEvent(event);
//        content.open();
        List<Chapter> chapterList = new ArrayList();
        BaseFont bfChinese = BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        BaseFont bfChinese = BaseFont.createFont("cjk_registry", "UniGB-UCS2-H", false);
        Integer size = 28;
        
        Font font = new Font(bfChinese, size, Font.BOLD);
        for (int i = 1; i <= 10; i++) {
            Chunk chapTitle = new Chunk("The " + i + " chapter", font);
            Chapter chapter = new Chapter(new Paragraph(chapTitle), i);
            chapTitle.setLocalDestination(chapter.getTitle().getContent());
            
            Paragraph chapterContent = new Paragraph("章节内容第1行\n章节内容第2行\n章节内容第3行\n章节内容第4行\n" + i, font);
            chapterContent.setLeading(size * 1.1f);
            chapter.add(chapterContent);
            
            for (int j = 0; j < i; j++) {
                Chunk secTitle = new Chunk("  二级The " + (j + 1) + " section");
                Section section = chapter.addSection(new Paragraph(secTitle));
                secTitle.setLocalDestination(section.getTitle().getContent());
                section.setIndentationLeft(10);
                section.add(new Paragraph("mangocool mangocool测试1aa1 "
                        + "\nmangocool mangocool 内容bb2"
                        + "\nmangocool mangocool 内容cc3", font));
                
                section.add(new Paragraph("mangocool mangocool测试1aa1 "
                        + "\nmangocool mangocool 内容bb2"
                        + "\nmangocool mangocool 内容cc3", font));
            }
//            content.add(chapter);
            chapterList.add(chapter);
        }
//        content.close();

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // add index page.
        String FILE_DIR = "C:\\e\\test/";
        String path = FILE_DIR + "createSamplePDF.pdf";
        FileOutputStream os = new FileOutputStream(path);
        PdfWriter writer = PdfWriter.getInstance(document, os);
        IndexEvent indexEvent = new IndexEvent();
        writer.setPageEvent(indexEvent);
        document.open();
//        Chapter indexChapter = new Chapter("Index：", -1);
//        indexChapter.setNumberDepth(-1);// not show number style
//        PdfPTable table = new PdfPTable(2);
//        for (Map.Entry<String, Integer> index : event.index.entrySet()) {
//            PdfPCell left = new PdfPCell(new Phrase(index.getKey()));
//            left.setBorder(Rectangle.NO_BORDER);
//            Chunk pageno = new Chunk(index.getValue() + "zzZZ章节");
//            pageno.setLocalGoto(index.getKey());
//            PdfPCell right = new PdfPCell(new Phrase(pageno));
//            right.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            right.setBorder(Rectangle.NO_BORDER);
//            table.addCell(left);
//            table.addCell(right);
//        }
//        indexChapter.add(table);
//        document.add(indexChapter);
        // add content chapter
        for (Chapter c : chapterList) {
            document.add(c);
            indexEvent.body = true;
        }
        document.close();
        os.close();
    }

}
