// function updateStatus() {
// 	var quantity = parseInt(document.getElementById('quantity').value);
// 	var statusSelect = document.getElementById('status');

// 	if (isNaN(quantity) || quantity <= 0) {
// 		statusSelect.value = false; // Out Of Stock
// 	} else {
// 		statusSelect.value = true; // In Stock
// 	}
// }

document.addEventListener('DOMContentLoaded', function() {
// const input = document.getElementById('file-input');
//     const image = document.getElementById('img-preview');
//     const deleteButton = document.getElementById('delete-button');

//     input.addEventListener('change', (e) => {
//       if (e.target.files.length) {  
// // URL.createObjectURL là một phương thức tạo ra một URL tạm thời trỏ đến tệp đã chọn.
// // e.target.files[0] lấy tệp đầu tiên (và thường là duy nhất) trong danh sách các tệp đã chọn.
//         const src = URL.createObjectURL(e.target.files[0]);
//         image.src = src;
//         deleteButton.classList.add('show');
//       }
//     });

//     deleteButton.addEventListener('click', () => {
//       image.src = '';
//       input.value = '';
//       deleteButton.classList.remove('show');
//     }); 
    
    
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
  const input = document.getElementById('file-input');
  const image = document.getElementById('img-preview');
  const deleteButton = document.getElementById('delete-button');
  const scope = angular.element(input).scope();
  
  input.addEventListener('change', (e) => {
    if (e.target.files.length) {  
      const src = URL.createObjectURL(e.target.files[0]);
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
    input.value = '';
    deleteButton.classList.remove('show');
  });
  


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
  $scope.save = function(){
    var item = angular.copy($scope.form)
    console.log($scope.form);
    $http.post(host, item, {
        transformRequest : angular.identity,
        headers : {'Content-Type': undefined}
    }).then(resp => {
      console.log(resp.data);
        $scope.getPage($scope.currentPage);
        $scope.reset();
    }).catch(err => {
      if(err.status === 400 && err.data){
        $scope.errors = err.data
      }else{
        console.log("Error"+err);
      }
    })
  }
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
    $scope.item = { image: ""}
    console.log("Image"+$scope.item.image);
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