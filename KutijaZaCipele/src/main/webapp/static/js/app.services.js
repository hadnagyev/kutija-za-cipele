var kutijaApp = angular.module('kutijaApp.services', []);

kutijaApp.service('fotkaRestService', [ '$http', function($http) {
	this.getFotke = function(request_params) {
		return $http.get('api/fotke/', {
			params : request_params
		});
	};
	this.getFotka = function(id, request_params) {
		return $http.get('api/fotke/' + id, {
			params : request_params
		});

	};
	this.getSveFotke = function(request_params) {
		return $http.get('api/fotke/sve', {
			params : request_params
		});
	};
	this.saveFotka = function(fotka) {

		return $http.post('api/fotke/data', fotka, {

		}).success(function(data, status) {
			alert("Uspesno poslati podaci fotografije");
		})

		.error(function(data, status) {
			alert("Greska, javiti koji status kod je izbacio " + status);
		});
	};
	
	this.saveFotkaData = function(formData){
		return $http.post('api/fotke',formData,
				{
					transformRequest : function(data,
							headersGetterFunction) {
						return data;
					},
					headers : {
						'Content-Type' : undefined
					}
				}).success(function(data, status) {
			alert("Uspesno poslata fotka");
		}).error(function(data, status) {
			alert("Nesto ne valja, javiti status kod koji je izbacio " + status);
		});
	}
	
	this.deleteFotka = function(id){
		return $http.post('api/fotke/delete/'+id);
		
	}


} ]);
