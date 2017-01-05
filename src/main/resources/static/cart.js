angular.module('ventas', [])
	.controller('venta', function($scope, $http) {
	
    $scope.venta = {};
    
    $scope.iniciarVenta = function(clienteId) {
    	get('/iniciarVenta?clienteId='+clienteId);
    },
    $scope.recargarVenta = function() {
    	get('/recargarVenta');
    },
    $scope.actualizarProducto = function(index, id) {
    	get('/actualizarProducto?index='+index+'&id='+id);
    },
    $scope.actualizarCantidad = function(index, cantidad) {
   		get('/actualizarCantidad?index='+index+'&cantidad='+cantidad);
   },
    $scope.agregarlinea = function() {
	   get('/agregarLinea'); 
   },

    $scope.removelinea = function(index) {
       // $scope.venta.lineas.splice(index, 1);
    }
    
    function get(get) {
    	var api = '/venta/';
	    $http.get(api+get).then(function(response) {
	        $scope.venta =response.data;
	    });
    }
});