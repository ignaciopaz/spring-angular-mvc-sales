angular.module('ventas', ['ngResource', 'ngAnimate'])
	.controller('venta', function($scope, $http, $resource) {
	
		var Venta = $resource('/ventas/:id',
				 { id:'@id'}, {
				  charge: {method:'POST', params:{charge:true}}
				 })
		
		
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
    $scope.confirmarCompraPost = function(venta) {
    	//get('confirmarCompra2?cliente='+JSON.stringify(venta.cliente));
    	//alert(JSON.stringify(venta.cliente) );
    	//$scope.venta.cliente = new Cliente(); //this object now has a $save() method
    	var ventaNew = new Venta();
    	ventaNew.cliente= venta.cliente;
    	ventaNew.lineas= venta.lineas;
    	ventaNew.$save(function(venta, putResponseHeaders){
    		$scope.venta =venta;
        });
    	get('recargarVenta');
    }
    
    function get(get) {
    	var path = '/venta/';
	    $http.get(path + get).then(function(response) {
	        $scope.venta =response.data;
	    });
    }
    function post(get) {
    	var path = '/venta/';
	    $http.post(path + get).then(function(response) {
	        $scope.venta =response.data;
	    });
    }
});