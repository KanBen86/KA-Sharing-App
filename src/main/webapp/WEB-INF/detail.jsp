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
            <div class="mb-3 mt-3">
                <div class="form-row">
                    <div class="col-md-4">
                        <img src="<c:url value="/pictures/TestAuto.png"/>" class="card-img" alt="Testauto Bild">
                    </div>
                    <div class="col-md-4">
                        <label for="hersteller">Hersteller: </label>
                        <select name="hersteller" class="form-control form-control-sm" >
                            <option value="null" var=""></option>
                            <c:forEach items="${herstellerListe}" var="herstellerValue">
                                <option value="${herstellerListe}">
                                    ${herstellerValue}
                                </option>
                            </c:forEach>
                        </select>
                        <label for="modell">Modell: </label>
                        <input name="modell" type="text" class="form-control form-control-sm"
                               value="${detailFahrzeug.modell}"></input>
                    </div>
                    <div class="col-md-4">
                        <label for="typ">Typ: </label>
                        <select name="typ" class="form-control form-control-sm" >
                            <option value="null" var=""></option>
                            <c:forEach items="${typListe}" var="typValue">
                                <option value="${typListe}">
                                    ${typValue}
                                </option>
                            </c:forEach>
                        </select>
                        <label for="ausfuehrung">Ausführung: </label>
                        <input name="ausfuehrung" type="text" class="form-control form-control-sm"
                               value="${detailFahrzeug.ausfuehrung}"></input>

                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="hauptuntersuchung">Hauptuntersuchung bis: </label>
                        <input name="hauptuntersuchung" type="date" lass="form-control form-control-sm"
                                   value="${detailFahrzeug.hauptuntersuchungBis}"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="klasse">Klasse: </label>
                        <select name="klasse" class="form-control form-control-sm" >
                            <option value="null" var=""></option>
                            <c:forEach items="${klasseListe}" var="klasseValue">
                                <option value="${klasseListe}">
                                    ${klasseValue}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="preisProTag">Preis pro Tag: </label>
                        <input name="preisProTag" type="number" class="form-control form-control-sm"
                               value="${detailFahrzeug.preisProTag}"></input>
                    </div>
                </div>
            </div>
                
            <!--weitere Infos für das Auto-->
            <div class="card mt-3">
                <div class="card-body">
                    
                
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="plaetze">Plätze: </label>
                            <input name="plaetze" type="number" class="form-control form-control-sm"
                                   value="${detailFahrzeug.plaetze}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="raeder">Räder: </label>
                            <input name="raeder" type="number" class="form-control form-control-sm"
                                   value="${detailFahrzeug.raeder}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="tueren">Türen: </label>
                            <input name="tueren" type="number" class="form-control form-control-sm"
                                   value="${detailFahrzeug.tueren}"></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="klimaanlage">Klimaanlage: </label>
                            <br/>
                            <input name="klimaanlage" type="checkbox"
                                   value="${detailFahrzeug.klimaanlage}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="elFensterheber">elektischer Fensterheber: </label>
                            <br/>
                            <input name="elFensterheber" type="checkbox"
                                   value="${detailFahrzeug.elektrischeFensterheber}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="servolenkung">Servolenkung: </label>
                            <br/>
                            <input name="servolenkung" type="checkbox"
                                   value="${detailFahrzeug.servolenkung}"></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="abs">ABS: </label>
                            <br/>
                            <input name="abs" type="checkbox"
                                   value="${detailFahrzeug.abs}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="esp">ESP: </label>
                            <br/>
                            <input name="esp" type="checkbox"
                                   value="${detailFahrzeug.esp}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cd">CD: </label>
                            <br/>
                            <input name="cd" type="checkbox"
                                   value="${detailFahrzeug.cd}"></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="navigation">Navigationsgerät: </label>
                            <br/>
                            <input name="navigation" type="checkbox"
                                   value="${detailFahrzeug.navigation}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="fahrassistent">Fahrassistent: </label>
                            <br/>
                            <input name="fahrassistent" type="checkbox"
                                   value="${detailFahrzeug.fahrassiSystem}"></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="leihstatus">Verfügbarkeit: </label>
                            <select name="leihstatus" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${statusList}" var="statusValue">
                                    <option value="${statusValue}">
                                        ${statusValue}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="anschaffungsDatum">Anschaffungsdatum: </label>
                            <input name="anschaffungsDatum" type="date" lass="form-control form-control-sm"
                                   value="${detailFahrzeug.anschaffungsDatum}"></input>
                        </div>
                       <div class="form-group col-md-4">
                            <label for="anschaffungsPreis">Anschaffungspreis: </label>
                            <input name="anschaffungsPreis" type="number" lass="form-control form-control-sm"
                                   value="${detailFahrzeug.anschaffungsPreis}"></input>
                        </div>
                    </div>
                
                </div>
            </div>
        
            
            <button type="button" class="btn btn-primary m-3">Zurück</button>
            <button type="button" class="btn btn-success m-3">Speichern</button>
    
        </form>
        
    </jsp:attribute>
</template:base>
