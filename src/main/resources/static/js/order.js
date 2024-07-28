document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('close-order-info').addEventListener('click', function() {
        document.querySelector('.col-4').style.display = 'none';
    });
});

let host = "http://localhost:8080/admin/orders"
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http){
    $scope.form = {}
    $scope.items = {}
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.totalPages = 0;

    $scope.getPage = function(page){
        $http.get(
          host, {
            params: {
              page: page,
              size: $scope.pageSize
            }
          }
        ).then(resp =>{
          $scope.items = resp.data.content;
          $scope.totalPages = resp.data.totalPages;
          $scope.currentPage = resp.data.number;
          console.log(resp.data.content);
        })
      }
    
      $scope.setPage = function (page){
        if(page >= 0 && page < $scope.totalPages){
          $scope.getPage(page);
        }
      }
    
      $scope.isActive = function(page){
        return $scope.currentPage == page;
      }



//EDIT
      $scope.item = {}
      $scope.edit = function(key){
        $http.get(`${host}/details/${key}`).then(resp =>{
            $scope.item = resp.data;
            $scope.key = key
            console.log("Item:", resp.data);
        }).catch(err => {
            console.log("Error:", err);  
        })
      }


$scope.getPage($scope.currentPage)
})