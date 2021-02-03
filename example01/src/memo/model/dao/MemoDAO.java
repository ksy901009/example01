package memo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.DbExample;
import memo.model.dto.MemoDTO;

public class MemoDAO {
	Connection conn = DbExample.dbConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemoDAO() {
		
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.dbConnClose(rs, pstmt, conn);
	}

	public int insertMemo(MemoDTO dto) {
		int result = 0;
		
		try {
			String sql = "insert into memo(no, writer, content, regiDate) values(seq_memo.nextVal, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			
			Timestamp regiDate = new Timestamp(System.currentTimeMillis());
			
			pstmt.setTimestamp(3, regiDate);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<MemoDTO> getListAll(MemoDTO dto, int startRecord, int lastRecord) {
		ArrayList<MemoDTO> list = new ArrayList<>();
		
		try {
//			String sql = "select * from memo where memberNo = ?";
			
			String basic_sql = ""
					+ "select * from memo where no > ? "
					+ "order by no desc";
					
					
			String sql = "select * from ("
			+ "select b.*, rownum rnum from"
			+ "(" + basic_sql + ") b"
			+ ") where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, startRecord);
			pstmt.setInt(3, lastRecord);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getString("regiDate"));
				
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getTotalRecord(MemoDTO dto) {
		int result = 0;
		
		try {
			String sql = "select count(*) from memo where no > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}