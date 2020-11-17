import com.llm.spring.dto.LoginThirdResponse;
import com.llm.spring.dto.ThirdLoginBindMobileWithRegistResponse;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

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
}
