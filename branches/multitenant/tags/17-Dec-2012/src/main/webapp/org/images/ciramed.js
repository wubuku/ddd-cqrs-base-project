var tabs = {
	init:function(index,targetCont,clsCont,clsActive){
		/*$.each($(targetCont),function(key,value){
			$(targetCont+' li.'+clsActive).removeClass(clsActive);
			$(value).find('li:nth-child('+index+')').addClass(clsActive);
			$(targetCont+' li a').click(function(){
				$current = $(targetCont+' li.'+clsActive);
				$current.removeClass(clsActive);
				$($current.attr('rel')).hide();
				console.log($current)
				$(this).parent().addClass(clsActive);
				$($(this).attr('rel')).show();
				if($(this).attr('rel') == '.emptyTab')$($(this).attr('rel')).html(this.innerHTML);
			});
		});*/
		$(clsCont).hide();//$(clsCont+':not(:first)').hide();
		$(targetCont+' li.'+clsActive).removeClass(clsActive);
		$li = $(targetCont+' li:nth-child('+index+')').addClass(clsActive);
		$($li.find('a').attr('rel')).show()
		$(targetCont+' li a').click(function(){
			$current = $(targetCont+' li.'+clsActive);
			$current.removeClass(clsActive);
			$($current.children(0).attr('rel')).hide();
			$(this).parent().addClass(clsActive);
			var contRef = $(this).attr('rel');
			$(contRef).show();
			if($(contRef).data('callback'))$(contRef).data('callback')();
			if(contRef.indexOf('.emptyTab')>=0){
				$(contRef).css({'padding':'100px 10px','text-align':'center'}).html(this.title?this.title:this.innerHTML);
			}
		});
	}
}

$.fn.equalH = function() {
	var height = 0,
	reset = $.browser.msie && $.browser.version < 7 ? "1%" : "auto";
  
	return this
		.css("height", reset)
		.each(function() {height = Math.max(height, this.offsetHeight);})
		.css("height", height)
		.each(function() {
			var h = this.offsetHeight;
			if (h > height) {
				$(this).css("height", height - (h - height));
			};
		});
};


