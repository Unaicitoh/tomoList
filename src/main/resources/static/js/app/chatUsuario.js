var input;
var nick;
var lista;
var usuarioActivo;

var options;

$(document).ready(function() {
	scrollDiv()

	//Info usuario lista y entrada
	input = document.getElementById("searcher");
	lista = document.getElementById("listUsers");
	usuarioActivo = document.getElementById("idu");
	input.addEventListener("input", userSearcher);
	$("#searcher").change(function() {
		loadOptions();
		var id = $("#searcher").data("id");
		if (id != undefined) {
			location.href = "/app/perfil?id=" + id;
		}
	});


	setInterval(function() {
		var mensaje=$("#floatingText").val();
		window.location.reload();
		$("#floatingText").val(mensaje);
	}, 10000)


	//Validacion Mensaje

	var msginput = document.getElementById("floatingText");

	msginput.addEventListener("input", function() {
		if ($(this).val().length > 0) {
			$("#sendMensaje").removeClass("disabled");
		} else {
			$("#sendMensaje").addClass("disabled");
		}
	})


	//Buscador amigos
	$("#amigos-searcher").change(function() {
		var idU = $("#idu").val();
		var username = $(this).val();
		loadAmigos(username, idU);
	});

	//Click lupa buscador de usuarios
	$("#search-icon").click(function() {
		if ($("#searcher").data("id") != undefined) {
			var id = $("#searcher").data("id");
			location.href = "/app/perfil?id=" + id;
		}
	});


	//Link perfil solicitudes
	$(".linkPerfilAmigo").click(function() {
		var idusuario = $(this).find("input").val();
		location.href = "/app/perfil?id=" + idusuario;
	});


});
function scrollDiv() {

	var div = document.getElementById('chat-body');
	var scrollHeight = div.scrollHeight;
	div.scrollTop = scrollHeight;
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