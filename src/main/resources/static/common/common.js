$('select[data-value]').each(function(index, el) {
  	const $el = $(el);

  	const defaultValue = $el.attr('data-value').trim();
	console.log(defaultValue);
	
	$el.val(defaultValue);
});

