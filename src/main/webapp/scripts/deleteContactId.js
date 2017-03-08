function onDelete(Id) {
$.ajax({
	type : "DELETE",
	url : "http://localhost:8080/resteasy/service/addressbook/deleteContact/"+Id,
	dataType : 'json',
	success : function(res) {
		console.log("success:"+res);
	},
	error : function(e) {
		console.log("Error: " + e);
	}
});
}