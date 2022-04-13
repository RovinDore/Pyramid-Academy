function showtext() {
    var brand = document.getElementById('brand').value;
    var color = document.getElementById('color').value;
    var type = document.getElementById('vehicleType').value;
    var model = document.getElementById('model').value;

    document.getElementById("show").innerHTML = "Brand: " + brand + "<br>" + "Color: " + color + "<br>" + "Type: " + type + "<br>" + "Model: " + model;
}

document.getElementById("#RegistrationForm").addEventListener("submit", function(event){
    showtext();
    event.preventDefault();
});