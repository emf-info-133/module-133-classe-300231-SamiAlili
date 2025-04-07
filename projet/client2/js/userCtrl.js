class UserCtrl {

    constructor() {
        $("#divSectionComposition input").each(function() {
            $(this).click(function() {
                $(this).val($(this).attr("value"))
                $(this).removeAttr("pkjoueur")

            })
        })
    }

    /*#################################
    Gestion de la section : Mes équipes
    ################################**/

    /**
     * Permet d'obtenir la liste des équipes de l'utilisateur.
     */
    getEquipes() {
        let self = this;
        getEquipes(function (data, text, jqXHR) { self.getEquipesSuccess(data, text, jqXHR); }, self.CallbackError);
    }

    /**
     * Si la méthode getEquipes réussi, création d'une liste à options avec les équipes.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    getEquipesSuccess(data, text, jqXHR) {
        var lst = document.getElementById("lstEquipes");
        lst.innerHTML = ""; //vider la liste avant de la remplir
        data.forEach(equipe => {
            var option = document.createElement("option");
            option.text = equipe.nom;
            option.setAttribute('pkEquipe', equipe.pkEquipe)
            lst.appendChild(option);
        });
        this.selectionnerEquipe(); //sélectionner la première équipe
    }

    /**
     * Affiche la composition de l'équipe sélectionnée. Code exécuté lorsque l'utilisateur change la sélection de l'équipe.
     */
    selectionnerEquipe() {
        let pkEquipeSelectionner = $("#lstEquipes option:selected").attr("pkequipe");
        let self = this; // Garde une référence à l'instance
        getCompositionEquipe(pkEquipeSelectionner, function (data, text, jqXHR) { self.selectionnerEquipeSuccess(data, text, jqXHR); }, self.CallbackError);
    }

    /**
     * Si la méthode selectionnerEquipe réussi, affichage de la composiiton.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    selectionnerEquipeSuccess(data, text, jqXHR) {
        //remettre à 0 la composition avant de charger celle de l'équipe choisie
        $("#txtAG").val("AG").removeAttr("pkjoueur");
        $("#txtAC").val("AC").removeAttr("pkjoueur");
        $("#txtAD").val("AD").removeAttr("pkjoueur");
        $("#txtMG").val("MG").removeAttr("pkjoueur");
        $("#txtMC").val("MC").removeAttr("pkjoueur");
        $("#txtMD").val("MD").removeAttr("pkjoueur");
        $("#txtDG").val("DG").removeAttr("pkjoueur");
        $("#txtDC1").val("DC1").removeAttr("pkjoueur");
        $("#txtDC2").val("DC2").removeAttr("pkjoueur");
        $("#txtDD").val("DD").removeAttr("pkjoueur");
        $("#txtG").val("G").removeAttr("pkjoueur");

        data.forEach(joueur => {
            this.switchcase(joueur, joueur.pkJoueur);
        });
    }
    /**
     * Supprime l'équipe sélectionnée. Suppession de l'équipe si l'utilisateur confirme.
     */
    supprimerEquipe() {
        if (window.confirm("Voulez-vous vraiment supprimer cette équipe ?")) {
            let pkEquipeSelectionner = $("#lstEquipes option:selected").attr("pkequipe");
            let self = this; // Garde une référence à l'instance
            supprimerEquipe(pkEquipeSelectionner, function (data, text, jqXHR) { self.supprimerEquipeSuccess(data, text, jqXHR); }, self.CallbackError);
        }
    }

    /**
     * Affiche un message de réussite et met à jour la liste des équipes si le résultat est vrai. Sinon affiche un message d'échec.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    supprimerEquipeSuccess(data, text, jqXHR) {
        if (data.res === 'true') {
            alert("L'équipe a été supprimée !")
            this.getEquipes()
        }
        else {
            alert("L'équipe n'a pas été supprimée !")
        }
    }

    /**
     * Ajoute une équipe à la db avec le nom entré par l'utilisateur.
     */
    ajouterEquipe() {
        var nomEquipe = document.getElementById("txtNomEquipe").value;
        let self = this; // Garde une référence à l'instance
        if (nomEquipe != "") {
            ajouterEquipe(nomEquipe, function (data, text, jqXHR) { self.ajouterEquipesSuccess(data, text, jqXHR); }, self.CallbackError);
        }
        else {
            alert("L'équipe n'a pas été créée !");
        }
    }

    /**
     * Si la méthode réussie ce code est effectué. 
     * Si le résultat est vrai, un message de réussite est affichée et la liste des équipes est mise à jour. Sinon, un message d'échec est affiché. 
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    ajouterEquipesSuccess(data, text, jqXHR) {
        if (data.res === 'true') {
            alert("L'équipe a été créée !")
            this.getEquipes()
        }
        else {
            alert("L'équipe n'a pas été créée !")
        }
    }

    /**
     * Permet d'enregistrer la composition de l'équipe en mettant à jour l'équipe dans la db.
     */
    enregistrerEquipe() {
        let pkEquipe = $("#lstEquipes option:selected").attr("pkequipe");
        let joueurs = [];
        joueurs.push($("#txtAG").attr("pkJoueur"));
        joueurs.push($("#txtAC").attr("pkJoueur"));
        joueurs.push($("#txtAD").attr("pkJoueur"));
        joueurs.push($("#txtMG").attr("pkJoueur"));
        joueurs.push($("#txtMC").attr("pkJoueur"));
        joueurs.push($("#txtMD").attr("pkJoueur"));
        joueurs.push($("#txtDG").attr("pkJoueur"));
        joueurs.push($("#txtDC1").attr("pkJoueur"));
        joueurs.push($("#txtDC2").attr("pkJoueur"));
        joueurs.push($("#txtDD").attr("pkJoueur"));
        joueurs.push($("#txtG").attr("pkJoueur"));

        joueurs.forEach(function (value, index, array) {
            if (value === undefined) {
                array[index] = -1;  // Remplace undefined par null
            }
        });

        console.log(joueurs)
        sauvegarderEquipe(pkEquipe, joueurs, this.enregistrerEquipesSuccess, this.CallbackError);
    }

    /**
     * Affiche un message d'échec ou de réussite en fonction du résultat de la méthode enregistrerEquipe.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    enregistrerEquipesSuccess(data, text, jqXHR) {
        if (data.res === 'true') {
            alert("L'équipe a été sauvegardée !")
        }
        else {
            alert("L'équipe n'a pas été sauvegardée !")
        }
    }

    /*#################################
    Gestion de la section : composition
    ################################**/

    /**
     * Permet d'obtenir la liste de tous les joueurs de la db.
     */
    obtenirListeJoueurs() {
        getAllJoueurs(this.obtenirListeJoueursSuccess, this.CallbackError);
    }

    /**
     * Création d'une liste contant tous les joueurs et leur posiiton sur le terrain. Cette méthode est effectuée si la méthode précédente a réussie.
     * Un attribut est crée pour chaque joueur. Cet attribut contient leur pk.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    obtenirListeJoueursSuccess(data, text, jqXHR) {
        let lst = document.getElementById('lstJoueurs');
        lst.innerHTML = ""; //vider la liste avant de la remplir

        data.forEach(joueur => {
            let option = document.createElement("option");
            option.text = joueur.pos + " - " + joueur.nom;
            option.setAttribute('pkJoueur', joueur.pkJoueur)
            lst.appendChild(option);
        });
    }

    /**
     * Permet d'ajouter le joueur choisi à la composition de l'équipe.
     */
    ajouterJoueurAEquipe() {
        let self = this;
        getAllJoueurs(function (data, text, jqXHR) { self.ajouterJoueurAEquipeSuccess(data, text, jqXHR); });
    }

    /**
     * Si la méthode ajouterJoueurAEquipe réussie, on place le joueur dans son emplacement à l'aide de la méthode switchcase.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    ajouterJoueurAEquipeSuccess(data, text, jqXHR) {
        let pkJoueur = $("#lstJoueurs option:selected").attr("pkjoueur");
        data.forEach(joueur => {
            if (pkJoueur == joueur.pkJoueur) {
                this.switchcase(joueur, pkJoueur);
            }
        });
    }


    
    

    /*#######################
    Gestion de la déconnexion
    ######################**/

    /**
     * Permet de se déconnecter du compte. Cela entraîne la destruction de la session utilisateur.
     */
    logout() {
        logout(this.logoutSuccess, this.CallbackError)
    }

    /**
     * Si la déconnexion réussie, on retourne à la page de connexion. Sinon on affiche une erreur.
     * @param {*} data 
     * @param {*} text 
     * @param {*} jqXHR 
     */
    logoutSuccess(data, text, jqXHR) {
        if (data.res === 'true') {
            indexCtrl.chargerVueLogin();
        }
        else {
            alert("La déconnexion a échoué");
        }
    }

    /*#############
    Autres méthodes
    ############**/

    /**
    * Méthode appelée en cas d'erreur lors de la lecture du webservice.
    * @param {type} data
    * @param {type} text
    * @param {type} jqXHR
    */
    CallbackError(request, status, error) {
        alert("erreur : " + error + ", request: " + request + ", status: " + status);
    }

    /**
     * Permet de trier les joueurs en fonction de leur position et de les afficher au bon emplacement dans la composition. Ajout d'un attribut contenant leur pk.
     * @param {*} joueur 
     * @param {*} pkJoueur 
     */
    switchcase(joueur, pkJoueur) {
        switch (joueur.pos) {
            case "AG":
                $("#txtAG").val(joueur.pos + " - " + joueur.nom);
                $("#txtAG").attr("pkJoueur", pkJoueur);
                break;
            case "AC":
                $("#txtAC").val(joueur.pos + " - " + joueur.nom);
                $("#txtAC").attr("pkJoueur", pkJoueur);
                break;
            case "AD":
                $("#txtAD").val(joueur.pos + " - " + joueur.nom);
                $("#txtAD").attr("pkJoueur", pkJoueur);
                break;
            case "MG":
                $("#txtMG").val(joueur.pos + " - " + joueur.nom);
                $("#txtMG").attr("pkJoueur", pkJoueur);
                break;
            case "MC":
                $("#txtMC").val(joueur.pos + " - " + joueur.nom);
                $("#txtMC").attr("pkJoueur", pkJoueur);
                break;
            case "MD":
                $("#txtMD").val(joueur.pos + " - " + joueur.nom);
                $("#txtMD").attr("pkJoueur", pkJoueur);
                break;
            case "DG":
                $("#txtDG").val(joueur.pos + " - " + joueur.nom);
                $("#txtDG").attr("pkJoueur", pkJoueur);
                break;
            case "DC1":
                $("#txtDC1").val(joueur.pos + " - " + joueur.nom);
                $("#txtDC1").attr("pkJoueur", pkJoueur);
                break;
            case "DC2":
                $("#txtDC2").val(joueur.pos + " - " + joueur.nom);
                $("#txtDC2").attr("pkJoueur", pkJoueur);
                break;
            case "DD":
                $("#txtDD").val(joueur.pos + " - " + joueur.nom);
                $("#txtDD").attr("pkJoueur", pkJoueur);
                break;
            case "G":
                $("#txtG").val(joueur.pos + " - " + joueur.nom);
                $("#txtG").attr("pkJoueur", pkJoueur);
                break;
        }
    }
}