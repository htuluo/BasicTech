import com.llm.spring.dto.LoginThirdResponse;
import com.llm.spring.dto.ThirdLoginBindMobileWithRegistResponse;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/11/17
 * @description： //TODO
 */
public class BeanUtilsTest {
    @Test
    public void test1() {

        ThirdLoginBindMobileWithRegistResponse thirdLoginBindMobileWithRegistResponse = new ThirdLoginBindMobileWithRegistResponse();
        LoginThirdResponse loginThirdResponse = new LoginThirdResponse();
        loginThirdResponse.setAgree_date("aaa");
        String[] custid_child = {"a", "b", "c"};
        loginThirdResponse.setCustid_child(custid_child);
        BeanUtils.copyProperties(loginThirdResponse, thirdLoginBindMobileWithRegistResponse);
        System.out.println(thirdLoginBindMobileWithRegistResponse.getCustid_child());
    }

    @Test
    public void test2() {
        Assert.isNull("ss", "lkl");
        Assert.isTrue(true, "yes it is true");
    }

    @Test
    public void test3() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("aabb");
        Thread.sleep(550);
        stopWatch.stop();
        stopWatch.start("ccc");
        Thread.sleep(250);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
