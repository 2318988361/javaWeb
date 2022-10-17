import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class GetBook {

    private static ArrayList<Book> books1 = new ArrayList<>();

    public static void main(String[] args) throws DocumentException {
        Document document = new SAXReader().read(new File("src/books.xml"));

        List<Element> books = document.getRootElement().elements("book");

        for (Element book : books) {
            Element name = book.element("name");
            Element author = book.element("author");
            Element price = book.element("price");
            String id = book.attributeValue("id");

            //创建Book对象，传入对应值
            Book book1 = new Book();
            book1.setId(Integer.parseInt(id));
            book1.setName(name.getText());
            book1.setAuthor(author.getText());
            book1.setPrice(Double.parseDouble(price.getText()));

            System.out.println("book对象信息" + book1);


            books1.add(book1);
            Iterator<Book> iterator = books1.iterator();
            while (iterator.hasNext()) {
                Book next = iterator.next();
                System.out.println(next);
            }
        }
    }
}
