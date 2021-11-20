var input;
var nick;
var lista;
var usuarioActivo;
var options;

$( document ).ready(function() {
    console.log( "ready!" );
	input = document.getElementById("searcher");
    lista = document.getElementById("listUsers");
    usuarioActivo = document.getElementById("idu");

    input.addEventListener("input", userSearcher);
	
	$("#search-icon").click(function(){
		if($("#searcher").data("id")!=undefined){
			var id=$("#searcher").data("id");
			location.href="/app/perfil/"+id;
		}

	}); 

});

async function userSearcher() {
	
    nick = input.value;
    var response = await fetch(`/api/usersearch/` + nick+ "/" + usuarioActivo.value);
    var usuarios = await response.json();
	loadOptions();
	lista.innerHTML = '';
    showUsuarios(usuarios);

	    

}

function showUsuarios(usuarios) {
    for (const usuario of usuarios) {
	
            var option = document.createElement("option");
			option.setAttribute("class","btnPerfil");
            option.dataset.id = usuario.idusuario;
            var showname = `${usuario.username}, ${usuario.nombre}`;
            option.setAttribute("value", showname);
           	$("#listUsers").append(option);
			
			
    }

}

function loadOptions() {
    options = $("#listUsers").children();
	for(option of options){
		if(nick==option.value){
			var id=option.getAttribute("data-id");
			input.dataset.id=id;
		}
	}

}