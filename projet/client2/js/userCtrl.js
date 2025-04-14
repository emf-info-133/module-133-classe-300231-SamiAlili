class UserCtrl {
    #competitions;

    constructor() {
        $(".competition-details-section").hide();
        getCompetitions(
            (x, y, z) => this.getCompetitionsSuccess(x, y, z),
            this.getCompetitionsError
        );

        $("#btn-participer").click(this.participer);
        $("#btn-voter").click(this.voter);
        $("#btnLogOut").click(this.logout);
    }

    getCompetitionsSuccess(data, text, jqXHR) {
        this.#competitions = data.data;

        $(".competitions-list").empty();

        this.#competitions.forEach((competition) => {
            let divComp = $("<div>");

            divComp.addClass("competition-item");
            divComp = divComp.text(competition.nom);

            divComp.click(() => this.afficherCompetitionInfos(competition));

            $(".competitions-list").append(divComp);
        });
    }

    getCompetitionsError(jqXHR, textStatus, errorThrown) {
        alert("Erreur lors de la connexion : " + textStatus);
    }

    afficherCompetitionInfos(competition) {
        $(".competition-details-section").show();

        $("#competition-name").text(competition.nom);
        $("#competition-name").attr("pk_competition", competition.id);
        $("#competition-etat").text(competition.etat);
        $("#competition-category").text(competition.categorie);

        let participants = competition.participants;

        $(".participants-list").empty();

        participants.forEach((participant) => {
            let divPart = $("<div>");

            divPart.addClass("participant-item");
            divPart = divPart.text(participant.nom);

            divPart.click(() => this.afficherParticipantInfos(participant));

            $(".participants-list").append(divPart);
        });
    }

    afficherParticipantInfos(participant) {
        $(".participant-details-section").show();

        $("#participant-name").text(participant.nom);
        $("#participant-votes").text(participant.votes);

        $("#participant-name").attr("pk_participant", participant.id);

        let voters = participant.votants;

        if (Array.isArray(voters)) {
            $("#participant-votes").text(voters.length);
            $(".voters-list").empty();
            voters.forEach((voter) => {
                let divVoter = $("<div>");

                divVoter.addClass("voter-item");
                divVoter.text(voter.nom);

                $(".voters-list").append(divVoter);
            });
        } else {
            $("#participant-votes").text("0");
        }
    }

    participer() {
        let pkCompetition = $("#competition-name").attr("pk_competition");
        participer(pkCompetition, this.participerSuccess, this.participerError);
    }

    participerSuccess(data, text, jqXHR) {
        indexCtrl.chargerVueUser();
    }

    participerError(jqXHR, textStatus, errorThrown) {
        alert("Erreur lors de la participation : " + textStatus);
    }

    voter() {
        let pkCompetition = $("#competition-name").attr("pk_competition");
        let pkReceveur = $("#participant-name").attr("pk_participant");
        voter(pkCompetition, pkReceveur, this.voterSuccess, this.voterError)
    }

    voterSuccess(data, text, jqXHR) {
        indexCtrl.chargerVueUser();
    }

    voterError(jqXHR, textStatus, errorThrown) {
        alert("Erreur lors de la votation : " + textStatus);
    }

    logout() {
        logout(this.logoutSuccess, this.logoutError)
    }

    logoutSuccess(data, text, jqXHR) {
        indexCtrl.chargerVueLogin();
    }

    logoutError(jqXHR, textStatus, errorThrown) {
        alert("Erreur lors de la déconnexion : " + textStatus);
    }
}
