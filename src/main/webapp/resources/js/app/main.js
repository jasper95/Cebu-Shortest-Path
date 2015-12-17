define(["jquery","gmap"], function($) {
    var map;
    function init()
    {
        var cebu = new google.maps.LatLng(10.3224,123.8985);
        var mapOptions = {
            center: cebu,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        stepDisplay = new google.maps.InfoWindow();
        $.ajax({
            url: "http://localhost:8084/CMSC142/get-all-vertices-edges",
            success: function(response){
                for(i=0; i <response.vertices.length; i++)
                    addMarker(response.vertices[i]);
            }
        });
    }
    function addMarker(vertex) {   
        // Add the marker at the clicked location, and add the next-available label
        // from the array of alphabetical characters.
        var marker = new google.maps.Marker({
          position: new google.maps.LatLng(vertex.lat,vertex.lng),
          title: vertex.name,
          map: map
        });
    }
    
    init();    
});