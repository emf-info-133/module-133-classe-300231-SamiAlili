class AdminCtrl {
    #competitions;

    constructor() {
        $(".competition-details-section").hide();
        getCompetitions(
            (x, y, z) => this.getCompetitionsSuccess(x, y, z),
            this.getCompetitionsError
        );

        $("#btnAjoutCompetition").click(() => {
            let nom = prompt("Veuillez donner un nom pour votre competition ?");
            if (nom === null) {
                return;
            }

            let categorie = prompt(
                "Veuillez donner une categorie pour votre competition ? (homme, femme, luxe, sport)"
            );
            if (categorie === null) {
                return;
            }
            categorie = categorie.toLowerCase();
            if (
                categorie !== "homme" &&
                categorie !== "femme" &&
                categorie !== "luxe" &&
                categorie !== "sport"
            ) {
                alert("categorie incorrecte");
                return;
            }

            ouvrirCompetition(
                nom,
                categorie,
                () => indexCtrl.chargerVueAdmin(),
                () => alert("Erreur lors de l'ouverture de la competition")
            );
        });

        $("#btnSupprimerCompetition").click(() => {
            let id = $("#competition-name").attr("pk_competition");


        })
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
        $("#competition-etat").val(competition.etat);
        $("#competition-category").val(competition.categorie);

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

        let voters = participant.votants;

        $(".voters-list").empty();

        voters.forEach((voter) => {
            let divVoter = $("<div>");

            divVoter.addClass("voter-item");
            divVoter.text(voter.nom);

            $(".voters-list").append(divVoter);
        });
    }
}
