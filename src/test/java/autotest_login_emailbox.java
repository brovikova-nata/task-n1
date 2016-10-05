
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;



public class autotest_login_emailbox {
  WebDriver driver;

  private static String[] getDocument() throws Exception {
    try {
      String[] arrayData;
      arrayData = new String[2];
      DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
      f.setValidating(false);
      DocumentBuilder dBuilder = f.newDocumentBuilder();
      Document doc = dBuilder.parse("src/data.xml");
      doc.getDocumentElement().normalize();
      NodeList nList = doc.getElementsByTagName("parameters");
      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          arrayData[0] =  eElement.getElementsByTagName("login").item(0).getTextContent();
          arrayData[1] =  eElement.getElementsByTagName("password").item(0).getTextContent();
        }
      }
      return arrayData;
    } catch (Exception exception) {
      String message = "XML parsing error!";
      throw new Exception(message);
    }
  }

  public void seturl() throws Exception {
    driver.get("https://mail.ru/");
  }

  public void filldata() throws Exception {
    String[] data = getDocument();
    driver.findElement(By.id("mailbox__login")).sendKeys(data[0]);
    driver.findElement(By.id("mailbox__password")).sendKeys(data[1]);
    driver.findElement(By.id("mailbox__auth__button")).click();
  }


  public void check_autorized() throws Exception {
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      WebElement element = driver.findElement(By.id("PH_user-email"));
      if( !element.getText().contains("test_autotest@mail.ru")) {
        throw new Exception("This login is not autorized!");
    }
  }

}
