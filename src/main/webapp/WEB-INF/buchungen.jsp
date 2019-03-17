<%-- 
    Document   : buchungen
    Created on : 17.03.2019, 10:26:58
    Author     : chp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Buchungen</jsp:attribute>
    <jsp:attribute name="main">
        <a href="<c:url value="/"/>">
            <button type="button" class="btn btn-primary btn-sm m-3">Zurück zur Übersicht</button>
        </a>
        <c:choose>
            <c:when test="${!empty AlleBuchungsFahrzeuge}">
                <c:forEach items="${AlleBuchungsFahrzeuge}" var="buchung">
                    <div class="card mb-3" style="max-width: 1000px;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="<c:url value="/pictures/TestAuto.png"/>" class="card-img" alt="Bild des Autos">
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
                                    <a href="<c:url value="/zurueckgeben/${buchung.fahrzeug.id}/"/>">
                                        <button type="button" class="btn btn-primary btn-sm">Zurückgeben</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when> 
            <c:otherwise>
                <%-- Hinweis, dass es noch keine Fahrzeuge gibt --%>
                <div class="message">
                    Es sind gerade keine Fahrzeuge ausgeliehen.
                </div>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>
