function agregar (){

	var usuario= document.forms["pago"]["docu"].value;
	var usuario= document.forms["pago"]["inicio"].value;
	var usuario= document.forms["pago"]["final"].value;

	if (usuario==null || usuario=="") {
		
		Swal.fire({
			type: 'error',
			title: 'Oops...',
			text: 'Por favor llene todos los campos!',
			footer: '<a href>Necesita ayuda?</a>'
		})
		return false;


	}else{
		
		Swal.fire({
			title: 'Esta Seguro de Generar un pago!!?',
			type: 'question',
			showCancelButton: true,
			confirmButtonColor: '#D4AF37',
			cancelButtonColor: '#d33',
			cancelButtonText: 'Cancelar',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.value) {
				Swal.fire({
  type: 'success',
  title: 'Pago Exitoso',
  showConfirmButton: false,
  timer: 30000
})

				window.location.href = "file:///C:/Users/usuario/Desktop/PRE-SUSTENTACI%C3%93N-GAES6/GAES%206%20-%20MAQUETACI%C3%93N/SI/html/Pagos/generar-pago.html"
				
			}
		})
		return false;

	}

	
}

function enviar (){

	var usuario= document.forms["pago"]["docu"].value;
	var usuario= document.forms["pago"]["inicio"].value;
	var usuario= document.forms["pago"]["final"].value;

	if (usuario==null || usuario=="") {
		
		Swal.fire({
			type: 'error',
			title: 'Oops...',
			text: 'Por favor llene todos los campos!',
			confirmButtonColor: '#D4AF37',
			footer: '<a href>Necesita ayuda?</a>',
		})
		return false;


	}else{
		
		Swal.fire({
			title: 'Esta Seguro de Imprimir un pago!!?',
			type: 'question',
			showCancelButton: true,
			confirmButtonColor: '#D4AF37',
			cancelButtonColor: '#d33',
			cancelButtonText: 'Cancelar',
			confirmButtonText: 'Yes!'
		}).then((result) => {
			if (result.value) {
				Swal.fire({
  type: 'success',
  title: 'impresion Enviada',
  showConfirmButton: false,
  timer: 15000

})
				window.location.href = "file:///C:/Users/usuario/Desktop/PRE-SUSTENTACI%C3%93N-GAES6/GAES%206%20-%20MAQUETACI%C3%93N/SI/html/Pagos/generar-pago.html"
				
			}
		})


		return false;

	}

	
}




