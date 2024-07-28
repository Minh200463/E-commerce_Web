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

let host = "http://localhost:8080/admin/products"
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http){
  const fileInput = document.getElementById('file-input');
  const image = document.getElementById('img-preview');
  const deleteButton = document.getElementById('delete-button');
  const scope = angular.element(fileInput).scope();
  
  fileInput.addEventListener('change', (e) => {
    if (e.target.files.length) {  
      var src = URL.createObjectURL(e.target.files[0]);
      scope.$apply(function() {
        scope.form.image = e.target.files[0].name;  // cập nhật biến model trong AngularJS
      });
      image.src = src;
      console.log(image.src);
      deleteButton.classList.add('show');
    }
  });
  
  deleteButton.addEventListener('click', () => {
    scope.$apply(function() {
      scope.form.image = null;  // cập nhật biến model trong AngularJS
    });
    image.src = '';
    fileInput.value = '';
    deleteButton.classList.remove('show');
  });
  

//Change Status
  $scope.updateStatus = function() {
    var quantity = $scope.form.quantity;

    if (isNaN(quantity) || quantity <= 0) {
        $scope.form.status = false; // Out Of Stock
    } else {
        $scope.form.status = true; // In Stock
    }
};


  
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


//SAVE
$scope.save = function () {
  var formData = new FormData();
  formData.append('products', new Blob([JSON.stringify($scope.form)], { type: 'application/json' }));
  // formData.append('imageFile', fileInput.files[0]);
  var nput = document.getElementById('file-input');
    if (nput== null || nput.files.length <=0) {
      formData.append('imageFile', null);
    }else{
      formData.append('imageFile', nput.files[0]);
    }

console.log("Form:", $scope.form);
  $http.post(host, formData, {
      headers: { 'Content-Type': undefined },
      transformRequest: angular.identity
  }).then(resp => {
      console.log("Success: " + resp.data);
      $scope.getPage($scope.currentPage);
      $scope.reset();
      image.src = "";
  }).catch(err => {
      if (err.status === 400 && err.data) {
          $scope.errors = err.data;
      } else {
          console.log("Error: " + err);
      }
  });
};





//EDIT
  $scope.item = {}
  $scope.edit = function(key){
    $http.get(`${host}/${key}`).then(resp =>{
      $scope.reset();
      $scope.form = resp.data;
      $scope.item = resp.data;
      $scope.key = key;
      console.log(resp.data);
    }).catch(err =>{
      console.log("Error"+err);
    })
  }

//RESET
  $scope.reset = function(){
    $scope.form = {}
    $scope.key = null
    $scope.errors = []
    $scope.item = { image: ''}
    fileInput.value = ''
  }

//DELETE
  $scope.delete = function(key){
    $http.delete(`${host}/${key}`).then(resp => {
      $scope.getPage($scope.currentPage);
      $scope.key = null;
    }).catch(err => {
      console.log("Error"+ err);
    })
  }

//LIST CATEGORY
  $scope.categories = []
  $scope.categoryList = function(){
    const url = "http://localhost:8080/admin/category";
    $http.get(url).then(resp =>{
      $scope.categories = resp.data;
      console.log("Category: " + $scope.categories);
    }).catch(error => {
      console.log("Error: "+error);
    })
  }


  
  $scope.categoryList();
  $scope.getPage($scope.currentPage);
})