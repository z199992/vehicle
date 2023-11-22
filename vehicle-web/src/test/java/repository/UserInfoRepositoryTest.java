package repository;

import com.xunlekj.Application;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        userInfoRepository.save(userInfo);
    }
}
