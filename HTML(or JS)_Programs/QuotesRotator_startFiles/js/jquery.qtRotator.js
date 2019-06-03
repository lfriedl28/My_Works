;(function($, window, undefined) {
    
    'use strict';
    
    var Modernizr = window.Modernizr;
    
    // Constructor function for our plugin object
    $.QTRotator = function(options, selectedDOMElement) {
        
        this.$selectedElement = $(selectedDOMElement);
        this._init(options);
        
    };
    
    // Set up properties and methods of our new plugin object's "class"
    
    // Set up defaults property
    $.QTRotator.defaults = {
        speed: 700,
        easing: 'ease',
        interval: 8000
    };
    
    // Object's method definitions via the prototype property
    //
    // Could have done this for _init()
    //
    // $.QTRotator._init = function(options) {};
    $.QTRotator.prototype = {
        
        _init: function(options) {
            
            this.mergedOptions = $.extend($.QTRotator.defaults, options);
            
            this._config();  // set more properties on our new object
            
            // show current quote
            this.$items.eq(this.currentIndex).addClass('quoteCurrent');
            
            if (this.support) {
                this._setTransition();
                this._startRotator();
            }
            
            // Start rotating the quotes
            
        },
        
        _config: function() {
            
            this.$items = $('.quoteContent');
            console.log("# of .quoteContent divs = " + this.$items.length);
            
            this.itemsCount = this.$items.length;
            
            this.currentIndex = 0;
            
            this.support = Modernizr.csstransitions;
            
            if (this.support) {
                
                this.$progressBar = $('<span class = "quoteProgress"></span>').appendTo(this.$selectedElement);
                
            } else {
                
                // do some alternative tasks here... maybe
                
            }
            
        },
        
        _setTransition: function() {
            
            setTimeout($.proxy(function() {
                
                this.$items.css('transition', 'opacity ' + this.mergedOptions.speed + 'ms ' + this.mergedOptions.easing);
                
            }, this), 25);
            
        },
        
        _startRotator: function() {
            
            // Animate the width of the progress bar
            // from 0% to 100% over time
            
            if (this.support) {
                
                this._startProgress();
                
            }
            
            // Reset the progress bar and animate in the next quote
            
            setTimeout($.proxy(function() {
                
               // reset progress bar
                if(this.support) {
                    this._resetProgressBar();
                }
                
                // bring in next quote
                this._nextQuote();
                this._startRotator();
                
            }, this), this.mergedOptions.interval + 100)
            
        },
        
        _startProgress: function() {
            
            setTimeout($.proxy(function() {
                this.$progressBar.css({
                    transition: 'width ' + this.mergedOptions.interval + 'ms linear',
                    width: '100%'
                });                
            }, this), 25);
            
        },
        
        _resetProgressBar: function() {
            
            this.$progressBar.css({
               
                transition: 'none',
                width: '0%'
                
            });
            
        },
        
        _nextQuote: function() {
            
            // hide the currently showing quote
            
            this.$items.eq(this.currentIndex).removeClass('quoteCurrent');
            
            // get the index of the next quote (div.quoteContent)
            // this.currentIndex +=1;
            // ternary operator:
            // identifier = condition ? then code : else code;
            this.currentIndex = this.currentIndex < this.itemsCount -  1 ? this.currentIndex + 1: 0;
            
            // show the next quote
           
            this.$items.eq(this.currentIndex).addClass('quoteCurrent');
            
        }
        
    };
    
    
    // Define our plugin method (function)
    $.fn.qtRotator = function(options) {
        
        if (typeof options === 'string') {
            
            // not as common, leave off code for now...
            
        }
        else {  // options !== 'string', usually meaning it is an object
            
            // here, this refers to the jQuery object that was used
            // to call this plugin method ($('#quoteRotator'))
            this.each(function() {
                
                // here inside our each()'s function, the context of this
                // has changed and it now refers to the current matched
                // element (DOM element), that each is iterating over right now.
                // In our case, this refers to div#quoteRotator
                var instance = $.data(this, 'qtRotator');
                
                if (instance) {
                    instance._init();
                } else {
                    
                   instance = $.data(this, 'qtRotator', new $.QTRotator(options, this)); 
                    
                }
                
            });
            
        }
        
        return this;  // make our qtRotator plugin method chainable
        
    };
    
    
})(jQuery, window);        