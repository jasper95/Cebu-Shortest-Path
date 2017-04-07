define(["jquery","gmap"], function($) {
    var map;
    var directionsDisplay;
    var directionsService;
    var stepDisplay;
    var markerArray = [];
    
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
            url: "http://localhost:8080/CMSC142/get-all-vertices-edges",
            success: function(response){
                for(i=0; i <response.vertices.length; i++)
                    if(response.vertices[i].isLandmark){
                        addMarker(response.vertices[i]);
                        $("#start").append('<option value="'+response.vertices[i].id+'" label="'+response.vertices[i].name+'"/>');
                        $("#end").append('<option value="'+response.vertices[i].id+'" label="'+response.vertices[i].name+'"/>');
                    }
                }
        });
        var rendererOptions = {
            map: map
        };
        directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        directionsService = new google.maps.DirectionsService();
        stepDisplay = new google.maps.InfoWindow();
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
                    findPath(a, b);
            } 
        }
    });
    $('#end').on('change', function(e){
        var a = $(this).val();
        if(a !== "-1"){
            var b = $("#start").val();
            if(b !== "-1"){
                if(a !== b)
                    //alert("find path");
                    findPath(b, a);
            } 
        }
    });
    function findPath(a, b){
        $.ajax({    
            url: "http://localhost:8084/CMSC142/find-shortest-path/"+a+"/"+b,
            success: function(response){
                for (var i = 0; i < markerArray.length; i++) {
                    markerArray[i].setMap(null);
                }
                // Now, clear the array itself.
                markerArray = [];
                var coords = [];
                for(i=0; i < response.path.length; i++){
                    coords.push(new google.maps.LatLng(response.path[i].lat, response.path[i].lng));
                }    
                var waypts1 = [];
                for(var j = 0;j < coords.length; j++){            
                      waypts1.push({location: coords[j], stopover: true});
                }
                  //i love lemonharpy -> http://lemonharpy.wordpress.com/2011/12/15/working-around-8-waypoint-limit-in-google-maps-directions-api/
                var batches = [];
                var itemsPerBatch = 10; // google API max - 1 start, 1 stop, and 8 waypoints
                var itemsCounter = 0;
                var wayptsExist = waypts1.length > 0;
                alert(coords.length);
                while (wayptsExist) {
                    var subBatch = [];
                    var subitemsCounter = 0;
                    for (var j = itemsCounter; j < waypts1.length; j++) {
                        subitemsCounter++;
                        subBatch.push(waypts1[j]);
                        if (subitemsCounter == itemsPerBatch)
                            break;
                    }
                    itemsCounter += subitemsCounter;
                    batches.push(subBatch);
                    wayptsExist = itemsCounter < waypts1.length;
                    // If it runs again there are still points. Minus 1 before continuing to 
                    // start up with end of previous tour leg
                    itemsCounter--;
                }
                var combinedResults;
                var unsortedResults = [{}]; // to hold the counter and the results themselves as they come back, to later sort
                var directionsResultsReturned = 0;

                for (var k = 0; k < batches.length; k++) {
                    var lastIndex = batches[k].length - 1;
                    alert(batches[k][0].location);
                    var start = batches[k][0].location;
                    var end = batches[k][lastIndex].location;

                    // trim first and last entry from array
                    var waypts = [];
                    waypts = batches[k];
                    waypts.splice(0, 1);
                    waypts.splice(waypts.length - 1, 1);

                    var request = {
                        origin : start,
                        destination : end,
                        waypoints : waypts,
                        travelMode : google.maps.TravelMode.DRIVING
                    };
                    (function (kk) {
                      directionsService.route(request, function (result, status) {
                          if (status == google.maps.DirectionsStatus.OK) {
                              var unsortedResult = {
                                  order : kk,
                                  result : result
                              };
                              unsortedResults.push(unsortedResult);

                              directionsResultsReturned++;

                              if (directionsResultsReturned == batches.length) // we've received all the results. put to map
                              {
                                  // sort the returned values into their correct order
                                  unsortedResults.sort(function (a, b) {
                                      return parseFloat(a.order) - parseFloat(b.order);
                                  });
                                  var count = 0;
                                  for (var key in unsortedResults) {
                                      if (unsortedResults[key].result != null) {
                                          if (unsortedResults.hasOwnProperty(key)) {
                                              if (count == 0) // first results. new up the combinedResults object
                                                  combinedResults = unsortedResults[key].result;
                                              else {
                                                  // only building up legs, overview_path, and bounds in my consolidated object. This is not a complete
                                                  // directionResults object, but enough to draw a path on the map, which is all I need
                                                  combinedResults.routes[0].legs = combinedResults.routes[0].legs.concat(unsortedResults[key].result.routes[0].legs);
                                                  combinedResults.routes[0].overview_path = combinedResults.routes[0].overview_path.concat(unsortedResults[key].result.routes[0].overview_path);

                                                  combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getNorthEast());
                                                  combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getSouthWest());
                                              }
                                              count++;
                                          }
                                      }
                                  } //end for
                                  directionsDisplay.setDirections(combinedResults);
                                  //showSteps(combinedResults);
                              } //end if
                          } // end if
                        }); // end directions
                    })(k); //end function
                } //end for
                    } 
                });
    }
    
//    function showSteps(directionResult) {
//        // For each step, place a marker, and add the text to the marker's
//        // info window. Also attach the marker to an array so we
//        // can keep track of it and remove it when calculating new
//        // routes.
//        var myRoute = directionResult.routes[0].legs[0];
//
//        for (var i = 0; i < myRoute.steps.length; i++) {
//          var marker = new google.maps.Marker({
//            position: myRoute.steps[i].start_point,
//            map: map
//          });
//          attachInstructionText(marker, myRoute.steps[i].instructions);
//          markerArray[i] = marker;
//        }
//      }
//
//      function attachInstructionText(marker, text) {
//        google.maps.event.addListener(marker, 'click', function() {
//          // Open an info window when the marker is clicked on,
//          // containing the text of the step.
//          stepDisplay.setContent(text);
//          stepDisplay.open(map, marker);
//        });
//      }
});