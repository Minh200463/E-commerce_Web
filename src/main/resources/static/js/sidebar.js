// // Lấy danh sách các mục nav-link
// const navLinks = document.querySelectorAll('.nav-link');

// // Lặp qua từng mục và thêm sự kiện click
// navLinks.forEach(link => {
//     link.addEventListener('click', function() {
//         // Xóa lớp active khỏi tất cả các mục nav-link
//         navLinks.forEach(navLink => {
//             navLink.parentElement.classList.remove('active');
//         });

//         // Thêm lớp active vào mục được click
//         this.parentElement.classList.add('active');
//     });
// });

// sidebar.js
// sidebar.js
document.addEventListener('DOMContentLoaded', (event) => {
    const navLinks = document.querySelectorAll('.nav-link');
    const activeLink = localStorage.getItem('activeLink');

    // Set the active class based on local storage
    if (activeLink) {
        const link = document.querySelector(`.nav-link[href='${activeLink}']`);
        if (link) {
            link.classList.add('active');
        }
    }

    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            // Remove the 'active' class from all links
            navLinks.forEach(link => link.classList.remove('active'));

            // Add the 'active' class to the clicked link
            this.classList.add('active');

            // Store the active link in local storage
            localStorage.setItem('activeLink', this.getAttribute('href'));
        });
    });
});

