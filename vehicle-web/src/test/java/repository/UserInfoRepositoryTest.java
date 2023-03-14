package repository;

import com.xunlekj.Application;
import com.xunlekj.user.model.entity.UserInfo;
import com.xunlekj.user.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("test");
        userInfo.setPassword("usexxxr");
        userInfo.setNickName("test");
        userInfoRepository.save(userInfo);
    }
}
