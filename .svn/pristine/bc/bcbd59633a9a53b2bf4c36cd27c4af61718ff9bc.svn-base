package web_portfolio;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iot.pf.dto.Board;
import com.iot.pf.exception.AnomalyException;
import com.iot.pf.service.BoardService;

//spring으로 test하겠다는 선언
@RunWith(SpringJUnit4ClassRunner.class)
//spring 설정 파일을 가져와서 쓴다. test를 하기 위해
@ContextConfiguration(locations = {
		"file:src/main/resources-common/applicationContext-datasource.xml",
		"file:src/main/resources-common/applicationContext-beans.xml"
})
public class BoardTest {
	@Autowired
	BoardService bs;

	@Test
	public void updateTest() {
		Board b = new Board();
		b.setTitle("업데이트했다.");
		b.setContents("잘했다.");
		b.setUserId("시험");
		b.setUserName("test");
		try {
			bs.updateTest(b);
		}catch(AnomalyException ac) {
			ac.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void insert() {
		Board b = new Board();
		b.setUserName("iot2");
		b.setTitle("iot2");
		b.setContents("iot2");
		

		try {
			bs.insert(b);
		} catch(AnomalyException ac) {
			ac.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
