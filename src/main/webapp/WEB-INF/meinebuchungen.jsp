<%-- 
    Document   : meinebuchungen
    Created on : 22.03.2019, 20:31:35
    Author     : chp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Meine Buchungen</jsp:attribute>
    <jsp:attribute name="main">
        <a href="<c:url value="/"/>">
            <button type="button" class="btn btn-primary btn-sm m-3">Zurück zur Übersicht</button>
        </a>
            
        <c:if test="${MeineFahrzeuge.message != null}">
            <div class="alert alert-danger" role="alert">
                <p>${MeineFahrzeuge.message}</p>
            </div>
        </c:if>    
            
        <c:choose>
            <c:when test="${!empty MeineFahrzeuge.responseList}">
                <c:forEach items="${MeineFahrzeuge.responseList}" var="buchung">
                    <div class="card mb-3" style="max-width: 1000px;">
                        <div class="row no-gutters">
                            <div class="col-md-4 herstellerLogo">
                                <c:choose>
                                    <c:when test="${buchung.fahrzeug.bild == null}">
                                        <img src="<c:url value="/pictures/hersteller/${buchung.fahrzeug.hersteller}.png"/>" class="card-img mx-auto"
                                             style="min-height: 110px; max-height: 110px; width:auto; max-width: 100%;" alt="Herstellerbild">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value='${buchung.fahrzeug.bild}'/>" class="card-img" alt="Fahrzeugbild">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <p class="card-text">Hersteller: ${buchung.fahrzeug.hersteller}</h5>
                                    <p class="card-text">Modell:     ${buchung.fahrzeug.modell}</h5>
                                    <p class="card-text">Typ:        ${buchung.fahrzeug.getriebeart}</p>
                                    <p class="card-text">Getriebe:   ${buchung.fahrzeug.getriebeart}</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <p class="card-text">Nutzer: ${buchung.nutzer.nickName}</p>
                                    <p class="card-text">von:    ${buchung.geliehenAb}</p>
                                    <p class="card-text">bis:    ${buchung.geliehenBis}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when> 
            <c:otherwise>
                <%-- Hinweis, dass es noch keine Fahrzeuge gibt --%>
                <div class="alert alert-warning" role="alert">
                    Sie haben gerade keine Fahrzeuge ausgeliehen.
                </div>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>
