/*
  But :    Javascript qui gère les changements de vue
  Auteur : matteo marinazzo
  Date :   16.06.2024 / V1.0
*/

$().ready(function () {    
    indexCtrl = new IndexCtrl();  // ctrl principal
    loginCtrl = new LoginCtrl();
    userCtrl = new UserCtrl();
    
  });
  
  class IndexCtrl {
    constructor() {
      this.chargerVueLogin();
    }
  
    /**
     * Charge les différentes vues.
     * @param {string} vue - Le nom de la vue à afficher.
     * @param {string} callback - Callback de la méthode.
     */
    chargerVue(vue, callback) {
  
      // charger la vue demandee
      $("#vue").load("vues/" + vue + ".html", function () {
  
        // si une fonction de callback est spécifiée, on l'appelle ici
        if (typeof callback !== "undefined") {
          callback();
        }
  
      });
    }
  
    /**
     * Charge la vue "login".
     */
    chargerVueLogin() {
      this.chargerVue("login", () =>  new LoginCtrl());
  
    }
  
  /**
   * Charge la vue "user".
   */
    chargerVueUser() {
      this.chargerVue("userView", () => new UserCtrl());
      userCtrl.getEquipes();
      userCtrl.obtenirListeJoueurs();
    }
  
  }
  