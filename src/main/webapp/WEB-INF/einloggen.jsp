<%-- 
    Document   : einloggen
    Created on : 20.03.2019, 14:58:41
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<template:base>
    <jsp:attribute name="title">Login</jsp:attribute>
    <jsp:attribute name="main">
        <form method="POST">
            <div class="card" style="width:400px">
                <div class="card-body">
                    <div class="form-check-inline">
                        <label class="form-check-label" for="kunde">
                            <input type="radio" name="nutzer" class="form-check-input"
                                   value="1" id="kunde" checked> Kunde
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label" for="mitarbeiter">
                            <input type="radio" name="nutzer" class="form-check-input"
                                   value="2" id="mitarbeiter"> Mitarbeiter
                        </label>
                    </div>
                    <h4 class="card-title">Benutzername:</h4>
                    <input name="nickName" type="text" class="form-control form-control-sm"</input>
                    <br>
                    <h4 class="card-title">Passwort:</h4>
                    <input name="passwort" type="text" class="form-control form-control-sm"</input>
                    <br>
                    <button type="submit" class="btn btn-success btn-sm m-3">Einloggen</button>
                    <br>
                    <p>
                        Noch keinen Account?
                    </p>
                    <a href="<c:url value="/registrieren"/>">
                        <button type="button" class="btn btn-primary btn-sm mr-2">Registrieren</button>
                    </a><a href="<c:url value="/"/>">
                        <button type="button" class="btn btn-primary btn-sm m-3">Zurück zur Übersicht</button>
                    </a>
                </div>
            </div>
        </form>
    </jsp:attribute>
</template:base>
