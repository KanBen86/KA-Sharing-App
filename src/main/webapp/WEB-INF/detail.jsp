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
                        <input id="plaetze" type="number" class="form-control"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="raeder">Räder: </label>
                        <input id="raeder" type="number" class="form-control"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="tueren">Türen: </label>
                        <input id="tueren" type="number" class="form-control"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Klimaanlage: </label>
                        <input id="klimaanlage" type="checkbox"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label>elektischer Fensterheber: </label>
                        <input id="elFensterheber" type="checkbox"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Servolenkung: </label>
                        <input id="servolenkung" type="checkbox"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>ABS: </label>
                        <input id="abs" type="checkbox"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label>ESP: </label>
                        <input id="esp" type="checkbox"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label>CD: </label>
                        <input id="cd" type="checkbox"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Navigationsgerät: </label>
                        <input id="navigation" type="checkbox"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Fahrassistent: </label>
                        <input id="fahrassistent" type="checkbox"></input>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="leihstatus">Verfügbarkeit: </label>
                        <select id="leihstatus">
                            <option>Test</option>
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="anschaffungsDatum"></label>
                        <input id="anschaffungsDatum" type="date"></input>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="anschaffungsPreis"></label>
                        <input id="anschaffungsPreis" type="number"></input>
                    </div>
                </div>
                
            </div>
        
            
        <button type="button" class="btn btn-primary m-3">Zurück</button>
        <button type="button" class="btn btn-success m-3">Speichern</button>
    
        </form>
    </jsp:attribute>
</template:base>
