package com.llm;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.llm.Dao.UserRepository;
import com.llm.common.Sex;
import com.llm.entity.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootJpaApplicationTests
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads(){
        List<User> users = new ArrayList<>();
        for (int i=0;i<10;i++){
            String name="name"+i;
            Integer age=19+i;
            Sex sex=i%2==0?Sex.male:Sex.female;
            User user = new User(name, age, "address", sex);
            users.add(user);
        }
//        userRepository.save(users);
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        int page=0;
        int size=2;
        Pageable pageable= PageRequest.of(page,size);
        Page<User> userPage = userRepository.search(null, null, null, pageable);
        System.out.println(JSON.toJSON(userPage));
        assertTrue( true );
    }
}
