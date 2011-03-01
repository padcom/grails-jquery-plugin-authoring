(function(\$){

// default values for options
var defaults = {  
};  

// methods
var methods = {
  // this function is called when the plugin is first initialized
  // put your plugin initialization (mostly the plugin body) here
  init: function(options) {
    return this.each(function() {
      \$.extend(defaults, options);

      // TODO: implement your plugin logic here
      //       remember to properly name your event handlers!

      // \$(window).bind('resize.${plugin_name}', methods.resize);

      // so that they can be easily unbound at once in the "destroy" method

    });
  },

  // this is the convention-based destructor
  destroy: function() {
    return this.each(function() {
      // \$(window).unbind('.${plugin_name}');
    });
  }
};

\$.fn.${plugin_name} = function(method) {
  // plugin body - do not modify
  if (methods[method]) {
    return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
  } else if (typeof method === 'object' || ! method) {
    return methods.init.apply(this, arguments);
  } else {
    \$.error('Method ' +  method + ' does not exist on jQuery.${plugin_name}');
  }    
};

})(jQuery);
