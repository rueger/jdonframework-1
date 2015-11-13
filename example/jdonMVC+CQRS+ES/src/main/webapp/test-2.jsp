<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="org.apache.ibatis.mapping.Environment"%>
<%@page import="org.apache.ibatis.session.Configuration"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@page import="org.apache.ibatis.transaction.TransactionFactory"%>
<%@page import="org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.jdon.framework.test.*"%>
<html>
<head>
<title>testSqlSessionFactory</title>
</head>
<body>
<%
	Context ctx = new InitialContext();
    Context envCtx = (Context) ctx.lookup("java:comp/env");
    DataSource ds = (DataSource) envCtx.lookup("jdbc/myds");

    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, ds);
    Configuration configuration = new Configuration(environment);
    configuration.addMapper(test2jspIF.class);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


    SqlSession sqlsession = sqlSessionFactory.openSession();


    String userid;

    try {
	   test2jspIF mapper = sqlsession.getMapper(test2jspIF.class);  
	   userid = (String) mapper.selectUserId(1);
    } finally {
      sqlsession.close();
    }

    out.println("userid = " + userid);
    
%>
</body>
</html>