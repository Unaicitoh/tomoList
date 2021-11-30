var input;
var nick;
var lista;
var usuarioActivo;
var entradaActual;
var options;

$(document).ready(function() {
	//Info usuario lista y entrada
	input = document.getElementById("searcher");
	lista = document.getElementById("listUsers");
	usuarioActivo = document.getElementById("idu");
	input.addEventListener("input", userSearcher);


	//Carga likes activos
	cargadorLikesActivos();
	//Carga de num likes
	cargaLikes();

	//Crea y elimina megustas
	creaEliminaLikes();

	//Cargador de Comentarios
	$("div.nComentarios").each(function() {
		var idEntrada = $(this).siblings("input#ide").attr("value");
		var commentContainer = $(this);
		cargadorComentarios(idEntrada, commentContainer);
	});

	//Click lupa buscador de usuarios
	$("#search-icon").click(function() {
		if ($("#searcher").data("id") != undefined) {
			var id = $("#searcher").data("id");
			location.href = "/app/perfil/" + id;
		}
	});

	//Estilos POSTS header y Link
	var headers = $(".card-header");
	headers.click(function() {
		var idUsuario = $(this).children().last().val();
		location.href = "/app/perfil/" + idUsuario;
	});

	headers.hover(function() {
		var ele = $(this);
		ele.toggleClass("bg-white");
		ele.toggleClass("bg-dark");
		ele.children().first().find("img").toggleClass("border-dark");
		ele.children().first().find("img").toggleClass("border-white");
		ele.children("h3").toggleClass("text-light");
		ele.children("h3").toggleClass("text-dark");

	});




});

function creaEliminaLikes() {
	
	$(".likeIcon").click(function() {
		var idU = $("#idu").val();
		var idE = $(this).siblings("input#ide").val();
		if ($(this).hasClass("bi-heart")) {
			$(this).removeClass("bi-heart");
			$(this).addClass("bi-heart-fill");
			crearLike(idE, idU);
		} else if ($(this).hasClass("bi-heart-fill")) {
			$(this).removeClass("bi-heart-fill");
			$(this).addClass("bi-heart");
			borrarLike(idE,idU);
		}
	});
}

function cargadorLikesActivos() {
	var idU = $("#idu").val();
	$(".likeIcon").each(function() {
		var idE = $(this).siblings("input#ide").val();
		comprobarLikesActivos(idE, idU, $(this));
	});
}

function cargaLikes() {
	$("div.nLikes").each(function() {
		var idEntrada = $(this).siblings("input#ide").attr("value");
		var likeContainer = $(this);
		cargadorLikes(idEntrada, likeContainer);
	});
}

async function comprobarLikesActivos(idE, idU, icono) {
	const response = await fetch("/api/likeActivo/" + idE + "/" + idU);
	const isActivo = await response.json();
	if (isActivo != false) {
		icono.removeClass("bi-heart");
		icono.addClass("bi-heart-fill")
	} else {
		icono.removeClass("bi-heart-fill");
		icono.addClass("bi-heart")
	}
}

async function borrarLike(idE, idU) {

	const response = await fetch("/api/borrarLike/" + idE + "/" + idU, { method: "DELETE" });
	cargaLikes();
}

async function crearLike(idE, idU) {

	const response = await fetch("/api/creaLike/" + idE + "/" + idU, { method: "POST" });
	cargaLikes();
}

async function cargadorLikes(identrada, likeDiv) {

	var response = await fetch("/api/postlikes/" + identrada);
	var likes = await response.json();
	var size = Object.keys(likes).length;
	likeDiv.text(size);
}

async function cargadorComentarios(identrada, commentDiv) {

	var response = await fetch(`/api/postcomments/` + identrada);
	var comments = await response.json();
	var size = Object.keys(comments).length;
	commentDiv.text(size);
}


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