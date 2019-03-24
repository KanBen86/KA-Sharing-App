<%-- 
    Document   : zurueckgeben
    Created on : 17.03.2019, 10:43:50
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<template:base>
    <jsp:attribute name="title">Zurückgeben</jsp:attribute>
    <jsp:attribute name="main">
        <form method="POST">
            <div class="card mb-3" style="max-width: 1000px;">
                <div class="row no-gutters">
                    <div class="col-md-4 herstellerLogo">
                        <c:choose>
                            <c:when test="${detailFahrzeug.response.bild == null}">
                                <img src="<c:url value="/pictures/hersteller/${detailFahrzeug.response.hersteller}.png"/>" class="card-img mx-auto"
                                     style="min-height: 110px; max-height: 110px; width:auto;  max-width: 100%;" alt="Herstellerbild">
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='${detailFahrzeug.response.bild}'/>" class="card-img" alt="Fahrzeugbild">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Ausgewähltes Fahrzeug: <c:out value="${detailFahrzeug.response.modell}"></c:out></h5>
                            <br>
                            <p class="card-text">Fahrzeug ID: <c:out value="${detailFahrzeug.response.id}"></c:out></p>
                            <br>
                            <p>Wählen Sie ihr den Status:</p>
                            <select name="leihstatus" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${statusList}" var="statusValue">
                                    <option value="${statusValue}"
                                            ${statusValue == detailFahrzeug.response.leihStatus ? 'selected' : ''}>
                                        ${statusValue}
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                            <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>
                            <a href="<c:url value="/buchungen"/>">
                                <button type="button" class="btn btn-primary btn-sm m-3">Zurück zu Buchungen</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
</template:base>
