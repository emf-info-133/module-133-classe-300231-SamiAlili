class LoginCtrl {
    constructor() {
        $("#btnLogin").click(() => this.login());
    }

    /**
     * Permet de tester le login.
     */
    login() {
        //code pour le login
        var username = $("#txtUsername").val();
        var password = $("#txtPassword").val();

        login(username, password, this.connectSuccess, this.connectError);
    }

    /**
     * Si le les infos de login sont valides, on change la vue pour aller à la vue utilisateur. Sinon on affiche un message d'erreur.
     * @param {*} data
     * @param {*} text
     * @param {*} jqXHR
     */
    connectSuccess(data, text, jqXHR) {
        if (data.admin) {
            indexCtrl.chargerVueAdmin();
        } else {
            alert("Vous devez vous connecter à un compte administrateur !");
        }
    }

    connectError(jqXHR, textStatus, errorThrown) {
        alert("Erreur lors de la connexion : " + textStatus);
    }
}
