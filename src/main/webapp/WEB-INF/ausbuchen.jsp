<%-- 
    Document   : ausbuchen
    Created on : 18.03.2019, 14:33:35
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<template:base>
    <jsp:attribute name="title">Ausbuchen</jsp:attribute>
    <jsp:attribute name="main">
        <form method="POST">
            <div class="card mb-3" style="max-width: 1000px;">
                <div class="row no-gutters">
                    <div class="col-md-4 herstellerLogo">
                        <c:choose>
                            <c:when test="${detailFahrzeug.response.bild == null}">
                                <img src="<c:url value="/pictures/hersteller/${detailFahrzeug.response.hersteller}.png"/>" class="card-img mx-auto"
                                     style="min-height: 110px; max-height: 110px; width:auto; max-width: 100%;" alt="Herstellerbild">
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='${detailFahrzeug.response.bild}'/>" class="card-img" alt="Fahrzeugbild">
                            </c:otherwise>
                         </c:choose>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Ausgewähltes Fahrzeug: ${detailFahrzeug.response.modell}</h5>
                            <br>
                            <p class="card-text">Fahrzeug ID: ${detailFahrzeug.response.id}</p>
                            <br>
                            <p>
                                Wählen Sie ihr den Status:
                            </p>
                            <select name="ausbuchungsGrund" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${ausbuchungList}" var="statusValue">
                                    <option value="${statusValue}"
                                            ${statusValue == detailFahrzeug.response.grund ? 'selected' : ''}>
                                        ${statusValue}
                                    </option>
                                </c:forEach>
                            </select>
                            <p>
                                Kommentar:
                            </p>
                            <textarea name='kommentar'>Hier Kommentar einfügen</textarea>
                            <br>
                            <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>
                            <a href="<c:url value="/detail/${detailFahrzeug.response.id}"/>">
                                <button type="button" class="btn btn-primary btn-sm m-3">Zurück zu Detail</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
</template:base>
