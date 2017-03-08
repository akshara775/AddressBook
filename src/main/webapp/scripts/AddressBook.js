function onSubmit(rowId) {
	
	var jsonObj;
	 try {
		var firstName = document.getElementById(rowId).childNodes[1].innerHTML;
		var lastName = document.getElementById(rowId).childNodes[3].innerHTML;
		// var middleName = document.getElementById(rowId).childNodes[0].value;
		// var dateofBirth = document.getElementById(rowId).childNodes[0].value;
		var email = document.getElementById(rowId).childNodes[5].innerHTML;
		// var homephone = document.getElementById(rowId).childNodes[0].value;
		// var workPhone = document.getElementById(rowId).childNodes[0].value;
		var cellPhone = document.getElementById(rowId).childNodes[7].innerHTML;
		// var fax = document.getElementById(rowId).childNodes[0].value;
		var homeAddress = document.getElementById(rowId).childNodes[9].innerHTML;
		// var workAddress = document.getElementById(rowId).childNodes[0].value;
		//alert('data: ' + firstName+' '+lastName+' '+email+' '+cellPhone+' '+homeAddress);
		jsonObj = {
			"firstName" : firstName,
			"lastName" : lastName,
			// "middleName" : middleName,
			// "dateOfBirth" : dateofBirth,
			// "workAddress" : workAddress,
			"homeAddress" : homeAddress,
			"emailIds" : [ email ],
			// "workPhone" : workPhone,
			"cellPhone" : cellPhone,
			// "fax" : fax
			"contactTag" : {
				"tag" : 0
			}
		};

		//console.log(JSON.stringify(jsonObj, null, 2));
		//alert('data: ' + JSON.stringify(jsonObj));

		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/resteasy/service/addressbook/addContact',
			data : JSON.stringify(jsonObj),
			success : function(data) {
				alert('data success: ' + data);
			},
			error: function() {
				alert('error'+data);
			},
			contentType : "application/json",
			Accept : "application/json",
			dataType : 'json'
		});
		
	}

	catch (err) {
		console.log(err);
	}
}

contactIdAlert = function() {}
contactIdAlert.warning = function(message) {
            $('#contactIdAlert').html('<div class="alert alert-info"><span class="glyphicon glyphicon-warning-sign"></span><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
        }

deleteIdAlert = function() {}
deleteIdAlert.warning = function(message) {
            $('#deleteIdAlert').html('<div class="alert alert-info"><span class="glyphicon glyphicon-warning-sign"></span><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
        }

deleteIdAlert.success = function(message) {
            $('#deleteIdSuccess').html('<div class="alert alert-success"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
        }

function sendTo(id){
//	alert('Id :'+id);
	if(id =='contactId'){
    	var Id = document.getElementById("id").value;
    	//  alert('Id:'+Id);
    	if (Id!=""){
        	location.href="GetContactById.html?id="+Id;
        	// alert('sendTo:'+Id+' '+window.location.href);

    	}
    	else{
    		contactIdAlert.warning(' Contact Id is required');
    		return false;
    	}
	} else	if(id =='deleteId'){
			var deleteId = document.getElementById("deleteId").value;
		//	alert('deleteId :'+deleteId);
    		if (deleteId!=""){
        		onDelete(deleteId);
        		window.location.reload();
        		// alert('sendTo:'+deleteId+' '+window.location.href);
    		}
    		else{
    			deleteIdAlert.warning(' Delete ContactId is required');
       			return false;
    		}
    		
		}
	
}