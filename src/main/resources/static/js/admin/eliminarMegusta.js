/**
 * Confirmacion y borrado de megusta
 */

var idusu;
var btnBorrar;
var filas;
window.onload = () => {

    btnBorrar = document.querySelectorAll(".btn-del");
    botones = document.querySelectorAll(".btn");
    filas = document.querySelectorAll(".row");
    btnBorrar.forEach(b => {
        b.addEventListener("click", borrado);
    });

    filas.forEach(f => {
        f.addEventListener("click", (ev) => {
            var id = ev.target.parentElement.parentElement.firstElementChild.value;

        })
    })

    for (btn of botones) {
        btn.addEventListener("click", (ev) => {
            ev.target.firstElementChild.click();
        });
    }

}

function borrado(ev) {
    var id = ev.target.parentElement.parentElement.parentElement.firstElementChild.value;
    var resultado = window.confirm("Estas seguro de borrar me gusta?");
    if (resultado) {
        location.href = "/admin/likes/borrado/" + id;
    } else {
        location.href = "/admin/likes"
    }
}