/*
 * Couche de services HTTP (worker).
 *
 * @author Olivier Neuhaus
 * @version 1.0 / 13-SEP-2013
 */

var BASE_URL = "http://localhost:8080/gw/";

function connect(username, passwd, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "login",
        data: {
            nom_utilisateur: username,
            mdp: passwd,
        },
        success: successCallback,
        error: errorCallback,
    });
}

function getCompetitions(successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: BASE_URL + "getCompetitions",
        success: successCallback,
        error: errorCallback,
    });
}

/**
 *
 * @param {*} username
 * @param {*} passwd
 * @param {*} successCallback
 * @param {*} errorCallback
 */
function signIn(username, passwd, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "signIn",
        data: {
            nom_utilisateur: username,
            mdp: passwd,
        },
        success: successCallback,
        error: errorCallback,
    });
}

/**
 *
 * @param {*} successCallback
 * @param {*} errorCallback
 */
function logout(successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "lougout",
        success: successCallback,
        error: errorCallback,
    });
}

/**
 *
 * @param {*} successCallback
 * @param {*} errorCallback
 */
function participer(pkCompetition, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            idCompetition: pkCompetition
        },
        url: BASE_URL + "participer",
        success: successCallback,
        error: errorCallback,
    });
}

/**
 *
 * @param {*} successCallback
 * @param {*} errorCallback
 */
function voter(pkCompetition, pkRceveur,successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {
            idCompetition: pkCompetition,
            idReceveur: pkRceveur
        },
        url: BASE_URL + "voter",
        success: successCallback,
        error: errorCallback,
    });
}