<%-- 
    Document   : detail
    Created on : 25.02.2019, 17:54:30
    Author     : chp
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
    $("#datepickerAnschaffungsdatum").datepicker();
    });
    $(function () {
    $("#datepickerHauptuntersuchungBis").datepicker();
    });
</script>

<template:base>
    <jsp:attribute name="title">Detailansicht</jsp:attribute>
    <jsp:attribute name="main">

        <!-- Ansicht wenn sich ein Mitarbeiter angemeldet hat -->

        <c:if test="${mitarbeiter.response != null}">
            <form method="POST">
                <!--Card von der Übersichtsseite-->
                <div class="card">
                    <div class="card-body mb-3 mt-3">
                        <div class="form-row">
                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${detailFahrzeug.response.bild != null}">
                                        <div class="d-flex justify-content-center fulldiv">
                                            <img src="<c:url value='${detailFahrzeug.response.bild}'/>" class="card-img" alt="Bild vom Auto">
                                        </div>        
                                    </c:when>
                                    <c:otherwise> 
                                        <div class="align-middle fulldiv herstellerLogo">
                                            <c:if test="${detailFahrzeug.response.hersteller != null}">
                                                <img src="<c:url value="/pictures/hersteller/${detailFahrzeug.response.hersteller}.png"/>" class="card-img mx-auto"
                                                    style="min-height: 110px; max-height: 110px; width:auto; max-width: 100%;" alt="Herstellerbild">
                                                <br />
                                            </c:if>
                                            <c:if test="${detailFahrzeug.response.hersteller == null}">
                                                <div class="d-flex justify-content-center">
                                                    <i class="fas fa-car fa-9x"></i>
                                                </div>
                                                <br />
                                            </c:if>
                                        </div>                                   
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-4">
                                <label for="hersteller">Hersteller: </label>
                                <select name="hersteller" class="form-control form-control-sm" required>
                                    <option value="" disabled selected style="display: none;">Bitte Hersteller wählen</option>
                                    <c:forEach items="${herstellerList}" var="herstellerValue">
                                        <option value="${herstellerValue}"
                                                ${herstellerValue == detailFahrzeug.response.hersteller ? 'selected' : ''}>
                                            ${herstellerValue}
                                        </option>
                                    </c:forEach>
                                </select>

                                <label for="modell">Modell: </label>
                                <input name="modell" type="text" class="form-control form-control-sm"
                                       value="${detailFahrzeug.response.modell}" required></input>

                                <label for="klasse">Klasse: </label>
                                <select name="klasse" class="form-control form-control-sm" required>
                                    <option value="" disabled selected style="display: none;">Bitte Klasse wählen</option>
                                    <c:forEach items="${klassenList}" var="klasseValue">
                                        <option value="${klasseValue}"
                                                ${klasseValue == detailFahrzeug.response.klasse ? 'selected' : ''}>
                                            ${klasseValue}
                                        </option>
                                    </c:forEach>
                                </select>

                                <label for="getriebeart">Getriebeart: </label>
                                <select name="getriebeart" class="form-control form-control-sm" required>
                                    <option value="" disabled selected style="display: none;">Bitte Getriebeart wählen</option>
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
                                <select name="typ" class="form-control form-control-sm" required>
                                    <option value="" disabled selected style="display: none;">Bitte Typ wählen</option>
                                    <c:forEach items="${typList}" var="typValue">
                                        <option value="${typValue}"
                                                ${typValue == detailFahrzeug.response.typ ? 'selected' : ''}>
                                            ${typValue}
                                        </option>
                                    </c:forEach>
                                </select>

                                <label for="ausfuehrung">Ausführung: </label>
                                <input name="ausfuehrung" type="text" class="form-control form-control-sm" required
                                       value="${detailFahrzeug.response.ausfuehrung}"></input>

                                <label for="preisProTag">Preis pro Tag: </label>
                                <input name="preisProTag" type="number" class="form-control form-control-sm" required
                                       value="${detailFahrzeug.response.preisProTag}"></input>

                                <label for="hauptuntersuchungBis">Hauptuntersuchung bis: </label>
                                <br/>
                                <input id="datepickerHauptuntersuchungBis" name="hauptuntersuchungBis" type="text" class="form-control form-control-sm" required
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
                                       value="${detailFahrzeug.response.plaetze}" required></input>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="raeder">Räder: </label>
                                <input name="raeder" type="number" class="form-control form-control-sm"
                                       value="${detailFahrzeug.response.raeder}" required></input>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="tueren">Türen: </label>
                                <input name="tueren" type="number" class="form-control form-control-sm"
                                       value="${detailFahrzeug.response.tueren}" required></input>
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
                                    <select name="treibstoff" class="form-control form-control-sm" required >
                                        <option value="" disabled selected style="display: none;">Bitte Treibstoffart wählen</option>
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
                                <select name="leihstatus" class="form-control form-control-sm" required >
                                    <option value="" disabled selected style="display: none;">Bitte Verfügbarkeit wählen</option>
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
                                <input id="datepickerAnschaffungsdatum" name="anschaffungsDatum" type="text" class="form-control form-control-sm"
                                       value="${detailFahrzeug.response.anschaffungsDatum}" required
                                       ></input>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="anschaffungsPreis">Anschaffungspreis in €: </label>
                                <input name="anschaffungsPreis" type="number" class="form-control form-control-sm"
                                       value="${detailFahrzeug.response.anschaffungsPreis}" required></input>
                            </div>
                        </div>

                    </div>
                </div>

                <a href="<c:url value='/'/>">
                    <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
                </a>

                <c:if test="${detailFahrzeug.response.id != null && mitarbeiter.response != null}">
                    <a href="<c:url value='/ausbuchen/${detailFahrzeug.response.id}'/>">
                        <button type="button" class="btn btn-danger btn-sm m-3">Fahrzeug ausbuchen</button>
                    </a>
                </c:if>

                <c:if test="${mitarbeiter.response != null}">
                    <button type="submit" class="btn btn-success btn-sm m-3">Speichern</button>
                </c:if>


            </form>
        </c:if>

        <!-- Ansicht wenn sich ein Kunde angemeldet hat -->
        <c:if test="${mitarbeiter.response == null}">

            <!--Card von der Übersichtsseite-->
            <div class="card detailKunde">
                <div class="card-body mb-3 mt-3">
                    <div class="form-row">
                        <div class="col-md-4">
                            <c:choose>
                                <c:when test="${detailFahrzeug.response.bild != null}">
                                    <div class="d-flex justify-content-center fulldiv">
                                        <img src="<c:url value='${detailFahrzeug.response.bild}'/>" class="card-img" alt="Bild vom Auto">
                                    </div>        
                                </c:when>
                                <c:otherwise> 
                                    <div class="align-middle fulldiv">
                                        <img src="<c:url value="/pictures/hersteller/${detailFahrzeug.response.hersteller}.png"/>" class="card-img mx-auto"
                                             style="min-height: 110px; max-height: 110px; width:auto; max-width: 100%;" alt="Herstellerbild">
                                    </div>                                   
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-4">
                            <p>Hersteller: </p>
                            <c:out value="${detailFahrzeug.response.hersteller}"></c:out>

                            <p>Modell: </p>
                            <c:out value="${detailFahrzeug.response.modell}"></c:out>

                            <p>Klasse: </p>
                            <c:out value="${detailFahrzeug.response.klasse}"></c:out>

                            <p>Getriebeart: </p>
                            <c:out value="${detailFahrzeug.response.getriebeart}"></c:out>

                        </div>
                        <div class="col-md-4">
                            <p>Typ: </p>
                            <c:out value="${detailFahrzeug.response.typ}"></c:out>

                            <p>Ausführung: </p>
                            <c:out value="${detailFahrzeug.response.ausfuehrung}"></c:out>

                            <p>Preis pro Tag: </p>
                            <c:out value="${detailFahrzeug.response.preisProTag}"></c:out>

                            <p>Hauptuntersuchung bis: </p>
                            <c:out value="${detailFahrzeug.response.hauptuntersuchungBis}"></c:out>
                        </div>
                    </div>

                </div>
            </div>

            <!--weitere Infos für das Auto-->
            <div class="card mt-3 detailKunde">
                <div class="card-body">


                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <p>Plätze: </p>
                            <c:out value="${detailFahrzeug.response.plaetze}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Räder: </p>
                            <c:out value="${detailFahrzeug.response.raeder}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Türen: </p>
                            <c:out value="${detailFahrzeug.response.tueren}"></c:out>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <p>Klimaanlage: </p>
                            <c:out value="${detailFahrzeug.response.klimaanlage == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>elektischer Fensterheber: </p>
                            <c:out value="${detailFahrzeug.response.elektrischeFensterheber == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Servolenkung: </p>
                            <c:out value="${detailFahrzeug.response.servolenkung == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <p>ABS: </p>
                            <c:out value="${detailFahrzeug.response.abs == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>ESP: </p>
                            <c:out value="${detailFahrzeug.response.esp == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>CD: </p>
                            <c:out value="${detailFahrzeug.response.cd == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <p>Navigationsgerät: </p>
                            <c:out value="${detailFahrzeug.response.navigation == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Fahrassistent: </p>
                            <c:out value="${detailFahrzeug.response.fahrassiSystem == true ? 'Ja' : 'Nein'}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Treibstoffart: </p>
                            <c:out value="${detailFahrzeug.response.treibstoff}"></c:out>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <p>Verfügbarkeit: </p>
                            <c:out value="${detailFahrzeug.response.leihStatus}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Anschaffungsdatum: </p>
                            <c:out value="${detailFahrzeug.response.anschaffungsDatum}"></c:out>
                        </div>
                        <div class="form-group col-md-4">
                            <p>Anschaffungspreis in €: </p>
                            <c:out value="${detailFahrzeug.response.anschaffungsPreis}"></c:out>
                        </div>
                    </div>

                </div>
            </div>

            <a href="<c:url value='/'/>">
                <button type="button" class="btn btn-primary  btn-sm m-3">Zurück</button>
            </a>

        </c:if>


    </jsp:attribute>
</template:base>
