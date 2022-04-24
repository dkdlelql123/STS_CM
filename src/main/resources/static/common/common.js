$('select[data-value]').each(function(index, el) {
  	const $el = $(el); 
  	
  	let defaultValue = $el.attr('data-value').trim();
	//console.log(defaultValue);
	
	if(defaultValue=="")
		defaultValue = "title"; 
	
	$el.val(defaultValue);
});

