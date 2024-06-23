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