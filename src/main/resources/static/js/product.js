function updateStatus() {
	var quantity = parseInt(document.getElementById('quantity').value);
	var statusSelect = document.getElementById('status');

	if (isNaN(quantity) || quantity <= 0) {
		statusSelect.value = 0; // Out Of Stock
	} else {
		statusSelect.value = 1; // In Stock
	}
}

document.addEventListener('DOMContentLoaded', function() {
const input = document.getElementById('file-input');
    const image = document.getElementById('img-preview');
    const deleteButton = document.getElementById('delete-button');

    input.addEventListener('change', (e) => {
      if (e.target.files.length) {
        const src = URL.createObjectURL(e.target.files[0]);
        image.src = src;
        deleteButton.classList.add('show');
      }
    });

    deleteButton.addEventListener('click', () => {
      image.src = '';
      input.value = '';
      deleteButton.classList.remove('show');
    }); 
    
    
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