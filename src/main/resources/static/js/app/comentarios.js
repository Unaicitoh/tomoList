
$(document).ready(function() {

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

	//Validacion Comentario
	$("textarea").keyup(function(){
		if($(this).val().length>0){
			$("#newComment").removeClass("disabled");
		}else{
			$("#newComment").addClass("disabled");
		}
	});
	$("textarea").keydown(function(){
		if($(this).val().length>0){
			$("#newComment").removeClass("disabled");
		}else{
			$("#newComment").addClass("disabled");
		}
	});



});
