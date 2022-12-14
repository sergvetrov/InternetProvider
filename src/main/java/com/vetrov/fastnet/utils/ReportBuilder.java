package com.vetrov.fastnet.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

public class ReportBuilder {
    static Logger log = Logger.getLogger(ReportBuilder.class.getName());

    public static void contractPDF(HttpServletResponse response, User user) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            BaseFont arial = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);

            Paragraph title = new Paragraph("Договор", new Font(arial, 16));

            Chapter chapter = new Chapter(title, 1);
            chapter.setNumberDepth(0);

            Paragraph title1 = new Paragraph("Договор на оказание услуг между компанией ООО «Fastnet» и физическим лицом", new Font(arial, 10));
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);

            Section section1 = chapter.addSection(title1);

            Paragraph personalInformation = new Paragraph("ФИО: " + user.getSurname() + " " + user.getFirstName() + " " + user.getLastName(), new Font(arial, 12));
            section1.add(personalInformation);

            String address = "Адрес: " + user.getDetails().getCity() + ", " +
                    user.getDetails().getStreet() +
                    " " + user.getDetails().getHome() +
                    ", кв. " + user.getDetails().getApartment();
            personalInformation = new Paragraph(address, new Font(arial, 12));
            section1.add(personalInformation);

            personalInformation = new Paragraph("Логин и пароль для входа в личный кабинет", new Font(arial, 14));
            section1.add(personalInformation);

            personalInformation = new Paragraph("Логин: " + user.getDetails().getPhone(), new Font(arial, 12));
            section1.add(personalInformation);

            personalInformation = new Paragraph("Пароль: " + user.getPassword(), new Font(arial, 12));
            section1.add(personalInformation);

            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);

            PdfPCell c1 = new PdfPCell(new Phrase("Тариф", new Font(arial, 12)));
            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Стоимость", new Font(arial, 12)));
            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Описание", new Font(arial, 12)));
            t.addCell(c3);

            Set<Tariff> tariffs = user.getTariffs();
            for (Tariff tariff : tariffs) {
                t.addCell(new Phrase(tariff.getName(), new Font(arial, 12)));
                t.addCell(new Phrase(String.valueOf(tariff.getPrice()), new Font(arial, 12)));
                t.addCell(new Phrase(tariff.getDescription(), new Font(arial, 12)));
            }
            section1.add(t);

            Paragraph footer = new Paragraph("© 2022-2022 ООО «Fastnet»", new Font(arial, 10));
            section1.add(footer);

            document.add(chapter);
            document.close();

            openInBrowser(response, baos);
            log.info("Contract for user was created in PDF file");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void tariffPDF(HttpServletResponse response, Tariff tariff) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            BaseFont arial = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);

            String name = "Тариф <" + tariff.getName() + ">";
            Paragraph title = new Paragraph(name, new Font(arial, 16));
            document.add(title);

            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);

            PdfPCell c1 = new PdfPCell(new Phrase("Тариф", new Font(arial, 12)));
            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Стоимость", new Font(arial, 12)));
            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Описание", new Font(arial, 12)));
            t.addCell(c3);

            t.addCell(new Phrase(tariff.getName(), new Font(arial, 12)));
            t.addCell(new Phrase(String.valueOf(tariff.getPrice()), new Font(arial, 12)));
            t.addCell(new Phrase(tariff.getDescription(), new Font(arial, 12)));

            document.add(t);

            Paragraph footer = new Paragraph("© 2022-2022 ООО «Fastnet»", new Font(arial, 10));
            document.add(footer);
            document.close();

            openInBrowser(response, baos);
            log.info("Tariffs for user generated to PDF");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    private static void openInBrowser(HttpServletResponse response, ByteArrayOutputStream baos) {
        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os;
        try {
            os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
