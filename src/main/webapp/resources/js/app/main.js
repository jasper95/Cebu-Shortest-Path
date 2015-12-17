define(["jquery","mapbox"], function($, mapbox) {
   L.mapbox.accessToken = 'pk.eyJ1IjoiamFzcGVyOTUiLCJhIjoiY2lpOHZkZHJvMDBsZnRqbTNmb242MG9qdiJ9.qxldio_NH_RgQxMwnKj5Jg';
   var map = L.mapbox.map('map', 'mapbox.streets')
   .setView([10.3224, 123.8985], 15);

});