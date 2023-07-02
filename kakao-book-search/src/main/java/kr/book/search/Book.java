package kr.book.search;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String thumbnail;

    public Book(String title, String author, String publisher, String thumbnail) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
