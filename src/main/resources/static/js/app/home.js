var input;
var nick;
var lista;
var usuarioActivo;
var btnBuscar;
var options;

window.onload = () => {
    input = document.getElementById("searcher");
    lista = document.getElementById("listUsers");
    usuarioActivo = document.getElementById("idu");
    btnBuscar = document.getElementById("btnBuscar");

    input.addEventListener("keyup", userSearcher);
    $("#options > option").click(function(){
        location.href="/app/perfil/"+$(this).attr("data-id");
    })
}


async function userSearcher() {
    lista.innerHTML = '';
    nick = input.value;
    var response = await fetch(`/api/usersearch/` + nick+ "/" + usuarioActivo.value);
    var usuarios = await response.json();
    showUsuarios(usuarios);
    loadOptions();

}

function showUsuarios(usuarios) {
    for (const usuario of usuarios) {
            var option = document.createElement("option");
            option.className = "btnPerfil";
            option.dataset.id = usuario.idusuario;
            var showname = `${usuario.username}, ${usuario.nombre}`;
            option.setAttribute("value", showname);
            lista.appendChild(option);
        
    }

}

function loadOptions() {
    options = document.querySelectorAll("btnPerfil");
}