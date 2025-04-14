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

/**
 *
 * @param {*} successCallback
 * @param {*} errorCallback
 */
function getEquipes(successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: BASE_URL + "equipeManager.php",
        data: "action=getEquipes",
        success: successCallback,
        error: errorCallback,
    });
}

function ajouterEquipe(nomEquipe, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: BASE_URL + "equipeManager.php",
        data: "action=ajouterEquipe&nom=" + nomEquipe,
        success: successCallback,
        error: errorCallback,
    });
}

function supprimerEquipe(pkEquipe, successCallback, errorCallback) {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: BASE_URL + "equipeManager.php",
        data: "action=supprimerEquipe&pkEquipe=" + pkEquipe,
        success: successCallback,
        error: errorCallback,
    });
}

function getCompositionEquipe(pkEquipe, successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: BASE_URL + "equipeManager.php",
        data: "action=getCompositionEquipe&pkEquipe=" + pkEquipe,
        success: successCallback,
        error: errorCallback,
    });
}

function sauvegarderEquipe(pkEquipe, joueurs, successCallback, errorCallback) {
    //    data: 'action=sauvegarderEquipe&pkEquipe=' + pkEquipe + '&pksJoueurs=' + joueurs,

    $.ajax({
        type: "PUT",
        dataType: "json",
        url: BASE_URL + "equipeManager.php",
        data: {
            action: "sauvegarderEquipe",
            pkEquipe: pkEquipe,
            pksJoueurs: joueurs,
        },
        success: successCallback,
        error: errorCallback,
    });
}

//gestion des joueurs
function getAllJoueurs(successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: BASE_URL + "joueurManager.php",
        data: "action=getAllJoueurs",
        success: successCallback,
        error: errorCallback,
    });
}
