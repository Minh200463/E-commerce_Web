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