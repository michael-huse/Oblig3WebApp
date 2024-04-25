


$(function() {
    //hent biletten med bilett-id fra url og vis denne i skjemaet
    const id = window.location.search.substring(1); // returnerer id=1 f.eks.
    const url = "/hentEnBilett?"+id;
    $.get(url, function (bilett) {
        $("#id").val(bilett.id); // Må ha med ID i skjema, hidden i html
        $("#film").val(bilett.film);
        $("#antall").val(bilett.antall);
        $("#fornavn").val(bilett.fornavn);
        $("#etternavn").val(bilett.etternavn);
        $("#telefonnummer").val(bilett.telefonnummer);
        $("#epost").val(bilett.epost);
    })
        .fail(function() {
            $("#feil").html("Feil i db - prøv igjen senere");

        });
});

//window.addEventListener("load", hentAlle);

$(function(){
    hentFilmer();
});

function hentFilmer() {
    $.get("/hentFilmer", function(filmer) {
        formaterFilmer(filmer);
    });
}

function formaterFilmer(filmer) {
    let ut = "<select id='valgtFilm'>";
    for (const film of filmer) {
        ut+="<option value='"+film.tittel+"'>"+film.tittel+"</option>";
    }
    ut+="</select>";
    $("#filmene").html(ut);
}

let utFornavn="";
let utEtternavn="";
let utEpost="";
let utTlf="";
const regexNavn = /^(?=.*[a-zA-Z])\S+$/;
const regexEpost = /^(?=.*[a-zA-Z0-9_\-\.])\S+@\S+\.\S{2,5}$/;
const regexNummer = /^(?=.*[4|9]\d{7})((0047)?|(\+47)?)[4|9]\d{7}$/;
function inputValideringfornavn() {

    let fornavninput=$("#fornavn").val();

    if (regexNavn.test(fornavninput)) {
        utFornavn=fornavninput;
        document.getElementById('fornavnValidering').innerText="";
    } else {
        utFornavn="feil";
        document.getElementById('fornavnValidering').innerText="Du må skrive inn et fornavn";
    }
}

function inputValideringetternavn() {

    let etternavninput=$("#etternavn").val();

    if (regexNavn.test(etternavninput)) {
        utEtternavn=etternavninput;
        document.getElementById('etternavnValidering').innerText="";
    } else {
        utEtternavn="feil";
        document.getElementById('etternavnValidering').innerText="Du må skrive inn et etternavn";
    }
}


function inputValideringTelefonnummer() {

    let tlfinput=$("#telefonnummer").val();

    if (regexNummer.test(tlfinput)) {
        utTlf=tlfinput;
        document.getElementById('tlfValidering').innerText="";
    } else {
        utTlf="feil";
        document.getElementById('tlfValidering').innerText="Du må skrive inn et telefonnummer";
    }
}

function inputValideringEpost() {

    let epostInput=$("#epost").val();

    if (regexEpost.test(epostInput)) {
        utEpost=epostInput;
        document.getElementById('epostValidering').innerText="";
    } else {
        utEpost="feil";
        document.getElementById('epostValidering').innerText="Du må skrive inn en epost";
    }
}


function endreBiletten() {
    if ($("#valgtFilm").val() === "" || $("#antall").val() === "" || utFornavn === "" || utEtternavn === "" || utTlf === "" || utEpost === "") {
        alert("Alle felt må være utfylt");
        return;
    }

    if (utFornavn !== "feil"  && utEtternavn !== "feil" && utTlf !== "feil" && utEpost!=="feil") {

        const bilett = {
            id : $("#id").val(),
            film: $("#valgtFilm").val(),
            antall: $("#antall").val(),
            fornavn: utFornavn,
            etternavn: utEtternavn,
            telefonnummer: utTlf,
            epost: utEpost
        };

        $.post("/endreBilett", bilett, function () {
            window.location.href = 'index.html';
        })
            .fail(function(jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                $("#feil").html(json.message);
            });


    }
}
