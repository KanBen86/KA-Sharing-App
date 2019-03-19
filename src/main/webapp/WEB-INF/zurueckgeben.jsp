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
                    <div class="col-md-4">
                        <img src="<c:url value="/pictures/TestAuto.png"/>" class="card-img" alt="...">
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
                            <a href="<c:url value="/"/>">
                                <button type="button" class="btn btn-primary btn-sm m-3">Zurück zu Buchungen</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
</template:base>
