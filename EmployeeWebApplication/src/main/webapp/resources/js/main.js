$(function(){
	
	$('a#buyLink').click(function(){
		$(this).hide();
		$(this).parent().parent().find('div.customerInfo').fadeIn();
		
	});
});