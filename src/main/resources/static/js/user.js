document.addEventListener('DOMContentLoaded', function() {
document.getElementById('successButton').addEventListener('click', function() {
    const message = document.getElementById('successMessage');
    message.classList.add('show');
    
    setTimeout(function() {
        message.classList.remove('show');
    }, 3500); // 2000 milliseconds = 2 seconds
});


//Thông báo
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


let host = "http://localhost:8080/admin/users"
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http){
    $scope.form = {};
    $scope.items = {};
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.totalPages = 0;

    $scope.loadPage = function (page) {
        $http.get(host, {
            params: {
                page: page,
                size: $scope.pageSize
            }
        }).then(resp => {
            $scope.items = resp.data.content;
            $scope.totalPages = resp.data.totalPages;
            $scope.currentPage = resp.data.number;
            console.log(resp.data);
        }).catch(error => {
            console.log("Error: " + error);
        });
    };

    $scope.setPage = function (page) {
        if (page >= 0 && page < $scope.totalPages) {
            $scope.loadPage(page);
        }
    };

    $scope.isActive = function (page) {
        return $scope.currentPage === page;
    };

//EDIT
    $scope.edit = function (key){
        $http.get(`${host}/${key}`).then(resp => {
            $scope.form = resp.data;
            $scope.key = key;
            console.log(resp.data);
        }).catch(err => {
            console.log("Error: " + err);
        })
    }

// SAVE
    $scope.save = function () {
        var item = angular.copy($scope.form);
        $http.post(host, item).then(resp => {
            console.log("Success: " + resp.data);
            $scope.loadPage($scope.currentPage);
            $scope.reset();
        }).catch(err => {
            if(err.status === 400 && err.data){
                $scope.errors = err.data
            }else{
                console.log("Error: " + err);
            }
        })
    }

// DELETE
    $scope.delete = function (key){
        var url = `${host}/${key}`;
        $http.delete(url).then(resp => {
            $scope.loadPage($scope.currentPage);
            $scope.key = null
        }).catch(err => {
            console.log("Error"+err);
        })
    }

// RESET
    $scope.reset = function () {
        $scope.form = {}
        $scope.key = null;
        $scope.errors = [];
    }


    // Load the first page when the controller is initialized
    $scope.loadPage($scope.currentPage);
})