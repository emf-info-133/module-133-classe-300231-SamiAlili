/*
 * Couche de services HTTP (worker).
 *
 * @author Sami Alili
 * @version 1.0
 */

var BASE_URL = "http://localhost:8080/gw/";

function login(username, passwd, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "login",
        data: {
            nom_utilisateur: username,
            mdp: passwd,
        },
        xhrFields: {
            withCredentials: true,
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

function ouvrirCompetition(nom, categorie, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "ouvrirCompetition",
        data: {
            nom: nom,
            categorie: categorie,
        },
        xhrFields: {
            withCredentials: true,
        },
        success: successCallback,
        error: errorCallback,
    });
}

function supprimerCompetition(id, successCallback, errorCallback) {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: BASE_URL + "supprimerCompetition/" + id,
        xhrFields: {
            withCredentials: true,
        },
        success: successCallback,
        error: errorCallback,
    });
}

function desinscrireParticipant(
    idCompetition,
    idParticipant,
    successCallback,
    errorCallback
) {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: BASE_URL + "desinscrire",
        xhrFields: {
            withCredentials: true,
        },
        data: {
            idUtilisateur: idParticipant,
            idCompetition: idCompetition,
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
        url: BASE_URL + "logout",
        xhrFields: {
            withCredentials: true,
        },
        success: successCallback,
        error: errorCallback,
    });
}
