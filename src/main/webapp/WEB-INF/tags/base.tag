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
        <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />         
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>  
        <link rel="stylesheet" href="<c:url value="/style.css"/>" />
        
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <header>
            <!--Navigationsbar-->
            <nav class="navbar sticky-top navbar-light bg-light p-0">
                <!--Logo und Name-->
                <a class="navbar-brand p-0">
                    <img class="logo p-0" src="<c:url value="/pictures/LogoWithout.png"/>" alt="KA-SHARING Logo" />
                    <span class="brand h3">KA-SHARING</span>
                </a>
                    
                <div>
                    <!--+ Fahrzeug Button-->
                    <a href="<c:url value="/newCar"/>">
                        <button type="button" class="btn btn-success btn-sm">+ Fahrzeug</button>
                    </a>
                    <!--An-Abmeldebutton-->
                    <button type="button" class="btn btn-primary btn-sm mr-2">Anmelden</button>
       
                </div>
                
            </nav>
        </header>
        <main>
            <div class="container">
                <jsp:invoke fragment="main"></jsp:invoke>
            </div>
        </main>
        <footer>
            <jsp:invoke fragment="footer"></jsp:invoke>
        </footer>
    </body>
</html>