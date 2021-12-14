package membership;

import common.JDBConnect;
/*
DAO(Data Access Object) : 실제 데이터베이스에 접근하여
	여러가지 CRUD 작업을 하기위한 객체.
*/

//DB 연결을 위한 클래스를 상속한다. 
public class MemberDAO extends JDBConnect {
	
	//인자가 4개인 부모의 생성자를 호출하여 연결한다. 
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	/*
	사용자가 입력한 아이디, 패스워드를 통해 회원테이블을 확인한 후
	존재하는 정보인 경우 DTO객체에 그 정보를 담아 반환한다.
	*/
	public MemberDTO getMemberDTO(String uid, String upass) {
		
		MemberDTO dto = new MemberDTO();
		//회원로그인을 위한 쿼리문 작성 
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			//쿼리문에 사용자가 입력한 아이디, 패스워드를 설정
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			//쿼리실행
			rs = psmt.executeQuery();
			
			//회원정보가 존재한다면 DTO객체에 회원정보를 저장한다. 
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//반환은 항상 함수를 호출한 지점에서 한다. 
		return dto;
	}
	
	//아이디 + 비밀번호 찾기 부분
	public MemberDTO getMemberInfo(String uid, String uname) {
        
    	MemberDTO dto = new MemberDTO();  
        
    	String query = "";
    	
    	if(uid.equals("")) 
    		query = "SELECT * FROM member WHERE name=?";
    	else 
    		query = "SELECT * FROM member WHERE id=? AND name=?";

        try {
            psmt = con.prepareStatement(query);
            if(uid.equals("")) {
                psmt.setString(1, uname);
            }
        	else {
        		psmt.setString(1, uid);     
        		psmt.setString(2, uname);
        	}
              
            rs = psmt.executeQuery();  
            
            if (rs.next()) {
                dto.setId(rs.getString("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString(3));
                dto.setRegidate(rs.getString(4));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return dto; 
    }
}
