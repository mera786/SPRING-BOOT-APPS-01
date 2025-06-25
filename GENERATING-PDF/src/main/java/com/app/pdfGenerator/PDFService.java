package com.app.pdfGenerator;


import com.app.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class PDFService {

    public byte[] createPdf(List<User> users) {
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Paragraph paragraph = new Paragraph("User Table");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
            document.add(new Paragraph("User with all details", f));
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(6);
            table.addCell("User ID");
            table.addCell("User Name");
            table.addCell("User Email");
            table.addCell("User Mobile");
            table.addCell("User City");
            table.addCell("User Country");
            for (User user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getName());
                table.addCell(user.getEmail());
                table.addCell(String.valueOf(user.getMobile()));
                table.addCell(user.getCity());
                table.addCell(user.getCountry());
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}

