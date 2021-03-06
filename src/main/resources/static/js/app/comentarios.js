
$(document).ready(function() {

window.localStorage.clear();
	cargadorLikesActivos();
	//Carga de num likes
	cargaLikes();
	//Crea y elimina megustas
	creaEliminaLikes();


	//Estilos POSTS header y Link
	var headers = $(".card-header");
	headers.click(function() {
		var idUsuario = $(this).children().last().val();
		location.href = "/app/perfil?id=" + idUsuario;
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

//Click en num megustas
	$(".nLikes").click(function(){
		var idEntrada = $(this).siblings("input#ide").val();
		location.href = "/app/likes/" + idEntrada;
	});
	
	var msgcomentario=document.getElementById("floatingTextarea");
	
	msgcomentario.addEventListener("input",function(){
		if ($(this).val().length > 0) {
			$("#newComment").removeClass("disabled");
		} else {
			$("#newComment").addClass("disabled");
		}
	})





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
			borrarLike(idE, idU);
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
