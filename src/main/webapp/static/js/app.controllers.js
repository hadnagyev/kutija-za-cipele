var kutijaApp = angular.module('kutijaApp.controllers', [ 'ngFileUpload' ]);

kutijaApp
		.controller(
				'FotkaController',
				function($http,$scope, $location, $filter, $routeParams,$anchorScroll,
						fotkaRestService) {
					$scope.nextFotka = 11;
					$scope.hasNextFotka = false;
					$scope.todayDate = new Date();
					$scope.previousFotka = -1;


					// Uzima jednu fotku za prikaz na stranici Fotke i proverava
					// da li postoji
					// sledeca u bazi

					$scope.getFotka = function(id) {

						if (id == null) {
							id = 0;
						}

						var request_params = {};
						request_params['currentFotka'] = id;
						if ($routeParams && $routeParams.id){
							request_params['currentFotka'] = id = $routeParams.id;
							
						}
						
						request_params['previousFotka'] = $scope.previousFotka;
						// ako dolazi sa stranice sa svim fotkama
//						if ($routeParams && $routeParams.id) {
//							
//							fotkaRestService.getFotka($routeParams.id).success(
//									function(data) {
//										$scope.fotka = data;
//										$scope.fotka.fotkaFullScreen = 500;
//										
//									})
//							// ako dolazi bez parametara u url linku znaci da je
//							// neko kliknuo na dugme iz bara
//						} else {

							
							
							fotkaRestService
									.getFotke(request_params)
									.success(
											function(data, status, headers) {
												$scope.fotka = data;
												$scope.hasNextFotka = headers('has-next-fotka');
												$scope.hasPreviousFotka = headers('has-previous-fotka');
												$scope.nextFotka = $scope.fotka.id;

												$scope.fotka.fotkaFullScreen = 500;
												
											}).error(function() {
									});
						}
						
					

					$scope.getSveFotke = function() {

						fotkaRestService.getSveFotke().success(function(data) {
							var fotka = $scope.sveFotke = data;
							console.dir(fotka.fotografijaFajl);
						}).error(function() {
							$scope.errorMessage = "Opps, something went wrong";
						});
					}

					$scope.initFotka = function() {
						$scope.fotka = {};
						if ($routeParams && $routeParams.id) {
							fotkaRestService.getFotka($routeParams.id).success(
									function(data) {
										$scope.fotka = data;

									})
						}
						$scope.fotka.datumNastankaFotografije = $scope.todayDate;
					};


					$scope.saveFotka = function() {
						$scope.fotka.datumSlanjaFotografije = new Date();
						$scope.fotka.fotografijaFajl = file.files[0].name;
						fotkaRestService.saveFotka($scope.fotka)
								.success(function() {
//									 $location.path('/fotke');
								}).error(function() {

								});
					};
					

					$scope.uploadFile = function() {
						var formData = new FormData();
						formData.append("file", file.files[0]);
						$scope.fotka.fotografijaFajl = file.files[0].name;
						
						fotkaRestService.saveFotkaData(formData);
					};
					
					$scope.deleteFotka = function(id){
						fotkaRestService.deleteFotka(id).success(function(){
							 $location.path('/fotke');
						}).error({
	
					});
					};
					

				});