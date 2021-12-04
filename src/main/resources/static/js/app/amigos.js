var input;
var nick;
var lista;
var usuarioActivo;
var autorSolicitud;
var options;
var listaS;
var listaA;
var autorAmistad;
$(document).ready(function() {
	//Info usuario lista y entrada
	input = document.getElementById("searcher");
	lista = document.getElementById("listUsers");
	usuarioActivo = document.getElementById("idu");
	input.addEventListener("input", userSearcher);

	listaS = $("#infoSolicitudes");
	listaS.hide();
	listaA = $("#infoAmigos");
	//Click lupa buscador de usuarios
	$("#search-icon").click(function() {
		if ($("#searcher").data("id") != undefined) {
			var id = $("#searcher").data("id");
			location.href = "/app/perfil?id=" + id;
		}
	});

	//Link perfil amistad
	$(".linkSolicitudPerfil").click(function() {
		var idusuario = $(this).find("input").val();
		location.href = "/app/perfil?id=" + idusuario;
	});

	//Link perfil solicitudes
	$(".linkPerfilAmigo").click(function() {
		var idusuario = $(this).find("input").val();
		location.href = "/app/perfil?id=" + idusuario;
	});

	//Abrir solicitudes
	$("#btn-solicitudes").click(function() {
		listaS = $("#infoSolicitudes");
		listaS.fadeToggle(200, "linear");
	});

	$("#btn-cerrar-solicitudes").click(function() {
		listaS = $("#infoSolicitudes");
		listaS.fadeToggle(200, "linear");
	});

	//Evento borrar solicitud
	$(".btnBorrarSolicitud").click(function() {
		autorSolicitud = $(this).next().val();
	});

	$("#btnModalBorrarSolicitud").click(function() {
		$("#borrarSolicitudForm").attr("action", "/app/borrarSolicitud/" + autorSolicitud);
		$("#borrarSolicitudForm").submit;
	});


	//Abrir amistad
	$("#btn-amigos").click(function() {
		listaA = $("#infoAmigos");
		listaA.fadeToggle(200, "linear");
	});

	$("#btn-cerrar-amigos").click(function() {
		listaA = $("#infoAmigos");
		listaA.fadeToggle(200, "linear");
	});

	//Evento borrar solicitud
	$(".btnBorrarAmigo").click(function() {
		autorAmistad = $(this).next().val();
	});

	$("#btnModalBorrarAmigo").click(function() {
		$("#borrarAmigoForm").attr("action", "/app/borrarAmistad/" + autorAmistad);
		$("#borrarAmigoForm").submit;
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