angular.module('ventas', ['ngAnimate'])
	.controller('venta', function($scope, $http) {
	
    $scope.venta = {};
    
    $scope.iniciarVenta = function(clienteId) {
    	get('iniciarVenta?clienteId='+clienteId);
    },
    $scope.recargarVenta = function() {
    	get('recargarVenta');
    },
    $scope.reiniciarVenta = function() {
    	get('reiniciarVenta');
    },
    $scope.actualizarProducto = function(index, id) {
    	get('actualizarProducto?index='+index+'&id='+id);
    },
    $scope.actualizarCantidad = function(index, cantidad) {
   		get('actualizarCantidad?index='+index+'&cantidad='+cantidad);
   },
    $scope.agregarlinea = function() {
	   get('agregarLinea');
   },

    $scope.removelinea = function(index) {
       // $scope.venta.lineas.splice(index, 1);
    },
   
   	$scope.confirmarCompra = function() {
   		get('confirmarCompra');
    }, 
   
    $scope.getStates = function(state){
    	return $http.get('http://myservice.com/api/states', {
    		params: {
    				state: state
    		}
    	})
    }
    
    function get(get) {
    	var path = '/venta/';
	    $http.get(path + get).then(function(response) {
	        $scope.venta =response.data;
	    });
    }
});