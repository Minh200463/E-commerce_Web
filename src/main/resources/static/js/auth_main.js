let host = "http://localhost:8080/ok/index"
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http){
    $scope.items = {}
    $scope.currentPage = 0;
    $scope.pageSize = 8;
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



$scope.getPage($scope.currentPage)
})