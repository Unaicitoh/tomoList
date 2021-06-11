/**
 * Confirmacion y borrado de usuario
 */

var idusu;
var btnBorrar;
var filas;
window.onload=()=>{

    btnBorrar=document.querySelectorAll(".btn-del");
    filas=document.querySelectorAll(".row");
   botones=document.querySelectorAll(".btn");
    filas=document.querySelectorAll(".row");
    btnBorrar.forEach(b => {
        b.addEventListener("click", borrado);
    });

    filas.forEach(f=>{
        f.addEventListener("click",(ev)=>{
            var id=ev.target.parentElement.parentElement.firstElementChild.value;
            
        })
    })
    
    for(btn of botones){
    btn.addEventListener("click", (ev)=>{
    	ev.target.firstElementChild.click();
    });
    }
    
}

function borrado(ev){
    var id=ev.target.parentElement.parentElement.parentElement.firstElementChild.value;
    var resultado=window.confirm("Estas seguro de borrar amistad?");
    if(resultado){
        location.href="/admin/mensajes/borrado/"+id;
    }else{
	 location.href="/admin/mensajes"
	}
}