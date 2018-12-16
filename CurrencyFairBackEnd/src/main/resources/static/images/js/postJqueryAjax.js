$(document).ready(function(){
	
	// SUBMIT FORM
    $("form").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		var inputs = $(this).find('input');
		
    	// prepare data from input-form
    	var data = {
    		firstname : $(inputs[0]).val(),
    		lastname : $(inputs[1]).val()
    	}

		ajaxPost(data);
    	
    	// reset input data
    	$(inputs[0]).val("");
    	$(inputs[1]).val("")
	});
    
    function ajaxPost(data){
    	$.ajax({
        url: "http://localhost:8080/getMessages"
      }).then(function(data) {
        $('#postResultMsg').append(data);
      });
    }
});