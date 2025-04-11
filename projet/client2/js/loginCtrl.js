class LoginCtrl {

    constructor() {
    }

    /**
     * Permet de tester le login.
     */
    login() {
        //code pour le login
        var username = document.getElementById("txtUsername").value;
        var password = document.getElementById("txtPassword").value;
        connect(username, password, this.connectSuccess, this.CallbackError)

    }

    /**
     * Si le les infos de login sont valides, on change la vue pour aller à la vue utilisateur. Sinon on affiche un message d'erreur.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    connectSuccess(data, text, jqXHR) {
        if (!data.admin) {
            indexCtrl.chargerVueUser();
        }
        else {
            alert("Erreur lors du login");
        }
    }

    /**
     * Permet à l'utilisateur de créer un compte qui sera ajouté à la db. Pour créer un compte, il faut que les champs respectent des critères.
     */
    register() {
        //code pour l'enregistrement
        var username = document.getElementById("txtUsername").value;
        var password = document.getElementById("txtPassword").value;
        if (this.valideInputs(username, password)) {
            signIn(username, password, this.registerSuccess, this.CallbackError);
        }
        else {
            alert("Un nom d'utilisateur et un mot de passe de minimum 3 caractères sont requis !");
        }

    }

    /**
     * En cas de succès, on affiche un message de réussite si le résultat est vrai, sinon on affiche un message d'erreur.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    registerSuccess(data, text, jqXHR) {
        alert(data.message);
    }

    /**
     * Permet de tester si le nom d'utilisateur et le mot de passe respectent certaines conditions. 
     * La seule condition est qu'ils doivent contenir au minimum 3 caractères.
     * @param {*} username - le nom d'utilisateur.
     * @param {*} password - le mot de passe.
     * @returns 
     */
    valideInputs(username, password) {
        if (username.length < 3 || password.length < 3) {
            return false;
        } else {
            return true;
        }
    }






    /**
    * Méthode appelée en cas d'erreur lors de la lecture du webservice.
    * @param {type} data
    * @param {type} text
    * @param {type} jqXHR
    */
    CallbackError(request, status, error) {
        alert("erreur : " + error + ", request: " + request + ", status: " + status);
    }

}