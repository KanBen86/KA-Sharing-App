<%-- 
    Document   : nutzerprofil
    Created on : 20.03.2019, 14:01:42
    Author     : chp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Nutzerprofil</jsp:attribute>
    <jsp:attribute name="main">
        
        <form method="POST">
            
            <div class="card">
                <div class="card-body mb-3 mt-3">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="id">Kundennummer: </label>
                            <input name="id" type="number" class="form-control form-control-sm"
                                    value="${nutzer.response.id}" readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="nickName">Nick Name: </label>
                            <input name="nickName" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.nickName}" readonly></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="email">E-Mail: </label>
                            <input name="email" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.email}" readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="rolle">Rolle: </label>
                            <input name="rolle" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.rolle}" readonly></input>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="card">
                <div class="card-body mb-3 mt-3">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="vorname">Vorname: </label>
                            <input name="vorname" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.vorname}" required></input>
                        </div>
                        <div class="col-md-6">
                            <label for="name">Name: </label>
                            <input name="name" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.name}" required></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="strasse">Straße: </label>
                            <input name="strasse" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.strasse}" required></input>
                        </div>
                        <div class="col-md-6">
                            <label for="hausnummer">Hausnummer: </label>
                            <input name="hausnummer" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.hausnummer}" required></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="hausnummer">Hausnummer: </label>
                            <input name="hausnummer" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.hausnummer}" required></input>
                        </div>
                        <div class="col-md-6">
                            <label for="ort">Ort: </label>
                            <input name="ort" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.ort}" required></input>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="card">
                <div class="card-body mb-3 mt-3">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="institut">Bankinstitut: </label>
                            <input name="institut" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.institut}" required></input>
                        </div>
                        <div class="col-md-6">
                            <label for="bic">BIC: </label>
                            <input name="bic" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.bic}" required></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12">
                            <label for="iban">IBAN: </label>
                            <input name="iban" type="text" class="form-control form-control-sm"
                                    value="${nutzer.response.iban}" required></input>
                        </div>
                    </div>
                </div>
            </div>
                        
                        
            <a href="<c:url value="/"/>">
                <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
            </a>
            
            <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>
            
        </form>
         
    </jsp:attribute>
</template:base>