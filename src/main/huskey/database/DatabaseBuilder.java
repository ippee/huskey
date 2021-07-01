package database;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Databaseインスタンスを取得するAPI
 *
 * @author いっぺー
 */
public class DatabaseBuilder {
    private final String dbName;
    private final String masterKey;
    private final String dbDir;

    public DatabaseBuilder(String dbName, String masterKey, String dbDir) {
        this.dbName = dbName;
        this.masterKey = masterKey;
        this.dbDir = dbDir;
    }

    /**
     * 全データベースの名前を一覧で取得
     *
     * @return String[]
     * @author いっぺー
     */
    public static String[] showDBList() {
        return new String[] {"SampleDB"};
    }

    /**
     * masterKeyの照合
     *
     * @return boolean
     * @author いっぺー
     */
    public boolean isKeyMatched() throws FileNotFoundException {
        return Objects.equals(this.masterKey, "sample");
    }

    /**
     * Databaseインスタンスの構築
     *
     * @return Database
     * @author いっぺー
     */
    public Database buildDatabase() throws FileNotFoundException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File file = Paths.get(this.dbDir + this.dbName).toFile();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            return new Database(doc, this.dbName, this.masterKey);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
