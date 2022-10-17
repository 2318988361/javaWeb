import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Dom4j {
    //演示图和加载XML文件
    @Test
    public void loadXML() throws DocumentException {
        //得到一个解析器
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));

    }

    //遍历所有student信息
    @Test
    public void listStus() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/students.xml"));

        //1.得到rootElement
        Element rootElement = document.getRootElement();
        //2.得到rootElement的studentElement的方法
        List<Element> students = rootElement.elements("student");
//        System.out.println(student.size());
        for (Element student : students) {//element是student
            //获取学生student的name Element
            Element name = student.element("name");
            Element age = student.element("age");
            Element gender = student.element("gender");

            System.out.println("学生信息：" + name.getText() + " " + age.getText()
                            + " " + gender.getText());
        }

        //获取指定信息
        Element student = (Element)rootElement.elements("student").get(0);
        System.out.println(student.element("name").getText());

        //获取student元素的属性
        System.out.println(student.attributeValue("id"));
    }
}
