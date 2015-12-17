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
                    if(response.vertices[i].isLandmark){
                        addMarker(response.vertices[i]);
                        $("#start").append('<option value="'+response.vertices[i].id+'" label="'+response.vertices[i].name+'"/>');
                        $("#end").append('<option value="'+response.vertices[i].id+'" label="'+response.vertices[i].name+'"/>');
                    }
                }
        });
    }
    function addMarker(vertex) {   
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(vertex.lat,vertex.lng),
            title: vertex.name,
            map: map
        });
    }
    init();
    $('#start').on('change', function(e){
        var a = $(this).val();
        if(a !== "-1"){
            var b = $("#end").val();  
            if(b !== "-1"){
                if(a !== b)
                    alert("find path")
            } 
        }
    });
    $('#end').on('change', function(e){
        var a = $(this).val();
        if(a !== "-1"){
            var b = $("#start").val();
            if(b !== "-1"){
                if(a !== b)
                    alert("find path");
            } 
        }
    });
    function findPath(a, b){
        
    }
    function displayResult(){
            
    }
});