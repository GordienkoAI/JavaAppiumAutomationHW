import org.junit.Assert;
import org.junit.Test;
import sun.applet.Main;

public class MainClassTest {

    // Домашнее задание Тест 1
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Метод MainClass вернул число не равное 14",MainClass.getLocalNumber() == 14);
    }

    @Test
    public void secondTestGetLocalNumber(){
        Assert.assertTrue("Метод MainClass вернул число не равное 14",MainClass.getLocalNumber() == 10);
    }

    //ДДомашнее задание  Тест 2
    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Метод getClassNumber вернул число меньшее 45", MainClass.getLocalNumber() > 45);
    }

    //Домашнее задание Тест 3
    @Test
    public void testGetClassString()
    {
         MainClass mainObj = new MainClass();
         String stringObj = mainObj.getClassString().substring(0,5).toLowerCase();


        Assert.assertTrue("Переменная calss_string не содержит подстроку 'Hello' или 'hello'", stringObj.contains("hello"));
    }
}
