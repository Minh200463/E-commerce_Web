document.addEventListener('DOMContentLoaded', function() {   
    //Thông báo success
    const successNotification = document.getElementById('success-notification');

    if (successNotification) {
        setTimeout(() => {
            successNotification.classList.add('fade-out');
            setTimeout(() => {
                successNotification.style.display = 'none';
            }, 500); // Thời gian khớp với transition trong CSS
        }, 3000);
    }
});


let host = "http://localhost:8080/admin/category"
        const app = angular.module("app", []);
        app.controller("ctrl", function ($scope, $http){
            $scope.form = {}
            $scope.items = {}


            $scope.load_all = function () {
                $http.get(host).then(resp => {
                    $scope.items = resp.data;
                    console.log(resp.data);
                }).catch(error => {
                    console.log("Error: " + error);
                })
            }

            $scope.edit = function (key){
                var url = `${host}/${key}`;
                $http.get(url).then(resp=> {
                    $scope.form = resp.data;
                    $scope.key = key;
                    console.log("Data: " + resp.data);
                }).catch(error => {
                    console.log("Error: " + error);
                })
            }

            $scope.reset = function () {
                $scope.form = {}
                $scope.key = null
            }


            $scope.delete = function (key) {
                var url = `${host}/${key}`;
                $http.delete(url).then(resp => {
                    $scope.reset();
                    $scope.load_all();
                    $scope.key = null;
                }).catch(error => {
                    console.log("Error: " + error);
                });
            }

            $scope.save = function () {
                var item = angular.copy($scope.form)
                $http.post(host, item).then(resp =>{
                    // item.categoryid = 0;
                    console.log("Success: " + resp);
                    $scope.load_all();
                    $scope.reset();
                }).catch(error => {
                    console.log("Error: " + error);
                })
            }

            $scope.update = function () {
                console.log("ok");
                var item = angular.copy($scope.form)
                var url = `${host}/${$scope.key}`;
                $http.put(url, item).then(resp => {
                    $scope.items[$scope.key] = resp.data;
                    $scope.load_all();
                    console.log("Success", resp);
                }).catch(error => {
                    console.log("Error", Error);
                })
            }

        $scope.load_all();
        })