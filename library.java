import java.util.*;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

interface Readable {
    void read();
}

class Book implements Readable {
    private int uniqueBookId;
    private String id;
    private String title;
    private String author;

    public Book(int uniqueBookId, String id, String title, String author) {
        this.uniqueBookId = uniqueBookId;
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getUniqueBookId() {
        return uniqueBookId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void read() {
        System.out.println("Reading book: " + title + " by " + author);
    }
}

class ReferenceBook extends Book {
    private String referenceType;

    public ReferenceBook(int uniqueBookId, String id, String title, String author, String referenceType) {
        super(uniqueBookId, id, title, author);
        this.referenceType = referenceType;
    }

    public String getReferenceType() {
        return referenceType;
    }
}

class BookCopies {
    private int uniqueBookId;
    private String title;
    private String author;
    private int availableCopies;

    public BookCopies(int uniqueBookId, String title, String author, int availableCopies) {
        this.uniqueBookId = uniqueBookId;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public int getUniqueBookId() {
        return uniqueBookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // Method to update available copies (encapsulation)
    public void updateAvailableCopies(int newCopies) {
        this.availableCopies = newCopies;
    }
}

class LibraryMember extends Person {
    private int memberId;
    private List<Book> booksBorrowed;

    public LibraryMember(String name, int age, int memberId) {
        super(name, age);
        this.memberId = memberId;
        this.booksBorrowed = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void borrowBook(Book book) {
        booksBorrowed.add(book);
    }

    public void returnBook(Book book) {
        booksBorrowed.remove(book);
    }
}

class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() { // Getter method for books list
        return books;
    }

    public int totalBooks() {
        return books.size();
    }

    public int availableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book instanceof ReferenceBook) {
                count++;
            }
        }
        return count;
    }

    public int issuedBooks() {
        return totalBooks() - availableBooks();
    }

    // Inner class LibraryMember management
    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public void removeMember(LibraryMember member) {
        members.remove(member);
    }

    public static void main(String[] args) {

        Library library = new Library();
        library.addBook(new Book(1, "Book1", "Java", "ABC"));
        library.addBook(new Book(2, "Book2", "Python", "DEF"));
       // library.addBook(new ReferenceBook(3, "RefBook1", "Programming", "GHI", "xyz"));
        library.addBook(new Book(1, "Book3", "Java", "ABC"));

        List<Book> allBooks = new ArrayList<>();
        allBooks.addAll(library.getBooks());

        for (Book book : allBooks) {
            book.read();
        }

        LibraryMember member1 = new LibraryMember("Ronita", 25, 1001);
        LibraryMember member2 = new LibraryMember("Chakraborty", 30, 1002);
        library.addMember(member1);
        library.addMember(member2);


        member1.borrowBook(library.books.get(0));
        member2.borrowBook(library.books.get(1));
       // member2.borrowBook(library.books.get(2));

        member1.returnBook(library.books.get(0));
        member1.returnBook(library.books.get(1));
        member2.returnBook(library.books.get(2));

        System.out.println("Total Books: " + library.totalBooks());
        System.out.println("Available Books: " + library.availableBooks());
        System.out.println("Issued Books: " + library.issuedBooks());

        b b = new b();
        A A = new b();
    }
}

 class A
{
    int a ;
    int b;



}

class b extends A
{
    int x;
    int y;
     public void read ( ){

     }
}