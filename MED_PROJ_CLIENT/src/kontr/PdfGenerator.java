package kontr;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import domen.Izvestaj;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PdfGenerator {

    public static void exportIzvestaj(Izvestaj i) {

        Document document = new Document(PageSize.A4, 36, 36, 100, 100);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            Font font10 = FontFactory.getFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, true, 10);
            document.open();

            // LOGO
            document.add(new Paragraph("IZVEŠTAJ LEKARA"));

            document.add(new Paragraph("\nDatum pregleda: "
                    + i.getDatumVreme().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), font10));

            document.add(new Paragraph("Pacijent: " + i.getPac().getImePrez() + "\n\n", font10));

            if(!i.getAnamneza().isEmpty()) document.add(new Paragraph("Anamneza:\n" + i.getAnamneza() + "\n\n", font10));
            if(!i.getNalaz().isEmpty()) document.add(new Paragraph("Klinički nalaz:\n" + i.getNalaz() + "\n\n", font10));
            if(!i.getDg().isEmpty()) document.add(new Paragraph("Dijagnoza:\n" + i.getDg() + "\n\n", font10));
            if(!i.getTerapija().isEmpty()) document.add(new Paragraph("Terapija:\n" + i.getTerapija() + "\n\n", font10));
            if(!i.getKontrola().isEmpty()) document.add(new Paragraph("Kontrola:\n" + i.getKontrola() + "\n", font10));
            if(i.getZakljucak()!=null && !i.getZakljucak().isEmpty()) document.add(new Paragraph("Zaključak:\n" + i.getZakljucak()+ "\n", font10));
            
            document.add(new Paragraph("\n" + i.getLekar().getImePrez()));
            document.add(new Paragraph("Specijalizacija lekara: " + i.getLekar().getSpec().getNazivSpec(), font10));

            if (i.getLekar().getSubspec().getNazivSpec() != null) {
                document.add(new Paragraph("Subspecijalizacija lekara: "
                        + i.getLekar().getSubspec().getNazivSpec() + "\n\n", font10));
            }
            document.close();

            // =============================
            // STAMPANJE ODMAH
            // =============================
            byte[] pdfBytes = baos.toByteArray();
            PDDocument pdf = PDDocument.load(new ByteArrayInputStream(pdfBytes));

            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(pdf));

                // KREIRANJE SETA ATRIBUTA ZA ŠTAMPU
                PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
                // Forsiranje jednostrane štampe (Sided.ONE_SIDED)
                attr.add(Sides.ONE_SIDED);

                // Prosledite atribute u print metodu
                job.print(attr);
            } catch (PrinterException ex) {
                ex.printStackTrace();
            } finally {
                pdf.close();
            }

            JOptionPane.showMessageDialog(null, "Dokument poslat na štampu!");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
