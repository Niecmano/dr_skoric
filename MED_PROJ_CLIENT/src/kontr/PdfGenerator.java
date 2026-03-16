package kontr;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import domen.Izvestaj;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PdfGenerator {

    public static void exportIzvestaj(Izvestaj i) {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // LOGO
            Image img = Image.getInstance(PdfGenerator.class.getResource("/slike/logo.png"));
            img.scaleToFit(270, 70);
            img.setAlignment(Image.ALIGN_CENTER);
//            document.add(img);

            // PODACI
            document.add(new Paragraph("\n\n\n\nIZVEŠTAJ LEKARA"));
            document.add(new Paragraph("\nDatum pregleda: " +
                    i.getZt().getDatumVreme().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            document.add(new Paragraph("Pacijent: " + i.getZt().getPac().getImePrez() + "\n"));

            document.add(new Paragraph("\nAnamneza:\n" + i.getAnamneza() + "\n\n"));
            document.add(new Paragraph("Klinički nalaz:\n" + i.getNalaz() + "\n\n"));
            document.add(new Paragraph("Dijagnoza:\n" + i.getDg() + "\n\n"));
            document.add(new Paragraph("Terapija:\n" + i.getTerapija() + "\n\n"));
            document.add(new Paragraph("Kontrola:\n" + i.getKontrola() + "\n"));

            document.add(new Paragraph("\n" + i.getZt().getLekar().getImePrez()));
            document.add(new Paragraph("Specijalizacija lekara: " + i.getZt().getLekar().getSpec().getNazivSpec()));

            if (i.getZt().getLekar().getSubspec().getNazivSpec() != null) {
                document.add(new Paragraph("Subspecijalizacija lekara: " +
                        i.getZt().getLekar().getSubspec().getNazivSpec() + "\n\n"));
            }

            // FOOTER
            Image fut = Image.getInstance(PdfGenerator.class.getResource("/slike/footer.png"));
            fut.setAlignment(Image.ALIGN_CENTER);
            fut.scaleToFit(580, 110);
//            document.add(fut);

            document.close();

            // =============================
            // STAMPANJE ODMAH
            // =============================
            byte[] pdfBytes = baos.toByteArray();
            PDDocument pdf = PDDocument.load(new ByteArrayInputStream(pdfBytes));

            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(pdf));

                // Odmah štampaj na default štampač
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace(); // ovde može biti PrinterAbortException ako štampa prekinuta
            } finally {
                pdf.close();
            }

            JOptionPane.showMessageDialog(null, "Dokument poslat na štampu!");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}