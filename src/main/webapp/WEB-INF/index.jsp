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
        
        <c:if test="${detailFahrzeug.message != null}">
            <div class="alert alert-danger" role="alert">
                <p>${detailFahrzeug.message}</p>
            </div>
        </c:if>
        
        <c:if test="${!empty AlleFahrzeuge.responseList}">
            <form method="POST">
                <div class="card mb-3 p-3" style="max-width: 1000px;">
                    <div class="row">
                        <div class="col-md-1">
                            <label for="geliehenAb">Von: </label>
                        </div>
                        <div class="col-md-4">
                            <input name="filterDatumAb" type="date" class="form-control form-control-sm"
                                    value="${filterDatumAb}"></input>
                        </div>
                        <div class="col-md-1">
                            <label for="geliehenBis">Bis: </label>
                        </div>
                        <div class="col-md-4">
                            <input name="filterDatumBis" type="date" class="form-control form-control-sm"
                                value="${filterDatumBis}"></input>  
                        </div>
                        <div class="col-md-2 text-right">
                            <button type="submit" class="btn btn-primary btn-sm">Filtern</button>
                        </div>
                    
                    </div>
                </div>
            </form>
        </c:if>
        
        <c:choose>
            <c:when test="${!empty AlleFahrzeuge.responseList}">
                <c:forEach items="${AlleFahrzeuge.responseList}" var="fahrzeug">
                    <div class="card mb-3" style="max-width: 1000px;">
                        <div class="row no-gutters">
                            <div class="col-md-4 herstellerLogo">
                                <c:choose>
                                    <c:when test="${fahrzeug.bild == null}">
                                        <img src="<c:url value="/pictures/hersteller/${fahrzeug.hersteller}.png"/>" class="card-img mx-auto"
                                             style="min-height: 110px; max-height: 110px; width:auto; max-width: 100%;" alt="Herstellerbild">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value='${fahrzeug.bild}'/>" class="card-img" alt="Fahrzeugbild">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <h5 class="card-title">${fahrzeug.hersteller} ${fahrzeug.modell}</h5>
                                    <p class="card-text">Getriebe: ${fahrzeug.getriebeart}</p>
                                    <a href="<c:url value="/detail/${fahrzeug.id}/"/>">
                                        <button class="btn btn-primary btn-sm">
                                            Details
                                        </button>
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <p class="card-text">Preis/Tag: ${fahrzeug.preisProTag}</p>
                                    <p class="card-text">Sitzplätze: ${fahrzeug.plaetze}</p>
                                    <a href="<c:url value="/book/${fahrzeug.id}/"/>">
                                        <button type="button" class="btn btn-primary btn-sm">Buchen</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when> 
            <c:otherwise>
                <%-- Hinweis, dass es noch keine Fahrzeuge gibt --%>
                <div class="alert alert-danger" role="alert">
                    Es sind noch keine Fahrzeuge vorhanden.
                </div>
            </c:otherwise>
        </c:choose>        
    </jsp:attribute>
</template:base>
