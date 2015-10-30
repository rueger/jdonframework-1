<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>   
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>User</title>
</head>
<body>

<div style="text-align: center;">
    <h1>User Edit</h1>
    <hr>

    <form action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="user.userId" value="${user.userId}"/>
        username:<input type="text" name="user.username" value="${user.username}"/>
        email:<input type="text" name="user.email" value="${user.email}"/>
        <br>
        <input type="submit" value="submit"/>
    </form>
    
    <form action="${pageContext.request.contextPath}/showUpload/${user.userId}" method="post">
        <input type="hidden" name="_method" value="DELETE" />
        <c:if test="${not empty  user.uploadFile}">
        	  pic :<img src="<%=request.getContextPath() %>/showUpload?pid=${user.userId}"  border='0'/>	
        </c:if>
        <input type="submit" value="删除图片" />
        
    </form>
</div>
</body>
</html>