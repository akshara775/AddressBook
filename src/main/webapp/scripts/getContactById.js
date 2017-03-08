	var getUrlParameter = function getUrlParameter(sParam) {
		var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        	sURLVariables = sPageURL.split('&'),
        	sParameterName,
        	i;
		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true : sParameterName[1];
			}
		}
	};
	var myId = getUrlParameter('id');

//	alert("Url:" + "http://localhost:8080/resteasy/service/addressbook/getContact/"+myId);

$.ajax({
	type : "GET",
	url : "http://localhost:8080/resteasy/service/addressbook/getContacts",
	dataType : 'json',
	success : function(data) {
		console.log("success");
		var items = [];
		$.each(data, function(key, val) {
			if(val.id==myId){
				// alert('val:'+val.id)
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
				items.push("</tr>");
				}
			});

			$('<tbody/>', {
				html : items.join("")
			}).appendTo("table");
			},
	error : function(e) {
		console.log("Error: " + e+" url:"+url);
	}

});
