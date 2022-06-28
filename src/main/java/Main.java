import org.example.otusSpring.entity.Quiz;
import org.example.otusSpring.service.OwnCsvReader;
import org.example.otusSpring.service.OwnCsvReaderImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");

        OwnCsvReader<Quiz> csvReader = classPathXmlApplicationContext.getBean("ownCsvReader", OwnCsvReaderImpl.class);

        List<Quiz> quizzes = csvReader.getData("questions.csv", Quiz.class);
        quizzes.stream()
                .map(Quiz::getQuestion)
                .forEach(System.out::println);

        classPathXmlApplicationContext.close();
    }
}


