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
        
        <form method="POST">
            <!--Card von der Übersichtsseite-->
            <div class="card">
                <div class="card-body mb-3 mt-3">
                    <div class="form-row">
                        <div class="col-md-4">
                            <c:choose>
                                <c:when test="${detailFahrzeug.response.bild != null}">
                                    <div class="d-flex justify-content-center fulldiv">
                                        <img src="<c:url value="${detailFahrzeug.response.bild}"/>" class="card-img" alt="Bild vom Auto">
                                    </div>        
                                </c:when>
                                <c:otherwise> 
                                    <div class="align-middle fulldiv">
                                        <div class="d-flex justify-content-center">
                                            <i class="fas fa-car fa-9x"></i>
                                        </div>
                                        <br />
                                        <div class="d-flex justify-content-center">
                                            <input id="bild" type="file" name="file" value="${detailFahrzeug.response.bild}"/>
                                        </div>
                                        <div class="alert alert-danger p-0 m-2" role="alert">
                                            Es ist noch keine Bild gespeichert.
                                        </div>
                                    </div>                                   
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-4">
                            <label for="hersteller">Hersteller: </label>
                            <select name="hersteller" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${herstellerList}" var="herstellerValue">
                                    <option value="${herstellerValue}"
                                            ${herstellerValue == detailFahrzeug.response.hersteller ? 'selected' : ''}>
                                        ${herstellerValue}
                                    </option>
                                </c:forEach>
                            </select>
                        
                            <label for="modell">Modell: </label>
                            <input name="modell" type="text" class="form-control form-control-sm"
                                value="${detailFahrzeug.response.modell}"></input>
                               
                            <label for="klasse">Klasse: </label>
                            <select name="klasse" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${klassenList}" var="klasseValue">
                                    <option value="${klasseValue}"
                                            ${klasseValue == detailFahrzeug.response.klasse ? 'selected' : ''}>
                                        ${klasseValue}
                                    </option>
                                </c:forEach>
                            </select>
                        
                            <label for="getriebeart">Getriebeart: </label>
                            <select name="getriebeart" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${getriebeList}" var="getriebeValue">
                                    <option value="${getriebeValue}"
                                            ${getriebeValue == detailFahrzeug.response.getriebeart ? 'selected' : ''}>
                                        ${getriebeValue}
                                    </option>
                                </c:forEach>
                            </select>
                        
                        </div>
                        <div class="col-md-4">
                            <label for="typ">Typ: </label>
                            <select name="typ" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${typList}" var="typValue">
                                    <option value="${typValue}"
                                            ${typValue == detailFahrzeug.response.typ ? 'selected' : ''}>
                                        ${typValue}
                                    </option>
                                </c:forEach>
                            </select>
                        
                            <label for="ausfuehrung">Ausführung: </label>
                            <input name="ausfuehrung" type="text" class="form-control form-control-sm"
                                   value="${detailFahrzeug.response.ausfuehrung}"></input>
                        
                            <label for="preisProTag">Preis pro Tag: </label>
                            <input name="preisProTag" type="number" class="form-control form-control-sm"
                                   value="${detailFahrzeug.response.preisProTag}"></input>
                        
                            <label for="hauptuntersuchung">Hauptuntersuchung bis: </label>
                            <br/>
                            <input name="hauptuntersuchung" type="date" lass="form-control form-control-sm"
                                       value="${detailFahrzeug.response.hauptuntersuchungBis}"></input>
                        </div>
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
                                   value="${detailFahrzeug.response.plaetze}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="raeder">Räder: </label>
                            <input name="raeder" type="number" class="form-control form-control-sm"
                                   value="${detailFahrzeug.response.raeder}"></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="tueren">Türen: </label>
                            <input name="tueren" type="number" class="form-control form-control-sm"
                                    value="${detailFahrzeug.response.tueren}"></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="klimaanlage">Klimaanlage: </label>
                            <br/>
                            <input name="klimaanlage" type="checkbox"
                                   value="${detailFahrzeug.response.klimaanlage}"
                                   <c:if test="${detailFahrzeug.response.klimaanlage}">checked="true"</c:if>
                                   ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="elFensterheber">elektischer Fensterheber: </label>
                            <br/>
                            <input name="elFensterheber" type="checkbox"
                                   value="${detailFahrzeug.response.elektrischeFensterheber}"
                                    <c:if test="${detailFahrzeug.response.elektrischeFensterheber}">checked="true"</c:if>
                                    ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="servolenkung">Servolenkung: </label>
                            <br/>
                            <input name="servolenkung" type="checkbox"
                                   value="${detailFahrzeug.response.servolenkung}"
                                   <c:if test="${detailFahrzeug.response.servolenkung}">checked="true"</c:if>
                                    ></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="abs">ABS: </label>
                            <br/>
                            <input name="abs" type="checkbox"
                                   value="${detailFahrzeug.response.abs}"
                                    <c:if test="${detailFahrzeug.response.abs}">checked="true"</c:if>
                                    ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="esp">ESP: </label>
                            <br/>
                            <input name="esp" type="checkbox"
                                   value="${detailFahrzeug.response.esp}"
                                   <c:if test="${detailFahrzeug.response.esp}">checked="true"</c:if>
                                    ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cd">CD: </label>
                            <br/>
                            <input name="cd" type="checkbox"
                                   value="${detailFahrzeug.response.cd}"
                                   <c:if test="${detailFahrzeug.response.cd}">checked="true"</c:if>
                                    ></input>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="navigation">Navigationsgerät: </label>
                            <br/>
                            <input name="navigation" type="checkbox"
                                   value="${detailFahrzeug.response.navigation}"
                                   <c:if test="${detailFahrzeug.response.navigation}">checked="true"</c:if>
                                    ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="fahrassistent">Fahrassistent: </label>
                            <br/>
                            <input name="fahrassistent" type="checkbox"
                                   value="${detailFahrzeug.response.fahrassiSystem}"
                                    <c:if test="${detailFahrzeug.response.fahrassiSystem}">checked="true"</c:if>
                                    ></input>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="treibstoff">Treibstoffart: </label>
                            <select name="treibstoff" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${treibstoffList}" var="treibstoffValue">
                                    <option value="${treibstoffValue}"
                                            ${treibstoffValue == detailFahrzeug.response.treibstoff ? 'selected' : ''}>
                                        ${treibstoffValue}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="leihstatus">Verfügbarkeit: </label>
                            <select name="leihstatus" class="form-control form-control-sm" >
                                <option value="null" var=""></option>
                                <c:forEach items="${statusList}" var="statusValue">
                                    <option value="${statusValue}"
                                            ${statusValue == detailFahrzeug.response.leihStatus ? 'selected' : ''}>
                                        ${statusValue}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="anschaffungsDatum">Anschaffungsdatum: </label>
                            <br/>
                            <input name="anschaffungsDatum" type="date" lass="form-control form-control-sm"
                                   value="${detailFahrzeug.response.anschaffungsDatum}"></input>
                        </div>
                       <div class="form-group col-md-4">
                            <label for="anschaffungsPreis">Anschaffungspreis in €: </label>
                            <br/>
                            <input name="anschaffungsPreis" type="number" lass="form-control form-control-sm"
                                   value="${detailFahrzeug.response.anschaffungsPreis}"></input>
                        </div>
                    </div>
                
                </div>
            </div>
        
            <a href="<c:url value="/"/>">
                <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
            </a>
            
            <c:if test="${detailFahrzeug.response.id != null}">
                <a href="<c:url value="/ausbuchen/${detailFahrzeug.response.id}"/>">
                    <button type="button" class="btn btn-danger btn-sm m-3">Fahrzeug ausbuchen</button>
                </a>
            </c:if>
            
            <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>
            
            
        </form>
        
    </jsp:attribute>
</template:base>
