// TROCAR DE BRANCO PRA VERDE NA PASSADA DO CURSOR
function iconOnOver(x) {
	x.style.color = "green";
}
function iconNormal(x) {
	x.style.color = "white";
}

function iconOnOverPersonalizado(x) {
	x.style.color = "#ffc107";
}

function iconNormalPersonalizado(x) {
	x.style.color = "#bb414d";
}


$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : "/caixa/input-clientes",
		dataType : 'json',
		success : function(item) {
			var jsonOk = JSON.stringify(item);
			$('#typeaheadIdInstaNomeCliente').typeahead({
				
			    displayText: function (item) {
			        return item.nome + ' , ' + item.telefone + ' , ' + item.instagram
			    },
			    afterSelect: function (item) {
			        this.$element[0].value = item.id
			    },
			    source: item
			});
				
			$('#consultaCadastroCliente').typeahead({
				
			    displayText: function (item) {
			        return item.nome + ', ' + item.telefone + ', ' + item.instagram
			    },
			    afterSelect: function (item) {
			        this.$element[0].value = item.nome + ', ' + item.telefone + ', ' + item.instagram
			    },
			    source: item
			});
		},
		error : function(e) {
			alert("Ocorreu um erro, contate o suporte!");
		}
	});

});

$(document).ready(function(){
	  $('[data-toggle="popover"]').popover({
		  trigger: 'hover'
	  });   
	});