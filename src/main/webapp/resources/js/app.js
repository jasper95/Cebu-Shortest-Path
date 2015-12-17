(function(config){
    if (window.hasOwnProperty('require')) {
        require.config(config);
    } else {
        window.require = config;
    }
})({
    "baseUrl": "resources/js/lib",
    "paths": {
      "app": "../app",
      "mapbox": "//api.mapbox.com/mapbox.js/v2.2.3/mapbox"
    },
    "shim": {
        "jquery.alpha": ["jquery"],
        "jquery.beta": ["jquery"]
    }
});

(function(config){
    if (window.hasOwnProperty('require')) {
        require.config(config);
    } else {
        window.require = config;
    }
})({
    "baseUrl": "resources/js/lib",
    "paths": {
        "mapbox": "//api.mapbox.com/mapbox.js/v2.2.3/mapbox",
        "app": "../app"
    },
});