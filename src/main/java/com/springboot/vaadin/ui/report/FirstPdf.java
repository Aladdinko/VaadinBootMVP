package com.springboot.vaadin.ui.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.vaadin.domain.Account;
import com.springboot.vaadin.service.AccesService;
import com.vaadin.data.util.BeanItemContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

/**
 * Created by maggouh on 13/03/17.
 */
@Component
public class FirstPdf {

    @Autowired
    public AccesService accesService;

    static BeanItemContainer<Account> container;

    private static String FILE = "c:/temp/FirstPdf.pdf";

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);

    public FirstPdf(AccesService accesService) {
        this.accesService = accesService;

    }

    public void buildPdf() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private void addContent(Document document) throws DocumentException {

        Anchor anchor = new Anchor("Show Autorisation text", catFont);
        anchor.setName("Show Autorisation text");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Administrator text", subFont);
        Section subCatPart = catPart.addSection(subPara);
        try {
            subCatPart.add(new Paragraph(FirstPdf.this.accesService.adminShowEcho("Hello administrators")));
        } catch (AccessDeniedException ex) {
            subCatPart.add(new Paragraph(FirstPdf.this.accesService.authenticatShowEcho("Hello non admins")));
        }
        subPara = new Paragraph("Users text", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(FirstPdf.this.accesService.authenticatShowEcho("Hello users")));
        subPara = new Paragraph("List Table", subFont);
        subCatPart = catPart.addSection(subPara);

        // add a list
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart)
            throws BadElementException {

        PdfPTable table = new PdfPTable(3);


        PdfPCell c1 = new PdfPCell(new Phrase("Username"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Password"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Roles"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("admin");
        table.addCell("admin");
        table.addCell("ROLE_ADMIN");

        subCatPart.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
