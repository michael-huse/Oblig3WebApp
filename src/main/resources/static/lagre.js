
$(function(){
    hentAlle();
});

function hentAlle() {
    $.get("/hentAlle", function(data) {
        formaterData(data);
    })

        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function formaterData(biletter){
    let ut = "<table class='table table-striped'>" +
        "<tr>" +
        "<th>Film</th><th>Antall</th>" +
        "<th>Fornavn</th>" + "<th>Etternavn</th>" +
        "<th>Telefonnummer</th>" + "<th>Epost</th>";

    for (let i in biletter) {
        ut += "<tr>" +
            "<td>" + biletter[i].film + "</td>" +
            "<td>" + biletter[i].antall + "</td>" +
            "<td>" + biletter[i].fornavn + "</td>" +
            "<td>" + biletter[i].etternavn + "</td>" +
            "<td>" + biletter[i].telefonnummer + "</td>" +
            "<td>" + biletter[i].epost + "</td>" +
            "<td> <a class='btn btn-primary' href='endreBilett.html?id=" + biletter[i].id + "'> Endre </a> </td>" +
            "<td> <button class='btn btn-danger' onclick='slettEnBilett(" + biletter[i].id + ")'>Slett</button> </td>" +
            "</tr>";
    }
    $("#biletter").html(ut);
}




function slettEnBilett(id) {
    const url="/slettEnBilett?id="+id;
    $.get(url,function () {
        window.location.href="/";
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function slettAlle() {
    const ok = confirm("Sikker på å slette alle?");
    if (ok) {
        $.get("/slettAlle", function () {
            hentAlle();
        })
            .fail(function (jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                $("#feil").html(json.message);
            });
    }

}