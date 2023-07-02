package kr.pdf.generator;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.OutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import kr.book.search.Book;

import java.io.IOException;
import java.util.List;

public class PdfGenerator {
    public static void generator(List<Book> books, String fileName) throws IOException {
        // PdfWriter 인스턴스 생성
        PdfWriter writer = new PdfWriter(fileName);

        // Pdf 도큐먼트 생성
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // 폰트 생성
        PdfFont font = null;
        font = PdfFontFactory.createFont("NanumGothicLight.ttf");
        document.setFont(font);

        Paragraph title = setTitle();
        document.add(title);

        // 도서 정보 테이블 생성
        Table table = createBookInfoTable();

        for (Book book : books) {
            table.addCell(book.getTitle());
            table.addCell(book.getAuthor());
            table.addCell(book.getPublisher());
            table.addCell(book.getThumbnail());

            // 이미지 데이터 생성
            ImageData imageData = ImageDataFactory.create(book.getThumbnail());
            Image image = new Image(imageData);
            image.setAutoScale(true);
            table.addCell(image);
        }
        document.add(table);
        document.close();
    }

    static Table createBookInfoTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.setMarginTop(20);

        table.addHeaderCell("제목");
        table.addHeaderCell("저자");
        table.addHeaderCell("출판사");
        table.addHeaderCell("이미지");

        return table;
    }

    static Paragraph setTitle() {
        Paragraph title = new Paragraph("금서목록");
        title.setFontSize(24);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setBold();
        return title;
    }

}
