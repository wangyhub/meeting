// 滚屏
$(document).ready(function(){
	$(window).scroll(function(){//滚动时触发函数
	$("#mbxID").css("top",$(document).scrollTop());//将滚动条高度赋给悬浮框。
	})
})
/**
$(document).ready(function(){
	var rollStart = $('.mbxID'), 
       rollSet = $('.mbxID'); 
       var offset = rollStart.offset(), 
       objWindow = $(window), 
       rollBox = rollStart.prev(); 
       if (objWindow.width() > 960) { 
           objWindow.scroll(function() { 
               if (objWindow.scrollTop() > offset.top) { 
                   rollStart.css('position','fixed'); 
                   rollStart.stop().animate({ 
                       top: 0 
                   }, 
                   400) 
               } else { 
                   rollStart.css('position','static'); 
                   rollStart.stop().animate({ 
                       top: 0 
                   }, 
                   400) 
               } 
           }) 
       } 
});
**/