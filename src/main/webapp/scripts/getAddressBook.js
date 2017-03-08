$.ajax({
	type : "GET",
	url : "http://localhost:8080/resteasy/service/addressbook/getContacts",
	dataType : 'json',
	success : function(data) {
		console.log("success");
		var items = [];
		
		$.each(data, function(key, val) {
			// alert('rowid: '+val.id);
			items.push("<tr>");
			items.push("<td id='" + key + "'>" + val.id + "</td>");
			items.push("<td id='" + key + "'>" + val.firstName + "</td>");
			items.push("<td id='" + key + "'>" + val.middleName + "</td>");
			items.push("<td id='" + key + "'>" + val.lastName + "</td>");
			items.push("<td id='" + key + "'>" + val.fax + "</td>");
			items.push("<td id=''" + key + "''>" + val.dateOfBirth + "</td>");
			items.push("<td id=''" + key + "''>" + val.homeAddress + "</td>");
			items.push("<td id=''" + key + "''>" + val.workAddress + "</td>");
			items.push("<td id=''" + key + "''>" + val.homePhone + "</td>");
			items.push("<td id=''" + key + "''>" + val.workPhone + "</td>");
			items.push("<td id=''" + key + "''>" + val.cellPhone + "</td>");
			items.push("<td id=''" + key + "''>" + val.emailIds + "</td>");
			items.push("<td><button type='button' " +
					"class='btn btn-info col-md-offset-0' data-toggle='modal' data-target='#myModal' >" +
					"<span class='glyphicon glyphicon-pencil'></span> Edit</button></td>");
			items.push("</tr>");

		});
		$('<tbody/>', {
			html : items.join("")
		}).appendTo("table");
	},
	error : function(e) {
		console.log("Error: " + e);
	}
});
