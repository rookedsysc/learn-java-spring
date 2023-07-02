import com.itextpdf.commons.utils.Base64;
import com.itextpdf.io.source.OutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFTest {
    public static void main(String[] args) {
        // PdfWriter 인스턴스 생성
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("hello.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // PdfDocument 객체 생성
        PdfDocument pdf = new PdfDocument(writer);

        // Document 객체 생성
        Document document = new Document(pdf);

        // Paragraph 추가
        document.add(new Paragraph("Hello, World!"));

        // Document 닫기
        document.close();
    }
}