$.extend($.expr[":"], {
    "Contains": function(elem, i, match, array) {
        return (elem.textContent || elem.innerText || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
});

$.fn.today = function(blank) {
	if(blank) return this.val('')
	var d = new Date();
	return this.val((d.getMonth()+1)+'/'+d.getDate()+'/'+d.getFullYear());
};

$.fn.now = function(blank) {
	if(blank) return this.val('')
	var d = new Date();
	if(d.getHours()>12) return this.val(((d.getHours()-12)<10?'0'+(d.getHours()-12):(d.getHours()-12))+':'+(d.getMinutes()<10?'0'+d.getMinutes():d.getMinutes())+' PM');
	else return this.val((d.getHours()<10?'0'+d.getHours():d.getHours())+':'+(d.getMinutes()<10?'0'+d.getMinutes():d.getMinutes())+' AM');
};



$.fn.eSlider = function(options){
	var defaults = {
		prevClass: 		'prev',
		prevText: 		'',
		nextClass: 		'next',	
		nextText: 		'',
		orientation:	'', //  'vertical' is optional;
		speed: 			500			
	}; 
		
	var options = $.extend(defaults, options);
	return this.each(function() {  
		obj = $(this); 				
		var s = $("ul>li", obj).length;
		var w = obj.width(); 
		//var h = $("li", obj).height()+10;//obj.height(); 
		var h = $("li", obj)[0].offsetHeight;
		var ts = s-1;
		var t = 0;
		var vertical = options.orientation;
		//$("ul", obj).css('width',s*w);
		$(obj).css({'height':h,'overflow':'hidden'});
		if(!vertical) $("li", obj).css('float','left');
		if(s>1){
			$(obj).prev().find('.pagination').html('<a href=\"javascript:void(0);\" class="'+options.prevClass+'">'+options.prevText+'</a><span class="page"> 1 of '+s+'</span><a href=\"javascript:void(0);\" class="'+options.nextClass+'">'+options.nextText+'</a>');
			
			$(obj).prev().find('.pagination a.next').click(function(){		
				animate("next");
				$(this).siblings('.page').html(' '+(t+1)+' of '+s);
			});
			$(obj).prev().find('.pagination a.prev').click(function(){		
				animate("prev");
				$(this).siblings('.page').html(' '+(t+1)+' of '+s);
			});	
		}
		if($(obj).prev().find('.close').length<=0)$(obj).prev().find('.pagination').append($(' <a href=\"javascript:void(0);\" class="close"></a>'))
		$(obj).prev().find('.pagination a.close').click(function(){
			$(obj).parent().hide(options.speed);
		});
		
		
		function animate(dir){
			if(dir == "next"){
				//t = (t>=ts) ? ts : t+1;	
				t = (t>=ts) ? 0 : t+1;	
			} else {
				//t = (t<=0) ? 0 : t-1;
				t = (t<=0) ? ts : t-1;
			};								
			if(!vertical) {
				p = (t*w*-1);
				$("ul",obj).animate({ marginLeft: p }, options.speed);				
			} else {
				p = (t*h*-1);
				$("ul",obj).animate({ marginTop: p }, options.speed);					
			}
		};
	});
}

function formatPhone (e,inp) {
	var input = document.getElementById(inp);
    evt = e || window.event; /* firefox uses reserved object e for event */
	var pressedkey = evt.which || evt.keyCode;
    var BlockedKeyCodes = new Array(8,27,13,9); //8 is backspace key
    var len = BlockedKeyCodes.length;
    var block = false;
    var str = '';
    for (i=0; i<len; i++){
       str=BlockedKeyCodes[i].toString();
       if (str.indexOf(pressedkey) >=0 ) block=true;
    }
    if (block) return true;

   var s = input.value;
   if (s.charAt(0) =='+') return false;
   filteredValues = '"`!@#$%^&*()_+|~-=\QWERT YUIOP{}ASDFGHJKL:ZXCVBNM<>?qwertyuiop[]asdfghjkl;zxcvbnm,./\\\'';
   var i;
   var returnString = '';
   for (i = 0; i < s.length; i++) {
         var c = s.charAt(i);
         if ((filteredValues.indexOf(c) == -1) & (returnString.length <  14 )) {
             if (returnString.length==0) returnString +='(';
    	     if (returnString.length==4) returnString +=')-';
    	     if (returnString.length==9) returnString +='-';
             returnString += c;
    	     }
   	}
   input.value = returnString;
   return false
}

function expandRHS(uuid){ 
	var rightCell = jQuery('#'+uuid).parent().find("td.soapNoteContR");
	if(rightCell.hasClass("CCRCollapse")){ 
		var rightHandle = rightCell.find("div.slideHandle");
		rightCell.toggleClass('CCRCollapse');
		rightHandle.toggleClass('SHCollapse');
		rightHandle.parent().find('div.panelCont').toggleClass('hidePanelCont');
		rightHandle.parent().find('div.panelFoot').toggleClass('hidePanelCont');
		rightHandle.parent().find('button').toggleClass('hidePanelCont');
		var obj = slideHandle.parent().parent().parent().parent().find('.z-listbox-header');
		obj.css('width','auto');
	} 
}

 function formatZipcode (e,inp) {
 	var input = document.getElementById(inp);
     evt = e || window.event; /* firefox uses reserved object e for event */
		var pressedkey = evt.which || evt.keyCode;
     var BlockedKeyCodes = new Array(8,27,13,9); //8 is backspace key
     var len = BlockedKeyCodes.length;
     var block = false;
     var str = '';
     for (i=0; i<len; i++){
        str=BlockedKeyCodes[i].toString();
        if (str.indexOf(pressedkey) >=0 ) block=true;
     }
     if (block) return true;

    var s = input.value;
    if (s.charAt(0) =='+') return false;
    filteredValues = '"`!@#$%^&*()_+|~-=\QWERT YUIOP{}ASDFGHJKL:ZXCVBNM<>?qwertyuiop[]asdfghjkl;zxcvbnm,./\\\'';
    var i;
    var returnString = '';
    for (i = 0; i < s.length; i++) {
          var c = s.charAt(i);
          if ((filteredValues.indexOf(c) == -1) & (returnString.length <  10 )) {
     	     if (returnString.length==5) returnString +='-';
	             returnString += c;
     	     }
    	}
    input.value = returnString;
    return false
}
 
 function formatSSN (e,inp) {
 	var input = document.getElementById(inp);
     evt = e || window.event; /* firefox uses reserved object e for event */
		var pressedkey = evt.which || evt.keyCode;
     var BlockedKeyCodes = new Array(8,27,13,9); //8 is backspace key
     var len = BlockedKeyCodes.length;
     var block = false;
     var str = '';
     for (i=0; i<len; i++){
        str=BlockedKeyCodes[i].toString();
        if (str.indexOf(pressedkey) >=0 ) block=true;
     }
     if (block) return true;

    var s = input.value;
    if (s.charAt(0) =='+') return false;
    filteredValues = '"`!@#$%^&*()_+|~-=\QWERT YUIOP{}ASDFGHJKL:ZXCVBNM<>?qwertyuiop[]asdfghjkl;zxcvbnm,./\\\'';
    var i;
    var returnString = '';
    for (i = 0; i < s.length; i++) {
          var c = s.charAt(i);
          if ((filteredValues.indexOf(c) == -1) & (returnString.length <  11 )) {
     	     if (returnString.length==3) returnString +='-';
     	     if (returnString.length==6) returnString +='-';
	             returnString += c;
     	     }
    	}
    input.value = returnString;
    return false
}