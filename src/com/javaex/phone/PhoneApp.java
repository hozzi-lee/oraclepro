package com.javaex.phone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class PhoneApp {
	public static void main(String[] args) throws IOException {
		BufferedReader bfR = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfW = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PhoneDao phoneDao = new PhoneDao();
		
		int personID;
		String name;
		String hp;
		String company;
		
		while (true) {
			bfW.write("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			bfW.newLine();
			bfW.write("--------------------------------------");
			bfW.newLine();
			bfW.write(">메뉴번호: ");
			bfW.flush();
			int str = Integer.parseInt(bfR.readLine());
			
			switch (str) {

			case 1:
				bfW.write("<1.리스트>");
				bfW.newLine();
				bfW.flush();
				showList(phoneDao.phoneList());
				continue;

			case 2:
				bfW.write("<2.등록>");
				bfW.flush();
				bfW.newLine();
				bfW.write("이름 > ");
				bfW.flush();
				name = bfR.readLine();
				bfW.write("휴대전화 > ");
				bfW.flush();
				hp = bfR.readLine();
				bfW.write("회사번호 > ");
				bfW.flush();
				company = bfR.readLine();

				int istCount = phoneDao.phoneInsert(new PhoneVo(name, hp, company));

				if (istCount > 0)
					bfW.write("[" + istCount + "건 등록되었습니다.]");
				else
					bfW.write("ERROR: " + istCount);
				
				bfW.newLine();
				bfW.newLine();
				continue;
				
			case 3:
				bfW.write("<3.수정>");
				bfW.flush();
				bfW.newLine();
				bfW.write("번호 > ");
				bfW.flush();
				personID = Integer.parseInt(bfR.readLine());
				bfW.write("이름 > ");
				bfW.flush();
				name = bfR.readLine();
				bfW.write("휴대전화 > ");
				bfW.flush();
				hp = bfR.readLine();
				bfW.write("회사번호 > ");
				bfW.flush();
				company = bfR.readLine();

				int udtCount = phoneDao.phoneUpdate(new PhoneVo(personID, name, hp, company));

				if (udtCount > 0)
					bfW.write("[" + udtCount + "건 수정되었습니다.]");
				else
					bfW.write("ERROR: " + udtCount);

				bfW.newLine();
				bfW.newLine();
				continue;

			case 4:
				bfW.write("<4.삭제>");
				bfW.flush();
				bfW.newLine();
				bfW.write("번호 > ");
				bfW.flush();
				personID = Integer.parseInt(bfR.readLine());
				
				int dltCount = phoneDao.phoneDelete(new PhoneVo(personID));
				
				if (dltCount > 0)
					bfW.write("[" + dltCount + "건 삭제되었습니다.]");
				else
					bfW.write("ERROR: " + dltCount);

				bfW.newLine();
				bfW.newLine();
				continue;

			case 5:
				bfW.write("<5.검색>");
				bfW.flush();
				bfW.newLine();
				bfW.write("검색어 > ");
				bfW.flush();
				String keyword = bfR.readLine();
				
				showList(phoneDao.phoneList(keyword));
				
				continue;

			case 6:
				bfW.newLine();
				bfW.write("**************************************");
				bfW.newLine();
				bfW.write("*               감사합니다               *");
				bfW.newLine();
				bfW.write("**************************************");
				bfW.flush();
				break;

			default:
				bfW.write("[다시 입력해주세요.]");
				bfW.newLine();
				bfW.newLine();
				bfW.flush();
				continue;
				
			}
			
			break;
			
		}
		
	}
	
	public static void showList(List<PhoneVo> e) {
		for (PhoneVo p : e) {
			System.out.println(
					"순번: " + p.getPersonID() + "\t"
					+ "이름: " + p.getName() + "\t"
					+ "휴대전화: " + p.getHp() + "\t"
					+ "회사번호: " + p.getCompany()
					);
		}
		System.out.println();
	}

}
