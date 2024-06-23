$(document).ready(function(){
    // Khi click vào một mục danh mục
    $('.dropdown-item').click(function(e){
        e.preventDefault();
        // Ẩn tất cả các dropdown menu
        $('.dropdown-menu').removeClass('show-dropdown-menu');
        // Hiển thị dropdown menu của mục vừa click
        $(this).siblings('.dropdown-menu').addClass('show-dropdown-menu');
    });

    // Khi click bất kỳ nơi nào khác trên trang
    $(document).click(function(e) {
        // Nếu click không phải vào dropdown menu hoặc mục danh mục
        if (!$(e.target).closest('.dropdown-item').length) {
            // Ẩn tất cả các dropdown menu
            $('.dropdown-menu').removeClass('show-dropdown-menu');
        }
    });
});
