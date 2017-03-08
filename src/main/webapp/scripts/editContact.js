$(function(){
	var columns=[];
	$('#myTable').on( 'click', 'tbody tr td', function() {
		   // Get array of column Headings
	       var columnHeadings = $("thead th").map(function() {
	                 return $(this).text();
	              }).get();
	      // alert('columnHeadings: '+columnHeadings);
	    // Remove the last column heading (for the Edit button)
	       columnHeadings.pop();
	    
	       // Get array of column values from the row where the Edit button was clicked
	       var columnValues = $(this).closest('tr').find('td').eq(0).text();
	       
	       for (step = 0; step < 12; step++){
	    	   var columnValue = $(this).closest('tr').find('td').eq(step).text();
	    	   columns.push(columnValue);
	       }
	       //alert("columns:"+columns);
	       

	       var modalBody = $('<div id="modalContent"></div>');
			  var modalForm = $('<form role="form" name="modalForm" action="/service/addressbook/updateContact" method="put"></form>');
			  // Create the form in the modal dynamically
			  $.each(columnHeadings, function(i, columnHeader) {
				  	
			       var formGroup = $('<div class="form-group"></div>');
			       formGroup.append('<label for="'+columnHeader+'">'+columnHeader+'</label>');
			       //alert('values:'+$(this).closest('tr').find('td').eq("'"+i+"'").text());
			       if(i==0){
			    	   formGroup.append('<input class="form-control" name="'+columnHeader+'" id="'+columnHeader+'" value="'+columns[i]+'" disabled/>'); 
			       }else {
			    	   formGroup.append('<input class="form-control" name="'+columnHeader+'" id="'+columnHeader+'" value="'+columns[i]+'" />'); 
			       }
			       
			       modalForm.append(formGroup);
			  });
			  
			 modalBody.append(modalForm);
			 $('.modal-body').html(modalBody);
			});
			
	
			$('#saveChanges').on('click',function() {
			   //alert( "Handler for .submit() called." );
			   var jsonObj;
			   try {
				   var myform = $('form');
				   var disabled = myform.find(':input:disabled').removeAttr('disabled');
				   var newColumns =  myform.serializeArray().map(function(v) {
					   return v.value;
					   });
				   disabled.attr('disabled','disabled');
				   
				   //alert('columns'+columns);
				   //alert('newColumns: '+newColumns);
				   
				   jsonObj = {
						   	"id":newColumns[0],
							"firstName" : newColumns[1],
							"middleName": newColumns[2],
							"lastName" : newColumns[3],
							"fax" : newColumns[4],
							"dateOfBirth" : newColumns[5],
							"homeAddress" : newColumns[6],
							"workAddress" : newColumns[7],
							"homePhone":newColumns[8],
							"workPhone" : newColumns[9],
							"cellPhone" : newColumns[10],
							"emailIds" : [newColumns[11]] ,
							"contactTag" : {
								"tag" : 0
							}
				  };
				   //alert('data: ' + JSON.stringify(jsonObj));
				   $.ajax({
					    type: 'PUT',
						url : 'http://localhost:8080/resteasy/service/addressbook/updateContact',
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
				   
				   window.location.reload();
			   }catch (err) {
					console.log(err);
				}
			   
			   event.preventDefault();
			});
			
				
});
		
	
	
	