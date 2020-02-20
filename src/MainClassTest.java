import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Метод MainClass вернул число не равное 14",MainClass.getLocalNumber() == 14);
    }


    @Test
    public void secondTestGetLocalNumber(){
        Assert.assertTrue("Метод MainClass вернул число не равное 14",MainClass.getLocalNumber() == 10);
    }

}
