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

        <c:if test="${kunde != null}">
            <form method="POST">

                <div class="card m-0 p-0 mt-3 p-2">
                    <div class="card-body m-0 p-0">
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <p>Kundennummer: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${kunde.response.id}"></c:out>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Nick Name: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${kunde.response.nickName}"></c:out>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <p>E-Mail: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${kunde.response.email}"></c:out>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Rolle: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Kunde</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card m-0 p-0 mt-3 p-2 pb-4">
                    <div class="card-body m-0 p-0">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="vorname">Vorname: </label>
                                <input name="vorname" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.vorname}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="name">Name: </label>
                                <input name="name" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.name}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="strasse">Straße: </label>
                                <input name="strasse" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.strasse}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="hausnummer">Hausnummer: </label>
                                <input name="hausnummer" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.hausnummer}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="plz">PLZ: </label>
                                <input name="plz" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.plz}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="ort">Ort: </label>
                                <input name="ort" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.adresse.ort}" required></input>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card m-0 p-0 mt-3 p-2 pb-4">
                    <div class="card-body m-0 p-0">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="institut">Bankinstitut: </label>
                                <input name="institut" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.bank.insitut}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="bic">BIC: </label>
                                <input name="bic" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.bank.bic}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="iban">IBAN: </label>
                                <input name="iban" type="text" class="form-control form-control-sm"
                                       value="${kunde.response.bank.iban}" required></input>
                            </div>
                        </div>
                    </div>
                </div>


                <a href="<c:url value="/"/>">
                    <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
                </a>

                <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>

            </form>
        </c:if>

        <c:if test="${mitarbeiter != null}">
            <form method="POST">

                <div class="card m-0 p-0 mt-3 p-2">
                    <div class="card-body m-0 p-0">
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <p>Mitarbeiternummer: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${mitarbeiter.response.id}"></c:out>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Nick Name: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${mitarbeiter.response.nickName}"></c:out>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <p>E-Mail: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <c:out value="${mitarbeiter.response.email}"></c:out>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Rolle: </p>
                            </div>
                            <div class="form-group col-md-3">
                                <p>Mitarbeiter</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card m-0 p-0 mt-3 p-2 pb-4">
                    <div class="card-body m-0 p-0">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="vorname">Vorname: </label>
                                <input name="vorname" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.vorname}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="name">Name: </label>
                                <input name="name" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.name}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="strasse">Straße: </label>
                                <input name="strasse" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.strasse}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="hausnummer">Hausnummer: </label>
                                <input name="hausnummer" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.hausnummer}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="plz">PLZ: </label>
                                <input name="plz" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.plz}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="ort">Ort: </label>
                                <input name="ort" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.adresse.ort}" required></input>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card m-0 p-0 mt-3 p-2 pb-4">
                    <div class="card-body p-0 m-0">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="institut">Bankinstitut: </label>
                                <input name="institut" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.bank.insitut}" required></input>
                            </div>
                            <div class="col-md-6">
                                <label for="bic">BIC: </label>
                                <input name="bic" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.bank.bic}" required></input>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="iban">IBAN: </label>
                                <input name="iban" type="text" class="form-control form-control-sm"
                                       value="${mitarbeiter.response.bank.iban}" required></input>
                            </div>
                        </div>
                    </div>
                </div>


                <a href="<c:url value="/"/>">
                    <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
                </a>

                <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>

            </form>
        </c:if>

    </jsp:attribute>
</template:base>