let regBtn = document.getElementById("regBtn");
let password = document.getElementById("password");
let username = document.getElementById("username");
let lastname = document.getElementById("lastname");
let firstname = document.getElementById("firstname");




regBtn.addEventListener("click", () => {
    if(!firstname.value || !lastname.value || !username.value || !password.value) {
        alert("Alla fält måste vara ifyllda");
        return;
    }
    const jsonData = {
        firstName: firstname.value,
        lastName: lastname.value,
        userName: username.value,
        password: password.value
    };
    const options =  {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    };

    fetch("http://localhost:8080/api/user", options)
    .then(response => response.json())
    .then(data => {
        alert("Ny användare skapad")
    })
    .catch(error => {
        alert("Användarnamn är upptaget");
    })
});
