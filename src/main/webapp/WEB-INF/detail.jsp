<%-- 
    Document   : detail
    Created on : 25.02.2019, 17:54:30
    Author     : chp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Detailansicht</jsp:attribute>
    <jsp:attribute name="main">
        
        <form>
            <!--Card von der Übersichtsseite-->
            <div class="card mb-3 mt-3">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <img src="<c:url value="/pictures/TestAuto.png"/>" class="card-img" alt="Testauto Bild">
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
                
            <!--weitere Infos für das Auto-->
            <div class="card mt-3">
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="plaetze">Plätze: </label>
                        <input name="plaetze" type="number" class="form-control"
                               value="${detailFahrzeug.plaetze}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="raeder">Räder: </label>
                        <input name="raeder" type="number" class="form-control"
                               value="${detailFahrzeug.raeder}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="tueren">Türen: </label>
                        <input name="tueren" type="number" class="form-control"
                               value="${detailFahrzeug.tueren}"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="klimaanlage">Klimaanlage: </label>
                        <input name="klimaanlage" type="checkbox"
                               value="${detailFahrzeug.klimaanlage}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="elFensterheber">elektischer Fensterheber: </label>
                        <input name="elFensterheber" type="checkbox"
                               value="${detailFahrzeug.elektrischeFensterheber}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="servolenkung">Servolenkung: </label>
                        <input name="servolenkung" type="checkbox"
                               value="${detailFahrzeug.servolenkung}"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="abs">ABS: </label>
                        <input name="abs" type="checkbox"
                               value="${detailFahrzeug.abs}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="esp">ESP: </label>
                        <input name="esp" type="checkbox"
                               value="${detailFahrzeug.esp}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cd">CD: </label>
                        <input name="cd" type="checkbox"
                               value="${detailFahrzeug.cd}"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="navigation">Navigationsgerät: </label>
                        <input name="navigation" type="checkbox"
                               value="${detailFahrzeug.navigation}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="fahrassistent">Fahrassistent: </label>
                        <input name="fahrassistent" type="checkbox"
                               value="${detailFahrzeug.fahrassiSystem}"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="leihstatus">Verfügbarkeit: </label>
                        <select name="leihstatus" >
                            <c:forEach items="${statusList}" var="statusValue">
                                <option value="${statusValue}">
                                    ${statusValue}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="anschaffungsDatum">Anschaffungsdatum: </label>
                        <input name="anschaffungsDatum" type="date"
                               value="${detailFahrzeug.anschaffungsDatum}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="anschaffungsPreis">Anschaffungspreis: </label>
                        <input name="anschaffungsPreis" type="number"
                               value="${detailFahrzeug.anschaffungsPreis}"></input>
                    </div>
                </div>
                
            </div>
        
            
            <button type="button" class="btn btn-primary m-3">Zurück</button>
            <button type="button" class="btn btn-success m-3">Speichern</button>
    
        </form>
        
    </jsp:attribute>
</template:base>
