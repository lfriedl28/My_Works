// jQuery statement anatomy
// $([selector]).method([parameter(s)])

//$(document).ready(function() {});
$(function() {
    
    var nativeWidth = 0,
        nativeHeight = 0,
        $magnifyDiv = $('.magnify');
    
    // Only after the native dimensions of
    // our image are available will our 
    // script show the "zoomed" version 
    // of the image
    //
    // Since nativeWidth and nativeHeight
    // start at 0, we need to get the image's
    // actual dimensions after it is finished
    // downloading.
    var imageObject = new Image();
    
    imageObject.src = $('.small').attr('src');  // load image
    console.log(imageObject.src);
    
    // wait until new image has finished loading...
    // imageObject.addEventListener('load', function() {}, false);
    imageObject.onload = function() {
        
        var magnifyOffset,
            mouseX,
            mouseY,
            $glass = $('.large'),
            glassWidth = $glass.width(),
            glassHeight = $glass.height(),
            backgroundX,
            backgroundY,
            posX,
            posY,
            $smallImage = $('.small'),
            smallImageWidth = $smallImage.width(),
            smallImageHeight = $smallImage.height();
        
        nativeWidth = imageObject.width;
        nativeHeight = imageObject.height;
        
        
        // When user moves the mouse anywhere within the div.magnify
        // do the following...
        $magnifyDiv.mousemove(function(e) {
            
            // Wrap the container in a jQuery object
            $target = $(this);
            
            // get coordinates of our container element relative
            // to the edges of the page (document)
            magnifyOffset = $target.offset();
            
            // Subtract the top and left offset values  to get 
            // our cursor location relative to the edges
            // of div.magnify
            
            mouseX = e.pageX - magnifyOffset.left;
            mouseY = e.pageY - magnifyOffset.top;
            
            //alert('left edge to page left edge = ' + mouseX + '\ntop edge to page top edge = ' + mouseY);
            
            // Fade in the magnifying glass if the mouse
            // is inside of div.magnify and fade it out when
            // it leaved div.magnify
            
            $magnifyDiv.mouseenter(function(){
                document.body.style.cursor = 'none';
                $glass.stop().fadeIn(100); 
            });
            
            // make the magnifying glass (div.large) follow the mouse
            //pointer.
            // Do calculations to "zoom" to the correct part of
            // the larger image (background image) that corresponds
            // to where the mouse pointer is.
            
            posX = mouseX - (glassWidth/2);
            posY = mouseY - (glassHeight/2);
            
            backgroundX = Math.round(mouseX / smallImageWidth * nativeWidth - (glassWidth/2)) * -1;
            backgroundY = Math.round(mouseY / smallImageHeight * nativeHeight - (glassHeight/2)) * -1;

            
            $glass.css({top : posY,
                        left: posX,
                        backgroundPosition: backgroundX + 'px ' + backgroundY + 'px'          
            });
            
            
            if ((mouseX < 0 || mouseX > smallImageWidth) || (mouseY < 0 || mouseY > smallImageHeight)) {
                $glass.stop().fadeOut(100);
                document.body.style.cursor = 'default';
            }
        
        });
    };
    
});