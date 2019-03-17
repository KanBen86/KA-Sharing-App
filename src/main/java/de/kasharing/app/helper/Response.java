/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.helper;

import java.util.List;

/**
 * Diese Klasse soll alle Daten sammeln, die bei einer Datenbankabfrage erhoben
 * werden können. 
 *
 * @author Benjamin Kanzler
 */
public class Response<E> {

    private List<E> responseList;

    private E response;

    private String message;

    private String status;

    private String exception;

    public List<E> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<E> responseList) {
        this.responseList = responseList;
    }

    public E getResponse() {
        return response;
    }

    public void setResponse(E response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
