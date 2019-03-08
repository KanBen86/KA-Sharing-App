<%-- 
    Document   : index
    Created on : 18.02.2019, 14:50:03
    Author     : Benjamin Kanzler, Vincent Neuhoff
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Index</jsp:attribute>
    <jsp:attribute name="main">
        <div class="card mb-3" style="max-width: 1000px;">
             <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="<c:url value="/pictures/TestAuto.png"/>" class="card-img" alt="...">
                </div>
                <div class="col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">Mercedes Test</h5>
                        <p class="card-text">Getriebe: Automatik</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card-body">
                        <p class="card-text">Preis/Tag: 3000€</p>
                        <button type="button" class="btn btn-primary btn-lg">Buchen</button>
                    </div>
                </div>
            </div>
        </div>                
    </jsp:attribute>
</template:base>
