package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";


	// 드라이버 연결
	private void getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: 드라이버 로딩 실패- " + e);
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}

	}


	// 필드 close
	private void close() {

		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}


	// SELECT
	public List<PhoneVo> phoneList() {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();

		getConnection();

		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id 순번, "
					+ " 	name 이름, "
					+ " 	hp 핸드폰번호, "
					+ " 	company 회사번호 "
					+ " FROM "
					+ " 	person"
					);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				phoneList.add(new PhoneVo(rs.getInt("순번"), rs.getString("이름"), rs.getString("핸드폰번호"), rs.getString("회사번호")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return phoneList;
	}


	// SELECT search
	public List<PhoneVo> phoneList(String keyword) {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();

		getConnection();

		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id 순번, "
					+ " 	name 이름, "
					+ " 	hp 휴대전화, "
					+ " 	company 회사번호 "
					+ " FROM "
					+ " 	person "
					+ " WHERE "
					+ " 	name LIKE '%" + keyword + "%' "
					+ " 	OR hp LIKE '%" + keyword + "%' "
					+ " 	OR company LIKE '%" + keyword + "%' "
					);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				phoneList.add(new PhoneVo(rs.getInt("순번"), rs.getString("이름"), rs.getString("휴대전화"), rs.getString("회사번호")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return phoneList;
	}


	// INSERT
	public int phoneInsert(PhoneVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" INSERT INTO "
					+ " 		person "
					+ " VALUES "
					+ " 	( seq_person_id.NEXTVAL, ?, ?, ? ) "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			
			count = pstmt.executeUpdate();
			
//			System.out.println("[" + count + "건 등록되었습니다.]");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}


	// UPDATE
	public int phoneUpdate(PhoneVo p) {
		int count = -1;

		getConnection();

		try {
			pstmt = conn.prepareStatement(
					" UPDATE "
					+ " 	person "
					+ " SET "
					+ " 	name = ?, "
					+ " 	hp = ?, "
					+ " 	company = ? "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			pstmt.setInt(4, p.getPersonID());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return count;
	}


	// DELETE
	public int phoneDelete(PhoneVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 		person "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setInt(1, p.getPersonID());

			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}

}
