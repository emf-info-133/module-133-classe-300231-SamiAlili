/*
 * Couche de services HTTP (worker).
 *
 * @author Olivier Neuhaus
 * @version 1.0 / 13-SEP-2013
 */

var BASE_URL = "../server/";

/**
 * Fonction permettant de charger les données d'équipe.
 * @param {type} teamid, id de l'équipe dans laquelle trouver les joueurs
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function connect(username, passwd, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    dataType: "json",
    url: BASE_URL + "loginManager.php",
    data: 'action=connect&username=' + username + '&password=' + passwd,
    success: successCallback,
    error: errorCallback
  });

}

/**
 * 
 * @param {*} username 
 * @param {*} passwd 
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function signin(username, passwd, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    dataType: "json",
    url: BASE_URL + "loginManager.php",
    data: 'action=signin&username=' + username + '&password=' + passwd,
    success: successCallback,
    error: errorCallback
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
    url: BASE_URL + "loginManager.php",
    data: 'action=logout',
    success: successCallback,
    error: errorCallback
  });
}

//gestion équipes

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
    data: 'action=getEquipes',
    success: successCallback,
    error: errorCallback
  });
}

function ajouterEquipe(nomEquipe, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    dataType: "json",
    url: BASE_URL + "equipeManager.php",
    data: 'action=ajouterEquipe&nom=' + nomEquipe,
    success: successCallback,
    error: errorCallback
  });
}

function supprimerEquipe(pkEquipe, successCallback, errorCallback) {
  $.ajax({
    type: "DELETE",
    dataType: "json",
    url: BASE_URL + "equipeManager.php",
    data: 'action=supprimerEquipe&pkEquipe=' + pkEquipe,
    success: successCallback,
    error: errorCallback
  });
}

function getCompositionEquipe(pkEquipe, successCallback, errorCallback) {
  $.ajax({
    type: "GET",
    dataType: "json",
    url: BASE_URL + "equipeManager.php",
    data: 'action=getCompositionEquipe&pkEquipe=' + pkEquipe,
    success: successCallback,
    error: errorCallback
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
    error: errorCallback
  });
}

//gestion des joueurs
function getAllJoueurs(successCallback, errorCallback) {
  $.ajax({
    type: "GET",
    dataType: "json",
    url: BASE_URL + "joueurManager.php",
    data: 'action=getAllJoueurs',
    success: successCallback,
    error: errorCallback
  });
}
