/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontr;

/**
 *
 * @author Nemanja
 */
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import domen.Izvestaj;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {

    public static void exportIzvestaj(Izvestaj i) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Sačuvaj izveštaj kao PDF");

        int result = fc.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = fc.getSelectedFile().getAbsolutePath();

        if (!filePath.endsWith(".pdf")) {
            filePath += ".pdf";
        }

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
//            Image img = Image.getInstance("C:\\Users\\Nemanja\\Desktop\\med.proj\\logo.png");
//            img.scaleToFit(270, 70);
//            img.setAlignment(Image.ALIGN_LEFT);

//            document.add(img); 
            document.add(new Paragraph("\n\nIZVEŠTAJ LEKARA\n\n"));
            document.add(new Paragraph("Datum pregleda: "+i.getZt().getDatumVreme().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            document.add(new Paragraph("\nPacijent:"+i.getZt().getPac()+"\n"));
//            document.add(new Paragraph("Datum rodjenja pacijenta:"+i.getZt().getPac().getDatumRodj().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
            document.add(new Paragraph("\n\nAnamneza:\n" + i.getAnamneza() + "\n\n"));
            document.add(new Paragraph("Dijagnoza:\n" + i.getDg() + "\n\n"));
            document.add(new Paragraph("Terapija:\n" + i.getTerapija() + "\n\n"));
            
             document.add(new Paragraph("\n\n\ndr "+i.getZt().getLekar()));
            document.add(new Paragraph("Specijalizacija lekara: "+i.getZt().getLekar().getSpec().getNazivSpec()));

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        JOptionPane.showMessageDialog(null, "PDF uspešno generisan!");
    }
}

