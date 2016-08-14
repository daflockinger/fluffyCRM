function showContent(callUrl){
		$.ajax({
			  method: "GET",
			  url: callUrl,
			}).done(
			  function( html ) {
			    $("#content").html(html);
			  }).fail(function(jqXHR, textStatus) {
				  console.log("error: " + textStatus + " " + jqXHR);
			  });
}


function showUserEdit(id,path){
	$.ajax({
		  method: "GET",
		  url: "/" + path + "/" + id,
		}).done(
		  function( html ) {
		    $('#editForm').html(html);
		  }).fail(function(jqXHR, textStatus) {
			  console.log("error: " + textStatus + " " + jqXHR);
		  });
}
function showAddEntity(path){
	$.ajax({
		  method: "GET",
		  url: "/" + path + "/-1",
	}).done(
		function( html ) {
			$('#editForm').html(html);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}
function deleteEntity(id,path){
	if(confirm('Delete ' + path + ' with id ' + id + '?')){
		$.ajax({
		  	method: "DELETE",
		  	url: "/" + path + "/" + id,
		}).done(
		  	function( html ) {
			  showContent("/" + path);
		}).fail(
			function(jqXHR, textStatus) {
			  console.log("error: " + textStatus + " " + jqXHR);
		});
	}
}

function saveEntity(path){
	$.ajax({
		  method: "POST",
		  url: "/" + path,
		  data: $("#editForm").serialize(),
	}).done(
		function( html ) {
			$('#editModal').modal('hide');
			setTimeout(function(){showContent(path);},1000);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}