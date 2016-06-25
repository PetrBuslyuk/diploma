package client;
import java.io.FileOutputStream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class createPDFReport {
  static String namePDFReport;
  private static String FILE;
  private static final Font CATFONT   = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
  private static final Font SUBFONT   = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
  private static final Font SMALLBOLD = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
  private static final String [] listOfTitles = {"Period", "Addition", "Withdrawal", "Reinvesting", "Deposit at the beginning of the month", "Deposit at the end of the month"};

  public static void createReport(boolean typeOfReport, ArrayList<company> Companies, company Company) {
    try {
        namePDFReport = companies.input("Введите название отчета", "Введите название отчета");
        if (namePDFReport==null){
            return ;
        }
        FILE  = companies.get_current_dir()+namePDFReport+".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        if(typeOfReport){
            createFullReport(document,Companies); 
        }else{
            createUnfullReport(document,Company);
        }
        document.close();
    }catch(FileNotFoundException | DocumentException e) {}
  }
  public static void createFullReport(Document document,ArrayList<company> Companies){
    Companies.stream().forEach((Company) -> {
        createUnfullReport(document, Company);
    });
  }
  public static void createUnfullReport(Document document,company Company){
      try {
          companies.log(Company.get_name());
            Paragraph paragraph0 = new Paragraph(" ",CATFONT);
            document.add(paragraph0);
            Paragraph paragraph1 = new Paragraph(
                    "Name of company: " + Company.get_name() +
                    ", Deposit: " + Company.get_depo() + 
                    ", Average percentage: " + Company.get_persent()+ "%" +
                    ", During of investment: " + Company.get_period()
                    , SUBFONT);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph(" ",CATFONT);
            document.add(paragraph2);
            String [] arrayOfFields = Company.get_company_to_array_in_report();
            companies.log(arrayOfFields.length);
            document.add(createTable(listOfTitles,arrayOfFields));
      } catch (DocumentException ex) {
          Logger.getLogger(createPDFReport.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public static PdfPTable createTable(String [] headers, String [] fields) throws BadElementException, DocumentException {
    PdfPTable table = new PdfPTable(headers.length);
    PdfPCell c1;
    for(String head : headers){
        c1 = new PdfPCell(new Phrase(head));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
    }
    for(String field : fields){
        table.addCell(field);
    }
    return table;
  }

  public static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
} 