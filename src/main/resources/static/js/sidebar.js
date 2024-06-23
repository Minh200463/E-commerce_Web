// Lấy danh sách các mục nav-link
const navLinks = document.querySelectorAll('.nav-link');

// Lặp qua từng mục và thêm sự kiện click
navLinks.forEach(link => {
    link.addEventListener('click', function() {
        // Xóa lớp active khỏi tất cả các mục nav-link
        navLinks.forEach(navLink => {
            navLink.parentElement.classList.remove('active');
        });

        // Thêm lớp active vào mục được click
        this.parentElement.classList.add('active');
    });
});