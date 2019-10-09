// VALIDAR ACTUALIZACION DE DATOS
function actualizar(){
	var direccion, telefono, correo, expresion;
	direccion = document.getElementById("direccion").value;
	telefono =  document.getElementById("telefono").value;
	correo = document.getElementById("correo").value;
	

	if (direccion == "" || telefono == "" || correo== "") {
		swal({
			title: "Incorrecto",
			text: "Los datos estan incompletos",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (direccion.length>25) {
		swal({
			title: "Incorrecto",
			text: "La direccion es demasiado larga",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (telefono.length>10) {
		swal({
			title: "Incorrecto",
			text: "El telefono es demasiado largo",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (correo.length>20) {
		swal({
			title: "Incorrecto",
			text: "El correo es demasiado largo",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (isNaN(telefono)) {
		swal({
			title: "Incorrecto",
			text: "El telefono es incorrecto",
			icon: "error",
			button: "Volver"
		});
		return false;
	}

}

function asignarRol(){
	var idUsuario, nombreUsuario, apellidoUsuario, docuUsuario, correoUsuario;
	idUsuario = document.getElementById("idUsuario").value;
	nombreUsuario = document.getElementById("nombreUsuario").value;
	apellidoUsuario = document.getElementById("apellidoUsuario").value;
	docuUsuario = document.getElementById("documentoUsuario").value;
	correoUsuario = document.getElementById("correoUsuario").value;

	if (idUsuario == "" || nombreUsuario == "" || apellidoUsuario == "" || docuUsuario == "" || correoUsuario == "") {
		swal({
			title: "Incorrecto",
			text: "Los campos estan vacios",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (nombreUsuario.lenght>15) {
		swal({
			title: "Incorrecto",
			text: "El nombre es muy largo",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (apellidoUsuario.lenght>15) {
		swal({
			title: "Incorrecto",
			text: "El apellido es muy largo",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (docuUsuario.length>8) {
		swal({
			title: "Incorrecto",
			text: "El documento es muy largo",
			icon: "error",
			button: "Volver"
		});
		return false;

	}else if (isNaN(docuUsuario)) {
		swal({
			title: "Incorrecto",
			text: "El documento es incorrecto",
			icon: "error",
			button: "Volver"
		});
		return false;
	}
}