var input;
var nick;
var lista;
var usuarioActivo;
var options;

$(document).ready(function() {
	console.log("ready!");
	input = document.getElementById("searcher");
	lista = document.getElementById("listUsers");
	usuarioActivo = document.getElementById("idu");

	input.addEventListener("input", userSearcher);

	$("#search-icon").click(function() {
		if ($("#searcher").data("id") != undefined) {
			var id = $("#searcher").data("id");
			location.href = "/app/perfil/" + id;
		}
	});

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
		
	})


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