<%@page import="utils.JSFunction"%>
<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String find = request.getParameter("find");
String id = request.getParameter("id");
String name = request.getParameter("name");

String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

MemberDTO memberDTO = null;
MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
if(find.equals("id_find")){ //아이디 찾기	
	memberDTO = dao.getMemberInfo("", name);
	if(memberDTO.getId()==null){
		JSFunction.alertBack("귀하의 정보가 없습니다.", out);
		return;
	}
	dao.close();
	JSFunction.alertLocation("귀하의 아이디는 "+ memberDTO.getId() +" 입니다", "Login.jsp", out);
}
else if(find.equals("pw_find")){ //비번 찾기	
	memberDTO = dao.getMemberInfo(id, name);
	if(memberDTO.getPass()==null){
		JSFunction.alertBack("귀하의 정보가 없습니다.", out);
		return;
	}
	dao.close();
	JSFunction.alertLocation("귀하의 비밀번호는 "+ memberDTO.getPass() +" 입니다", "Login.jsp", out);
}
%>