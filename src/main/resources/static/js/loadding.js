document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("checkoutForm").onsubmit = function() {
        document.getElementById("loading").style.display = "block";
        setTimeout(function() {
            document.getElementById("checkoutForm").submit();
        }, 1500); // 1.5 seconds
        return false; // Prevent the form from submitting normally
    };

    window.onbeforeunload = function() {
        document.getElementById("loading").style.display = "none";
    };
});
