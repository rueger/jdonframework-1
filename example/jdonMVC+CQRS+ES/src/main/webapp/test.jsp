<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<html>
<head>
<title>testJNDI</title>
</head>
<body>
<%      
Context ctx = new InitialContext();
Context envCtx = (Context) ctx.lookup("java:comp/env");
DataSource ds = (DataSource) envCtx.lookup("jdbc/myds");
Connection conn = ds.getConnection();
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("select * from TESTUSER");
while (rs.next()) {
        out.println(rs.getInt(1) + "<br>");
}
rs.close();
stmt.close();
conn.close();
%>
</body>
</html>