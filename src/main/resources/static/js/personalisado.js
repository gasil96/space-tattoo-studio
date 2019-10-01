// TROCAR DE BRANCO PRA VERDE NA PASSADA DO CURSOR
function iconOnOver(x) {
	x.style.color = "green";
}
function iconNormal(x) {
	x.style.color = "white";
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