<%-- 
    Document   : book
    Created on : 12.03.2019, 14:20:48
    Author     : Vincent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepickerStart").datepicker();
    });
    $(function () {
        $("#datepickerEnde").datepicker();
    });
</script>

<template:base>
    <jsp:attribute name="title">Buchen</jsp:attribute>
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
                            <p>
                                Wählen Sie ihr:
                                <br>
                                Startdatum: <input type="text" name="startDatum" id="datepickerStart">
                                und
                                Enddatum: <input type="text" name="endDatum" id="datepickerEnde">
                            </p>
                            <button type="submit" class="btn btn-success btn-sm m-3">Buchen</button>
                            <a href="<c:url value="/"/>">
                                <button type="button" class="btn btn-primary btn-sm m-3">Zurück zur Übersicht</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
</template:base>

