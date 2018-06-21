package web_portfolio;
//spring으로 test하겠다는 선언

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iot.pf.dto.Board;
import com.iot.pf.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
//spring 설정 파일을 가져와서 쓴다. test를 하기 위해
@ContextConfiguration(locations = {
		"file:src/main/resources-common/applicationContext-datasource.xml",
		"file:src/main/resources-common/applicationContext-beans.xml"
		})
public class AttachmentTest {
	@Autowired
	BoardService bs;
	
	@Test
	public void insert() {
		Board b1 = new Board();
		b1.setTitle("첨부파일용2");
		b1.setContents("첨부파일용2");
		b1.setUserId("test1");
		b1.setUserName("장췐");
		b1.setHasFile("1");
		
		File f1 = new File("c:/20180404.xlsx");
		File f2 = new File("c:/hello.class");
		File[] files = new File[] {f1, f2};
		
		bs.writeTest(b1, files);
	}
}
