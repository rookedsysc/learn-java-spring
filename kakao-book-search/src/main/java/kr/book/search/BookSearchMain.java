package kr.book.search;

import kr.pdf.generator.PdfGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookSearchMain {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("도서제목을 입력하세요 :");
            String bookTitle = scanner.nextLine();
            List<Book> books = KakaoBookApi.searchBooks(bookTitle);

            if(books.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                for(Book book:books) {
                    System.out.println(book);
                }
                String filename = "BookList.pdf";
                PdfGenerator.generator(books, filename);
                System.out.println(filename + " 파일이 생성되었습니다.");
            }
        } catch (Exception e) {
            System.out.println("PDF Gen Error : " + e.toString());
        }
    }
}
