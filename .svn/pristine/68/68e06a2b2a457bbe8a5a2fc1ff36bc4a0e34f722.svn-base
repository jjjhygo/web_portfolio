package web_portfolio;


import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iot.pf.dto.Board;
import com.iot.pf.dto.User;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.UserService;
//spring으로 test하겠다는 선언
@RunWith(SpringJUnit4ClassRunner.class)
//spring 설정 파일을 가져와서 쓴다. test를 하기 위해
@ContextConfiguration(locations = {
		"file:src/main/resources-common/applicationContext-datasource.xml",
		"file:src/main/resources-common/applicationContext-beans.xml"
})
public class UserTest {
	@Autowired
	UserService us;

	@Test
	public void list() {
		ArrayList<User> result = us.list();
		System.out.println("result : " + result);

	}
	@Test
	public void join() {
		User u = new User();
		u.setUserId("jjhygo");
		u.setUserPw("20180424");
		u.setNickname("IOT");
		u.setEmail("123@naver.com");
		u.setUserName("정영호");
		u.setIsAdmin("N");

		try {
			us.join(u);
		}catch (AnomalyException ae) {
			ae.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();

			switch(e.getMessage()) {
			case "INSERT_ANOMALY":
				System.out.println("삽입 이상!!!");
				break;
			}
		}
		String id = "jjhygo";
		User user = us.getUser(id);
		try {
			us.delete(user.getSeq());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	@Test
//	public void update() {
//		int seq = 366;
//		try {
//			us.update(seq);
//		}catch(AnomalyException ae) {
//			ae.printStackTrace();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void delete() {
		int seq = 368;
		try {
			us.delete(seq);
		}catch(AnomalyException ae) {
			ae.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
