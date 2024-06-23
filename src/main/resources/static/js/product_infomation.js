document.addEventListener("DOMContentLoaded", function() {
    var quantityInput = document.getElementById('quantity');
    var minusButton = document.querySelector('.quantity-left-minus');
    var plusButton = document.querySelector('.quantity-right-plus');

    minusButton.addEventListener("click", function() {
        var quantity = parseInt(quantityInput.value);
        if(quantity > 1) {
            quantityInput.value = quantity - 1;
        }
    });

    plusButton.addEventListener("click", function() {
        var quantity = parseInt(quantityInput.value);
        quantityInput.value = quantity + 1;
    });
});
