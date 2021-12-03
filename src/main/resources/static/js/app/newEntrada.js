
$(document).ready(function() {

	//Validacion Comentario
	$("textarea").keyup(function() {
		if ($(this).val().length > 0) {
			$("#newComment").removeClass("disabled");
		} else {
			$("#newComment").addClass("disabled");
		}
	});
	$("textarea").keydown(function() {
		if ($(this).val().length > 0) {
			$("#newComment").removeClass("disabled");
		} else {
			$("#newComment").addClass("disabled");
		}
	});



});

