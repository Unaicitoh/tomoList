var input;
var nick;
var lista;
var usuarioActivo;
var autorSolicitud;
var options;
var lista;
$(document).ready(function() {
	//Info usuario lista y entrada
	input = document.getElementById("searcher");
	lista = document.getElementById("listUsers");
	usuarioActivo = document.getElementById("idu");
	input.addEventListener("input", userSearcher);

	lista = $("#infoSolicitudes");
	lista.hide();

	//Click lupa buscador de usuarios
	$("#search-icon").click(function() {
		if ($("#searcher").data("id") != undefined) {
			var id = $("#searcher").data("id");
			location.href = "/app/perfil?id=" + id;
		}
	});

	//Abrir solicitudes
	$("#btn-solicitudes").click(function() {
		lista = $("#infoSolicitudes");
		lista.fadeToggle(200, "linear");
	});

$("#btn-cerrar-solicitudes").click(function() {
	lista = $("#infoSolicitudes");
		lista.fadeToggle(200 , "linear");
});

//Evento borrar solicitud
$(".btnBorrarSolicitud").click(function() {
	autorSolicitud = $(this).next().val();
});

$("#btnModalBorrarSolicitud").click(function() {
	$("#borrarSolicitudForm").attr("action", "/app/borrarSolicitud/" + autorSolicitud);
	$("#borrarSolicitudForm").submit;
});

});




async function userSearcher() {

	nick = input.value;
	var response = await fetch(`/api/usersearch/` + nick + "/" + usuarioActivo.value);
	var usuarios = await response.json();
	loadOptions();
	lista.innerHTML = '';
	showUsuarios(usuarios);

}

function showUsuarios(usuarios) {
	for (const usuario of usuarios) {

		var option = document.createElement("option");
		option.setAttribute("class", "btnPerfil");
		option.dataset.id = usuario.idusuario;
		var showname = `${usuario.username}, ${usuario.nombre}`;
		option.setAttribute("value", showname);
		$("#listUsers").append(option);

	}

}

function loadOptions() {
	options = $("#listUsers").children();
	for (option of options) {
		if (nick == option.value) {
			var id = option.getAttribute("data-id");
			input.dataset.id = id;
		}
	}

}