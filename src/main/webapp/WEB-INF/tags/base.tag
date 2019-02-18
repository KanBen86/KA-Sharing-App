<%-- 
    Document   : base
    Created on : 18.02.2019, 14:53:44
    Author     : Benjamin Kanzler
--%>

<%@tag pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="main" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>

<html>
    <head>
        <meta charset="utf-8">

        <title>🗑️ KA-Sharing | ${title}</title>
        <link rel="stylesheet" href="<c:url value="/style.css"/>" />

        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <header>
            <jsp:invoke fragment="header"></jsp:invoke>
        </header>
        <main>
            <jsp:invoke fragment="main"></jsp:invoke>
        </main>
        <footer>
            <jsp:invoke fragment="footer"></jsp:invoke>
        </footer>
    </body>
</html>