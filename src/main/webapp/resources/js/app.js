(function(config){
    if (window.hasOwnProperty('require')) {
        require.config(config);
    } else {
        window.require = config;
    }
})({
    "baseUrl": "resources/js/lib",
    "paths": {
        "gmap": "//maps.googleapis.com/maps/api/js?key=AIzaSyDgc5j3MverrRKKXa1HabDuUC2THaGJyUA",
        "app": "../app"
    },
});