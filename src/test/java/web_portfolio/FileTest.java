package web_portfolio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
public static void main(String[] args) {
	FileInputStream fis = null;
	
//	File f = new File("c:\\20180404.xlsx");
	File f = new File("c:/20180404.xlsx");
	
		try {
			if(f.exists())
			fis= new FileInputStream(f);
			
		File target = new File("c:/filetest/copy/");
		if(!target.exists())
			target.mkdirs();
		target = new File(target, f.getName());//폴더와 파일의 생성은 분리해서 작업해야한다.
		FileOutputStream fos = new FileOutputStream(target);
		int data = 0;
		while((data=fis.read())!=-1) {//파일복사
			fos.write(data);
		}
		f.delete();//파일이동
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	//폴더생성
	/*System.out.println("name : " + f.getName());
	System.out.println("size : " + f.length());
	
	File newDir = new File("c:/test");
	System.out.println(newDir.getAbsolutePath() + " exists ? " + newDir.exists());
	if(!newDir.exists()) { //mkdir은 맨 끝의 폴더만 해당(abc) 이전경로파일(test) 유무를 먼저 확인해야한다. 
	newDir = new File(newDir, "abc");
	System.out.println(newDir.getAbsolutePath() + " exists ? " + newDir.exists());
	if(!newDir.exists()) newDir.mkdir();
	//c:test/abc
	}
	newDir = new File("c:/test1/zyx");
	System.out.println(newDir.getAbsolutePath() + " exists ? " + newDir.exists());
	if(!newDir.exists()) newDir.mkdirs();//mkdirs는 한번에 가능하다.*/
	
}
}
